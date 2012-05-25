package com.dreamtech360.lfims.model.service.impl.casemanagement;

import java.util.ArrayList;
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
import com.dreamtech360.lfims.model.api.casemanagement.CaseDiary;
import com.dreamtech360.lfims.model.api.casemanagement.DefendentDetails;
import com.dreamtech360.lfims.model.api.impl.casemanagement.CaseDiaryImpl;
import com.dreamtech360.lfims.model.api.impl.casemanagement.MutableCaseDiaryImpl;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSNode;
import com.dreamtech360.lfims.model.base.LFIMSNodeKey;
import com.dreamtech360.lfims.model.base.LFIMSNodeStructure;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.impl.casemanagement.CaseDiaryAttributeMapper;
import com.dreamtech360.lfims.model.search.query.LFIMSDecimalValue;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryConstraint;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryParameters;
import com.dreamtech360.lfims.model.search.query.LFIMSStringValue;
import com.dreamtech360.lfims.model.service.base.LFIMSJCRSessionThreadLocal;
import com.dreamtech360.lfims.model.service.base.LFIMSModelJCRService;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;

public class CaseDiaryService extends LFIMSModelJCRService<CaseDiary>
{
	private static LFIMSNodeStructure<CaseDiary> nodeStructure=null;
	public CaseDiaryService(Repository repository){
		this.repository=repository;
	}

