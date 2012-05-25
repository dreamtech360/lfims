package com.dreamtech360.lfims.model.service.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.query.QueryResult;
import javax.jcr.query.qom.Constraint;
import javax.jcr.query.qom.QueryObjectModel;
import com.dreamtech360.lfims.model.base.MutableLFIMSEntityObject;
import com.dreamtech360.lfims.model.base.LFIMSCompositObject;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSNodeKey;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryConstraint;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryParameters;
import com.dreamtech360.lfims.model.search.query.LFIMSQueryResults;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;
import com.dreamtech360.lfims.util.LFIMSUtils;

public abstract class LFIMSModelJCRService<T> extends LFIMSJCRService<T> implements LFIMSModelService<T> {

	ThreadLocal<Integer> offsetParam =new OFFSET();
	ThreadLocal<Integer> limitParam =new LIMIT();

	private  static class OFFSET extends ThreadLocal<Integer>{

		protected Integer initialValue(){
			return new Integer(-1);
		}
	}

	private  static class LIMIT extends ThreadLocal<Integer>{

		protected Integer initialValue(){
			return new Integer(0);
		}
	}

	protected abstract Node store(Node rootNode,LFIMSObject<T> record) throws LFIMSServiceException;
	protected abstract void update(Node rootNode,LFIMSObject<T> record) throws LFIMSServiceException;
	protected abstract LFIMSObject<T> createNewRecord() throws LFIMSServiceException;
	protected abstract LFIMSQueryParameters<T> getSearchQueryParameters(Map<LFIMSAttributeMapper<T>, List<String>> searchCriteria) throws LFIMSServiceException;
	protected abstract LFIMSQueryParameters<T> getDeleteQueryParameters(List<Integer> ids) throws LFIMSServiceException;
	protected abstract LFIMSObject<T> convertNodeToObject(Node nodes,boolean readOnly)  throws LFIMSServiceException;

	public LFIMSObject<T> createRecord(int id) {

		return null; 
	}

	public LFIMSObject<T> createRecord() throws LFIMSServiceException {
		LFIMSObject<T> record=null;
		try{
			initSession();
			Node rootNode=initRootNode();
			record=storeRecordInternal(createNewRecord(),rootNode);
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}finally{
			try {
				closeSession();
			} catch (Exception e) {

				e.printStackTrace();
			} 
		}
		return record;
	}
	//If ID is 0 then the search critetia is for all records 
	protected Map<LFIMSAttributeMapper<T>, List<String>> getSearchCriteria(int id){
		LFIMSNodeKey<T> childNode= getRootNodeStructureDetails().getNodeType().getNodeKey();
		Map<LFIMSAttributeMapper<T>, List<String>> searchCriteria=new HashMap<LFIMSAttributeMapper<T>,List<String>>();
		List<String> value=new ArrayList<String>();
		if(id>0)
			value.add(String.valueOf(id));
		else
			value=null;
		searchCriteria.put(childNode.getSearchCriteria(), value);
		return searchCriteria;
	}

	public LFIMSObject<T> loadRecord(int id)  throws LFIMSServiceException {
		//This check is required because if its a composit node then I should get a Mutable object back 
		//and use that object to set the reference 
		//if(LFIMSUtils.isCompositNode(getClass()))
		//	return searchRecords(getSearchCriteria(id),false).get(0) ;
		return searchRecords(getSearchCriteria(id),true).get(0) ;
	}

	public List<LFIMSObject<T>> loadAllRecord(int offset,int limit) throws LFIMSServiceException {

		Map<LFIMSAttributeMapper<T>, List<String>> searchCriteria=new HashMap<LFIMSAttributeMapper<T>,List<String>>();
		//if(LFIMSUtils.isCompositNode(getClass()))
		//	return searchRecords(searchCriteria,false) ;
		return searchRecords(searchCriteria,true,offset,limit) ;
	}

	public List<LFIMSObject<T>> loadAllRecord() throws LFIMSServiceException {

		Map<LFIMSAttributeMapper<T>, List<String>> searchCriteria=new HashMap<LFIMSAttributeMapper<T>,List<String>>();
	//	if(LFIMSUtils.isCompositNode(getClass()))
		//	return searchRecords(searchCriteria,false) ;
		return searchRecords(searchCriteria,true) ;

	}
	public List<LFIMSObject<T>> loadAllRecordToEdit() throws LFIMSServiceException {
		Map<LFIMSAttributeMapper<T>, List<String>> searchCriteria=new HashMap<LFIMSAttributeMapper<T>,List<String>>();
		return searchRecords(searchCriteria,false) ;
	}

	public LFIMSObject<T> loadRecordToEdit(int id)  throws LFIMSServiceException {
		return searchRecords(getSearchCriteria(id),false).get(0) ;
	}

