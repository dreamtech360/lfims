package com.dreamtech360.lfims.service.cachemanagement;

import org.osgi.framework.BundleContext;
import com.dreamtech360.lfims.model.service.base.LFIMSGenericService;
import com.dreamtech360.lfims.service.base.LFIMSGenericServiceFactory;

public class LFIMSCacheManagementServiceFactory implements LFIMSGenericServiceFactory<LFIMSCacheManager>  {

	
	private BundleContext context=null;
	
	public LFIMSCacheManagementServiceFactory(){}
	
	public LFIMSCacheManagementServiceFactory(BundleContext context){
		this.context=context;
	}
 
	
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSGenericService<LFIMSCacheManager> lookupService() {
		// TODO Auto-generated method stub
		LFIMSGenericService<LFIMSCacheManager> service=(LFIMSGenericService<LFIMSCacheManager>)context.getService(context.getServiceReference(LFIMSCacheManagementService.class.getName()));
		return service;
	}

	
	public LFIMSGenericService<LFIMSCacheManager> createService() {
		// TODO Auto-generated method stub
		return new LFIMSCacheManagementService(null);
	}




}
