package com.dreamtech360.lfims.model.service.impl.casemgmtmaintenance;

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

import com.dreamtech360.lfims.model.api.casemgmtmaintenance.CaseMgmtMaintenance;
import com.dreamtech360.lfims.model.api.impl.casemgmtmaintenance.CaseMgmtMaintenanceImpl;
import com.dreamtech360.lfims.model.api.impl.casemgmtmaintenance.MutableCaseMgmtMaintenanceImpl;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSNode;
import com.dreamtech360.lfims.model.base.LFIMSNodeKey;
import com.dreamtech360.lfims.model.base.LFIMSNodeStructure;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.impl.casemgmtmaintenance.CaseMgmtMaintenanceAttributeMapper;
import com.dreamtech360.lfims.model.search.query.LFIMSDecimalValue;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryConstraint;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryParameters;
import com.dreamtech360.lfims.model.search.query.LFIMSStringValue;
import com.dreamtech360.lfims.model.service.base.LFIMSJCRSessionThreadLocal;
import com.dreamtech360.lfims.model.service.base.LFIMSModelJCRService;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class CaseMgmtMaintenanceService extends LFIMSModelJCRService<CaseMgmtMaintenance>
{
	private static LFIMSNodeStructure<CaseMgmtMaintenance> nodeStructure=null;
	private LFIMSTransactionManagementService txnService;
	public CaseMgmtMaintenanceService(Repository repository){
		this.repository=repository;
	}

	public CaseMgmtMaintenanceService(Repository repository,LFIMSTransactionManagementService transactionManagerService){
		this.repository=repository;
		this.txnService=transactionManagerService;
	}
	@Override
	protected void update(Node node, LFIMSObject<CaseMgmtMaintenance> record) throws LFIMSServiceException {

		CaseMgmtMaintenance caseMgmtMasterRecord=(CaseMgmtMaintenance)record;
		try{
			populateValue(node,caseMgmtMasterRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}

	@Override
	protected Node store(Node rootNode, LFIMSObject<CaseMgmtMaintenance> record) throws LFIMSServiceException   {

		CaseMgmtMaintenance caseMgmtMasterRecord=(CaseMgmtMaintenance)record;
		Node caseMgmtMasterNode=null;
		try{
			caseMgmtMasterNode= rootNode.addNode("lfims:caseMgmtMaintenanceDetails");
			populateValue(caseMgmtMasterNode,caseMgmtMasterRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return caseMgmtMasterNode;
	} 

	private void populateValue(Node node, CaseMgmtMaintenance record) throws LFIMSServiceException{
		try{
		//	  fields: ['id','advName', 'advAddress1','advAddress2','cityPin','contactPhone','email'],
			node.setProperty("lfims:id", record.getId());
			node.setProperty("lfims:code", record.getCode());
			node.setProperty("lfims:name", record.getName());
			node.setProperty("lfims:description", record.getDescription());
						
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}

	}

	@Override
	protected LFIMSObject<CaseMgmtMaintenance> convertNodeToObject(Node node, boolean readOnly) throws LFIMSServiceException {
		LFIMSObject<CaseMgmtMaintenance> caseMgmtMasterRecord=null;
	
			try {
				if(readOnly==true){
					caseMgmtMasterRecord=new CaseMgmtMaintenanceImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:code").getString(),
							node.getProperty("lfims:name").getString(),
							node.getProperty("lfims:description").getString());
						}
				else{
					caseMgmtMasterRecord=new MutableCaseMgmtMaintenanceImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:code").getString(),
							node.getProperty("lfims:name").getString(),
							node.getProperty("lfims:description").getString());
				}
				
			} catch (Exception e) {
				throw new LFIMSServiceException(e);
			}
		
		return caseMgmtMasterRecord;
	}
	
	@Override
	protected LFIMSNodeStructure<CaseMgmtMaintenance> getRootNodeStructureDetails() {
		return nodeStructure;
	}


	@Override
	protected LFIMSQueryParameters<CaseMgmtMaintenance> getSearchQueryParameters(final Map<LFIMSAttributeMapper<CaseMgmtMaintenance>, List<String>> searchCriteria) throws LFIMSServiceException {

		final Selector selector;
		final LFIMSQueryConstraint<CaseMgmtMaintenance> queryConstraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<CaseMgmtMaintenance> childNode=getRootNodeStructureDetails().getNodeType();
		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			queryConstraint=new LFIMSQueryConstraint<CaseMgmtMaintenance>(){
				public List<Constraint> getConstraints() throws LFIMSServiceException {
					List<Constraint> constraints=new ArrayList<Constraint>();
					try {
						Constraint constraint=queryOMF.and(
								
										queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CaseMgmtMaintenanceAttributeMapper.ID.getFieldName()), searchCriteria.get(CaseMgmtMaintenanceAttributeMapper.ID)==null?QueryObjectModelConstants.JCR_OPERATOR_GREATER_THAN_OR_EQUAL_TO:QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(searchCriteria.get(CaseMgmtMaintenanceAttributeMapper.ID)==null?0:Integer.valueOf(searchCriteria.get(CaseMgmtMaintenanceAttributeMapper.ID).get(0))))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CaseMgmtMaintenanceAttributeMapper.CODE.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(CaseMgmtMaintenanceAttributeMapper.CODE)==null?"%":searchCriteria.get(CaseMgmtMaintenanceAttributeMapper.CODE).get(0))))
												),
												queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CaseMgmtMaintenanceAttributeMapper.DESCRIPTION.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(CaseMgmtMaintenanceAttributeMapper.DESCRIPTION)==null?"%":searchCriteria.get(CaseMgmtMaintenanceAttributeMapper.DESCRIPTION).get(0)))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CaseMgmtMaintenanceAttributeMapper.NAME.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(CaseMgmtMaintenanceAttributeMapper.NAME)==null?"%":searchCriteria.get(CaseMgmtMaintenanceAttributeMapper.NAME).get(0))))
												));
									

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
	protected LFIMSQueryParameters<CaseMgmtMaintenance> getDeleteQueryParameters(final List<Integer> searchCriteria) throws LFIMSServiceException {
		final Selector selector;
		final LFIMSQueryConstraint<CaseMgmtMaintenance> constraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<CaseMgmtMaintenance> childNode=getRootNodeStructureDetails().getNodeType();

		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			constraint=new LFIMSQueryConstraint<CaseMgmtMaintenance>(){

				public List<Constraint> getConstraints() throws LFIMSServiceException {

					List<Constraint> constraints=new ArrayList<Constraint>();
					try {

						Iterator<Integer> valueIterator=searchCriteria.iterator();
						while(valueIterator.hasNext()){

							int value=valueIterator.next();
							constraints.add(queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CaseMgmtMaintenanceAttributeMapper.ID.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(value))));
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

	private LFIMSQueryParameters<CaseMgmtMaintenance> getParameters(final Selector selector,final LFIMSQueryConstraint<CaseMgmtMaintenance> constraint, final Ordering[] ordering, final Column[] columns){

		return new LFIMSQueryParameters<CaseMgmtMaintenance>(){
			public Selector getSelector() {
				return selector;
			}
			public LFIMSQueryConstraint<CaseMgmtMaintenance> getConstraint() {
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
	protected LFIMSObject<CaseMgmtMaintenance>  createNewRecord()	throws LFIMSServiceException {
		LFIMSObject<CaseMgmtMaintenance> caseMgmtMasterRecord=new MutableCaseMgmtMaintenanceImpl();
		return caseMgmtMasterRecord;
	}


	static{
		
		
		nodeStructure=new LFIMSNodeStructure<CaseMgmtMaintenance>(){

			public LFIMSNode<CaseMgmtMaintenance> getNodeType() {
				// TODO Auto-generated method stub
				return new LFIMSNode<CaseMgmtMaintenance>(){
					public String getNodeName() {
						return "CaseMgmtMaintenanceDetails";
					}
					public String getNodeType() {
						return "lfims:CaseMgmtMaintenanceDetails";
					}
					public LFIMSNodeKey<CaseMgmtMaintenance> getNodeKey() {
						return new LFIMSNodeKey<CaseMgmtMaintenance>(){
							public String getKeyName() {
								return "lfims:id";
							}
							public int getKeyType() {
								return PropertyType.DECIMAL;
							}
							public LFIMSAttributeMapper<CaseMgmtMaintenance> getSearchCriteria() {
								return CaseMgmtMaintenanceAttributeMapper.ID;
							}
						};
					}
				};
			}

			public LFIMSNodeStructure<CaseMgmtMaintenance> getTopNodeStructure() {
				// TODO Auto-generated method stub
				return new LFIMSNodeStructure<CaseMgmtMaintenance>(){

					public LFIMSNode<CaseMgmtMaintenance> getNodeType() {
						// TODO Auto-generated method stub
						return new LFIMSNode<CaseMgmtMaintenance>(){
							public String getNodeName() {
								return "CaseMgmtMaintenanceDetailsList";
							}
							public String getNodeType() {
								return "lfims:CaseMgmtMaintenanceDetailsList";
							}
							public LFIMSNodeKey<CaseMgmtMaintenance> getNodeKey() {
								return new LFIMSNodeKey<CaseMgmtMaintenance>(){
									public String getKeyName() {
										return "lfims:maxid";
									}
									public int getKeyType() {
										return PropertyType.DECIMAL;
									}
									public LFIMSAttributeMapper<CaseMgmtMaintenance> getSearchCriteria() {
										return CaseMgmtMaintenanceAttributeMapper.ID;
									}
								};
							}
						};
					}

					public LFIMSNodeStructure<CaseMgmtMaintenance> getTopNodeStructure() {
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
