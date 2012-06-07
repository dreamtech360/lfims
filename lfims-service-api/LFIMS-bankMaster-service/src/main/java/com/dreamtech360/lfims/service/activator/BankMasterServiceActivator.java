package com.dreamtech360.lfims.service.activator;

import javax.jcr.Repository;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.service.impl.bankmaster.BankMasterServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementServiceFactory;

public class BankMasterServiceActivator implements BundleActivator {

	private ServiceRegistration serviceRegistry=null;
	private Repository repository=null;
	private ServiceReference txnServiceRef=null;
	
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub\
		
		txnServiceRef=context.getServiceReference(LFIMSTransactionManagementServiceFactory.class.getName());
		LFIMSTransactionManagementServiceFactory transactionManagementServiceFactory=(LFIMSTransactionManagementServiceFactory)context.getService(txnServiceRef);
		
		repository=(Repository)context.getService(context.getServiceReference(Repository.class.getName()));
		
		BankMasterServiceFactory serviceFactory=new BankMasterServiceFactory(context,repository,(LFIMSTransactionManagementService)transactionManagementServiceFactory.lookupService());
		serviceRegistry=context.registerService(BankMasterServiceFactory.class.getName(), serviceFactory, null);
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		if(serviceRegistry!=null)
		context.ungetService(serviceRegistry.getReference());

	}

}
