package com.dreamtech360.lfims.model.search.query;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.query.Row;
import javax.jcr.query.RowIterator;

import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;

public class LFIMSQueryResults<T> {

	private List<Row> queryResultRows;
	private List<Node> queryResultNodes;

	public LFIMSQueryResults(){
		queryResultRows=new ArrayList<Row>();
		queryResultNodes=new ArrayList<Node>();
	}

	public void setRows(RowIterator rowIterator) throws LFIMSServiceException{

		while(rowIterator.hasNext()){
			Row row=rowIterator.nextRow();
			queryResultRows.add(row);
			try {
				queryResultNodes.add(row.getNode());
			} catch (Exception e) {
				throw new LFIMSServiceException(e);
			}
		}
	}
	public void setNodes(NodeIterator nodeIterator){

		while(nodeIterator.hasNext()){
			queryResultNodes.add(nodeIterator.nextNode());
		}
	}

	public List<Node> getResultNodes(){
		return this.queryResultNodes;
	}

	public List<Row> getResultRows(){
		return this.queryResultRows;
	}
	
	 

}
