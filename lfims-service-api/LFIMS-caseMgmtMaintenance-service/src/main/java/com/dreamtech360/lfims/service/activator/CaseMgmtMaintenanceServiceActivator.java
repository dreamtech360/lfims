package com.dreamtech360.lfims.service.activator;

import javax.jcr.Repository;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.service.impl.casemgmtmaintenance.CaseMgmtMaintenanceServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementServiceFactory;

public class CaseMgmtMaintenanceServiceActivator implements BundleActivator {

	private ServiceRegistration serviceRegistry=null;
	private Repository repository=null;
	private ServiceReference txnServiceRef=null;
	
	public void start(BundleContext context) throws Exception {
		
		txnServiceRef=context.getServiceReference(LFIMSTransactionManagementServiceFactory.class.getName());
		LFIMSTransactionManagementServiceFactory transactionManagementServiceFactory=(LFIMSTransactionManagementServiceFactory)context.getService(txnServiceRef);
		
		repository=(Repository)context.getService(context.getServiceReference(Repository.class.getName()));
		LFIMSTransactionManagementService txnService=(LFIMSTransactionManagementService)transactionManagementServiceFactory.lookupService();
		 
		CaseMgmtMaintenanceServiceFactory serviceFactory=new CaseMgmtMaintenanceServiceFactory(context,repository,txnService);
		serviceRegistry=context.registerService(CaseMgmtMaintenanceServiceFactory.class.getName(), serviceFactory, null);
	}

	public void stop(BundleContext context) throws Exception {
		if(serviceRegistry!=null)
		context.ungetService(serviceRegistry.getReference());

	}

}
