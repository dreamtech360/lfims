package com.dreamtech360.lfims.model.service.impl.courtmaster;

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
 
import com.dreamtech360.lfims.model.api.courtmaster.CourtMaster;
import com.dreamtech360.lfims.model.api.impl.courtmaster.CourtMasterImpl;
import com.dreamtech360.lfims.model.api.impl.courtmaster.MutableCourtMasterImpl;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSNode;
import com.dreamtech360.lfims.model.base.LFIMSNodeKey;
import com.dreamtech360.lfims.model.base.LFIMSNodeStructure;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.impl.courtmaster.CourtMasterAttributeMapper;
import com.dreamtech360.lfims.model.search.query.LFIMSDecimalValue;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryConstraint;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryParameters;
import com.dreamtech360.lfims.model.search.query.LFIMSStringValue;
import com.dreamtech360.lfims.model.service.base.LFIMSJCRSessionThreadLocal;
import com.dreamtech360.lfims.model.service.base.LFIMSModelJCRService;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class CourtMasterMaintenanceService extends LFIMSModelJCRService<CourtMaster>
{
	private static LFIMSNodeStructure<CourtMaster> nodeStructure=null;
	public CourtMasterMaintenanceService(Repository repository){
		this.repository=repository;
	}
	public CourtMasterMaintenanceService(Repository repository,LFIMSTransactionManagementService transactionManagerService){
		this.repository=repository;
		this.transactionManager=transactionManagerService.getTransactionManager();
	}
	@Override
	protected void update(Node node, LFIMSObject<CourtMaster> record) throws LFIMSServiceException {

		CourtMaster courtMasterRecord=(CourtMaster)record;
		try{
			populateValue(node,courtMasterRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}

	@Override
	protected Node store(Node rootNode, LFIMSObject<CourtMaster> record) throws LFIMSServiceException   {

		CourtMaster courtMasterRecord=(CourtMaster)record;
		Node courtMasterNode=null;
		try{
			courtMasterNode = rootNode.addNode("lfims:courtDetails");
			populateValue(courtMasterNode,courtMasterRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return courtMasterNode;
	} 

	private void populateValue(Node node, CourtMaster record) throws LFIMSServiceException{
		try{
		//	  fields: ['id','advName', 'advAddress1','advAddress2','cityPin','contactPhone','email'],
			node.setProperty("lfims:id", record.getId());
			node.setProperty("lfims:name", record.getName());
			node.setProperty("lfims:fullName", record.getFullName());
			node.setProperty("lfims:advAddress1", record.getAdvAddress1());
			node.setProperty("lfims:advAddress2", record.getAdvAddress2());
			node.setProperty("lfims:cityPin", record.getCityPin());
			node.setProperty("lfims:contactPhone", record.getContactPhone());
			
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}

	}

	@Override
	protected LFIMSObject<CourtMaster> convertNodeToObject(Node node, boolean readOnly) throws LFIMSServiceException {
		LFIMSObject<CourtMaster> bankMaster=null;
	
			try {
				if(readOnly==true){
					bankMaster=new CourtMasterImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:name").getString(),
							node.getProperty("lfims:fullName").getString(),
							node.getProperty("lfims:advAddress1").getString(),
							node.getProperty("lfims:advAddress2").getString(),
							node.getProperty("lfims:cityPin").getString(),
							node.getProperty("lfims:contactPhone").getString());
						}
				else{
					bankMaster=new MutableCourtMasterImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:name").getString(),
							node.getProperty("lfims:fullName").getString(),
							node.getProperty("lfims:advAddress1").getString(),
							node.getProperty("lfims:advAddress2").getString(),
							node.getProperty("lfims:cityPin").getString(),
							node.getProperty("lfims:contactPhone").getString());
				}
				
			} catch (Exception e) {
				throw new LFIMSServiceException(e);
			}
		
		return bankMaster;
	}
	
	@Override
	protected LFIMSNodeStructure<CourtMaster> getRootNodeStructureDetails() {
		return nodeStructure;
	}

	@Override
	protected LFIMSQueryParameters<CourtMaster> getSearchQueryParameters(final Map<LFIMSAttributeMapper<CourtMaster>, List<String>> searchCriteria) throws LFIMSServiceException {

		final Selector selector;
		final LFIMSQueryConstraint<CourtMaster> queryConstraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<CourtMaster> childNode=getRootNodeStructureDetails().getNodeType();
		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			queryConstraint=new LFIMSQueryConstraint<CourtMaster>(){
				public List<Constraint> getConstraints() throws LFIMSServiceException {
					List<Constraint> constraints=new ArrayList<Constraint>();
					try {
						Constraint constraint=queryOMF.and(
								queryOMF.and(
										queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CourtMasterAttributeMapper.ID.getFieldName()), searchCriteria.get(CourtMasterAttributeMapper.ID)==null?QueryObjectModelConstants.JCR_OPERATOR_GREATER_THAN_OR_EQUAL_TO:QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(searchCriteria.get(CourtMasterAttributeMapper.ID)==null?0:Integer.valueOf(searchCriteria.get(CourtMasterAttributeMapper.ID).get(0))))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CourtMasterAttributeMapper.NAME.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(CourtMasterAttributeMapper.NAME)==null?"%":searchCriteria.get(CourtMasterAttributeMapper.NAME).get(0))))
												),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CourtMasterAttributeMapper.ADDRESS_1.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(CourtMasterAttributeMapper.ADDRESS_1)==null?"%":searchCriteria.get(CourtMasterAttributeMapper.ADDRESS_1).get(0))))
										),
										queryOMF.and(
												queryOMF.and(
														queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CourtMasterAttributeMapper.ADDRESS_2.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(CourtMasterAttributeMapper.ADDRESS_2)==null?"%":searchCriteria.get(CourtMasterAttributeMapper.ADDRESS_2).get(0)))),
														queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CourtMasterAttributeMapper.CITY_PIN.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(CourtMasterAttributeMapper.CITY_PIN)==null?"%":searchCriteria.get(CourtMasterAttributeMapper.CITY_PIN).get(0))))
														),
														queryOMF.and(
														queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CourtMasterAttributeMapper.CONTACT_PHONE.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(CourtMasterAttributeMapper.CONTACT_PHONE)==null?"%":searchCriteria.get(CourtMasterAttributeMapper.CONTACT_PHONE).get(0)))),
														queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CourtMasterAttributeMapper.FULL_NAME.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(CourtMasterAttributeMapper.FULL_NAME)==null?"%":searchCriteria.get(CourtMasterAttributeMapper.FULL_NAME).get(0))))
														)
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
	protected LFIMSQueryParameters<CourtMaster> getDeleteQueryParameters(final List<Integer> searchCriteria) throws LFIMSServiceException {
		final Selector selector;
		final LFIMSQueryConstraint<CourtMaster> constraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<CourtMaster> childNode=getRootNodeStructureDetails().getNodeType();

		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			constraint=new LFIMSQueryConstraint<CourtMaster>(){

				public List<Constraint> getConstraints() throws LFIMSServiceException {

					List<Constraint> constraints=new ArrayList<Constraint>();
					try {

						Iterator<Integer> valueIterator=searchCriteria.iterator();
						while(valueIterator.hasNext()){

							int value=valueIterator.next();
							constraints.add(queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CourtMasterAttributeMapper.ID.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(value))));
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

	private LFIMSQueryParameters<CourtMaster> getParameters(final Selector selector,final LFIMSQueryConstraint<CourtMaster> constraint, final Ordering[] ordering, final Column[] columns){

		return new LFIMSQueryParameters<CourtMaster>(){
			public Selector getSelector() {
				return selector;
			}
			public LFIMSQueryConstraint<CourtMaster> getConstraint() {
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
	protected LFIMSObject<CourtMaster>  createNewRecord()	throws LFIMSServiceException {
		LFIMSObject<CourtMaster> bankMasterRecord=new MutableCourtMasterImpl();
		return bankMasterRecord;
	}

	

	static{
		

		nodeStructure=new LFIMSNodeStructure<CourtMaster>(){

			public LFIMSNode<CourtMaster> getNodeType() {
				// TODO Auto-generated method stub
				return new LFIMSNode<CourtMaster>(){
					public String getNodeName() {
						return "CourtDetails";
					}
					public String getNodeType() {
						return "lfims:CourtDetails";
					}
					public LFIMSNodeKey<CourtMaster> getNodeKey() {
						return new LFIMSNodeKey<CourtMaster>(){
							public String getKeyName() {
								return "lfims:id";
							}
							public int getKeyType() {
								return PropertyType.DECIMAL;
							}
							public LFIMSAttributeMapper<CourtMaster> getSearchCriteria() {
								return CourtMasterAttributeMapper.ID;
							}
						};
					}
				};
			}

			public LFIMSNodeStructure<CourtMaster> getTopNodeStructure() {
				// TODO Auto-generated method stub
				return new LFIMSNodeStructure<CourtMaster>(){

					public LFIMSNode<CourtMaster> getNodeType() {
						// TODO Auto-generated method stub
						return new LFIMSNode<CourtMaster>(){
							public String getNodeName() {
								return "CourtDetailsList";
							}
							public String getNodeType() {
								return "lfims:CourtDetailsList";
							}
							public LFIMSNodeKey<CourtMaster> getNodeKey() {
								return new LFIMSNodeKey<CourtMaster>(){
									public String getKeyName() {
										return "lfims:maxid";
									}
									public int getKeyType() {
										return PropertyType.DECIMAL;
									}
									public LFIMSAttributeMapper<CourtMaster> getSearchCriteria() {
										return CourtMasterAttributeMapper.ID;
									}
								};
							}
						};
					}

					public LFIMSNodeStructure<CourtMaster> getTopNodeStructure() {
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
