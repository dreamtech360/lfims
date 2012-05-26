package com.dreamtech360.lfims.service.activator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.service.impl.casemanagement.CaseDefendentDetailsServiceFactory;
import com.dreamtech360.lfims.service.impl.casemanagement.CaseDiaryServiceFactory;
import com.dreamtech360.lfims.service.impl.casemanagement.CaseImportantDocumentsServiceFactory;
import com.dreamtech360.lfims.service.impl.casemanagement.CaseMasterServiceFactory;
import com.dreamtech360.lfims.service.impl.casemanagement.CaseSecurityDetailsServiceFactory;

public class CaseManagementServiceActivator implements BundleActivator {

	private List<ServiceRegistration> serviceRegistryList=new ArrayList<ServiceRegistration>();
	
	public void start(BundleContext context) throws Exception {
	
		CaseMasterServiceFactory serviceFactory=new CaseMasterServiceFactory(context);
		serviceRegistryList.add(context.registerService(CaseMasterServiceFactory.class.getName(), serviceFactory, null));
		
		CaseDefendentDetailsServiceFactory caseDefendentDetailsServiceFactory=new CaseDefendentDetailsServiceFactory(context);
		serviceRegistryList.add(context.registerService(CaseDefendentDetailsServiceFactory.class.getName(), caseDefendentDetailsServiceFactory, null));
		
		CaseDiaryServiceFactory caseDiaryServiceFactory=new CaseDiaryServiceFactory(context);
		serviceRegistryList.add(context.registerService(CaseDiaryServiceFactory.class.getName(), caseDiaryServiceFactory, null));
		
		CaseImportantDocumentsServiceFactory caseImportantDocumentsServiceFactory=new CaseImportantDocumentsServiceFactory(context);
		serviceRegistryList.add(context.registerService(CaseImportantDocumentsServiceFactory.class.getName(), caseImportantDocumentsServiceFactory, null));
		
		CaseSecurityDetailsServiceFactory caseSecurityDetailsServiceFactory=new CaseSecurityDetailsServiceFactory(context);
		serviceRegistryList.add(context.registerService(CaseSecurityDetailsServiceFactory.class.getName(), caseSecurityDetailsServiceFactory, null));
		
		
	}

	public void stop(BundleContext context) throws Exception {
	
		if(serviceRegistryList!=null){
			Iterator<ServiceRegistration> iterator=serviceRegistryList.iterator();
			while(iterator.hasNext()){
				ServiceRegistration serviceRegistration=iterator.next();
				context.ungetService(serviceRegistration.getReference());
			}
		}
		

	}

}
