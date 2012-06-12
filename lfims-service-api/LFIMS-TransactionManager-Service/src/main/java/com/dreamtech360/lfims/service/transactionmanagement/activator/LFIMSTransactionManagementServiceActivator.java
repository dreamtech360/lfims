package com.dreamtech360.lfims.service.transactionmanagement.activator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementServiceFactory;

public class LFIMSTransactionManagementServiceActivator implements BundleActivator {
	
	private ServiceRegistration serviceRegistration=null;
	private ServiceRegistration factoryRegistration=null;
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
	
		LFIMSTransactionManagementService transactionService=new LFIMSTransactionManagementService();
		System.out.println("*******************************"+LFIMSTransactionManagementService.class.toString());
		serviceRegistration=context.registerService(LFIMSTransactionManagementService.class.getName(), transactionService, null);
		
		transactionService.beginTransaction();
		System.out.println(transactionService.getStatus());
		transactionService.commitTransaction();
		System.out.println(transactionService.getStatus());
	
		
		LFIMSTransactionManagementServiceFactory serviceFactory=new LFIMSTransactionManagementServiceFactory(context);
		factoryRegistration=context.registerService(LFIMSTransactionManagementServiceFactory.class.getName(), serviceFactory, null);
		
		
		
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
