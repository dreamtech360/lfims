package com.dreamtech360.lfims.resources.api.activator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.dreamtech360.lfims.service.impl.advocatemaster.AdvocateMasterServiceFactory;
import com.dreamtech360.lfims.service.impl.bankmaster.BankMasterServiceFactory;
import com.dreamtech360.lfims.service.impl.branchmaster.BranchMasterServiceFactory;
import com.dreamtech360.lfims.service.impl.casemanagement.CaseDefendentDetailsServiceFactory;
import com.dreamtech360.lfims.service.impl.casemanagement.CaseDiaryServiceFactory;
import com.dreamtech360.lfims.service.impl.casemanagement.CaseImportantDocumentsServiceFactory;
import com.dreamtech360.lfims.service.impl.casemanagement.CaseMasterServiceFactory;
import com.dreamtech360.lfims.service.impl.casemanagement.CaseSecurityDetailsServiceFactory;
import com.dreamtech360.lfims.service.impl.casemgmtmaintenance.CaseMgmtMaintenanceServiceFactory;
import com.dreamtech360.lfims.service.impl.courtmaster.CourtMasterServiceFactory;
import com.dreamtech360.lfims.service.impl.expensesmaster.ExpensesMasterServiceFactory;
import com.dreamtech360.lfims.service.impl.ndpmaster.NdpMasterServiceFactory;
import com.dreamtech360.lfims.service.impl.ouradvocatemaster.OurAdvocateMasterServiceFactory;
import com.dreamtech360.lfims.services.LFIMSServiceFactoryLocator;
import com.dreamtech360.lfims.services.ServiceEnum;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class LFIMSAPIActivator implements BundleActivator {

	private List<ServiceReference> serviceReference=null;
	@Override
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		serviceReference=new ArrayList<ServiceReference>();
		
		serviceReference.add(context.getServiceReference(BankMasterServiceFactory.class.getName()));
		BankMasterServiceFactory bankMasterServiceFactory=(BankMasterServiceFactory)context.getService(serviceReference.get(0));
		LFIMSServiceFactoryLocator.registerServiceFactory(ServiceEnum.BANK_MASTER_SERVICE, bankMasterServiceFactory);
		
		serviceReference.add(context.getServiceReference(BranchMasterServiceFactory.class.getName()));
		BranchMasterServiceFactory branchMasterServiceFactory=(BranchMasterServiceFactory)context.getService(serviceReference.get(1));
		LFIMSServiceFactoryLocator.registerServiceFactory(ServiceEnum.BRANCH_MASTER_SERVICE, branchMasterServiceFactory);
		
		serviceReference.add(context.getServiceReference(AdvocateMasterServiceFactory.class.getName()));
		AdvocateMasterServiceFactory advocateMasterServiceFactory=(AdvocateMasterServiceFactory)context.getService(serviceReference.get(2));
		LFIMSServiceFactoryLocator.registerServiceFactory(ServiceEnum.ADVOCATE_MASTER_SERVICE, advocateMasterServiceFactory);
		
		serviceReference.add(context.getServiceReference(OurAdvocateMasterServiceFactory.class.getName()));
		OurAdvocateMasterServiceFactory ourAdvocateMasterServiceFactory=(OurAdvocateMasterServiceFactory)context.getService(serviceReference.get(3));
		LFIMSServiceFactoryLocator.registerServiceFactory(ServiceEnum.OUR_ADVOCATE_MASTER_SERVICE, ourAdvocateMasterServiceFactory);
		
		serviceReference.add(context.getServiceReference(CourtMasterServiceFactory.class.getName()));
		CourtMasterServiceFactory courtMasterServiceFactory=(CourtMasterServiceFactory)context.getService(serviceReference.get(4));
		LFIMSServiceFactoryLocator.registerServiceFactory(ServiceEnum.COURT_MASTER_SERVICE, courtMasterServiceFactory);
		
		serviceReference.add(context.getServiceReference(ExpensesMasterServiceFactory.class.getName()));
		ExpensesMasterServiceFactory expensesMasterServiceFactory=(ExpensesMasterServiceFactory)context.getService(serviceReference.get(5));
		LFIMSServiceFactoryLocator.registerServiceFactory(ServiceEnum.EXPENSES_MASTER_SERVICE, expensesMasterServiceFactory);
		
		serviceReference.add(context.getServiceReference(NdpMasterServiceFactory.class.getName()));
		NdpMasterServiceFactory ndpMasterServiceFactory=(NdpMasterServiceFactory)context.getService(serviceReference.get(6));
		LFIMSServiceFactoryLocator.registerServiceFactory(ServiceEnum.NDP_MASTER_SERVICE, ndpMasterServiceFactory);
		
		serviceReference.add(context.getServiceReference(CaseMgmtMaintenanceServiceFactory.class.getName()));
		CaseMgmtMaintenanceServiceFactory caseMgmtMaintenanceServiceFactory=(CaseMgmtMaintenanceServiceFactory)context.getService(serviceReference.get(7));
		LFIMSServiceFactoryLocator.registerServiceFactory(ServiceEnum.CASE_MGMT_MAINTENANCE, caseMgmtMaintenanceServiceFactory);
		
		serviceReference.add(context.getServiceReference(CaseMasterServiceFactory.class.getName()));
		CaseMasterServiceFactory caseMasterServiceFactory=(CaseMasterServiceFactory)context.getService(serviceReference.get(8));
		LFIMSServiceFactoryLocator.registerServiceFactory(ServiceEnum.CASE_MASTER, caseMasterServiceFactory);
		
		serviceReference.add(context.getServiceReference(CaseDefendentDetailsServiceFactory.class.getName()));
		CaseDefendentDetailsServiceFactory caseDefendentDetailsServiceFactory=(CaseDefendentDetailsServiceFactory)context.getService(serviceReference.get(9));
		LFIMSServiceFactoryLocator.registerServiceFactory(ServiceEnum.CASE_DEFENDENT_DETAILS, caseDefendentDetailsServiceFactory);
		
		serviceReference.add(context.getServiceReference(CaseDiaryServiceFactory.class.getName()));
		CaseDiaryServiceFactory caseDiaryServiceFactory=(CaseDiaryServiceFactory)context.getService(serviceReference.get(10));
		LFIMSServiceFactoryLocator.registerServiceFactory(ServiceEnum.CASE_DIARY, caseDiaryServiceFactory);
		
		serviceReference.add(context.getServiceReference(CaseImportantDocumentsServiceFactory.class.getName()));
		CaseImportantDocumentsServiceFactory caseImportantDocumentsServiceFactory=(CaseImportantDocumentsServiceFactory)context.getService(serviceReference.get(11));
		LFIMSServiceFactoryLocator.registerServiceFactory(ServiceEnum.CASE_IMPORTANT_DOCUMENTS, caseImportantDocumentsServiceFactory);
		
		serviceReference.add(context.getServiceReference(CaseSecurityDetailsServiceFactory.class.getName()));
		CaseSecurityDetailsServiceFactory caseSecurityDetailsServiceFactory=(CaseSecurityDetailsServiceFactory)context.getService(serviceReference.get(12));
		LFIMSServiceFactoryLocator.registerServiceFactory(ServiceEnum.CASE_SECURITY_DETAILS, caseSecurityDetailsServiceFactory);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if(serviceReference!=null){
			Iterator<ServiceReference> serviceReferenceIterator=serviceReference.iterator();
			while(serviceReferenceIterator.hasNext()){
				context.ungetService(serviceReferenceIterator.next());
			}
		}
		serviceReference=null;
		
	} 
}
