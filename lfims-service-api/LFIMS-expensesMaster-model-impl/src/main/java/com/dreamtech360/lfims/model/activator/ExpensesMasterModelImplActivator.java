package com.dreamtech360.lfims.model.activator;

import javax.jcr.Repository;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.model.service.impl.expensesmaster.ExpensesMasterMaintenanceService;

public class ExpensesMasterModelImplActivator implements BundleActivator {

	private ServiceRegistration serviceRegistration=null;
	private Repository repository=null;
	
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		repository=(Repository)context.getService(context.getServiceReference(Repository.class.getName()));
		ExpensesMasterMaintenanceService serviceImpl=new ExpensesMasterMaintenanceService(repository);
		System.out.println("*******************************"+ExpensesMasterMaintenanceService.class.toString());	
		serviceRegistration=context.registerService(ExpensesMasterMaintenanceService.class.getName(), serviceImpl, null);
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		if(serviceRegistration!=null)
				context.ungetService(serviceRegistration.getReference());
		serviceRegistration=null;
		

	}

}
