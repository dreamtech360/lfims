package com.dreamtech360.lfims.model.service.impl.branchmaster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List; 
import java.util.Map;
import javax.jcr.Node;
import javax.jcr.PropertyType;
import javax.jcr.Repository;
import javax.jcr.Value;
import javax.jcr.ValueFactory;
import javax.jcr.query.qom.Column;
import javax.jcr.query.qom.Constraint;
import javax.jcr.query.qom.Ordering;
import javax.jcr.query.qom.QueryObjectModelConstants;
import javax.jcr.query.qom.QueryObjectModelFactory;
import javax.jcr.query.qom.Selector;
import com.dreamtech360.lfims.model.api.branchmaster.BranchMaster;
import com.dreamtech360.lfims.model.api.impl.branchmaster.BranchMasterImpl;
import com.dreamtech360.lfims.model.api.impl.branchmaster.MutableBranchMasterImpl;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSNode;
import com.dreamtech360.lfims.model.base.LFIMSNodeKey;
import com.dreamtech360.lfims.model.base.LFIMSNodeStructure;
import com.dreamtech360.lfims.model.base.Referenceable;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.impl.branchmaster.BranchMasterAttributeMapper;
import com.dreamtech360.lfims.model.search.query.LFIMSDecimalValue;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryConstraint;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryParameters;
import com.dreamtech360.lfims.model.search.query.LFIMSStringValue;
import com.dreamtech360.lfims.model.service.base.LFIMSJCRSessionThreadLocal;
import com.dreamtech360.lfims.model.service.base.LFIMSModelJCRService;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class BranchMasterMaintenanceService extends LFIMSModelJCRService<BranchMaster>
{
	private static LFIMSNodeStructure<BranchMaster> nodeStructure=null;
	private enum Actions{
		CREATE,
		UPDATE
	};
	
	public BranchMasterMaintenanceService(Repository repository){
		this.repository=repository;
	}
	
	public BranchMasterMaintenanceService(Repository repository,LFIMSTransactionManagementService transactionManagerService){
		this.repository=repository;
		this.transactionManager=transactionManagerService.getTransactionManager();
	}
 
	@Override
	protected void update(Node node, LFIMSObject<BranchMaster> record) throws LFIMSServiceException {

		BranchMaster branchMasterRecord=(BranchMaster)record;
		try{
			populateValue(node,branchMasterRecord,Actions.UPDATE);
		}catch(LFIMSServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}

	@Override
	protected Node store(Node rootNode, LFIMSObject<BranchMaster> record) throws LFIMSServiceException   {

		BranchMaster branchMasterRecord=(BranchMaster)record;
		Node branchDetailsNode=null;
		try{
			branchDetailsNode = rootNode.addNode("lfims:branchDetails");
			Referenceable ref=branchMasterRecord.getReference("com.dreamtech360.lfims.model.api.bankmaster.BankMaster");
			//It is the UUID of the bank Master
			String bankDetailsNodeReference = branchMasterRecord.getReferenceKey(ref);
			Node bankDetailsNode = LFIMSJCRSessionThreadLocal.get().getSession().getNodeByIdentifier(bankDetailsNodeReference);
			Value[] membersArray =  bankDetailsNode.getProperty(ref.getReferenceMetaDetails().getRefPropertyName()).getValues();
			List<Value> membersList = new ArrayList<Value>(Arrays.asList(membersArray));
			ValueFactory vf =  LFIMSJCRSessionThreadLocal.get().getSession().getValueFactory();
			membersList.add(vf.createValue(branchDetailsNode));
			bankDetailsNode.setProperty(ref.getReferenceMetaDetails().getRefPropertyName(), membersList.toArray(membersArray));
					
			populateValue(branchDetailsNode,branchMasterRecord,Actions.CREATE);
		}catch(LFIMSServiceException e){
			throw e;
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return branchDetailsNode;
	}

	private void populateValue(Node node, BranchMaster record, Actions action) throws LFIMSServiceException{
		try{
			Referenceable reference=record.getReference("com.dreamtech360.lfims.model.api.bankmaster.BankMaster");
			node.setProperty(BranchMasterAttributeMapper.ID.getFieldName(), record.getId());
			//As it is a update scenario the reference column will not change hence not required to be updated
			if(action==Actions.CREATE)
				node.setProperty(BranchMasterAttributeMapper.BANK_NAME.getFieldName(), reference.getReferenceMetaDetails().getPayload());
			node.setProperty(BranchMasterAttributeMapper.NAME.getFieldName(), record.getName());
			node.setProperty(BranchMasterAttributeMapper.REGION.getFieldName(), record.getRegion());
			node.setProperty(BranchMasterAttributeMapper.ADDRESS.getFieldName(), record.getAddress());
			node.setProperty(BranchMasterAttributeMapper.CONTACT_PHONE.getFieldName(), record.getContactPhone());
			node.setProperty(BranchMasterAttributeMapper.CONTACT_PERSON.getFieldName(), record.getContactPerson());
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}

	}
	
	@Override
	protected LFIMSObject<BranchMaster> convertNodeToObject(Node node, boolean readOnly) throws LFIMSServiceException {
		LFIMSObject<BranchMaster> branchMaster=null;
		try{
		if(readOnly==true){
			branchMaster=new BranchMasterImpl(
					Integer.parseInt(node.getProperty(BranchMasterAttributeMapper.ID.getFieldName()).getString()),
					node.getProperty(BranchMasterAttributeMapper.NAME.getFieldName()).getString(),
					node.getProperty(BranchMasterAttributeMapper.REGION.getFieldName()).getString(),
					node.getProperty(BranchMasterAttributeMapper.ADDRESS.getFieldName()).getString(),
					node.getProperty(BranchMasterAttributeMapper.CONTACT_PERSON.getFieldName()).getString(),
					node.getProperty(BranchMasterAttributeMapper.CONTACT_PHONE.getFieldName()).getString(),
					node.getProperty(BranchMasterAttributeMapper.BANK_NAME.getFieldName()).getString());
		}
		else{
			branchMaster=new MutableBranchMasterImpl(
					Integer.parseInt(node.getProperty(BranchMasterAttributeMapper.ID.getFieldName()).getString()),
					node.getProperty(BranchMasterAttributeMapper.NAME.getFieldName()).getString(),
					node.getProperty(BranchMasterAttributeMapper.REGION.getFieldName()).getString(),
					node.getProperty(BranchMasterAttributeMapper.ADDRESS.getFieldName()).getString(),
					node.getProperty(BranchMasterAttributeMapper.CONTACT_PERSON.getFieldName()).getString(),
					node.getProperty(BranchMasterAttributeMapper.CONTACT_PHONE.getFieldName()).getString(),
					node.getProperty(BranchMasterAttributeMapper.BANK_NAME.getFieldName()).getString());
		}
		}catch(Exception e){
			throw new LFIMSServiceException (e);
		}
		return branchMaster;
	}
		
	@Override
	protected LFIMSNodeStructure<BranchMaster> getRootNodeStructureDetails() {
		return nodeStructure;
	}


	@Override
	protected LFIMSQueryParameters<BranchMaster> getSearchQueryParameters(final Map<LFIMSAttributeMapper<BranchMaster>, List<String>> searchCriteria) throws LFIMSServiceException {

		final Selector selector;
		final LFIMSQueryConstraint<BranchMaster> queryConstraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<BranchMaster> childNode=getRootNodeStructureDetails().getNodeType();
		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			queryConstraint=new LFIMSQueryConstraint<BranchMaster>(){
				public List<Constraint> getConstraints() throws LFIMSServiceException {
					List<Constraint> constraints=new ArrayList<Constraint>();
					try {
						Constraint constraint=queryOMF.and(
								queryOMF.and(
										queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), BranchMasterAttributeMapper.ID.getFieldName()), searchCriteria.get(BranchMasterAttributeMapper.ID)==null?QueryObjectModelConstants.JCR_OPERATOR_GREATER_THAN_OR_EQUAL_TO:QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(searchCriteria.get(BranchMasterAttributeMapper.ID)==null?0:Integer.valueOf(searchCriteria.get(BranchMasterAttributeMapper.ID).get(0))))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), BranchMasterAttributeMapper.NAME.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(BranchMasterAttributeMapper.NAME)==null?"%":searchCriteria.get(BranchMasterAttributeMapper.NAME).get(0))))
												),
												queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), BranchMasterAttributeMapper.REGION.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(BranchMasterAttributeMapper.REGION)==null?"%":searchCriteria.get(BranchMasterAttributeMapper.REGION).get(0)))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), BranchMasterAttributeMapper.BANK_NAME.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(BranchMasterAttributeMapper.BANK_NAME)==null?"%":searchCriteria.get(BranchMasterAttributeMapper.BANK_NAME).get(0))))
												)
										),
										queryOMF.and(
												queryOMF.and(
														queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), BranchMasterAttributeMapper.ADDRESS.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(BranchMasterAttributeMapper.ADDRESS)==null?"%":searchCriteria.get(BranchMasterAttributeMapper.ADDRESS).get(0)))),
														queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), BranchMasterAttributeMapper.CONTACT_PERSON.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(BranchMasterAttributeMapper.CONTACT_PERSON)==null?"%":searchCriteria.get(BranchMasterAttributeMapper.CONTACT_PERSON).get(0))))
														),
														queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), BranchMasterAttributeMapper.CONTACT_PHONE.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(BranchMasterAttributeMapper.CONTACT_PHONE)==null?"%":searchCriteria.get(BranchMasterAttributeMapper.CONTACT_PHONE).get(0))))
												)
								);

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
	protected LFIMSQueryParameters<BranchMaster> getDeleteQueryParameters(final List<Integer> searchCriteria) throws LFIMSServiceException {
		final Selector selector;
		final LFIMSQueryConstraint<BranchMaster> constraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<BranchMaster> childNode=getRootNodeStructureDetails().getNodeType();

		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			constraint=new LFIMSQueryConstraint<BranchMaster>(){

				public List<Constraint> getConstraints() throws LFIMSServiceException {

					List<Constraint> constraints=new ArrayList<Constraint>();
					try {

						Iterator<Integer> valueIterator=searchCriteria.iterator();
						while(valueIterator.hasNext()){

							int value=valueIterator.next();
							constraints.add(queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), BranchMasterAttributeMapper.ID.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(value))));
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

	private LFIMSQueryParameters<BranchMaster> getParameters(final Selector selector,final LFIMSQueryConstraint<BranchMaster> constraint, final Ordering[] ordering, final Column[] columns){

		return new LFIMSQueryParameters<BranchMaster>(){
			public Selector getSelector() {
				return selector;
			}
			public LFIMSQueryConstraint<BranchMaster> getConstraint() {
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
	protected LFIMSObject<BranchMaster> createNewRecord()
			throws LFIMSServiceException {
		LFIMSObject<BranchMaster> branchMasterRecord=new MutableBranchMasterImpl();
		return branchMasterRecord;
	}
	
	
	static{
		
		
		nodeStructure=new LFIMSNodeStructure<BranchMaster>(){

			public LFIMSNode<BranchMaster> getNodeType() {
				// TODO Auto-generated method stub
				return new LFIMSNode<BranchMaster>(){
					public String getNodeName() {
						return "BranchDetails";
					}
					public String getNodeType() {
						return "lfims:BranchDetails";
					}
					public LFIMSNodeKey<BranchMaster> getNodeKey() {
						return new LFIMSNodeKey<BranchMaster>(){
							public String getKeyName() {
								return "lfims:id";
							}
							public int getKeyType() {
								return PropertyType.DECIMAL;
							}
							public LFIMSAttributeMapper<BranchMaster> getSearchCriteria() {
								return BranchMasterAttributeMapper.ID;
							}
						};
					}
				};
			}

			public LFIMSNodeStructure<BranchMaster> getTopNodeStructure() {
				// TODO Auto-generated method stub
				return new LFIMSNodeStructure<BranchMaster>(){

					public LFIMSNode<BranchMaster> getNodeType() {
						// TODO Auto-generated method stub
						return new LFIMSNode<BranchMaster>(){
							public String getNodeName() {
								return "BranchDetailsList";
							}
							public String getNodeType() {
								return "lfims:BranchDetailsList";
							}
							public LFIMSNodeKey<BranchMaster> getNodeKey() {
								return new LFIMSNodeKey<BranchMaster>(){
									public String getKeyName() {
										return "lfims:maxid";
									}
									public int getKeyType() {
										return PropertyType.DECIMAL;
									}
									public LFIMSAttributeMapper<BranchMaster> getSearchCriteria() {
										return BranchMasterAttributeMapper.ID;
									}
								};
							}
						};
					}

					public LFIMSNodeStructure<BranchMaster> getTopNodeStructure() {
					
						return null;
					}

					@Override
					public boolean isNodeTypeCollection() {
					
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
