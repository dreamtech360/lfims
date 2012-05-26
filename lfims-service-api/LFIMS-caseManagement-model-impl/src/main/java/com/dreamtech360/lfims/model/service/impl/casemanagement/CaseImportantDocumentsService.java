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

import com.dreamtech360.lfims.model.api.casemanagement.DefendentDetails;
import com.dreamtech360.lfims.model.api.casemanagement.ImportantDocuments;
import com.dreamtech360.lfims.model.api.impl.casemanagement.ImportantDocumentsImpl;
import com.dreamtech360.lfims.model.api.impl.casemanagement.MutableImportantDocumentsImpl;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSNode;
import com.dreamtech360.lfims.model.base.LFIMSNodeKey;
import com.dreamtech360.lfims.model.base.LFIMSNodeStructure;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.impl.casemanagement.ImportantDocumentsAttributeMapper;
import com.dreamtech360.lfims.model.search.query.LFIMSDecimalValue;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryConstraint;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryParameters;
import com.dreamtech360.lfims.model.search.query.LFIMSStringValue;
import com.dreamtech360.lfims.model.service.base.LFIMSJCRSessionThreadLocal;
import com.dreamtech360.lfims.model.service.base.LFIMSModelJCRService;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;

public class CaseImportantDocumentsService extends LFIMSModelJCRService<ImportantDocuments>
{
	private static LFIMSNodeStructure<ImportantDocuments> nodeStructure=null;
	public CaseImportantDocumentsService(Repository repository){
		this.repository=repository;
	}

