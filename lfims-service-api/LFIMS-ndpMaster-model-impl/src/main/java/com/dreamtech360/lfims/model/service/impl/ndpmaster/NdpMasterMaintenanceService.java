package com.dreamtech360.lfims.model.service.impl.ndpmaster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.jcr.Node;
import javax.jcr.PropertyType;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.query.qom.Column;
import javax.jcr.query.qom.Constraint;
import javax.jcr.query.qom.Ordering;
import javax.jcr.query.qom.QueryObjectModelConstants;
import javax.jcr.query.qom.QueryObjectModelFactory;
import javax.jcr.query.qom.Selector;
import javax.transaction.xa.XAResource;

import com.dreamtech360.lfims.model.api.ndpmaster.NdpMaster;
import com.dreamtech360.lfims.model.api.impl.ndpmaster.NdpMasterImpl;
import com.dreamtech360.lfims.model.api.impl.ndpmaster.MutableNdpMasterImpl;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSNode;
import com.dreamtech360.lfims.model.base.LFIMSNodeKey;
import com.dreamtech360.lfims.model.base.LFIMSNodeStructure;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.impl.ndpmaster.NdpMasterAttributeMapper;
import com.dreamtech360.lfims.model.search.query.LFIMSDecimalValue;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryConstraint;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryParameters;
import com.dreamtech360.lfims.model.search.query.LFIMSStringValue;
import com.dreamtech360.lfims.model.service.base.LFIMSJCRSessionThreadLocal;
import com.dreamtech360.lfims.model.service.base.LFIMSModelJCRService;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class NdpMasterMaintenanceService extends LFIMSModelJCRService<NdpMaster>
{
	private static LFIMSNodeStructure<NdpMaster> nodeStructure=null;
	private LFIMSTransactionManagementService txnService;
	public NdpMasterMaintenanceService(Repository repository){
		this.repository=repository;
	}
	public NdpMasterMaintenanceService(Repository repository,LFIMSTransactionManagementService transactionManagerService){
		this.repository=repository;
		this.txnService=transactionManagerService;
	}

	@Override
	protected void update(Node node, LFIMSObject<NdpMaster> record) throws LFIMSServiceException {

		NdpMaster ndpMasterRecord=(NdpMaster)record;
		try{
			populateValue(node,ndpMasterRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}

	@Override
	protected Node store(Node rootNode, LFIMSObject<NdpMaster> record) throws LFIMSServiceException   {

		NdpMaster ndpMasterRecord=(NdpMaster)record;
		Node ndpMasterNode=null;
		try{
			ndpMasterNode = rootNode.addNode("lfims:ndpDetails");
			populateValue(ndpMasterNode,ndpMasterRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return ndpMasterNode;
	} 

	private void populateValue(Node node, NdpMaster record) throws LFIMSServiceException{
		try{
		//	  fields: ['id','advName', 'advAddress1','advAddress2','cityPin','contactPhone','email'],
			node.setProperty("lfims:id", record.getId());
			node.setProperty("lfims:purpose", record.getPurpose());
			node.setProperty("lfims:description", record.getDescription());
						
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}

	}

	@Override
	protected LFIMSObject<NdpMaster> convertNodeToObject(Node node, boolean readOnly) throws LFIMSServiceException {
		LFIMSObject<NdpMaster> ndpMaster=null;
	
			try {
				if(readOnly==true){
					ndpMaster=new NdpMasterImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:purpose").getString(),
							node.getProperty("lfims:description").getString());
						}
				else{
					ndpMaster=new MutableNdpMasterImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:purpose").getString(),
							node.getProperty("lfims:description").getString());
				}
				
			} catch (Exception e) {
				throw new LFIMSServiceException(e);
			}
		
		return ndpMaster;
	}

	@Override
	protected LFIMSNodeStructure<NdpMaster> getRootNodeStructureDetails() {
		return nodeStructure;
	}


	@Override
	protected LFIMSQueryParameters<NdpMaster> getSearchQueryParameters(final Map<LFIMSAttributeMapper<NdpMaster>, List<String>> searchCriteria) throws LFIMSServiceException {

		final Selector selector;
		final LFIMSQueryConstraint<NdpMaster> queryConstraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<NdpMaster> childNode=getRootNodeStructureDetails().getNodeType();
		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			queryConstraint=new LFIMSQueryConstraint<NdpMaster>(){
				public List<Constraint> getConstraints() throws LFIMSServiceException {
					List<Constraint> constraints=new ArrayList<Constraint>();
					try {
						Constraint constraint=queryOMF.and(
								
										queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), NdpMasterAttributeMapper.ID.getFieldName()), searchCriteria.get(NdpMasterAttributeMapper.ID)==null?QueryObjectModelConstants.JCR_OPERATOR_GREATER_THAN_OR_EQUAL_TO:QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(searchCriteria.get(NdpMasterAttributeMapper.ID)==null?0:Integer.valueOf(searchCriteria.get(NdpMasterAttributeMapper.ID).get(0))))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), NdpMasterAttributeMapper.PURPOSE.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(NdpMasterAttributeMapper.PURPOSE)==null?"%":searchCriteria.get(NdpMasterAttributeMapper.PURPOSE).get(0))))
												),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), NdpMasterAttributeMapper.DESCRIPTION.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(NdpMasterAttributeMapper.DESCRIPTION)==null?"%":searchCriteria.get(NdpMasterAttributeMapper.DESCRIPTION).get(0)))));
									

						constraints.add(constraint);
					} catch (Exception e) {
						throw new LFIMSServiceException(e);
					} 
					return constraints;
				}

			};
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return getParameters(selector, queryConstraint,ordering,columns);
	}

	@Override
	protected LFIMSQueryParameters<NdpMaster> getDeleteQueryParameters(final List<Integer> searchCriteria) throws LFIMSServiceException {
		final Selector selector;
		final LFIMSQueryConstraint<NdpMaster> constraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<NdpMaster> childNode=getRootNodeStructureDetails().getNodeType();

		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			constraint=new LFIMSQueryConstraint<NdpMaster>(){

				public List<Constraint> getConstraints() throws LFIMSServiceException {

					List<Constraint> constraints=new ArrayList<Constraint>();
					try {

						Iterator<Integer> valueIterator=searchCriteria.iterator();
						while(valueIterator.hasNext()){

							int value=valueIterator.next();
							constraints.add(queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), NdpMasterAttributeMapper.ID.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(value))));
						}
					} catch (Exception e) {
						throw new LFIMSServiceException(e);
					} 
					return constraints;
				}
			};
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return getParameters(selector, constraint,ordering,columns);
	}

	private LFIMSQueryParameters<NdpMaster> getParameters(final Selector selector,final LFIMSQueryConstraint<NdpMaster> constraint, final Ordering[] ordering, final Column[] columns){

		return new LFIMSQueryParameters<NdpMaster>(){
			public Selector getSelector() {
				return selector;
			}
			public LFIMSQueryConstraint<NdpMaster> getConstraint() {
				return constraint;
			}
			public Column[] getColumns() {
				return columns;
			}
			public Ordering[] getOrdering() {
				return ordering;
			}
		};
	}

	@Override
	protected LFIMSObject<NdpMaster>  createNewRecord()	throws LFIMSServiceException {
		LFIMSObject<NdpMaster> ndpMasterRecord=new MutableNdpMasterImpl();
		return ndpMasterRecord;
	}

	

	static{
		
		nodeStructure=new LFIMSNodeStructure<NdpMaster>(){

			public LFIMSNode<NdpMaster> getNodeType() {
				// TODO Auto-generated method stub
				return new LFIMSNode<NdpMaster>(){
					public String getNodeName() {
						return "NdpDetails";
					}
					public String getNodeType() {
						return "lfims:NdpDetails";
					}
					public LFIMSNodeKey<NdpMaster> getNodeKey() {
						return new LFIMSNodeKey<NdpMaster>(){
							public String getKeyName() {
								return "lfims:id";
							}
							public int getKeyType() {
								return PropertyType.DECIMAL;
							}
							public LFIMSAttributeMapper<NdpMaster> getSearchCriteria() {
								return NdpMasterAttributeMapper.ID;
							}
						};
					}
				};
			}

			public LFIMSNodeStructure<NdpMaster> getTopNodeStructure() {
				// TODO Auto-generated method stub
				return new LFIMSNodeStructure<NdpMaster>(){

					public LFIMSNode<NdpMaster> getNodeType() {
						// TODO Auto-generated method stub
						return new LFIMSNode<NdpMaster>(){
							public String getNodeName() {
								return "NdpDetailsList";
							}
							public String getNodeType() {
								return "lfims:NdpDetailsList";
							}
							public LFIMSNodeKey<NdpMaster> getNodeKey() {
								return new LFIMSNodeKey<NdpMaster>(){
									public String getKeyName() {
										return "lfims:maxid";
									}
									public int getKeyType() {
										return PropertyType.DECIMAL;
									}
									public LFIMSAttributeMapper<NdpMaster> getSearchCriteria() {
										return NdpMasterAttributeMapper.ID;
									}
								};
							}
						};
					}

					public LFIMSNodeStructure<NdpMaster> getTopNodeStructure() {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public boolean isNodeTypeCollection() {
						// TODO Auto-generated method stub
						return true;
					}
					
				};
			}

			@Override
			public boolean isNodeTypeCollection() {
				// TODO Auto-generated method stub
				return false;
			}
			
			
		};
		
	}

	
	@Override
	protected void putSessionInTxn(Session session) throws LFIMSServiceException {
		if(txnService!=null && txnService.inTransaction()){
			XAResource xaResource=(XAResource)session;
			txnService.registerXAResource(xaResource);
			//If the call is in a transaction then the closure of the session will be done by the synchronizer registered with the transaction below
			txnService.registerSynchronization();
		}
	
	
	}
	
	@Override
	protected boolean notInTransaction() throws LFIMSServiceException {
		// TODO Auto-generated method stub
		if(txnService.inTransaction())
			return false;
		return true;
	}
	
	
}
