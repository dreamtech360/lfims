package com.dreamtech360.lfims.service.cachemanagement.activator;




import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.service.cachemanagement.LFIMSCacheManagementService;
import com.dreamtech360.lfims.service.cachemanagement.LFIMSCacheManagementServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementServiceFactory;

public class LFIMSCacheManagementServiceActivator implements BundleActivator {
	
	private ServiceRegistration serviceRegistration=null;
	private ServiceRegistration factoryRegistration=null;
	private ServiceReference txnServiceRef=null;
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
	
		txnServiceRef=context.getServiceReference(LFIMSTransactionManagementServiceFactory.class.getName());
		LFIMSTransactionManagementServiceFactory transactionManagementServiceFactory=(LFIMSTransactionManagementServiceFactory)context.getService(txnServiceRef);
		 
		LFIMSCacheManagementService cacheService=new LFIMSCacheManagementService((LFIMSTransactionManagementService) transactionManagementServiceFactory.lookupService());
		System.out.println("*******************************"+LFIMSCacheManagementService.class.toString());
		serviceRegistration=context.registerService(LFIMSCacheManagementService.class.getName(), cacheService, null);
		
		LFIMSCacheManagementServiceFactory serviceFactory=new LFIMSCacheManagementServiceFactory(context);
		factoryRegistration=context.registerService(LFIMSCacheManagementServiceFactory.class.getName(), serviceFactory, null);
		
		
		
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		if(serviceRegistration!=null)
			context.ungetService(serviceRegistration.getReference());
	serviceRegistration=null;
	
	if(factoryRegistration!=null)
		context.ungetService(factoryRegistration.getReference());
	factoryRegistration=null;
	}

}
