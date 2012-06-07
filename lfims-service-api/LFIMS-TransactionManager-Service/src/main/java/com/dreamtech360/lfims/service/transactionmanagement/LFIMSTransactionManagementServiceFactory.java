package com.dreamtech360.lfims.service.transactionmanagement;

import org.osgi.framework.BundleContext;
import com.dreamtech360.lfims.model.service.base.LFIMSGenericService;
import com.dreamtech360.lfims.service.base.LFIMSGenericServiceFactory;

public class LFIMSTransactionManagementServiceFactory implements LFIMSGenericServiceFactory<LFIMSTransactionManager>  {

	
	private BundleContext context=null;
	
	public LFIMSTransactionManagementServiceFactory(){}
	
	public LFIMSTransactionManagementServiceFactory(BundleContext context){
		this.context=context;
	}
 
	
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSGenericService<LFIMSTransactionManager> lookupService() {
		// TODO Auto-generated method stub
		LFIMSGenericService<LFIMSTransactionManager> service=(LFIMSGenericService<LFIMSTransactionManager> )context.getService(context.getServiceReference(LFIMSTransactionManagementService.class.getName()));
		return service;
	}

	
	public LFIMSGenericService<LFIMSTransactionManager> createService() {
		// TODO Auto-generated method stub
		return new LFIMSTransactionManagementService();
	}




}
