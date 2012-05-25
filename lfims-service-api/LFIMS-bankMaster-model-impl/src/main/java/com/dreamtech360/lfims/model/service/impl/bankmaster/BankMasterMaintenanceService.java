package com.dreamtech360.lfims.model.service.impl.bankmaster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.jcr.Node;
import javax.jcr.PropertyType;
import javax.jcr.Repository;
import javax.jcr.Value;
import javax.jcr.query.qom.Column;
import javax.jcr.query.qom.Constraint;
import javax.jcr.query.qom.Ordering;
import javax.jcr.query.qom.QueryObjectModelConstants;
import javax.jcr.query.qom.QueryObjectModelFactory;
import javax.jcr.query.qom.Selector;

import com.dreamtech360.lfims.model.api.bankmaster.BankMaster;
import com.dreamtech360.lfims.model.api.impl.bankmaster.BankMasterImpl;
import com.dreamtech360.lfims.model.api.impl.bankmaster.MutableBankMasterImpl;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSNode;
import com.dreamtech360.lfims.model.base.LFIMSNodeKey;
import com.dreamtech360.lfims.model.base.LFIMSNodeStructure;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.impl.bankmaster.BankMasterAttributeMapper;
import com.dreamtech360.lfims.model.search.query.LFIMSDecimalValue;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryConstraint;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryParameters;
import com.dreamtech360.lfims.model.search.query.LFIMSStringValue;
import com.dreamtech360.lfims.model.service.base.LFIMSJCRSessionThreadLocal;
import com.dreamtech360.lfims.model.service.base.LFIMSModelJCRService;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;

public class BankMasterMaintenanceService extends LFIMSModelJCRService<BankMaster>
{
	private static LFIMSNodeStructure<BankMaster> nodeStructure=null;
	public BankMasterMaintenanceService(Repository repository){
		this.repository=repository;
	}

