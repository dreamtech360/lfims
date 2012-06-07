package com.dreamtech360.lfims.service.activator;

import javax.jcr.Repository;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.service.impl.expensesmaster.ExpensesMasterServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementServiceFactory;

public class ExpensesMasterServiceActivator implements BundleActivator {

	private ServiceRegistration serviceRegistry=null;
	private Repository repository=null;
	private ServiceReference txnServiceRef=null;
	
	public void start(BundleContext context) throws Exception {
		
		txnServiceRef=context.getServiceReference(LFIMSTransactionManagementServiceFactory.class.getName());
		LFIMSTransactionManagementServiceFactory transactionManagementServiceFactory=(LFIMSTransactionManagementServiceFactory)context.getService(txnServiceRef);
		
		repository=(Repository)context.getService(context.getServiceReference(Repository.class.getName()));
		LFIMSTransactionManagementService txnService=(LFIMSTransactionManagementService)transactionManagementServiceFactory.lookupService();
		 
		
		ExpensesMasterServiceFactory serviceFactory=new ExpensesMasterServiceFactory(context,repository,txnService);
		serviceRegistry=context.registerService(ExpensesMasterServiceFactory.class.getName(), serviceFactory, null);
	}

	public void stop(BundleContext context) throws Exception {
		if(serviceRegistry!=null)
		context.ungetService(serviceRegistry.getReference());

	}

}
