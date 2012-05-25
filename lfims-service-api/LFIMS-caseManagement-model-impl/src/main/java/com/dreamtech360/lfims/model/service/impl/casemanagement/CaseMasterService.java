package com.dreamtech360.lfims.model.service.impl.casemanagement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import org.apache.jackrabbit.value.DateValue;

import ucar.unidata.util.DateUtil;

import com.dreamtech360.lfims.model.api.casemanagement.CaseMaster;
import com.dreamtech360.lfims.model.api.casemanagement.DefendentDetails;
import com.dreamtech360.lfims.model.api.impl.casemanagement.CaseMasterImpl;
import com.dreamtech360.lfims.model.api.impl.casemanagement.MutableCaseMasterImpl;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSNode;
import com.dreamtech360.lfims.model.base.LFIMSNodeKey;
import com.dreamtech360.lfims.model.base.LFIMSNodeStructure;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.impl.casemanagement.CaseMasterAttributeMapper;
import com.dreamtech360.lfims.model.search.query.LFIMSDateValue;
import com.dreamtech360.lfims.model.search.query.LFIMSDecimalValue;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryConstraint;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryParameters;
import com.dreamtech360.lfims.model.search.query.LFIMSStringValue;
import com.dreamtech360.lfims.model.service.base.LFIMSJCRSessionThreadLocal;
import com.dreamtech360.lfims.model.service.base.LFIMSModelJCRService;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;
 
public class CaseMasterService extends LFIMSModelJCRService<CaseMaster>
{
	private static LFIMSNodeStructure<CaseMaster> nodeStructure=null;
	public CaseMasterService(Repository repository){
		this.repository=repository;
	}

