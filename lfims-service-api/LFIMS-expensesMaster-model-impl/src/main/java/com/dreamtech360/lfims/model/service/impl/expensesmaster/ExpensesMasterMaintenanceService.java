package com.dreamtech360.lfims.model.service.impl.expensesmaster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.jcr.Node;
import javax.jcr.PropertyType;
import javax.jcr.Repository;
import javax.jcr.query.qom.Column;
import javax.jcr.query.qom.Constraint;
import javax.jcr.query.qom.Ordering;
import javax.jcr.query.qom.QueryObjectModelConstants;
import javax.jcr.query.qom.QueryObjectModelFactory;
import javax.jcr.query.qom.Selector;

import com.dreamtech360.lfims.model.api.expensesmaster.ExpensesMaster;
import com.dreamtech360.lfims.model.api.impl.expensesmaster.ExpensesMasterImpl;
import com.dreamtech360.lfims.model.api.impl.expensesmaster.MutableExpensesMasterImpl;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSNode;
import com.dreamtech360.lfims.model.base.LFIMSNodeKey;
import com.dreamtech360.lfims.model.base.LFIMSNodeStructure;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.impl.expensesmaster.ExpensesMasterAttributeMapper;
import com.dreamtech360.lfims.model.search.query.LFIMSDecimalValue;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryConstraint;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryParameters;
import com.dreamtech360.lfims.model.search.query.LFIMSStringValue;
import com.dreamtech360.lfims.model.service.base.LFIMSJCRSessionThreadLocal;
import com.dreamtech360.lfims.model.service.base.LFIMSModelJCRService;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class ExpensesMasterMaintenanceService extends LFIMSModelJCRService<ExpensesMaster>
{
	private static LFIMSNodeStructure<ExpensesMaster> nodeStructure=null;
	public ExpensesMasterMaintenanceService(Repository repository){
		this.repository=repository;
	}
	public ExpensesMasterMaintenanceService(Repository repository,LFIMSTransactionManagementService transactionManagerService){
		this.repository=repository;
		this.transactionManager=transactionManagerService.getTransactionManager();
	}

	@Override
	protected void update(Node node, LFIMSObject<ExpensesMaster> record) throws LFIMSServiceException {

		ExpensesMaster expensesMasterRecord=(ExpensesMaster)record;
		try{
			populateValue(node,expensesMasterRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}

	@Override
	protected Node store(Node rootNode, LFIMSObject<ExpensesMaster> record) throws LFIMSServiceException   {

		ExpensesMaster expensesMasterRecord=(ExpensesMaster)record;
		Node expensesMasterNode=null;
		try{
			expensesMasterNode = rootNode.addNode("lfims:expensesDetails");
			populateValue(expensesMasterNode,expensesMasterRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return expensesMasterNode;
	} 

	private void populateValue(Node node, ExpensesMaster record) throws LFIMSServiceException{
		try{
		//	  fields: ['id','advName', 'advAddress1','advAddress2','cityPin','contactPhone','email'],
			node.setProperty("lfims:id", record.getId());
			node.setProperty("lfims:reason", record.getReason());
			node.setProperty("lfims:description", record.getDescription());
						
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}

	}

	@Override
	protected LFIMSObject<ExpensesMaster> convertNodeToObject(Node node, boolean readOnly) throws LFIMSServiceException {
		LFIMSObject<ExpensesMaster> bankMaster=null;
	
			try {
				if(readOnly==true){
					bankMaster=new ExpensesMasterImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:reason").getString(),
							node.getProperty("lfims:description").getString());
						}
				else{
					bankMaster=new MutableExpensesMasterImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:reason").getString(),
							node.getProperty("lfims:description").getString());
				}
				
			} catch (Exception e) {
				throw new LFIMSServiceException(e);
			}
		
		return bankMaster;
	}
	
	@Override
	protected LFIMSNodeStructure<ExpensesMaster> getRootNodeStructureDetails() {
		return nodeStructure;
	}

	@Override
	protected LFIMSQueryParameters<ExpensesMaster> getSearchQueryParameters(final Map<LFIMSAttributeMapper<ExpensesMaster>, List<String>> searchCriteria) throws LFIMSServiceException {

		final Selector selector;
		final LFIMSQueryConstraint<ExpensesMaster> queryConstraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<ExpensesMaster> childNode=getRootNodeStructureDetails().getNodeType();
		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			queryConstraint=new LFIMSQueryConstraint<ExpensesMaster>(){
				public List<Constraint> getConstraints() throws LFIMSServiceException {
					List<Constraint> constraints=new ArrayList<Constraint>();
					try {
						Constraint constraint=queryOMF.and(
								
										queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), ExpensesMasterAttributeMapper.ID.getFieldName()), searchCriteria.get(ExpensesMasterAttributeMapper.ID)==null?QueryObjectModelConstants.JCR_OPERATOR_GREATER_THAN_OR_EQUAL_TO:QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(searchCriteria.get(ExpensesMasterAttributeMapper.ID)==null?0:Integer.valueOf(searchCriteria.get(ExpensesMasterAttributeMapper.ID).get(0))))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), ExpensesMasterAttributeMapper.REASON.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(ExpensesMasterAttributeMapper.REASON)==null?"%":searchCriteria.get(ExpensesMasterAttributeMapper.REASON).get(0))))
												),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), ExpensesMasterAttributeMapper.DESCRIPTION.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(ExpensesMasterAttributeMapper.DESCRIPTION)==null?"%":searchCriteria.get(ExpensesMasterAttributeMapper.DESCRIPTION).get(0)))));
									

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
	protected LFIMSQueryParameters<ExpensesMaster> getDeleteQueryParameters(final List<Integer> searchCriteria) throws LFIMSServiceException {
		final Selector selector;
		final LFIMSQueryConstraint<ExpensesMaster> constraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<ExpensesMaster> childNode=getRootNodeStructureDetails().getNodeType();

		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			constraint=new LFIMSQueryConstraint<ExpensesMaster>(){

				public List<Constraint> getConstraints() throws LFIMSServiceException {

					List<Constraint> constraints=new ArrayList<Constraint>();
					try {

						Iterator<Integer> valueIterator=searchCriteria.iterator();
						while(valueIterator.hasNext()){

							int value=valueIterator.next();
							constraints.add(queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), ExpensesMasterAttributeMapper.ID.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(value))));
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

	private LFIMSQueryParameters<ExpensesMaster> getParameters(final Selector selector,final LFIMSQueryConstraint<ExpensesMaster> constraint, final Ordering[] ordering, final Column[] columns){

		return new LFIMSQueryParameters<ExpensesMaster>(){
			public Selector getSelector() {
				return selector;
			}
			public LFIMSQueryConstraint<ExpensesMaster> getConstraint() {
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
	protected LFIMSObject<ExpensesMaster>  createNewRecord()	throws LFIMSServiceException {
		LFIMSObject<ExpensesMaster> expensesMasterRecord=new MutableExpensesMasterImpl();
		return expensesMasterRecord;
	}

	

	static{
		
		nodeStructure=new LFIMSNodeStructure<ExpensesMaster>(){

			public LFIMSNode<ExpensesMaster> getNodeType() {
				// TODO Auto-generated method stub
				return new LFIMSNode<ExpensesMaster>(){
					public String getNodeName() {
						return "ExpensesDetails";
					}
					public String getNodeType() {
						return "lfims:ExpensesDetails";
					}
					public LFIMSNodeKey<ExpensesMaster> getNodeKey() {
						return new LFIMSNodeKey<ExpensesMaster>(){
							public String getKeyName() {
								return "lfims:id";
							}
							public int getKeyType() {
								return PropertyType.DECIMAL;
							}
							public LFIMSAttributeMapper<ExpensesMaster> getSearchCriteria() {
								return ExpensesMasterAttributeMapper.ID;
							}
						};
					}
				};
			}

			public LFIMSNodeStructure<ExpensesMaster> getTopNodeStructure() {
				// TODO Auto-generated method stub
				return new LFIMSNodeStructure<ExpensesMaster>(){

					public LFIMSNode<ExpensesMaster> getNodeType() {
						// TODO Auto-generated method stub
						return new LFIMSNode<ExpensesMaster>(){
							public String getNodeName() {
								return "ExpensesDetailsList";
							}
							public String getNodeType() {
								return "lfims:ExpensesDetailsList";
							}
							public LFIMSNodeKey<ExpensesMaster> getNodeKey() {
								return new LFIMSNodeKey<ExpensesMaster>(){
									public String getKeyName() {
										return "lfims:maxid";
									}
									public int getKeyType() {
										return PropertyType.DECIMAL;
									}
									public LFIMSAttributeMapper<ExpensesMaster> getSearchCriteria() {
										return ExpensesMasterAttributeMapper.ID;
									}
								};
							}
						};
					}

					public LFIMSNodeStructure<ExpensesMaster> getTopNodeStructure() {
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
	
	
	
}
