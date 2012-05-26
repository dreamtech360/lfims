package com.dreamtech360.lfims.model.activator;

import javax.jcr.Repository;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.model.service.impl.courtmaster.CourtMasterMaintenanceService;

public class CourtMasterModelImplActivator implements BundleActivator {

	private ServiceRegistration serviceRegistration=null;
	private Repository repository=null;
	
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		repository=(Repository)context.getService(context.getServiceReference(Repository.class.getName()));
		CourtMasterMaintenanceService serviceImpl=new CourtMasterMaintenanceService(repository);
		System.out.println("*******************************"+CourtMasterMaintenanceService.class.toString());	
		serviceRegistration=context.registerService(CourtMasterMaintenanceService.class.getName(), serviceImpl, null);
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		if(serviceRegistration!=null)
				context.ungetService(serviceRegistration.getReference());
		serviceRegistration=null;
		

	}

}
