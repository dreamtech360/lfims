package com.dreamtech360.lfims.model.activator;

import javax.jcr.Repository;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.model.service.impl.bankmaster.BankMasterMaintenanceService;

public class BankMasterModelImplActivator implements BundleActivator {

	private ServiceRegistration serviceRegistration=null;
	private Repository repository=null;
	
	public void start(BundleContext context) throws Exception {
	
		repository=(Repository)context.getService(context.getServiceReference(Repository.class.getName()));
		BankMasterMaintenanceService serviceImpl=new BankMasterMaintenanceService(repository);
		System.out.println("*******************************"+BankMasterMaintenanceService.class.toString());	
		serviceRegistration=context.registerService(BankMasterMaintenanceService.class.getName(), serviceImpl, null);
	}

	public void stop(BundleContext context) throws Exception {
	
		if(serviceRegistration!=null)
				context.ungetService(serviceRegistration.getReference());
		serviceRegistration=null;
		

	}

}
