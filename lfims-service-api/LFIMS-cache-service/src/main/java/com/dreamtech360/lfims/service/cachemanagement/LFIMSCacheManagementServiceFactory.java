package com.dreamtech360.lfims.service.cachemanagement;

import org.osgi.framework.BundleContext;

import com.dreamtech360.lfims.model.service.base.LFIMSGenericService;
import com.dreamtech360.lfims.service.base.LFIMSGenericServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class LFIMSCacheManagementServiceFactory implements LFIMSGenericServiceFactory<LFIMSCacheService>  {

	
	private BundleContext context=null;
	private volatile LFIMSGenericService<LFIMSCacheService> service=null;
	private LFIMSTransactionManagementService txnService=null;
	
	public LFIMSCacheManagementServiceFactory(){}
	
	public LFIMSCacheManagementServiceFactory(BundleContext context,LFIMSTransactionManagementService txnService){
		this.context=context;
		this.txnService=txnService;
	}
 
	
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSGenericService<LFIMSCacheService> lookupService() {
		return createService();
	}

	
	public LFIMSGenericService<LFIMSCacheService> createService() {
		if(service==null){
			synchronized(this){
			service= new LFIMSCacheManagementService(txnService);
			}
		}
	
		return service;
	}




}
