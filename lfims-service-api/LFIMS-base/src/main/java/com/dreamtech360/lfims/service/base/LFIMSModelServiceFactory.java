package com.dreamtech360.lfims.service.base;

import com.dreamtech360.lfims.model.service.base.LFIMSModelService;

/*Services can be of two types
 * One that deals with models
 * and the other that are generic in nature like cache service, transaction service etc
 * Every service factory should extend this class 
 */

public abstract class LFIMSModelServiceFactory<T> implements LFIMSGenericServiceFactory<T>{

	
	
	 
	protected abstract LFIMSModelService<T> createModelService();
	protected abstract LFIMSModelService<T> lookupModelService();
	protected abstract LFIMSModelService<T> createTxnModelService();
	protected abstract LFIMSModelService<T> lookupTxnModelService();
	
	public LFIMSModelService<T> createService(){
		return createModelService();
	}
	public LFIMSModelService<T> lookupService(){
		return lookupModelService();
	}
	
	public LFIMSModelService<T> createTxnService(){
		return createTxnModelService();
	}
	public LFIMSModelService<T> lookupTxnService(){
		return lookupTxnModelService();
	}
	
	

}
