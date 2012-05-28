package com.dreamtech360.lfims.services;

/*public enum ServiceMap {

	    BANK_MASTER_SERVICE (BankMaster.class.toString());

	    private final String serviceName;   // The service name that manipulates this model
	  
	    ServiceMap(String serviceName) {
	        this.serviceName = serviceName;
	       
	    }
	    public String serviceName() { return serviceName; }
	  
}*/

public enum ServiceEnum{
	BANK_MASTER_SERVICE,
	BRANCH_MASTER_SERVICE,
	ADVOCATE_MASTER_SERVICE,
	OUR_ADVOCATE_MASTER_SERVICE,
	COURT_MASTER_SERVICE,
	EXPENSES_MASTER_SERVICE,
	NDP_MASTER_SERVICE,
	CASE_MGMT_MAINTENANCE,
	CASE_DEFENDENT_DETAILS,
	CASE_DIARY,
	CASE_IMPORTANT_DOCUMENTS,
	CASE_MASTER,
	CASE_SECURITY_DETAILS
};
 