	@Override
	protected void update(Node node, LFIMSObject<ImportantDocuments> record) throws LFIMSServiceException {

		ImportantDocuments importantDocumentsRecord=(ImportantDocuments)record;
		try{
			populateValue(node,importantDocumentsRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}

	@Override
	protected Node store(Node rootNode, LFIMSObject<ImportantDocuments> record) throws LFIMSServiceException   {

		ImportantDocuments importantDocumentsRecord=(ImportantDocuments)record;
		Node importantDocumentsNode=null;
		try{
			importantDocumentsNode = rootNode.addNode("lfims:document");
			populateValue(importantDocumentsNode,importantDocumentsRecord);
		}catch(LFIMSServiceException e){
			throw e;
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return importantDocumentsNode;
	} 

	private void populateValue(Node node, ImportantDocuments record) throws LFIMSServiceException{
		try{
		
			
			
			node.setProperty("lfims:id", record.getId());
			node.setProperty("lfims:documentName", record.getDocumentName());
			node.setProperty("lfims:originalYN", record.isOriginalYN());
			node.setProperty("lfims:receivedFrom", record.getReceivedFrom());
			node.setProperty("lfims:receivedDate", record.getReceivedDate().toString());
			node.setProperty("lfims:receivedBy", record.getReceivedBy());
			node.setProperty("lfims:keptIn", record.getKeptIn());
			node.setProperty("lfims:otherDetailsReceipt", record.getOtherDetailsReceipt());
			node.setProperty("lfims:returnDate", record.getReturnDate().toString());
			node.setProperty("lfims:returnTo", record.getReturnTo());
			node.setProperty("lfims:returnBy", record.getReturnBy());
			node.setProperty("lfims:otherDetailsReturn", record.getOtherDetailsReturn());
			
						
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}

	}

	@Override
	protected LFIMSObject<ImportantDocuments> convertNodeToObject(Node node, boolean readOnly) throws LFIMSServiceException {
		LFIMSObject<ImportantDocuments> importantDocumentsRecord=null;
	
			
			try {
				if(readOnly==true){
					importantDocumentsRecord=new ImportantDocumentsImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:documentName").getString(),
							Boolean.parseBoolean(node.getProperty("lfims:originalYN").getString()),
							node.getProperty("lfims:receivedFrom").getString(),
							new Date(node.getProperty("lfims:receivedDate").getString()),
							node.getProperty("lfims:receivedBy").getString(),
							node.getProperty("lfims:keptIn").getString(),
							node.getProperty("lfims:otherDetailsReceipt").getString(),
							new Date(node.getProperty("lfims:returnDate").getString()),
							node.getProperty("lfims:returnTo").getString(),
							node.getProperty("lfims:returnBy").getString(),
							node.getProperty("lfims:otherDetailsReturn").getString());
						}
				else{
					importantDocumentsRecord=new MutableImportantDocumentsImpl(
							Integer.parseInt(node.getProperty("lfims:id").getString()),
							node.getProperty("lfims:documentName").getString(),
							Boolean.parseBoolean(node.getProperty("lfims:originalYN").getString()),
							node.getProperty("lfims:receivedFrom").getString(),
							new Date(node.getProperty("lfims:receivedDate").getString()),
							node.getProperty("lfims:receivedBy").getString(),
							node.getProperty("lfims:keptIn").getString(),
							node.getProperty("lfims:otherDetailsReceipt").getString(),
							new Date(node.getProperty("lfims:returnDate").getString()),
							node.getProperty("lfims:returnTo").getString(),
							node.getProperty("lfims:returnBy").getString(),
							node.getProperty("lfims:otherDetailsReturn").getString());
				}
				
			} catch (Exception e) {
				throw new LFIMSServiceException(e);
			}
		
		return importantDocumentsRecord;
	}
	
	@Override
	protected LFIMSNodeStructure<ImportantDocuments> getRootNodeStructureDetails() {
		return nodeStructure;
	}
	
	@Override
	protected LFIMSQueryParameters<ImportantDocuments> getSearchQueryParameters(final Map<LFIMSAttributeMapper<ImportantDocuments>, List<String>> searchCriteria) throws LFIMSServiceException {

		final Selector selector;
		final LFIMSQueryConstraint<ImportantDocuments> queryConstraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<ImportantDocuments> childNode=getRootNodeStructureDetails().getNodeType();
		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			queryConstraint=new LFIMSQueryConstraint<ImportantDocuments>(){
				public List<Constraint> getConstraints() throws LFIMSServiceException {
					List<Constraint> constraints=new ArrayList<Constraint>();
					try {
						
						Constraint constraint=queryOMF.and(
												queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), ImportantDocumentsAttributeMapper.ID.getFieldName()), searchCriteria.get(ImportantDocumentsAttributeMapper.ID)==null?QueryObjectModelConstants.JCR_OPERATOR_GREATER_THAN_OR_EQUAL_TO:QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(searchCriteria.get(ImportantDocumentsAttributeMapper.ID)==null?0:Integer.valueOf(searchCriteria.get(ImportantDocumentsAttributeMapper.ID).get(0))))),
												queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), ImportantDocumentsAttributeMapper.DOCUMENT_NAME.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(ImportantDocumentsAttributeMapper.DOCUMENT_NAME)==null?"%":searchCriteria.get(ImportantDocumentsAttributeMapper.DOCUMENT_NAME).get(0)))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), ImportantDocumentsAttributeMapper.RECEIVED_FROM.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(ImportantDocumentsAttributeMapper.RECEIVED_FROM)==null?"%":searchCriteria.get(ImportantDocumentsAttributeMapper.RECEIVED_FROM).get(0))))
												)
												),
												queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), ImportantDocumentsAttributeMapper.RECEIVED_DATE.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(ImportantDocumentsAttributeMapper.RECEIVED_DATE)==null?"%":searchCriteria.get(ImportantDocumentsAttributeMapper.RECEIVED_DATE).get(0)))),
												queryOMF.and(
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), ImportantDocumentsAttributeMapper.RETURN_TO.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(ImportantDocumentsAttributeMapper.RETURN_TO)==null?"%":searchCriteria.get(ImportantDocumentsAttributeMapper.RETURN_TO).get(0)))),
												queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), ImportantDocumentsAttributeMapper.RETURN_DATE.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_LIKE, queryOMF.literal(new LFIMSStringValue(searchCriteria.get(ImportantDocumentsAttributeMapper.RETURN_DATE)==null?"%":searchCriteria.get(ImportantDocumentsAttributeMapper.RETURN_DATE).get(0))))
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
	protected LFIMSQueryParameters<ImportantDocuments> getDeleteQueryParameters(final List<Integer> searchCriteria) throws LFIMSServiceException {
		final Selector selector;
		final LFIMSQueryConstraint<ImportantDocuments> constraint;
		final Ordering[] ordering = null;
		final Column[] columns= null;
		final QueryObjectModelFactory queryOMF=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory();			
		LFIMSNode<ImportantDocuments> childNode=getRootNodeStructureDetails().getNodeType();

		try{
			selector=queryOMF.selector(childNode.getNodeType(), childNode.getNodeName());
			constraint=new LFIMSQueryConstraint<ImportantDocuments>(){

				public List<Constraint> getConstraints() throws LFIMSServiceException {

					List<Constraint> constraints=new ArrayList<Constraint>();
					try {

						Iterator<Integer> valueIterator=searchCriteria.iterator();
						while(valueIterator.hasNext()){

							int value=valueIterator.next();
							constraints.add(queryOMF.comparison(queryOMF.propertyValue(selector.getSelectorName(), ImportantDocumentsAttributeMapper.ID.getFieldName()), QueryObjectModelConstants.JCR_OPERATOR_EQUAL_TO, queryOMF.literal(new LFIMSDecimalValue(value))));
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

	private LFIMSQueryParameters<ImportantDocuments> getParameters(final Selector selector,final LFIMSQueryConstraint<ImportantDocuments> constraint, final Ordering[] ordering, final Column[] columns){

		return new LFIMSQueryParameters<ImportantDocuments>(){
			public Selector getSelector() {
				return selector;
			}
			public LFIMSQueryConstraint<ImportantDocuments> getConstraint() {
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
	protected LFIMSObject<ImportantDocuments>  createNewRecord()	throws LFIMSServiceException {
		LFIMSObject<ImportantDocuments> importantDocumentsRecord=new MutableImportantDocumentsImpl();
		return importantDocumentsRecord;
	}

	static{
		
		
	nodeStructure=new LFIMSNodeStructure<ImportantDocuments>(){

			public LFIMSNode<ImportantDocuments> getNodeType() {

				return new LFIMSNode<ImportantDocuments>(){
					public String getNodeName() {
						return "Document";
					}
					public String getNodeType() {
						return "lfims:Document";
					}
					public LFIMSNodeKey<ImportantDocuments> getNodeKey() {
						return new LFIMSNodeKey<ImportantDocuments>(){
							public String getKeyName() {
								return "lfims:id";
							}
							public int getKeyType() {
								return PropertyType.DECIMAL;
							}
							public LFIMSAttributeMapper<ImportantDocuments> getSearchCriteria() {
								return ImportantDocumentsAttributeMapper.ID;
							}
						};
					}
				};
			}

			public LFIMSNodeStructure<ImportantDocuments> getTopNodeStructure() {

				return new LFIMSNodeStructure<ImportantDocuments>(){

					public LFIMSNode<ImportantDocuments> getNodeType() {

						return new LFIMSNode<ImportantDocuments>(){
							public String getNodeName() {
								return "DocumentList";
							}
							public String getNodeType() {
								return "lfims:DocumentList";
							}
							public LFIMSNodeKey<ImportantDocuments> getNodeKey() {
								return new LFIMSNodeKey<ImportantDocuments>(){
									public String getKeyName() {
										return "lfims:maxid";
									}
									public int getKeyType() {
										return PropertyType.DECIMAL;
									}
									public LFIMSAttributeMapper<ImportantDocuments> getSearchCriteria() {
										return ImportantDocumentsAttributeMapper.ID;
									}
								};
							}
						};
					}

					public LFIMSNodeStructure<ImportantDocuments> getTopNodeStructure() {
						return new LFIMSNodeStructure<ImportantDocuments>(){

							public LFIMSNode<ImportantDocuments> getNodeType() {

								return new LFIMSNode<ImportantDocuments>(){
									public String getNodeName() {
										return "CaseDetails";
									}
									public String getNodeType() {
										return "lfims:CaseDetails";
									}
									public LFIMSNodeKey<ImportantDocuments> getNodeKey() {
										return null;
									}
								};
							}

							public LFIMSNodeStructure<ImportantDocuments> getTopNodeStructure() {

								return new LFIMSNodeStructure<ImportantDocuments>(){

									public LFIMSNode<ImportantDocuments> getNodeType() {

										return new LFIMSNode<ImportantDocuments>(){
											public String getNodeName() {
												return "Case";
											}
											public String getNodeType() {
												return "lfims:Case";
											}
											public LFIMSNodeKey<ImportantDocuments> getNodeKey() {
												return new LFIMSNodeKey<ImportantDocuments>(){
													public String getKeyName() {
														return "lfims:id";
													}
													public int getKeyType() {
														return PropertyType.DECIMAL;
													}
													public LFIMSAttributeMapper<ImportantDocuments> getSearchCriteria() {
														return ImportantDocumentsAttributeMapper.ID;
													}
												};
											}
										};
									}

									public LFIMSNodeStructure<ImportantDocuments> getTopNodeStructure() {

										return new LFIMSNodeStructure<ImportantDocuments>(){

											public LFIMSNode<ImportantDocuments> getNodeType() {

												return new LFIMSNode<ImportantDocuments>(){
													public String getNodeName() {
														return "CaseList";
													}
													public String getNodeType() {
														return "lfims:CaseList";
													}
													public LFIMSNodeKey<ImportantDocuments> getNodeKey() {
														return new LFIMSNodeKey<ImportantDocuments>(){
															public String getKeyName() {
																return "lfims:maxid";
															}
															public int getKeyType() {
																return PropertyType.DECIMAL;
															}
															public LFIMSAttributeMapper<ImportantDocuments> getSearchCriteria() {
																return ImportantDocumentsAttributeMapper.ID;
															}
														};
													}
												};
											}

											public LFIMSNodeStructure<ImportantDocuments> getTopNodeStructure() {

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
