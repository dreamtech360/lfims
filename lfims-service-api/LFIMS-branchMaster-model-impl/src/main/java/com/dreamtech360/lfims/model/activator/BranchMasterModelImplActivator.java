package com.dreamtech360.lfims.model.activator;

import javax.jcr.Repository;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.model.service.impl.branchmaster.BranchMasterMaintenanceService;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementServiceFactory;

public class BranchMasterModelImplActivator implements BundleActivator {

	private ServiceRegistration serviceRegistration=null;
	private Repository repository=null;
	private ServiceReference txnServiceRef=null;
	
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
		txnServiceRef=context.getServiceReference(LFIMSTransactionManagementServiceFactory.class.getName());
		LFIMSTransactionManagementServiceFactory transactionManagementServiceFactory=(LFIMSTransactionManagementServiceFactory)context.getService(txnServiceRef);
		
		
		repository=(Repository)context.getService(context.getServiceReference(Repository.class.getName()));
		BranchMasterMaintenanceService serviceImpl=new BranchMasterMaintenanceService(repository,(LFIMSTransactionManagementService)transactionManagementServiceFactory.createService());
		System.out.println("*******************************"+BranchMasterMaintenanceService.class.toString());	
		serviceRegistration=context.registerService(BranchMasterMaintenanceService.class.getName(), serviceImpl, null);
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		if(serviceRegistration!=null)
				context.ungetService(serviceRegistration.getReference());
		serviceRegistration=null;
		

	}

}
