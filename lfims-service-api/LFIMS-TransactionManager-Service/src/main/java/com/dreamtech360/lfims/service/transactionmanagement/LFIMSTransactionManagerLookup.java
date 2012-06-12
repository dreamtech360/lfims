package com.dreamtech360.lfims.service.transactionmanagement;

import java.util.Properties;
import javax.transaction.xa.XAResource;
import javax.transaction.*;
import net.sf.ehcache.transaction.manager.TransactionManagerLookup;
import net.sf.ehcache.transaction.xa.EhcacheXAResource;

public class LFIMSTransactionManagerLookup implements TransactionManagerLookup {

		
	
	public void register(EhcacheXAResource arg0) {
		
			try {
				LFIMSTransactionManager.getTxManager().getTransaction().enlistResource(arg0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}

	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub

	}

	
	public void unregister(EhcacheXAResource arg0) {
		
			try {
				LFIMSTransactionManager.getTxManager().getTransaction().delistResource((XAResource)arg0,XAResource.TMSUCCESS);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
	}

	public TransactionManager getTransactionManager() {
		// TODO Auto-generated method stub
		return LFIMSTransactionManager.getTxManager();
	}

}
