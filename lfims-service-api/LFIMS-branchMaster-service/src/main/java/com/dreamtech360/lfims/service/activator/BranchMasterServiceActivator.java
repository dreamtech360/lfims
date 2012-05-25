package com.dreamtech360.lfims.service.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.service.impl.branchmaster.BranchMasterServiceFactory;

public class BranchMasterServiceActivator implements BundleActivator {

	private ServiceRegistration serviceRegistry=null;
	
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		BranchMasterServiceFactory serviceFactory=new BranchMasterServiceFactory(context);
		serviceRegistry=context.registerService(BranchMasterServiceFactory.class.getName(), serviceFactory, null);
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		if(serviceRegistry!=null)
		context.ungetService(serviceRegistry.getReference());

	}

}
