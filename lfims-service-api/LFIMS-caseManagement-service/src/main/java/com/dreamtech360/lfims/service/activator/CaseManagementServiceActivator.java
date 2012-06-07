package com.dreamtech360.lfims.service.activator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Repository;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.service.impl.casemanagement.CaseDefendentDetailsServiceFactory;
import com.dreamtech360.lfims.service.impl.casemanagement.CaseDiaryServiceFactory;
import com.dreamtech360.lfims.service.impl.casemanagement.CaseImportantDocumentsServiceFactory;
import com.dreamtech360.lfims.service.impl.casemanagement.CaseMasterServiceFactory;
import com.dreamtech360.lfims.service.impl.casemanagement.CaseSecurityDetailsServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementServiceFactory;

public class CaseManagementServiceActivator implements BundleActivator {

	private List<ServiceRegistration> serviceRegistryList=new ArrayList<ServiceRegistration>();
	private Repository repository=null;
	private ServiceReference txnServiceRef=null;
	
	public void start(BundleContext context) throws Exception {
	
		
		txnServiceRef=context.getServiceReference(LFIMSTransactionManagementServiceFactory.class.getName());
		LFIMSTransactionManagementServiceFactory transactionManagementServiceFactory=(LFIMSTransactionManagementServiceFactory)context.getService(txnServiceRef);
		
		repository=(Repository)context.getService(context.getServiceReference(Repository.class.getName()));
		LFIMSTransactionManagementService txnService=(LFIMSTransactionManagementService)transactionManagementServiceFactory.lookupService();
		
		CaseMasterServiceFactory serviceFactory=new CaseMasterServiceFactory(context,repository,txnService);
		serviceRegistryList.add(context.registerService(CaseMasterServiceFactory.class.getName(), serviceFactory, null));
		
		CaseDefendentDetailsServiceFactory caseDefendentDetailsServiceFactory=new CaseDefendentDetailsServiceFactory(context,repository,txnService);
		serviceRegistryList.add(context.registerService(CaseDefendentDetailsServiceFactory.class.getName(), caseDefendentDetailsServiceFactory, null));
		
		CaseDiaryServiceFactory caseDiaryServiceFactory=new CaseDiaryServiceFactory(context,repository,txnService);
		serviceRegistryList.add(context.registerService(CaseDiaryServiceFactory.class.getName(), caseDiaryServiceFactory, null));
		
		CaseImportantDocumentsServiceFactory caseImportantDocumentsServiceFactory=new CaseImportantDocumentsServiceFactory(context,repository,txnService);
		serviceRegistryList.add(context.registerService(CaseImportantDocumentsServiceFactory.class.getName(), caseImportantDocumentsServiceFactory, null));
		
		CaseSecurityDetailsServiceFactory caseSecurityDetailsServiceFactory=new CaseSecurityDetailsServiceFactory(context,repository,txnService);
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
