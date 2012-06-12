package com.dreamtech360.lfims.service.cachemanagement.activator;




import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.model.service.base.LFIMSGenericService;
import com.dreamtech360.lfims.service.cachemanagement.LFIMSCacheManagementService;
import com.dreamtech360.lfims.service.cachemanagement.LFIMSCacheManagementServiceFactory;
import com.dreamtech360.lfims.service.cachemanagement.LFIMSCacheService;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementServiceFactory;

public class LFIMSCacheManagementServiceActivator implements BundleActivator {
	
	private ServiceRegistration factoryRegistration=null;
	private ServiceReference txnServiceRef=null;
	public void start(BundleContext context) throws Exception {
		
	
		txnServiceRef=context.getServiceReference(LFIMSTransactionManagementServiceFactory.class.getName());
		LFIMSTransactionManagementServiceFactory transactionManagementServiceFactory=(LFIMSTransactionManagementServiceFactory)context.getService(txnServiceRef);
		 
		LFIMSCacheManagementServiceFactory serviceFactory=new LFIMSCacheManagementServiceFactory(context,(LFIMSTransactionManagementService)transactionManagementServiceFactory.createService());
		factoryRegistration=context.registerService(LFIMSCacheManagementServiceFactory.class.getName(), serviceFactory, null);
		
		//LFIMSCacheService serv=(LFIMSCacheService) serviceFactory.createService();
		
		//serv.getCache("sample");
		
		
		
	}

	public void stop(BundleContext context) throws Exception {
	
	
	if(factoryRegistration!=null)
		context.ungetService(factoryRegistration.getReference());
	factoryRegistration=null;
	}

}