	@Override
	protected void update(Node node, LFIMSObject<CaseMaster> record) throws LFIMSServiceException {

		CaseMaster caseMasterRecord=(CaseMaster)record;
		try{
			populateValue(node,caseMasterRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}

	@Override
	protected Node store(Node rootNode, LFIMSObject<CaseMaster> record) throws LFIMSServiceException   {

		CaseMaster caseMasterRecord=(CaseMaster)record;
		Node caseMasterNode=null;
		try{
			caseMasterNode = rootNode.addNode("lfims:case");
			populateValue(caseMasterNode,caseMasterRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return caseMasterNode;
	} 

	private void populateValue(Node node, CaseMaster record) throws LFIMSServiceException{
		try{
			
			
			node.setProperty("lfims:id", record.getId());
			node.setProperty("lfims:respondentName", record.getRespondentName());
			node.setProperty("lfims:caseNo", record.getCaseNo());
			node.setProperty("lfims:rpmaNo", record.getRpmaNo());
			node.setProperty("lfims:bankName", record.getBankName());
			node.setProperty("lfims:branchName", record.getBranchName());
			node.setProperty("lfims:courtName", record.getCourtName());
			node.setProperty("lfims:limitDate", new LFIMSDateValue(record.getLimitDate()));
			node.setProperty("lfims:caseNature", record.getCaseNature());
			node.setProperty("lfims:caseAmount", record.getCaseAmount());
			node.setProperty("lfims:filingDate", new LFIMSDateValue(record.getFilingDate()));
			node.setProperty("lfims:judgementDate", new LFIMSDateValue(record.getJudgementDate()));
			node.setProperty("lfims:certificateDate", new LFIMSDateValue(record.getCertificateDate()));
			node.setProperty("lfims:closingDate", new LFIMSDateValue(record.getClosingDate()));
			node.setProperty("lfims:oldCourtName", record.getOldCourtName());
			node.setProperty("lfims:oldCourtNumber", record.getOldCourtNumber());
			node.setProperty("lfims:casePosition", record.getCasePosition());
			node.setProperty("lfims:caseForum", record.getCaseForum());
			node.setProperty("lfims:otherAdvocateName", record.getOtherAdvocateName());
			node.setProperty("lfims:otherAdvocatePhone", record.getOtherAdvocatePhone());
			node.setProperty("lfims:specialOfficerAppointed", record.isSpecialOfficerAppointed());
			node.setProperty("lfims:caseTransferDate", new LFIMSDateValue(record.getCaseTransferDate()));
						
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}

	}

	@Override
	protected LFIMSObject<CaseMaster> convertNodeToObject(Node node, boolean readOnly) throws LFIMSServiceException {
		LFIMSObject<CaseMaster> caseMasterRecord=null;
		
		
			try {
				if(readOnly==true){
					caseMasterRecord=new CaseMasterImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:respondentName").getString(),
							node.getProperty("lfims:caseNo").getString(),
							node.getProperty("lfims:rpmaNo").getString(),
							node.getProperty("lfims:bankName").getString(),
							node.getProperty("lfims:branchName").getString(),
							node.getProperty("lfims:courtName").getString(),
							new Date(node.getProperty("lfims:limitDate").getDate().getTimeInMillis()),
							node.getProperty("lfims:caseNature").getString(),
							Double.parseDouble(node.getProperty("lfims:caseAmount").getString()),
							new Date(node.getProperty("lfims:filingDate").getDate().getTimeInMillis()),
							new Date(node.getProperty("lfims:judgementDate").getDate().getTimeInMillis()),
							new Date(node.getProperty("lfims:certificateDate").getDate().getTimeInMillis()),
							new Date(node.getProperty("lfims:closingDate").getDate().getTimeInMillis()),
							node.getProperty("lfims:oldCourtName").getString(),
							node.getProperty("lfims:oldCourtNumber").getString(),
							node.getProperty("lfims:casePosition").getString(),
							node.getProperty("lfims:caseForum").getString(),
							node.getProperty("lfims:otherAdvocateName").getString(),
							node.getProperty("lfims:otherAdvocatePhone").getString(),
							Boolean.getBoolean(node.getProperty("lfims:specialOfficerAppointed").getString()),
							new Date(node.getProperty("lfims:caseTransferDate").getDate().getTimeInMillis()));
						}
				else{
					caseMasterRecord=new MutableCaseMasterImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:respondentName").getString(),
							node.getProperty("lfims:caseNo").getString(),
							node.getProperty("lfims:rpmaNo").getString(),
							node.getProperty("lfims:bankName").getString(),
							node.getProperty("lfims:branchName").getString(),
							node.getProperty("lfims:courtName").getString(),
							new Date(node.getProperty("lfims:limitDate").getDate().getTimeInMillis()),
							node.getProperty("lfims:caseNature").getString(),
							Double.parseDouble(node.getProperty("lfims:caseAmount").getString()),
							new Date(node.getProperty("lfims:filingDate").getDate().getTimeInMillis()),
							new Date(node.getProperty("lfims:judgementDate").getDate().getTimeInMillis()),
							new Date(node.getProperty("lfims:certificateDate").getDate().getTimeInMillis()),
							new Date(node.getProperty("lfims:closingDate").getDate().getTimeInMillis()),
							node.getProperty("lfims:oldCourtName").getString(),
							node.getProperty("lfims:oldCourtNumber").getString(),
							node.getProperty("lfims:casePosition").getString(),
							node.getProperty("lfims:caseForum").getString(),
							node.getProperty("lfims:otherAdvocateName").getString(),
							node.getProperty("lfims:otherAdvocatePhone").getString(),
							Boolean.getBoolean(node.getProperty("lfims:specialOfficerAppointed").getString()),
							new Date(node.getProperty("lfims:caseTransferDate").getDate().getTimeInMillis()));
				}
				
			} catch (Exception e) {
				throw new LFIMSServiceException(e);
			}
		
		return caseMasterRecord;
	}
		
	@Override
	protected LFIMSNodeStructure<CaseMaster> getRootNodeStructureDetails() {
		return nodeStructure;
	}
	

	@Override
	protected LFIMSQueryParameters<CaseMaster> getSearchQueryParameters(final Map<LFIMSAttributeMapper<CaseMaster>, List<String>> searchCriteria) throws LFIMSServiceException {

		final Selector selector;
		final LFIMSQueryConstraint<CaseMaster> queryConstraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<CaseMaster> childNode=getRootNodeStructureDetails().getNodeType();
		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			queryConstraint=new LFIMSQueryConstraint<CaseMaster>(){
				public List<Constraint> getConstraints() throws LFIMSServiceException {
					List<Constraint> constraints=new ArrayList<Constraint>();
					try {
						Constraint constraint=queryOMF.and(
								
										queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CaseMasterAttributeMapper.ID.getFieldName()), searchCriteria.get(CaseMasterAttributeMapper.ID)==null?QueryObjectModelConstants.JCR_OPERATOR_GREATER_THAN_OR_EQUAL_TO:QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(searchCriteria.get(CaseMasterAttributeMapper.ID)==null?0:Integer.valueOf(searchCriteria.get(CaseMasterAttributeMapper.ID).get(0))))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CaseMasterAttributeMapper.RESPONDENT_NAME.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(CaseMasterAttributeMapper.RESPONDENT_NAME)==null?"%":searchCriteria.get(CaseMasterAttributeMapper.RESPONDENT_NAME).get(0))))
												),
												queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CaseMasterAttributeMapper.CASE_NO.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(CaseMasterAttributeMapper.CASE_NO)==null?"%":searchCriteria.get(CaseMasterAttributeMapper.CASE_NO).get(0)))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CaseMasterAttributeMapper.RPMA_NO.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(CaseMasterAttributeMapper.RPMA_NO)==null?"%":searchCriteria.get(CaseMasterAttributeMapper.RPMA_NO).get(0))))
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
	protected LFIMSQueryParameters<CaseMaster> getDeleteQueryParameters(final List<Integer> searchCriteria) throws LFIMSServiceException {
		final Selector selector;
		final LFIMSQueryConstraint<CaseMaster> constraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<CaseMaster> childNode=getRootNodeStructureDetails().getNodeType();

		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			constraint=new LFIMSQueryConstraint<CaseMaster>(){

				public List<Constraint> getConstraints() throws LFIMSServiceException {

					List<Constraint> constraints=new ArrayList<Constraint>();
					try {

						Iterator<Integer> valueIterator=searchCriteria.iterator();
						while(valueIterator.hasNext()){

							int value=valueIterator.next();
							constraints.add(queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CaseMasterAttributeMapper.ID.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(value))));
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

	private LFIMSQueryParameters<CaseMaster> getParameters(final Selector selector,final LFIMSQueryConstraint<CaseMaster> constraint, final Ordering[] ordering, final Column[] columns){

		return new LFIMSQueryParameters<CaseMaster>(){
			public Selector getSelector() {
				return selector;
			}
			public LFIMSQueryConstraint<CaseMaster> getConstraint() {
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
	protected LFIMSObject<CaseMaster>  createNewRecord()	throws LFIMSServiceException {
		LFIMSObject<CaseMaster> caseMasterRecord=new MutableCaseMasterImpl();
		return caseMasterRecord;
	}

	static{
		
		
		nodeStructure=new LFIMSNodeStructure<CaseMaster>(){

			public LFIMSNode<CaseMaster> getNodeType() {
			
				return new LFIMSNode<CaseMaster>(){
					public String getNodeName() {
						return "Case";
					}
					public String getNodeType() {
						return "lfims:Case";
					}
				
					public LFIMSNodeKey<CaseMaster> getNodeKey() {
						return new LFIMSNodeKey<CaseMaster>(){
							public String getKeyName() {
								return "lfims:id";
							}
							public int getKeyType() {
								return PropertyType.DECIMAL;
							}
							public LFIMSAttributeMapper<CaseMaster> getSearchCriteria() {
								return CaseMasterAttributeMapper.ID;
							}
						};
					}
				};
			}

			public LFIMSNodeStructure<CaseMaster> getTopNodeStructure() {
				
				return new LFIMSNodeStructure<CaseMaster>(){

					public LFIMSNode<CaseMaster> getNodeType() {
					
						return new LFIMSNode<CaseMaster>(){
							public String getNodeName() {
								return "CaseList";
							}
							public String getNodeType() {
								return "lfims:CaseList";
							}
							public LFIMSNodeKey<CaseMaster> getNodeKey() {
								return new LFIMSNodeKey<CaseMaster>(){
									public String getKeyName() {
										return "lfims:maxid";
									}
									public int getKeyType() {
										return PropertyType.DECIMAL;
									}
									public LFIMSAttributeMapper<CaseMaster> getSearchCriteria() {
										return CaseMasterAttributeMapper.ID;
									}
								};
							}
						};
					}

					public LFIMSNodeStructure<CaseMaster> getTopNodeStructure() {
					
						return null;
					}

					@Override
					public boolean isNodeTypeCollection() {
						return true;
					}
					
				};
			}

			public String getCompositKeyIdentifier() {
				return "lfims:uuid";
			}

			@Override
			public boolean isNodeTypeCollection() {
				return false;
			}
			
		};
		
	}

	
	
	
	
	
}