	@Override
	protected void update(Node node, LFIMSObject<BankMaster> record) throws LFIMSServiceException {

		BankMaster bankMasterRecord=(BankMaster)record;
		try{
			populateValue(node,bankMasterRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}

	@Override
	protected Node store(Node rootNode, LFIMSObject<BankMaster> record) throws LFIMSServiceException   {

		BankMaster bankMasterRecord=(BankMaster)record;
		Node bankDetailsNode =null;
		try{
			 bankDetailsNode = rootNode.addNode("lfims:bankDetails");
			populateValue(bankDetailsNode,bankMasterRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return bankDetailsNode;
	}

	private void populateValue(Node node, BankMaster record) throws LFIMSServiceException{
		try{
			node.setProperty("lfims:id", record.getId());
			node.setProperty("lfims:name", record.getName());
			node.setProperty("lfims:fullName", record.getFullName());
			node.setProperty("lfims:headOffice", record.getHeadOffice());
			node.setProperty("lfims:contactPhone", record.getContactPhone());
			node.setProperty("lfims:contactPerson", record.getContactPerson());
			node.setProperty("lfims:branches",new Value[]{});
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}

	}

	@Override
	protected LFIMSObject<BankMaster> convertNodeToObject(Node node, boolean readOnly) throws LFIMSServiceException {
		LFIMSObject<BankMaster> bankMaster=null;
	
			try {
				if(readOnly==true){
					bankMaster=new BankMasterImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:name").getString(),
							node.getProperty("lfims:fullName").getString(),
							node.getProperty("lfims:headOffice").getString(),
							node.getProperty("lfims:contactPerson").getString(),
							node.getProperty("lfims:contactPhone").getString());
				}
				else{
					bankMaster=new MutableBankMasterImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:name").getString(),
							node.getProperty("lfims:fullName").getString(),
							node.getProperty("lfims:headOffice").getString(),
							node.getProperty("lfims:contactPerson").getString(),
							node.getProperty("lfims:contactPhone").getString());
				}
				
			} catch (Exception e) {
				throw new LFIMSServiceException(e);
			}
		
		return bankMaster;
	}
	
	
	@Override
	protected LFIMSNodeStructure<BankMaster> getRootNodeStructureDetails() {
			
		return nodeStructure;
	}

	@Override
	protected LFIMSQueryParameters<BankMaster> getSearchQueryParameters(final Map<LFIMSAttributeMapper<BankMaster>, List<String>> searchCriteria) throws LFIMSServiceException {

		final Selector selector;
		final LFIMSQueryConstraint<BankMaster> queryConstraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<BankMaster> childNode=getRootNodeStructureDetails().getNodeType();
		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			queryConstraint=new LFIMSQueryConstraint<BankMaster>(){
				public List<Constraint> getConstraints() throws LFIMSServiceException {
					List<Constraint> constraints=new ArrayList<Constraint>();
					try {
						Constraint constraint=queryOMF.and(
								queryOMF.and(
										queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), BankMasterAttributeMapper.ID.getFieldName()), searchCriteria.get(BankMasterAttributeMapper.ID)==null?QueryObjectModelConstants.JCR_OPERATOR_GREATER_THAN_OR_EQUAL_TO:QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(searchCriteria.get(BankMasterAttributeMapper.ID)==null?0:Integer.valueOf(searchCriteria.get(BankMasterAttributeMapper.ID).get(0))))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), BankMasterAttributeMapper.NAME.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(BankMasterAttributeMapper.NAME)==null?"%":searchCriteria.get(BankMasterAttributeMapper.NAME).get(0))))
												),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), BankMasterAttributeMapper.FULL_NAME.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(BankMasterAttributeMapper.FULL_NAME)==null?"%":searchCriteria.get(BankMasterAttributeMapper.FULL_NAME).get(0))))
										),
										queryOMF.and(
												queryOMF.and(
														queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), BankMasterAttributeMapper.HEAD_OFFICE.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(BankMasterAttributeMapper.HEAD_OFFICE)==null?"%":searchCriteria.get(BankMasterAttributeMapper.HEAD_OFFICE).get(0)))),
														queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), BankMasterAttributeMapper.CONTACT_PERSON.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(BankMasterAttributeMapper.CONTACT_PERSON)==null?"%":searchCriteria.get(BankMasterAttributeMapper.CONTACT_PERSON).get(0))))
														),
														queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), BankMasterAttributeMapper.CONTACT_PHONE.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(BankMasterAttributeMapper.CONTACT_PHONE)==null?"%":searchCriteria.get(BankMasterAttributeMapper.CONTACT_PHONE).get(0))))
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
	protected LFIMSQueryParameters<BankMaster> getDeleteQueryParameters(final List<Integer> searchCriteria) throws LFIMSServiceException {
		final Selector selector;
		final LFIMSQueryConstraint<BankMaster> constraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<BankMaster> childNode=getRootNodeStructureDetails().getNodeType();

		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			constraint=new LFIMSQueryConstraint<BankMaster>(){

				public List<Constraint> getConstraints() throws LFIMSServiceException {

					List<Constraint> constraints=new ArrayList<Constraint>();
					try {

						Iterator<Integer> valueIterator=searchCriteria.iterator();
						while(valueIterator.hasNext()){

							int value=valueIterator.next();
							constraints.add(queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), BankMasterAttributeMapper.ID.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(value))));
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

	private LFIMSQueryParameters<BankMaster> getParameters(final Selector selector,final LFIMSQueryConstraint<BankMaster> constraint, final Ordering[] ordering, final Column[] columns){

		return new LFIMSQueryParameters<BankMaster>(){
			public Selector getSelector() {
				return selector;
			}
			public LFIMSQueryConstraint<BankMaster> getConstraint() {
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
	protected LFIMSObject<BankMaster>  createNewRecord()	throws LFIMSServiceException {
		LFIMSObject<BankMaster> bankMasterRecord=new MutableBankMasterImpl();
		return bankMasterRecord;
	}

	

	/*@Override
	protected Node convertToJCRNode(LFIMSNode<BankMaster> object) throws LFIMSServiceException {
		try{
			Node bankDetailsNode = rootNode.addNode("lfims:bankDetails");
			Map<LFIMSAttributeMapper<BankMaster>,String> attributes=object.getAttributes();
			
			populateValue(bankDetailsNode,bankMasterRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}*/
	
	static{
		
		nodeStructure=new LFIMSNodeStructure<BankMaster>(){

			public LFIMSNode<BankMaster> getNodeType() {
				return new LFIMSNode<BankMaster>(){
					public String getNodeName() {
						return "BankDetails";
					}
					public String getNodeType() {
						return "lfims:BankDetails";
					}
					public LFIMSNodeKey<BankMaster> getNodeKey() {
						return new LFIMSNodeKey<BankMaster>(){
							public String getKeyName() {
								return "lfims:id";
							}
							public int getKeyType() {
								return PropertyType.DECIMAL;
							}
							public LFIMSAttributeMapper<BankMaster> getSearchCriteria() {
								return BankMasterAttributeMapper.ID;
							}
						};
					}
				};
			}

			public LFIMSNodeStructure<BankMaster> getTopNodeStructure() {
				return new LFIMSNodeStructure<BankMaster>(){

					public LFIMSNode<BankMaster> getNodeType() {
						return new LFIMSNode<BankMaster>(){
							public String getNodeName() {
								return "BankDetailsList";
							}
							public String getNodeType() {
								return "lfims:BankDetailsList";
							}
							public LFIMSNodeKey<BankMaster> getNodeKey() {
								return new LFIMSNodeKey<BankMaster>(){
									public String getKeyName() {
										return "lfims:maxid";
									}
									public int getKeyType() {
										return PropertyType.DECIMAL;
									}
									public LFIMSAttributeMapper<BankMaster> getSearchCriteria() {
										return BankMasterAttributeMapper.ID;
									}
								};
							}
						};
					}

					public LFIMSNodeStructure<BankMaster> getTopNodeStructure() {
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
