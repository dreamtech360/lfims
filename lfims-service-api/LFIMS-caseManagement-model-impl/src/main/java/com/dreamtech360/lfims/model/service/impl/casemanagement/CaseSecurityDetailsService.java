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
import com.dreamtech360.lfims.model.api.casemanagement.SecurityDetails;
import com.dreamtech360.lfims.model.api.impl.casemanagement.SecurityDetailsImpl;
import com.dreamtech360.lfims.model.api.impl.casemanagement.MutableSecurityDetailsImpl;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSNode;
import com.dreamtech360.lfims.model.base.LFIMSNodeKey;
import com.dreamtech360.lfims.model.base.LFIMSNodeStructure;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.impl.casemanagement.SecurityDetailsAttributeMapper;
import com.dreamtech360.lfims.model.search.query.LFIMSDecimalValue;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryConstraint;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryParameters;
import com.dreamtech360.lfims.model.search.query.LFIMSStringValue;
import com.dreamtech360.lfims.model.service.base.LFIMSJCRSessionThreadLocal;
import com.dreamtech360.lfims.model.service.base.LFIMSModelJCRService;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;

public class CaseSecurityDetailsService extends LFIMSModelJCRService<SecurityDetails>
{
	private static LFIMSNodeStructure<SecurityDetails> nodeStructure=null;
	public CaseSecurityDetailsService(Repository repository){
		this.repository=repository;
	}

