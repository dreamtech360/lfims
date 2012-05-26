package com.dreamtech360.lfims.model.service.base;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.QueryManager;
import javax.jcr.query.qom.QueryObjectModelFactory;

public class LFIMSJCRSession {
	
	private Session session=null;
	private QueryManager queryManager= null;
	private QueryObjectModelFactory queryOMF=null;
	
	
	public LFIMSJCRSession(Session session) throws RepositoryException{
		this.session=session;
		this.queryManager=this.session.getWorkspace().getQueryManager();
		this.queryOMF=this.queryManager.getQOMFactory();
		
	}
	
	public Session getSession(){
		return this.session;
	}
	
	public QueryManager getQueryManager(){
		return queryManager;
	}
	
	public QueryObjectModelFactory getQueryObjectFactory(){
		return queryOMF;
	}
}
