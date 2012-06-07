package com.dreamtech360.lfims.service.transactionmanagement;

import javax.transaction.TransactionManager;

import com.dreamtech360.lfims.model.service.base.LFIMSGenericService;

public class LFIMSTransactionManagementService implements LFIMSGenericService<LFIMSTransactionManager> {

	
	public TransactionManager getTransactionManager(){
		
		return LFIMSTransactionManager.getTxManager();
		
	}
	
	
}
