package com.dreamtech360.lfims.model.service.base;

import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.xa.XAResource;

public class LFIMSTransactionListner implements Synchronization {

	
	public void afterCompletion(int arg0) {
		// TODO Auto-generated method stub
		//saveSessionData();
		System.out.println("Transaction completion called");

		/*if(txnManager !=null)
			try {
				XAResource xaResource=(XAResource)LFIMSJCRSessionThreadLocal.get().getSession();
				txnManager.getTransaction().delistResource(xaResource, arg0);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

		if(LFIMSJCRSessionThreadLocal.get() !=null){
			LFIMSJCRSessionThreadLocal.get().getSession().logout();
			LFIMSJCRSessionThreadLocal.unset();
		}

	}

	public void beforeCompletion() {
		// TODO Auto-generated method stub

	}

}
