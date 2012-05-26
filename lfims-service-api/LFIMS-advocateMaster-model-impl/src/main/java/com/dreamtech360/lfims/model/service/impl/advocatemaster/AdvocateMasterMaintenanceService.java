package com.dreamtech360.lfims.model.service.impl.advocatemaster;

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

import com.dreamtech360.lfims.model.api.advocatemaster.AdvocateMaster;
import com.dreamtech360.lfims.model.api.impl.advocatemaster.AdvocateMasterImpl;
import com.dreamtech360.lfims.model.api.impl.advocatemaster.MutableAdvocateMasterImpl;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSNode;
import com.dreamtech360.lfims.model.base.LFIMSNodeKey;
import com.dreamtech360.lfims.model.base.LFIMSNodeStructure;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.impl.advocatemaster.AdvocateMasterAttributeMapper;
import com.dreamtech360.lfims.model.search.query.LFIMSDecimalValue;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryConstraint;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryParameters;
import com.dreamtech360.lfims.model.search.query.LFIMSStringValue;
import com.dreamtech360.lfims.model.service.base.LFIMSJCRSessionThreadLocal;
import com.dreamtech360.lfims.model.service.base.LFIMSModelJCRService;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;

public class AdvocateMasterMaintenanceService extends LFIMSModelJCRService<AdvocateMaster>
{
	private static LFIMSNodeStructure<AdvocateMaster> nodeStructure=null;
	public AdvocateMasterMaintenanceService(Repository repository){
		this.repository=repository;
	}

