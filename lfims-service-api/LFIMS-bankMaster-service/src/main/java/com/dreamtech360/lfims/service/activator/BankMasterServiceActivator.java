package com.dreamtech360.lfims.service.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.service.impl.bankmaster.BankMasterServiceFactory;

public class BankMasterServiceActivator implements BundleActivator {

	private ServiceRegistration serviceRegistry=null;
	
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		BankMasterServiceFactory serviceFactory=new BankMasterServiceFactory(context);
		serviceRegistry=context.registerService(BankMasterServiceFactory.class.getName(), serviceFactory, null);
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		if(serviceRegistry!=null)
		context.ungetService(serviceRegistry.getReference());

	}

}
