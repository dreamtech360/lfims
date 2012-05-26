package com.dreamtech360.lfims.service.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.service.impl.ndpmaster.NdpMasterServiceFactory;

public class NdpMasterServiceActivator implements BundleActivator {

	private ServiceRegistration serviceRegistry=null;
	
	public void start(BundleContext context) throws Exception {
		NdpMasterServiceFactory serviceFactory=new NdpMasterServiceFactory(context);
		serviceRegistry=context.registerService(NdpMasterServiceFactory.class.getName(), serviceFactory, null);
	}

	public void stop(BundleContext context) throws Exception {
		if(serviceRegistry!=null)
		context.ungetService(serviceRegistry.getReference());

	}

}
