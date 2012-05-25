package com.dreamtech360.lfims.model.service.impl.ouradvocatemaster;

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

import com.dreamtech360.lfims.model.api.impl.ouradvocatemaster.MutableOurAdvocateMasterImpl;
import com.dreamtech360.lfims.model.api.impl.ouradvocatemaster.OurAdvocateMasterImpl;
import com.dreamtech360.lfims.model.api.ouradvocatemaster.OurAdvocateMaster;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSNode;
import com.dreamtech360.lfims.model.base.LFIMSNodeKey;
import com.dreamtech360.lfims.model.base.LFIMSNodeStructure;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.impl.ouradvocatemaster.OurAdvocateMasterAttributeMapper;
import com.dreamtech360.lfims.model.search.query.LFIMSDecimalValue;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryConstraint;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryParameters;
import com.dreamtech360.lfims.model.search.query.LFIMSStringValue;
import com.dreamtech360.lfims.model.service.base.LFIMSJCRSessionThreadLocal;
import com.dreamtech360.lfims.model.service.base.LFIMSModelJCRService;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;

public class OurAdvocateMasterMaintenanceService extends LFIMSModelJCRService<OurAdvocateMaster>
{
	private static LFIMSNodeStructure<OurAdvocateMaster> nodeStructure=null;
	public OurAdvocateMasterMaintenanceService(Repository repository){
		this.repository=repository;
	}

