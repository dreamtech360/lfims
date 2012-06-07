package com.dreamtech360.lfims.model.service.impl.casemanagement;

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

import com.dreamtech360.lfims.model.api.casemanagement.DefendentDetails;
import com.dreamtech360.lfims.model.api.impl.casemanagement.DefendentDetailsImpl;
import com.dreamtech360.lfims.model.api.impl.casemanagement.MutableDefendentDetailsImpl;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSNode;
import com.dreamtech360.lfims.model.base.LFIMSNodeKey;
import com.dreamtech360.lfims.model.base.LFIMSNodeStructure;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.impl.casemanagement.DefendentDetailsAttributeMapper;
import com.dreamtech360.lfims.model.search.query.LFIMSDecimalValue;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryConstraint;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryParameters;
import com.dreamtech360.lfims.model.search.query.LFIMSStringValue;
import com.dreamtech360.lfims.model.service.base.LFIMSJCRSessionThreadLocal;
import com.dreamtech360.lfims.model.service.base.LFIMSModelJCRService;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class CaseDefendentDetailsService extends LFIMSModelJCRService<DefendentDetails>
{
	
	private static LFIMSNodeStructure<DefendentDetails> nodeStructure=null;
	
	public CaseDefendentDetailsService(Repository repository){
		this.repository=repository;
	}

	public CaseDefendentDetailsService(Repository repository,LFIMSTransactionManagementService transactionManagerService){
		this.repository=repository;
		this.transactionManager=transactionManagerService.getTransactionManager();
	}
	@Override
	protected void update(Node node, LFIMSObject<DefendentDetails> record) throws LFIMSServiceException {

		DefendentDetails defendentDetailsRecord=(DefendentDetails)record;
		try{
			populateValue(node,defendentDetailsRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}

	@Override
	protected Node store(Node rootNode, LFIMSObject<DefendentDetails> record) throws LFIMSServiceException   {

		DefendentDetails defendentDetailsRecord=(DefendentDetails)record;
		Node defendentDetailsNode=null;
		try{
			defendentDetailsNode = rootNode.addNode("lfims:defendent");
			populateValue(defendentDetailsNode,defendentDetailsRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return defendentDetailsNode;
	} 

	private void populateValue(Node node, DefendentDetails record) throws LFIMSServiceException{
		try{

			node.setProperty("lfims:id", record.getId());
			node.setProperty("lfims:defendentName", record.getDefendentName());
			node.setProperty("lfims:status", record.getStatus());
			node.setProperty("lfims:deadOrAlive", record.isDeadOrAlive());
			node.setProperty("lfims:address1", record.getAddress1());
			node.setProperty("lfims:address2", record.getAddress2());
			node.setProperty("lfims:address3", record.getAddress3());

		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}

	}

	@Override
	protected LFIMSObject<DefendentDetails> convertNodeToObject(Node node, boolean readOnly) throws LFIMSServiceException {
		LFIMSObject<DefendentDetails> defendentDetailsRecord=null;

		try {
			if(readOnly==true){
				defendentDetailsRecord=new DefendentDetailsImpl(
						Integer.parseInt(node.getProperty("lfims:id").getString()),
						node.getProperty("lfims:defendentName").getString(),
						Integer.parseInt(node.getProperty("lfims:status").getString()),
						Boolean.parseBoolean(node.getProperty("lfims:deadOrAlive").getString()),
						node.getProperty("lfims:address1").getString(),
						node.getProperty("lfims:address2").getString(),
						node.getProperty("lfims:address3").getString());
			}
			else{
				defendentDetailsRecord=new MutableDefendentDetailsImpl(
						Integer.parseInt(node.getProperty("lfims:id").getString()),
						node.getProperty("lfims:defendentName").getString(),
						Integer.parseInt(node.getProperty("lfims:status").getString()),
						Boolean.parseBoolean(node.getProperty("lfims:deadOrAlive").getString()),
						node.getProperty("lfims:address1").getString(),
						node.getProperty("lfims:address2").getString(),
						node.getProperty("lfims:address3").getString());
			}

		} catch (Exception e) {
			throw new LFIMSServiceException(e);
		}

		return defendentDetailsRecord;
	}
	

	@Override
	protected LFIMSNodeStructure<DefendentDetails> getRootNodeStructureDetails() {
		return nodeStructure;
	}


	@Override
	protected LFIMSQueryParameters<DefendentDetails> getSearchQueryParameters(final Map<LFIMSAttributeMapper<DefendentDetails>, List<String>> searchCriteria) throws LFIMSServiceException {

		final Selector selector;
		final LFIMSQueryConstraint<DefendentDetails> queryConstraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<DefendentDetails> childNode=getRootNodeStructureDetails().getNodeType();
		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			queryConstraint=new LFIMSQueryConstraint<DefendentDetails>(){
				public List<Constraint> getConstraints() throws LFIMSServiceException {
					List<Constraint> constraints=new ArrayList<Constraint>();
					try {
						Constraint constraint=queryOMF.and(

							
								
								queryOMF.and(
										queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), DefendentDetailsAttributeMapper.ID.getFieldName()), searchCriteria.get(DefendentDetailsAttributeMapper.ID)==null?QueryObjectModelConstants.JCR_OPERATOR_GREATER_THAN_OR_EQUAL_TO:QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(searchCriteria.get(DefendentDetailsAttributeMapper.ID)==null?0:Integer.valueOf(searchCriteria.get(DefendentDetailsAttributeMapper.ID).get(0))))),
										queryOMF.and(
										queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), DefendentDetailsAttributeMapper.DEFENDENT_NAME.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(DefendentDetailsAttributeMapper.DEFENDENT_NAME)==null?"%":searchCriteria.get(DefendentDetailsAttributeMapper.DEFENDENT_NAME).get(0)))),
										queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), DefendentDetailsAttributeMapper.STATUS.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(DefendentDetailsAttributeMapper.STATUS)==null?"%":searchCriteria.get(DefendentDetailsAttributeMapper.STATUS).get(0))))
										)
										),
										queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), DefendentDetailsAttributeMapper.DEAD_OR_ALIVE.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(DefendentDetailsAttributeMapper.DEAD_OR_ALIVE)==null?"%":searchCriteria.get(DefendentDetailsAttributeMapper.DEAD_OR_ALIVE).get(0)))),
												queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), DefendentDetailsAttributeMapper.ADDRESS1.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(DefendentDetailsAttributeMapper.ADDRESS1)==null?"%":searchCriteria.get(DefendentDetailsAttributeMapper.ADDRESS1).get(0)))),
												queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), DefendentDetailsAttributeMapper.ADDRESS2.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(DefendentDetailsAttributeMapper.ADDRESS2)==null?"%":searchCriteria.get(DefendentDetailsAttributeMapper.ADDRESS2).get(0)))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), DefendentDetailsAttributeMapper.ADDRESS3.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(DefendentDetailsAttributeMapper.ADDRESS3)==null?"%":searchCriteria.get(DefendentDetailsAttributeMapper.ADDRESS3).get(0))))
												)
												)
												
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
	protected LFIMSQueryParameters<DefendentDetails> getDeleteQueryParameters(final List<Integer> searchCriteria) throws LFIMSServiceException {
		final Selector selector;
		final LFIMSQueryConstraint<DefendentDetails> constraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<DefendentDetails> childNode=getRootNodeStructureDetails().getNodeType();

		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			constraint=new LFIMSQueryConstraint<DefendentDetails>(){

				public List<Constraint> getConstraints() throws LFIMSServiceException {

					List<Constraint> constraints=new ArrayList<Constraint>();
					try {

						Iterator<Integer> valueIterator=searchCriteria.iterator();
						while(valueIterator.hasNext()){

							int value=valueIterator.next();
							constraints.add(queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), DefendentDetailsAttributeMapper.ID.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(value))));
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

	private LFIMSQueryParameters<DefendentDetails> getParameters(final Selector selector,final LFIMSQueryConstraint<DefendentDetails> constraint, final Ordering[] ordering, final Column[] columns){

		return new LFIMSQueryParameters<DefendentDetails>(){
			public Selector getSelector() {
				return selector;
			}
			public LFIMSQueryConstraint<DefendentDetails> getConstraint() {
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
	protected LFIMSObject<DefendentDetails>  createNewRecord()	throws LFIMSServiceException {
		LFIMSObject<DefendentDetails> defendentDetailsRecord=new MutableDefendentDetailsImpl();
		return defendentDetailsRecord;
	}


static{
	
	  nodeStructure=new LFIMSNodeStructure<DefendentDetails>(){

		public LFIMSNode<DefendentDetails> getNodeType() {

			return new LFIMSNode<DefendentDetails>(){
				public String getNodeName() {
					return "Defendent";
				}
				public String getNodeType() {
					return "lfims:Defendent";
				}
				public LFIMSNodeKey<DefendentDetails> getNodeKey() {
					return new LFIMSNodeKey<DefendentDetails>(){
						public String getKeyName() {
							return "lfims:id";
						}
						public int getKeyType() {
							return PropertyType.DECIMAL;
						}
						public LFIMSAttributeMapper<DefendentDetails> getSearchCriteria() {
							return DefendentDetailsAttributeMapper.ID;
						}
					};
				}
			};
		}

		public LFIMSNodeStructure<DefendentDetails> getTopNodeStructure() {

			return new LFIMSNodeStructure<DefendentDetails>(){

				public LFIMSNode<DefendentDetails> getNodeType() {

					return new LFIMSNode<DefendentDetails>(){
						public String getNodeName() {
							return "DefendentList";
						}
						public String getNodeType() {
							return "lfims:DefendentList";
						}
						public LFIMSNodeKey<DefendentDetails> getNodeKey() {
							return new LFIMSNodeKey<DefendentDetails>(){
								public String getKeyName() {
									return "lfims:maxid";
								}
								public int getKeyType() {
									return PropertyType.DECIMAL;
								}
								public LFIMSAttributeMapper<DefendentDetails> getSearchCriteria() {
									return DefendentDetailsAttributeMapper.ID;
								}
							};
						}
					};
				}

				public LFIMSNodeStructure<DefendentDetails> getTopNodeStructure() {
					return new LFIMSNodeStructure<DefendentDetails>(){

						public LFIMSNode<DefendentDetails> getNodeType() {

							return new LFIMSNode<DefendentDetails>(){
								public String getNodeName() {
									return "CaseDetails";
								}
								public String getNodeType() {
									return "lfims:CaseDetails";
								}
								public LFIMSNodeKey<DefendentDetails> getNodeKey() {
									return null;
								}
							};
						}

						public LFIMSNodeStructure<DefendentDetails> getTopNodeStructure() {

							return new LFIMSNodeStructure<DefendentDetails>(){

								public LFIMSNode<DefendentDetails> getNodeType() {

									return new LFIMSNode<DefendentDetails>(){
										public String getNodeName() {
											return "Case";
										}
										public String getNodeType() {
											return "lfims:Case";
										}
										public LFIMSNodeKey<DefendentDetails> getNodeKey() {
											return new LFIMSNodeKey<DefendentDetails>(){
												public String getKeyName() {
													return "lfims:id";
												}
												public int getKeyType() {
													return PropertyType.DECIMAL;
												}
												public LFIMSAttributeMapper<DefendentDetails> getSearchCriteria() {
													return DefendentDetailsAttributeMapper.ID;
												}
											};
										}
									};
								}

								public LFIMSNodeStructure<DefendentDetails> getTopNodeStructure() {

									return new LFIMSNodeStructure<DefendentDetails>(){

										public LFIMSNode<DefendentDetails> getNodeType() {

											return new LFIMSNode<DefendentDetails>(){
												public String getNodeName() {
													return "CaseList";
												}
												public String getNodeType() {
													return "lfims:CaseList";
												}
												public LFIMSNodeKey<DefendentDetails> getNodeKey() {
													return new LFIMSNodeKey<DefendentDetails>(){
														public String getKeyName() {
															return "lfims:maxid";
														}
														public int getKeyType() {
															return PropertyType.DECIMAL;
														}
														public LFIMSAttributeMapper<DefendentDetails> getSearchCriteria() {
															return DefendentDetailsAttributeMapper.ID;
														}
													};
												}
											};
										}

										public LFIMSNodeStructure<DefendentDetails> getTopNodeStructure() {

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
								
								public String getCompositKeyIdentifier() {
									return "lfims:uuid";
								}

							};
						}

						@Override
						public boolean isNodeTypeCollection() {
							return true;
						}

					};
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
		
		public String getCompositKeyIdentifier() {
			return "lfims:uuid";
		}

	};
}



}