	@Override
	protected void update(Node node, LFIMSObject<SecurityDetails> record) throws LFIMSServiceException {

		SecurityDetails securityDetailsRecord=(SecurityDetails)record;
		try{
			populateValue(node,securityDetailsRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}

	@Override
	protected Node store(Node rootNode, LFIMSObject<SecurityDetails> record) throws LFIMSServiceException   {

		SecurityDetails securityDetailsRecord=(SecurityDetails)record;
		Node securityDetailsNode=null;
		try{
			securityDetailsNode = rootNode.addNode("lfims:security");
			populateValue(securityDetailsNode,securityDetailsRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return securityDetailsNode;
	} 

	private void populateValue(Node node, SecurityDetails record) throws LFIMSServiceException{
		try{
		
			node.setProperty("lfims:id", record.getId());
			node.setProperty("lfims:securityDetails", record.getSecurityDetails());
			
						
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}

	}

	@Override
	protected LFIMSObject<SecurityDetails> convertNodeToObject(Node node, boolean readOnly) throws LFIMSServiceException {
		LFIMSObject<SecurityDetails> securityDetailsRecord=null;
	
			try {
				if(readOnly==true){
					securityDetailsRecord=new SecurityDetailsImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:securityDetails").getString());
						}
				else{
					securityDetailsRecord=new MutableSecurityDetailsImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:securityDetails").getString());
				}
				
			} catch (Exception e) {
				throw new LFIMSServiceException(e);
			}
		
		return securityDetailsRecord;
	}

	
	@Override
	protected LFIMSNodeStructure<SecurityDetails> getRootNodeStructureDetails() {
		return nodeStructure;
	}

	@Override
	protected LFIMSQueryParameters<SecurityDetails> getSearchQueryParameters(final Map<LFIMSAttributeMapper<SecurityDetails>, List<String>> searchCriteria) throws LFIMSServiceException {

		final Selector selector;
		final LFIMSQueryConstraint<SecurityDetails> queryConstraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<SecurityDetails> childNode=getRootNodeStructureDetails().getNodeType();
		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			queryConstraint=new LFIMSQueryConstraint<SecurityDetails>(){
				public List<Constraint> getConstraints() throws LFIMSServiceException {
					List<Constraint> constraints=new ArrayList<Constraint>();
					try {
						Constraint constraint=queryOMF.and(
								
									
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), SecurityDetailsAttributeMapper.ID.getFieldName()), searchCriteria.get(SecurityDetailsAttributeMapper.ID)==null?QueryObjectModelConstants.JCR_OPERATOR_GREATER_THAN_OR_EQUAL_TO:QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(searchCriteria.get(SecurityDetailsAttributeMapper.ID)==null?0:Integer.valueOf(searchCriteria.get(SecurityDetailsAttributeMapper.ID).get(0))))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), SecurityDetailsAttributeMapper.SECURITY_DETAILS.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(SecurityDetailsAttributeMapper.SECURITY_DETAILS)==null?"%":searchCriteria.get(SecurityDetailsAttributeMapper.SECURITY_DETAILS).get(0))))
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
	protected LFIMSQueryParameters<SecurityDetails> getDeleteQueryParameters(final List<Integer> searchCriteria) throws LFIMSServiceException {
		final Selector selector;
		final LFIMSQueryConstraint<SecurityDetails> constraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<SecurityDetails> childNode=getRootNodeStructureDetails().getNodeType();

		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			constraint=new LFIMSQueryConstraint<SecurityDetails>(){

				public List<Constraint> getConstraints() throws LFIMSServiceException {

					List<Constraint> constraints=new ArrayList<Constraint>();
					try {

						Iterator<Integer> valueIterator=searchCriteria.iterator();
						while(valueIterator.hasNext()){

							int value=valueIterator.next();
							constraints.add(queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), SecurityDetailsAttributeMapper.ID.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(value))));
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

	private LFIMSQueryParameters<SecurityDetails> getParameters(final Selector selector,final LFIMSQueryConstraint<SecurityDetails> constraint, final Ordering[] ordering, final Column[] columns){

		return new LFIMSQueryParameters<SecurityDetails>(){
			public Selector getSelector() {
				return selector;
			}
			public LFIMSQueryConstraint<SecurityDetails> getConstraint() {
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
	protected LFIMSObject<SecurityDetails>  createNewRecord()	throws LFIMSServiceException {
		LFIMSObject<SecurityDetails> securityDetailsRecord=new MutableSecurityDetailsImpl();
		return securityDetailsRecord;
	}
	
	
	
	static{
		 nodeStructure=new LFIMSNodeStructure<SecurityDetails>(){

			public LFIMSNode<SecurityDetails> getNodeType() {

				return new LFIMSNode<SecurityDetails>(){
					public String getNodeName() {
						return "Security";
					}
					public String getNodeType() {
						return "lfims:Security";
					}
					public LFIMSNodeKey<SecurityDetails> getNodeKey() {
						return new LFIMSNodeKey<SecurityDetails>(){
							public String getKeyName() {
								return "lfims:id";
							}
							public int getKeyType() {
								return PropertyType.DECIMAL;
							}
							public LFIMSAttributeMapper<SecurityDetails> getSearchCriteria() {
								return SecurityDetailsAttributeMapper.ID;
							}
						};
					}
				};
			}

			public LFIMSNodeStructure<SecurityDetails> getTopNodeStructure() {

				return new LFIMSNodeStructure<SecurityDetails>(){

					public LFIMSNode<SecurityDetails> getNodeType() {

						return new LFIMSNode<SecurityDetails>(){
							public String getNodeName() {
								return "SecurityList";
							}
							public String getNodeType() {
								return "lfims:SecurityList";
							}
							public LFIMSNodeKey<SecurityDetails> getNodeKey() {
								return new LFIMSNodeKey<SecurityDetails>(){
									public String getKeyName() {
										return "lfims:maxid";
									}
									public int getKeyType() {
										return PropertyType.DECIMAL;
									}
									public LFIMSAttributeMapper<SecurityDetails> getSearchCriteria() {
										return SecurityDetailsAttributeMapper.ID;
									}
								};
							}
						};
					}

					public LFIMSNodeStructure<SecurityDetails> getTopNodeStructure() {
						return new LFIMSNodeStructure<SecurityDetails>(){

							public LFIMSNode<SecurityDetails> getNodeType() {

								return new LFIMSNode<SecurityDetails>(){
									public String getNodeName() {
										return "CaseDetails";
									}
									public String getNodeType() {
										return "lfims:CaseDetails";
									}
									public LFIMSNodeKey<SecurityDetails> getNodeKey() {
										return null;
									}
								};
							}

							public LFIMSNodeStructure<SecurityDetails> getTopNodeStructure() {

								return new LFIMSNodeStructure<SecurityDetails>(){

									public LFIMSNode<SecurityDetails> getNodeType() {

										return new LFIMSNode<SecurityDetails>(){
											public String getNodeName() {
												return "Case";
											}
											public String getNodeType() {
												return "lfims:Case";
											}
											public LFIMSNodeKey<SecurityDetails> getNodeKey() {
												return new LFIMSNodeKey<SecurityDetails>(){
													public String getKeyName() {
														return "lfims:id";
													}
													public int getKeyType() {
														return PropertyType.DECIMAL;
													}
													public LFIMSAttributeMapper<SecurityDetails> getSearchCriteria() {
														return SecurityDetailsAttributeMapper.ID;
													}
												};
											}
										};
									}

									public LFIMSNodeStructure<SecurityDetails> getTopNodeStructure() {

										return new LFIMSNodeStructure<SecurityDetails>(){

											public LFIMSNode<SecurityDetails> getNodeType() {

												return new LFIMSNode<SecurityDetails>(){
													public String getNodeName() {
														return "CaseList";
													}
													public String getNodeType() {
														return "lfims:CaseList";
													}
													public LFIMSNodeKey<SecurityDetails> getNodeKey() {
														return new LFIMSNodeKey<SecurityDetails>(){
															public String getKeyName() {
																return "lfims:maxid";
															}
															public int getKeyType() {
																return PropertyType.DECIMAL;
															}
															public LFIMSAttributeMapper<SecurityDetails> getSearchCriteria() {
																return SecurityDetailsAttributeMapper.ID;
															}
														};
													}
												};
											}

											public LFIMSNodeStructure<SecurityDetails> getTopNodeStructure() {

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