	@Override
	protected void update(Node node, LFIMSObject<AdvocateMaster> record) throws LFIMSServiceException {

		AdvocateMaster advocateMasterRecord=(AdvocateMaster)record;
		try{
			populateValue(node,advocateMasterRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	} 

	@Override
	protected Node store(Node rootNode, LFIMSObject<AdvocateMaster> record) throws LFIMSServiceException   {

		AdvocateMaster advocateMasterRecord=(AdvocateMaster)record;
		Node advocateMasterNode =null;
		try{
			advocateMasterNode = rootNode.addNode("lfims:advocateDetails");
			populateValue(advocateMasterNode,advocateMasterRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return advocateMasterNode;
	} 

	private void populateValue(Node node, AdvocateMaster record) throws LFIMSServiceException{
		try{
			//	  fields: ['id','advName', 'advAddress1','advAddress2','cityPin','contactPhone','email'],
			node.setProperty("lfims:id", record.getId());
			node.setProperty("lfims:advName", record.getAdvName());
			node.setProperty("lfims:advAddress1", record.getAdvAddress1());
			node.setProperty("lfims:advAddress2", record.getAdvAddress2());
			node.setProperty("lfims:cityPin", record.getCityPin());
			node.setProperty("lfims:contactPhone", record.getContactPhone());
			node.setProperty("lfims:email",record.getEmail());
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}

	}

	@Override
	protected LFIMSObject<AdvocateMaster> convertNodeToObject(Node node, boolean readOnly) throws LFIMSServiceException {
		LFIMSObject<AdvocateMaster> bankMaster=null;

		try {
			if(readOnly==true){
				bankMaster=new AdvocateMasterImpl(
						Integer.parseInt(node.getProperty("lfims:id").getString()),
						node.getProperty("lfims:advName").getString(),
						node.getProperty("lfims:advAddress1").getString(),
						node.getProperty("lfims:advAddress2").getString(),
						node.getProperty("lfims:cityPin").getString(),
						node.getProperty("lfims:contactPhone").getString(),
						node.getProperty("lfims:email").getString());
			}
			else{
				bankMaster=new MutableAdvocateMasterImpl(
						Integer.parseInt(node.getProperty("lfims:id").getString()),
						node.getProperty("lfims:advName").getString(),
						node.getProperty("lfims:advAddress1").getString(),
						node.getProperty("lfims:advAddress2").getString(),
						node.getProperty("lfims:cityPin").getString(),
						node.getProperty("lfims:contactPhone").getString(),
						node.getProperty("lfims:email").getString());
			}

		} catch (Exception e) {
			throw new LFIMSServiceException(e);
		}

		return bankMaster;
	}

	@Override
	protected LFIMSNodeStructure<AdvocateMaster> getRootNodeStructureDetails() {

		return nodeStructure;
	}


	@Override
	protected LFIMSQueryParameters<AdvocateMaster> getSearchQueryParameters(final Map<LFIMSAttributeMapper<AdvocateMaster>, List<String>> searchCriteria) throws LFIMSServiceException {

		final Selector selector;
		final LFIMSQueryConstraint<AdvocateMaster> queryConstraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<AdvocateMaster> childNode=getRootNodeStructureDetails().getNodeType();
		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			queryConstraint=new LFIMSQueryConstraint<AdvocateMaster>(){
				public List<Constraint> getConstraints() throws LFIMSServiceException {
					List<Constraint> constraints=new ArrayList<Constraint>();
					try {
						Constraint constraint=queryOMF.and(
								queryOMF.and(
										queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), AdvocateMasterAttributeMapper.ID.getFieldName()), searchCriteria.get(AdvocateMasterAttributeMapper.ID)==null?QueryObjectModelConstants.JCR_OPERATOR_GREATER_THAN_OR_EQUAL_TO:QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(searchCriteria.get(AdvocateMasterAttributeMapper.ID)==null?0:Integer.valueOf(searchCriteria.get(AdvocateMasterAttributeMapper.ID).get(0))))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), AdvocateMasterAttributeMapper.ADV_NAME.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(AdvocateMasterAttributeMapper.ADV_NAME)==null?"%":searchCriteria.get(AdvocateMasterAttributeMapper.ADV_NAME).get(0))))
												),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), AdvocateMasterAttributeMapper.ADDRESS_1.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(AdvocateMasterAttributeMapper.ADDRESS_1)==null?"%":searchCriteria.get(AdvocateMasterAttributeMapper.ADDRESS_1).get(0))))
										),
										queryOMF.and(
												queryOMF.and(
														queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), AdvocateMasterAttributeMapper.ADDRESS_2.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(AdvocateMasterAttributeMapper.ADDRESS_2)==null?"%":searchCriteria.get(AdvocateMasterAttributeMapper.ADDRESS_2).get(0)))),
														queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), AdvocateMasterAttributeMapper.CITY_PIN.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(AdvocateMasterAttributeMapper.CITY_PIN)==null?"%":searchCriteria.get(AdvocateMasterAttributeMapper.CITY_PIN).get(0))))
														),
														queryOMF.and(
																queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), AdvocateMasterAttributeMapper.CONTACT_PHONE.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(AdvocateMasterAttributeMapper.CONTACT_PHONE)==null?"%":searchCriteria.get(AdvocateMasterAttributeMapper.CONTACT_PHONE).get(0)))),
																queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), AdvocateMasterAttributeMapper.EMAIL.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(AdvocateMasterAttributeMapper.EMAIL)==null?"%":searchCriteria.get(AdvocateMasterAttributeMapper.EMAIL).get(0))))
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
	protected LFIMSQueryParameters<AdvocateMaster> getDeleteQueryParameters(final List<Integer> searchCriteria) throws LFIMSServiceException {
		final Selector selector;
		final LFIMSQueryConstraint<AdvocateMaster> constraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<AdvocateMaster> childNode=getRootNodeStructureDetails().getNodeType();

		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			constraint=new LFIMSQueryConstraint<AdvocateMaster>(){

				public List<Constraint> getConstraints() throws LFIMSServiceException {

					List<Constraint> constraints=new ArrayList<Constraint>();
					try {

						Iterator<Integer> valueIterator=searchCriteria.iterator();
						while(valueIterator.hasNext()){

							int value=valueIterator.next();
							constraints.add(queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), AdvocateMasterAttributeMapper.ID.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(value))));
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

	private LFIMSQueryParameters<AdvocateMaster> getParameters(final Selector selector,final LFIMSQueryConstraint<AdvocateMaster> constraint, final Ordering[] ordering, final Column[] columns){

		return new LFIMSQueryParameters<AdvocateMaster>(){
			public Selector getSelector() {
				return selector;
			}
			public LFIMSQueryConstraint<AdvocateMaster> getConstraint() {
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
	protected LFIMSObject<AdvocateMaster>  createNewRecord()	throws LFIMSServiceException {
		LFIMSObject<AdvocateMaster> advocateMasterRecord=new MutableAdvocateMasterImpl();
		return advocateMasterRecord;
	}



	static{

		nodeStructure=new LFIMSNodeStructure<AdvocateMaster>(){

			public LFIMSNode<AdvocateMaster> getNodeType() {
				// TODO Auto-generated method stub
				return new LFIMSNode<AdvocateMaster>(){
					public String getNodeName() {
						return "AdvocateDetails";
					}
					public String getNodeType() {
						return "lfims:AdvocateDetails";
					}
					public LFIMSNodeKey<AdvocateMaster> getNodeKey() {
						return new LFIMSNodeKey<AdvocateMaster>(){
							public String getKeyName() {
								return "lfims:id";
							}
							public int getKeyType() {
								return PropertyType.DECIMAL;
							}
							public LFIMSAttributeMapper<AdvocateMaster> getSearchCriteria() {
								return AdvocateMasterAttributeMapper.ID;
							}
						};
					}
				};
			}

			public LFIMSNodeStructure<AdvocateMaster> getTopNodeStructure() {
				// TODO Auto-generated method stub
				return new LFIMSNodeStructure<AdvocateMaster>(){

					public LFIMSNode<AdvocateMaster> getNodeType() {
						// TODO Auto-generated method stub
						return new LFIMSNode<AdvocateMaster>(){
							public String getNodeName() {
								return "AdvocateDetailsList";
							}
							public String getNodeType() {
								return "lfims:AdvocateDetailsList";
							}
							public LFIMSNodeKey<AdvocateMaster> getNodeKey() {
								return new LFIMSNodeKey<AdvocateMaster>(){
									public String getKeyName() {
										return "lfims:maxid";
									}
									public int getKeyType() {
										return PropertyType.DECIMAL;
									}
									public LFIMSAttributeMapper<AdvocateMaster> getSearchCriteria() {
										return AdvocateMasterAttributeMapper.ID;
									}
								};
							}
						};
					}

					public LFIMSNodeStructure<AdvocateMaster> getTopNodeStructure() {
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