	public void removeAllRecords() throws LFIMSServiceException {
		try{
			initSession();
			Node rootNode=initRootNode();
			LFIMSNodeKey<T> topNode= getRootNodeStructureDetails().getTopNodeStructure().getNodeType().getNodeKey();
			NodeIterator iterator=rootNode.getNodes();
			while(iterator.hasNext()){
				iterator.nextNode().remove();
			}
			saveSessionData();
			rootNode.setProperty(topNode.getKeyName(), 0);
			saveSessionData();

		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}finally{
			try {
				closeSession();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}

	public void removeRecord(int id) throws LFIMSServiceException{
		try{
			initSession();
			Node node=searchNodes(getSearchCriteria(id),true).get(0) ;
			node.remove();
			saveSessionData();
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}finally{
			try {
				closeSession();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}	
	}

	public void removeRecords(List<Integer> ids) throws LFIMSServiceException{

		QueryObjectModel qom=null;
		LFIMSQueryResults<T> queryResults=new LFIMSQueryResults<T>();
		LFIMSQueryParameters<T> queryParams=null;

		try{
			initSession();
			queryParams=getDeleteQueryParameters(ids);
			LFIMSQueryConstraint<T> constraints=queryParams.getConstraint();
			Iterator<Constraint> constraintsIterator=constraints.getConstraints().iterator();

			while(constraintsIterator.hasNext()){
				Constraint constraint = constraintsIterator.next();
				qom=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory().createQuery(queryParams.getSelector(), constraint, queryParams.getOrdering(),queryParams.getColumns());
				QueryResult queryResult=qom.execute();
				queryResults.setRows(queryResult.getRows());
			}

			List<Node> nodeList=queryResults.getResultNodes();
			Iterator<Node> nodesIterator=nodeList.iterator();

			while(nodesIterator.hasNext()){
				Node node=nodesIterator.next();
				node.remove();
			} 

			saveSessionData();

		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}finally{
			try {
				closeSession();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}

	}

	private final LFIMSObject<T> storeRecordInternal(LFIMSObject<T> record, Node rootNode) throws  LFIMSServiceException {
		int maxId;
		try{

			LFIMSNodeKey<T> topNode= getRootNodeStructureDetails().getTopNodeStructure().getNodeType().getNodeKey();

			maxId = rootNode.getProperty(topNode.getKeyName()).getDecimal().intValue();
			((MutableLFIMSEntityObject <T>) record).setId(++maxId);
			Node newNode=store(rootNode,record);
			String nodeUuid=newNode.getIdentifier();
			if(record instanceof LFIMSCompositObject<?>)
			{
				((LFIMSCompositObject<T>) record).setUuid(nodeUuid);
				newNode.setProperty(getRootNodeStructureDetails().getCompositKeyIdentifier(),nodeUuid);
			}
			saveSessionData();
			rootNode.setProperty(topNode.getKeyName(), maxId);
			saveSessionData();
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return record;
	}

	public final void storeRecord(LFIMSObject<T> record) throws  LFIMSServiceException {

		try{
			initSession();
			Node rootNode=null;
			if(record instanceof LFIMSCompositObject<?>){
				LFIMSCompositObject<T> compositRecord=(LFIMSCompositObject<T>) record;
				if(compositRecord.getUuid()!=null && !"".equals(compositRecord.getUuid()))
					//throw new LFIMSServiceException("The <UUID> of a composit node can not be blank or null please set the <UUID> before calling this method");
					rootNode=initRootNode((LFIMSCompositObject<T>) record);
				else
					rootNode=initRootNode();
			}
			else
				rootNode=initRootNode();

			storeRecordInternal(record,rootNode);
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}finally{
			try {
				closeSession();
			} catch (Exception e) {

				e.printStackTrace();
			} 
		}
	}

	public void storeAllRecord(List<LFIMSObject<T>> records) throws  LFIMSServiceException  {
		try{
			initSession();
			Node rootNode=initRootNode();
			Iterator<LFIMSObject<T>> iterator=records.iterator();
			while(iterator.hasNext()){
				LFIMSObject<T> record=iterator.next();
				storeRecordInternal(record,rootNode);
			}

		}catch(LFIMSServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}finally{
			try {
				closeSession();
			} catch (Exception e) {

				e.printStackTrace();
			} 
		}

	}

	public List<LFIMSObject<T>> searchRecords(Map<LFIMSAttributeMapper<T>, List<String>> searchCriteria,boolean readOnly,int offset, int limit) throws LFIMSServiceException {

		try{
			//For drop down population, Ext-JS forms that have dropdowns mapped to store the values for offset and limit will be 0
			limitParam.set(limit);
			offsetParam.set(offset);
			if(LFIMSUtils.isCompositNode(getClass()))
				return searchRecords(searchCriteria,false) ;
			return searchRecords(searchCriteria,readOnly);
		}finally{
			limitParam.remove();
			offsetParam.remove();
		}
	}

	public List<Node> fetchNodes(Map<LFIMSAttributeMapper<T>, List<String>> searchCriteria,boolean readOnly) throws LFIMSServiceException {

		List<Node> nodes=null;
		try{
			initSession();
			nodes=searchNodes(searchCriteria,readOnly);
		}catch(LFIMSServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}finally{
			try {
				closeSession();
			} catch (Exception e) {

				e.printStackTrace();
			} 
		}
		return nodes;
	}

	public Map<String,LFIMSObject<T>> fetchNodeReferences(Map<LFIMSAttributeMapper<T>, List<String>> searchCriteria,boolean readOnly) throws LFIMSServiceException {

		List<Node> nodes=null;
		Map<String,LFIMSObject<T>> nodeIdentifiers=null;

		try{
			initSession();
			nodes=searchNodes(searchCriteria,readOnly);
			if(nodes.size()>0){
				nodeIdentifiers=new HashMap<String,LFIMSObject<T>>();
				Iterator<Node> iterator=nodes.iterator();
				while(iterator.hasNext()){
					Node node=iterator.next();
					LFIMSObject<T> record=convertNodeToObject(node,readOnly);
					nodeIdentifiers.put(node.getIdentifier(),record);
				}
			}
		}catch(LFIMSServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}finally{
			try {
				closeSession();
			} catch (Exception e) {

				e.printStackTrace();
			} 
		}
		return nodeIdentifiers;
	}

	protected List<Node> searchNodes(Map<LFIMSAttributeMapper<T>, List<String>> searchCriteria,boolean readOnly) throws LFIMSServiceException {

		List<Node> nodes=null;
		QueryObjectModel qom=null;
		LFIMSQueryResults<T> queryResults=new LFIMSQueryResults<T>();
		LFIMSQueryParameters<T> queryParameters =null;

		try{
			queryParameters = getSearchQueryParameters(searchCriteria);
			LFIMSQueryConstraint<T> queryConstraints = queryParameters.getConstraint();
			List<Constraint> constraints=queryConstraints.getConstraints();
			Iterator<Constraint> iterator=constraints.iterator();

			while(iterator.hasNext()){
				Constraint constraint=iterator.next();
				qom=LFIMSJCRSessionThreadLocal.get().getQueryObjectFactory().createQuery(queryParameters.getSelector(), constraint, queryParameters.getOrdering(),queryParameters.getColumns());

				if(limitParam.get()>0 && offsetParam.get()>=0){

					qom.setLimit(limitParam.get());
					qom.setOffset(offsetParam.get());
				}

				QueryResult queryResult=qom.execute();

				queryResults.setRows(queryResult.getRows());
				System.out.println("queryResult count====>"+queryResults.getResultNodes().size());
			}

			nodes=queryResults.getResultNodes();
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		if(nodes==null)
			throw new LFIMSServiceException("No results found");

		return nodes;
	}

	private List<LFIMSObject<T>> convertNodeToObject(List<Node> nodes, boolean readOnly) throws LFIMSServiceException {
		List<LFIMSObject<T>> results=new ArrayList<LFIMSObject<T>> ();
		LFIMSObject<T> caseMasterRecord=null;
		Iterator<Node> iterator=nodes.iterator();
		while (iterator.hasNext()){

			Node node=iterator.next();
			try {
				caseMasterRecord=convertNodeToObject(node, readOnly);
				if(caseMasterRecord instanceof LFIMSCompositObject<?> )
					((LFIMSCompositObject<T>) caseMasterRecord).setUuid(node.getProperty(getRootNodeStructureDetails().getCompositKeyIdentifier()).getString());
				results.add(caseMasterRecord);
			}catch (LFIMSServiceException e) {
				throw e;
			}
			catch (Exception e) {
				throw new LFIMSServiceException(e);
			}
		}
		return results;
	}

	public List<LFIMSObject<T>> searchRecords(Map<LFIMSAttributeMapper<T>, List<String>> searchCriteria,boolean readOnly) throws LFIMSServiceException {

		List<Node> nodes=null;
		List<LFIMSObject<T>> records=null;
		try{
			initSession();
			nodes=searchNodes(searchCriteria,readOnly);
			if(nodes!=null)
				records=convertNodeToObject(nodes,readOnly);
		}catch(LFIMSServiceException e){
			throw e;
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}finally{
			try {
				closeSession();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return records;
	}

	public long getTotalRecords() throws LFIMSServiceException {

		long totalRecords=0;

		try{
			initSession();
			List<Node> nodesList=searchNodes(getSearchCriteria(0), true);
			totalRecords=nodesList.size();
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}finally{
			try {
				closeSession();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}		
		return totalRecords;

	}

	public long getTotalRecords(Map<LFIMSAttributeMapper<T>,List<String>> searchCriteria) throws LFIMSServiceException {

		long totalRecords=0;
		try{
			initSession();
			List<Node> nodesList=searchNodes(searchCriteria, true);
			totalRecords=nodesList.size();
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}finally{
			try {
				closeSession();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}		
		return totalRecords;
	}

	public void updateRecord(LFIMSObject<T> record) throws LFIMSServiceException{

		try{
			initSession();
			Node node=searchNodes(getSearchCriteria(((MutableLFIMSEntityObject <T>) record).getId()),true).get(0) ;
			update(node,record);
			saveSessionData();
		}
		catch(Exception e){
			throw new LFIMSServiceException(e);
		}finally{
			try {
				closeSession();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}		
	}

}
