package com.dreamtech360.lfims.services;

import com.dreamtech360.lfims.service.cachemanagement.LFIMSCacheManagementServiceFactory;
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
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementServiceFactory;

/*public enum ServiceMap {

	    BANK_MASTER_SERVICE (BankMaster.class.toString());

	    private final String serviceName;   // The service name that manipulates this model
	  
	    ServiceMap(String serviceName) {
	        this.serviceName = serviceName;
	       
	    }
	    public String serviceName() { return serviceName; }
	  
}*/

public enum ServiceEnum{
	BANK_MASTER_SERVICE(BankMasterServiceFactory.class.getName()),
	BRANCH_MASTER_SERVICE(BranchMasterServiceFactory.class.getName()),
	ADVOCATE_MASTER_SERVICE(AdvocateMasterServiceFactory.class.getName()),
	OUR_ADVOCATE_MASTER_SERVICE(OurAdvocateMasterServiceFactory.class.getName()),
	COURT_MASTER_SERVICE(CourtMasterServiceFactory.class.getName()),
	EXPENSES_MASTER_SERVICE(ExpensesMasterServiceFactory.class.getName()),
	NDP_MASTER_SERVICE(NdpMasterServiceFactory.class.getName()),
	CASE_MGMT_MAINTENANCE(CaseMgmtMaintenanceServiceFactory.class.getName()),
	CASE_DEFENDENT_DETAILS(CaseDefendentDetailsServiceFactory.class.getName()),
	CASE_DIARY(CaseDiaryServiceFactory.class.getName()),
	CASE_IMPORTANT_DOCUMENTS(CaseImportantDocumentsServiceFactory.class.getName()),
	CASE_MASTER(CaseMasterServiceFactory.class.getName()),
	CASE_SECURITY_DETAILS(CaseSecurityDetailsServiceFactory.class.getName()),
	CACHE_MANAGEMENT_SERVICE(LFIMSCacheManagementServiceFactory.class.getName()),
	TRANSACTION_MANAGEMENT_SERVICE(LFIMSTransactionManagementServiceFactory.class.getName());
	
	private final String factoryName;

	public String getFactoryName(){
		return factoryName;
	}
	
	ServiceEnum(String factoryName){
		this.factoryName=factoryName;
	
	}
};
 