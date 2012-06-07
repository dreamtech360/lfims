package com.dreamtech360.lfims.model.service.base;

import java.util.Stack;

import javax.jcr.AccessDeniedException;
import javax.jcr.InvalidItemStateException;
import javax.jcr.ItemExistsException;
import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.ReferentialIntegrityException;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.version.VersionException;
import javax.transaction.RollbackException;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import javax.transaction.xa.XAResource;



import com.dreamtech360.lfims.model.base.LFIMSCompositObject;
import com.dreamtech360.lfims.model.base.LFIMSNode;
import com.dreamtech360.lfims.model.base.LFIMSNodeStructure;


public abstract class LFIMSJCRService<T> {


	protected Repository repository=null;
	protected TransactionManager transactionManager=null;
	
	
	//This is a important method that all subclass should implement as per LFIMS architecture
	//each and every object node is stored inside a List object node and hence the root
	//for every node is a node that is of List type
	protected abstract LFIMSNodeStructure<T> getRootNodeStructureDetails();
	//public abstract Node getJCRNode(LFIMSNode<T> object) throws LFIMSServiceException;

	 
	protected final Node initRootNode() throws RepositoryException {
		
		Node rootNode=null;
		Session session= LFIMSJCRSessionThreadLocal.get().getSession();
		Node root = session.getRootNode();
		Stack<LFIMSNode<T>> nodeStack=new Stack<LFIMSNode<T>>();
		LFIMSNodeStructure<T> nodeStructure=getRootNodeStructureDetails();
			
		//boolean isCompositModel = nodeStructure.isCompositModel();
			
			LFIMSNodeStructure<T> rootNodeStructure=nodeStructure.getTopNodeStructure();
					
			while(rootNodeStructure!=null){
				nodeStack.push(rootNodeStructure.getNodeType());
				rootNodeStructure=rootNodeStructure.getTopNodeStructure();
			}
			while(!nodeStack.isEmpty()){
				LFIMSNode<T> node=nodeStack.pop();
				if (!root.hasNode(node.getNodeName())) {
					rootNode = root.addNode(node.getNodeName(),node.getNodeType());
					if(node.hasNodeKey())
						rootNode.setProperty(node.getNodeKey().getKeyName(), 0);
				}
				rootNode = root.getNode(node.getNodeName());
			}
			session.save();
			return rootNode;
		
	}

	
protected final Node initRootNode(LFIMSCompositObject<T> node) throws RepositoryException {
		
		Node rootNode=null;
		Session session= LFIMSJCRSessionThreadLocal.get().getSession();
		rootNode = session.getNodeByIdentifier(node.getUuid());
		
			return rootNode;
		
	}


	protected final void initSession() throws LoginException, RepositoryException, IllegalStateException, RollbackException, SystemException{

		Session session=null;
	
		if(!LFIMSJCRSessionThreadLocal.exists()){
			
			session = repository.login(new SimpleCredentials("admin","admin".toCharArray()));
	
			
			if(transactionManager!=null && transactionManager.getTransaction()!=null){
				XAResource xaResource=(XAResource)session;
				transactionManager.getTransaction().enlistResource(xaResource);
				//If the call is in a transaction then the closure of the session will be done by the synchronizer registered with the transaction below
				transactionManager.getTransaction().registerSynchronization(new LFIMSTransactionListner());
			}
			
			LFIMSJCRSession jcrSession=new LFIMSJCRSession(session);
			LFIMSJCRSessionThreadLocal.set(jcrSession);
			
		}
	}

	protected final void closeSession()  {
		
		LFIMSJCRSessionThreadLocal.get().getSession().logout();
		LFIMSJCRSessionThreadLocal.unset();
	}

	protected final void saveSessionData() throws AccessDeniedException, ItemExistsException, ReferentialIntegrityException, ConstraintViolationException, InvalidItemStateException, VersionException, LockException, NoSuchNodeTypeException, RepositoryException{
		LFIMSJCRSessionThreadLocal.get().getSession().save();
	}

}
