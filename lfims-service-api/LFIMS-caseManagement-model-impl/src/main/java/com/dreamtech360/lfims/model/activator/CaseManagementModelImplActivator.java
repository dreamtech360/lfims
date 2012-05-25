package com.dreamtech360.lfims.model.activator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Repository;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.dreamtech360.lfims.model.service.impl.casemanagement.CaseDefendentDetailsService;
import com.dreamtech360.lfims.model.service.impl.casemanagement.CaseDiaryService;
import com.dreamtech360.lfims.model.service.impl.casemanagement.CaseImportantDocumentsService;
import com.dreamtech360.lfims.model.service.impl.casemanagement.CaseMasterService;
import com.dreamtech360.lfims.model.service.impl.casemanagement.CaseSecurityDetailsService;

public class CaseManagementModelImplActivator implements BundleActivator {

	private List<ServiceRegistration> serviceRegistrationList=new ArrayList<ServiceRegistration>();
	private Repository repository=null;
	
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		repository=(Repository)context.getService(context.getServiceReference(Repository.class.getName()));
		CaseMasterService serviceImpl=new CaseMasterService(repository);
		System.out.println("*******************************"+CaseMasterService.class.toString());	
		serviceRegistrationList.add(context.registerService(CaseMasterService.class.getName(), serviceImpl, null));
		
		CaseDefendentDetailsService caseDefendentDetailsServiceImpl=new CaseDefendentDetailsService(repository);
		System.out.println("*******************************"+CaseDefendentDetailsService.class.toString());
		serviceRegistrationList.add(context.registerService(CaseDefendentDetailsService.class.getName(), caseDefendentDetailsServiceImpl, null));
		
		CaseDiaryService caseDiaryServiceImpl=new CaseDiaryService(repository);
		System.out.println("*******************************"+CaseDiaryService.class.toString());
		serviceRegistrationList.add(context.registerService(CaseDiaryService.class.getName(), caseDiaryServiceImpl, null));
		
		CaseImportantDocumentsService caseImportantDocumentsServiceImpl=new CaseImportantDocumentsService(repository);
		System.out.println("*******************************"+CaseImportantDocumentsService.class.toString());
		serviceRegistrationList.add(context.registerService(CaseImportantDocumentsService.class.getName(), caseImportantDocumentsServiceImpl, null));
		
		CaseSecurityDetailsService caseSecurityDetailsServiceImpl=new CaseSecurityDetailsService(repository);
		System.out.println("*******************************"+CaseSecurityDetailsService.class.toString());
		serviceRegistrationList.add(context.registerService(CaseSecurityDetailsService.class.getName(), caseSecurityDetailsServiceImpl, null));
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		if(serviceRegistrationList!=null){
			
			Iterator<ServiceRegistration> iterator=serviceRegistrationList.iterator();
			while(iterator.hasNext()){
				ServiceRegistration serviceRegistration=iterator.next();
				context.ungetService(serviceRegistration.getReference());
			}
		}
				
		serviceRegistrationList=null;
		

	}

}
