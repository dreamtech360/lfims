package com.dreamtech360.lfims.service.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.service.impl.casemgmtmaintenance.CaseMgmtMaintenanceServiceFactory;

public class CaseMgmtMaintenanceServiceActivator implements BundleActivator {

	private ServiceRegistration serviceRegistry=null;
	
	public void start(BundleContext context) throws Exception {
		CaseMgmtMaintenanceServiceFactory serviceFactory=new CaseMgmtMaintenanceServiceFactory(context);
		serviceRegistry=context.registerService(CaseMgmtMaintenanceServiceFactory.class.getName(), serviceFactory, null);
	}

	public void stop(BundleContext context) throws Exception {
		if(serviceRegistry!=null)
		context.ungetService(serviceRegistry.getReference());

	}

}