	@Override
	protected void update(Node node, LFIMSObject<OurAdvocateMaster> record) throws LFIMSServiceException {

		OurAdvocateMaster advocateMasterRecord=(OurAdvocateMaster)record;
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
	protected Node store(Node rootNode, LFIMSObject<OurAdvocateMaster> record) throws LFIMSServiceException   {

		OurAdvocateMaster ourAdvocateMaster=(OurAdvocateMaster)record;
		Node ourAdvocateMasterNode=null;
		try{
			ourAdvocateMasterNode = rootNode.addNode("lfims:ourAdvocateDetails");
			populateValue(ourAdvocateMasterNode,ourAdvocateMaster);
		}catch(LFIMSServiceException e){
			throw e;
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return ourAdvocateMasterNode;
	} 

	private void populateValue(Node node, OurAdvocateMaster record) throws LFIMSServiceException{
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
	protected LFIMSObject<OurAdvocateMaster> convertNodeToObject(Node node, boolean readOnly) throws LFIMSServiceException {
		LFIMSObject<OurAdvocateMaster> ourAdvocateMaster=null;
	
			try {
				if(readOnly==true){
					ourAdvocateMaster=new OurAdvocateMasterImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:advName").getString(),
							node.getProperty("lfims:advAddress1").getString(),
							node.getProperty("lfims:advAddress2").getString(),
							node.getProperty("lfims:cityPin").getString(),
							node.getProperty("lfims:contactPhone").getString(),
							node.getProperty("lfims:email").getString());
				}
				else{
					ourAdvocateMaster=new MutableOurAdvocateMasterImpl(
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
		
		return ourAdvocateMaster;
	}
	@Override
	protected LFIMSNodeStructure<OurAdvocateMaster> getRootNodeStructureDetails() {
		return nodeStructure;
	}

	@Override
	protected LFIMSQueryParameters<OurAdvocateMaster> getSearchQueryParameters(final Map<LFIMSAttributeMapper<OurAdvocateMaster>, List<String>> searchCriteria) throws LFIMSServiceException {

		final Selector selector;
		final LFIMSQueryConstraint<OurAdvocateMaster> queryConstraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<OurAdvocateMaster> childNode=getRootNodeStructureDetails().getNodeType();
		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			queryConstraint=new LFIMSQueryConstraint<OurAdvocateMaster>(){
				public List<Constraint> getConstraints() throws LFIMSServiceException {
					List<Constraint> constraints=new ArrayList<Constraint>();
					try {
						Constraint constraint=queryOMF.and(
								queryOMF.and(
										queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), OurAdvocateMasterAttributeMapper.ID.getFieldName()), searchCriteria.get(OurAdvocateMasterAttributeMapper.ID)==null?QueryObjectModelConstants.JCR_OPERATOR_GREATER_THAN_OR_EQUAL_TO:QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(searchCriteria.get(OurAdvocateMasterAttributeMapper.ID)==null?0:Integer.valueOf(searchCriteria.get(OurAdvocateMasterAttributeMapper.ID).get(0))))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), OurAdvocateMasterAttributeMapper.ADV_NAME.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(OurAdvocateMasterAttributeMapper.ADV_NAME)==null?"%":searchCriteria.get(OurAdvocateMasterAttributeMapper.ADV_NAME).get(0))))
												),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), OurAdvocateMasterAttributeMapper.ADDRESS_1.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(OurAdvocateMasterAttributeMapper.ADDRESS_1)==null?"%":searchCriteria.get(OurAdvocateMasterAttributeMapper.ADDRESS_1).get(0))))
										),
										queryOMF.and(
												queryOMF.and(
														queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), OurAdvocateMasterAttributeMapper.ADDRESS_2.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(OurAdvocateMasterAttributeMapper.ADDRESS_2)==null?"%":searchCriteria.get(OurAdvocateMasterAttributeMapper.ADDRESS_2).get(0)))),
														queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), OurAdvocateMasterAttributeMapper.CITY_PIN.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(OurAdvocateMasterAttributeMapper.CITY_PIN)==null?"%":searchCriteria.get(OurAdvocateMasterAttributeMapper.CITY_PIN).get(0))))
														),
														queryOMF.and(
														queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), OurAdvocateMasterAttributeMapper.CONTACT_PHONE.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(OurAdvocateMasterAttributeMapper.CONTACT_PHONE)==null?"%":searchCriteria.get(OurAdvocateMasterAttributeMapper.CONTACT_PHONE).get(0)))),
														queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), OurAdvocateMasterAttributeMapper.EMAIL.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(OurAdvocateMasterAttributeMapper.EMAIL)==null?"%":searchCriteria.get(OurAdvocateMasterAttributeMapper.EMAIL).get(0))))
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
	protected LFIMSQueryParameters<OurAdvocateMaster> getDeleteQueryParameters(final List<Integer> searchCriteria) throws LFIMSServiceException {
		final Selector selector;
		final LFIMSQueryConstraint<OurAdvocateMaster> constraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<OurAdvocateMaster> childNode=getRootNodeStructureDetails().getNodeType();

		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			constraint=new LFIMSQueryConstraint<OurAdvocateMaster>(){

				public List<Constraint> getConstraints() throws LFIMSServiceException {

					List<Constraint> constraints=new ArrayList<Constraint>();
					try {

						Iterator<Integer> valueIterator=searchCriteria.iterator();
						while(valueIterator.hasNext()){

							int value=valueIterator.next();
							constraints.add(queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), OurAdvocateMasterAttributeMapper.ID.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(value))));
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

	private LFIMSQueryParameters<OurAdvocateMaster> getParameters(final Selector selector,final LFIMSQueryConstraint<OurAdvocateMaster> constraint, final Ordering[] ordering, final Column[] columns){

		return new LFIMSQueryParameters<OurAdvocateMaster>(){
			public Selector getSelector() { 
				return selector;
			}
			public LFIMSQueryConstraint<OurAdvocateMaster> getConstraint() {
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
	protected LFIMSObject<OurAdvocateMaster>  createNewRecord()	throws LFIMSServiceException {
		LFIMSObject<OurAdvocateMaster> ourAdvocateMasterRecord=new MutableOurAdvocateMasterImpl();
		return ourAdvocateMasterRecord;
	}

	
static{
	
	
	 nodeStructure=new LFIMSNodeStructure<OurAdvocateMaster>(){

		public LFIMSNode<OurAdvocateMaster> getNodeType() {
			return new LFIMSNode<OurAdvocateMaster>(){
				public String getNodeName() {
					return "OurAdvocateDetails";
				}
				public String getNodeType() {
					return "lfims:OurAdvocateDetails";
				}
				public LFIMSNodeKey<OurAdvocateMaster> getNodeKey() {
					return new LFIMSNodeKey<OurAdvocateMaster>(){
						public String getKeyName() {
							return "lfims:id";
						}
						public int getKeyType() {
							return PropertyType.DECIMAL;
						}
						public LFIMSAttributeMapper<OurAdvocateMaster> getSearchCriteria() {
							return OurAdvocateMasterAttributeMapper.ID;
						}
					};
				}
			};
		}

		public LFIMSNodeStructure<OurAdvocateMaster> getTopNodeStructure() {
			return new LFIMSNodeStructure<OurAdvocateMaster>(){

				public LFIMSNode<OurAdvocateMaster> getNodeType() {
					// TODO Auto-generated method stub
					return new LFIMSNode<OurAdvocateMaster>(){
						public String getNodeName() {
							return "OurAdvocateDetailsList";
						}
						public String getNodeType() {
							return "lfims:OurAdvocateDetailsList";
						}
						public LFIMSNodeKey<OurAdvocateMaster> getNodeKey() {
							return new LFIMSNodeKey<OurAdvocateMaster>(){
								public String getKeyName() {
									return "lfims:maxid";
								}
								public int getKeyType() {
									return PropertyType.DECIMAL;
								}
								public LFIMSAttributeMapper<OurAdvocateMaster> getSearchCriteria() {
									return OurAdvocateMasterAttributeMapper.ID;
								}
							};
						}
					};
				}

				public LFIMSNodeStructure<OurAdvocateMaster> getTopNodeStructure() {
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
			return false;
		}
				
	};
}
	
}