	@Override
	protected void update(Node node, LFIMSObject<CaseDiary> record) throws LFIMSServiceException {

		CaseDiary caseDiaryRecord=(CaseDiary)record;
		try{
			populateValue(node,caseDiaryRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}

	@Override
	protected Node store(Node rootNode, LFIMSObject<CaseDiary> record) throws LFIMSServiceException   {

		CaseDiary caseDiaryRecord=(CaseDiary)record;
		Node caseDiaryNode=null;
		try{
			caseDiaryNode = rootNode.addNode("lfims:diary");
			populateValue(caseDiaryNode,caseDiaryRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return caseDiaryNode;
	} 

	private void populateValue(Node node, CaseDiary record) throws LFIMSServiceException{
		try{
	
			node.setProperty("lfims:id", record.getId());
			node.setProperty("lfims:hearingDate", record.getHearingDate().toString());
			node.setProperty("lfims:beforeBenchName", record.getBeforeBenchName());
			node.setProperty("lfims:ourAdvocateName", record.getOurAdvocateName());
			node.setProperty("lfims:advocateName", record.getAdvocateName());
			node.setProperty("lfims:ourWitnessDetails", record.getOurWitnessDetails());
			node.setProperty("lfims:othersWitnessDetails", record.getOthersWitnessDetails());
			node.setProperty("lfims:ourDocuments", record.getOurDocuments());
			node.setProperty("lfims:othersDocuments", record.getOthersDocuments());
			node.setProperty("lfims:caseCompletionMethod", record.getCaseCompletionMethod());
			node.setProperty("lfims:otherDetails", record.getOtherDetails());
			node.setProperty("lfims:quicky", record.getQuicky());
			node.setProperty("lfims:nextHearingDate", record.getNextHearingDate().toString());
			node.setProperty("lfims:nextDatePurpose", record.getNextDatePurpose().toString());
			
						
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}

	}

	@Override
	protected LFIMSObject<CaseDiary> convertNodeToObject(Node node, boolean readOnly) throws LFIMSServiceException {
		LFIMSObject<CaseDiary> caseDiaryRecord=null;
	
			try {
				if(readOnly==true){
					caseDiaryRecord=new CaseDiaryImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							new Date(node.getProperty("lfims:hearingDate").getString()),
							node.getProperty("lfims:beforeBenchName").getString(),
							node.getProperty("lfims:ourAdvocateName").getString(),
							node.getProperty("lfims:advocateName").getString(),
							node.getProperty("lfims:ourWitnessDetails").getString(),
							node.getProperty("lfims:othersWitnessDetails").getString(),
							node.getProperty("lfims:ourDocuments").getString(),
							node.getProperty("lfims:othersDocuments").getString(),
							node.getProperty("lfims:caseCompletionMethod").getString(),
							node.getProperty("lfims:otherDetails").getString(),
							Integer.parseInt(node.getProperty("lfims:quicky").getString()),
							new Date(node.getProperty("lfims:nextHearingDate").getString()),
							new Date(node.getProperty("lfims:nextDatePurpose").getString()));
						}
				else{
					caseDiaryRecord=new MutableCaseDiaryImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							new Date(node.getProperty("lfims:hearingDate").getString()),
							node.getProperty("lfims:beforeBenchName").getString(),
							node.getProperty("lfims:ourAdvocateName").getString(),
							node.getProperty("lfims:advocateName").getString(),
							node.getProperty("lfims:ourWitnessDetails").getString(),
							node.getProperty("lfims:othersWitnessDetails").getString(),
							node.getProperty("lfims:ourDocuments").getString(),
							node.getProperty("lfims:othersDocuments").getString(),
							node.getProperty("lfims:caseCompletionMethod").getString(),
							node.getProperty("lfims:otherDetails").getString(),
							Integer.parseInt(node.getProperty("lfims:quicky").getString()),
							new Date(node.getProperty("lfims:nextHearingDate").getString()),
							new Date(node.getProperty("lfims:nextDatePurpose").getString()));
				}
				
			} catch (Exception e) {
				throw new LFIMSServiceException(e);
			}
		
		return caseDiaryRecord;
	}
	
	@Override
	protected LFIMSNodeStructure<CaseDiary> getRootNodeStructureDetails() {
		return nodeStructure;
	}
	
	
	@Override
	protected LFIMSQueryParameters<CaseDiary> getSearchQueryParameters(final Map<LFIMSAttributeMapper<CaseDiary>, List<String>> searchCriteria) throws LFIMSServiceException {

		final Selector selector;
		final LFIMSQueryConstraint<CaseDiary> queryConstraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<CaseDiary> childNode=getRootNodeStructureDetails().getNodeType();
		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			queryConstraint=new LFIMSQueryConstraint<CaseDiary>(){
				public List<Constraint> getConstraints() throws LFIMSServiceException {
					List<Constraint> constraints=new ArrayList<Constraint>();
					try {
						Constraint constraint=queryOMF.and(
										queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CaseDiaryAttributeMapper.ID.getFieldName()), searchCriteria.get(CaseDiaryAttributeMapper.ID)==null?QueryObjectModelConstants.JCR_OPERATOR_GREATER_THAN_OR_EQUAL_TO:QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(searchCriteria.get(CaseDiaryAttributeMapper.ID)==null?0:Integer.valueOf(searchCriteria.get(CaseDiaryAttributeMapper.ID).get(0))))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CaseDiaryAttributeMapper.HEARING_DATE.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(CaseDiaryAttributeMapper.HEARING_DATE)==null?"%":searchCriteria.get(CaseDiaryAttributeMapper.HEARING_DATE).get(0))))
												),
												queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CaseDiaryAttributeMapper.NEXT_HEARING_DATE.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(CaseDiaryAttributeMapper.NEXT_HEARING_DATE)==null?"%":searchCriteria.get(CaseDiaryAttributeMapper.NEXT_HEARING_DATE).get(0)))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CaseDiaryAttributeMapper.NEXT_DATE_PURPOSE.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(CaseDiaryAttributeMapper.NEXT_DATE_PURPOSE)==null?"%":searchCriteria.get(CaseDiaryAttributeMapper.NEXT_DATE_PURPOSE).get(0))))
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
	protected LFIMSQueryParameters<CaseDiary> getDeleteQueryParameters(final List<Integer> searchCriteria) throws LFIMSServiceException {
		final Selector selector;
		final LFIMSQueryConstraint<CaseDiary> constraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<CaseDiary> childNode=getRootNodeStructureDetails().getNodeType();

		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			constraint=new LFIMSQueryConstraint<CaseDiary>(){

				public List<Constraint> getConstraints() throws LFIMSServiceException {

					List<Constraint> constraints=new ArrayList<Constraint>();
					try {

						Iterator<Integer> valueIterator=searchCriteria.iterator();
						while(valueIterator.hasNext()){

							int value=valueIterator.next();
							constraints.add(queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), CaseDiaryAttributeMapper.ID.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(value))));
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

	private LFIMSQueryParameters<CaseDiary> getParameters(final Selector selector,final LFIMSQueryConstraint<CaseDiary> constraint, final Ordering[] ordering, final Column[] columns){

		return new LFIMSQueryParameters<CaseDiary>(){
			public Selector getSelector() {
				return selector;
			}
			public LFIMSQueryConstraint<CaseDiary> getConstraint() {
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
	protected LFIMSObject<CaseDiary>  createNewRecord()	throws LFIMSServiceException {
		LFIMSObject<CaseDiary> caseDiaryRecord=new MutableCaseDiaryImpl();
		return caseDiaryRecord;
	}

	

	static{
		
		nodeStructure=new LFIMSNodeStructure<CaseDiary>(){

			public LFIMSNode<CaseDiary> getNodeType() {

				return new LFIMSNode<CaseDiary>(){
					public String getNodeName() {
						return "Diary";
					}
					public String getNodeType() {
						return "lfims:Diary";
					}
					public LFIMSNodeKey<CaseDiary> getNodeKey() {
						return new LFIMSNodeKey<CaseDiary>(){
							public String getKeyName() {
								return "lfims:id";
							}
							public int getKeyType() {
								return PropertyType.DECIMAL;
							}
							public LFIMSAttributeMapper<CaseDiary> getSearchCriteria() {
								return CaseDiaryAttributeMapper.ID;
							}
						};
					}
				};
			}

			public LFIMSNodeStructure<CaseDiary> getTopNodeStructure() {

				return new LFIMSNodeStructure<CaseDiary>(){

					public LFIMSNode<CaseDiary> getNodeType() {

						return new LFIMSNode<CaseDiary>(){
							public String getNodeName() {
								return "DiaryList";
							}
							public String getNodeType() {
								return "lfims:DiaryList";
							}
							public LFIMSNodeKey<CaseDiary> getNodeKey() {
								return new LFIMSNodeKey<CaseDiary>(){
									public String getKeyName() {
										return "lfims:maxid";
									}
									public int getKeyType() {
										return PropertyType.DECIMAL;
									}
									public LFIMSAttributeMapper<CaseDiary> getSearchCriteria() {
										return CaseDiaryAttributeMapper.ID;
									}
								};
							}
						};
					}

					public LFIMSNodeStructure<CaseDiary> getTopNodeStructure() {
						return new LFIMSNodeStructure<CaseDiary>(){

							public LFIMSNode<CaseDiary> getNodeType() {

								return new LFIMSNode<CaseDiary>(){
									public String getNodeName() {
										return "CaseDetails";
									}
									public String getNodeType() {
										return "lfims:CaseDetails";
									}
									public LFIMSNodeKey<CaseDiary> getNodeKey() {
										return null;
									}
								};
							}

							public LFIMSNodeStructure<CaseDiary> getTopNodeStructure() {

								return new LFIMSNodeStructure<CaseDiary>(){

									public LFIMSNode<CaseDiary> getNodeType() {

										return new LFIMSNode<CaseDiary>(){
											public String getNodeName() {
												return "Case";
											}
											public String getNodeType() {
												return "lfims:Case";
											}
											public LFIMSNodeKey<CaseDiary> getNodeKey() {
												return new LFIMSNodeKey<CaseDiary>(){
													public String getKeyName() {
														return "lfims:id";
													}
													public int getKeyType() {
														return PropertyType.DECIMAL;
													}
													public LFIMSAttributeMapper<CaseDiary> getSearchCriteria() {
														return CaseDiaryAttributeMapper.ID;
													}
												};
											}
										};
									}

									public LFIMSNodeStructure<CaseDiary> getTopNodeStructure() {

										return new LFIMSNodeStructure<CaseDiary>(){

											public LFIMSNode<CaseDiary> getNodeType() {

												return new LFIMSNode<CaseDiary>(){
													public String getNodeName() {
														return "CaseList";
													}
													public String getNodeType() {
														return "lfims:CaseList";
													}
													public LFIMSNodeKey<CaseDiary> getNodeKey() {
														return new LFIMSNodeKey<CaseDiary>(){
															public String getKeyName() {
																return "lfims:maxid";
															}
															public int getKeyType() {
																return PropertyType.DECIMAL;
															}
															public LFIMSAttributeMapper<CaseDiary> getSearchCriteria() {
																return CaseDiaryAttributeMapper.ID;
															}
														};
													}
												};
											}

											public LFIMSNodeStructure<CaseDiary> getTopNodeStructure() {

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
									public String getCompositKeyIdentifier() {
										return "lfims:uuid";
									}

								};
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
						return true;
					}

				};
			}

			@Override
			public boolean isNodeTypeCollection() {
				// TODO Auto-generated method stub
				return false;
			}
			public String getCompositKeyIdentifier() {
				return "lfims:uuid";
			}

		};

		
	}
	
	
	
	
}
