package com.dreamtech360.lfims.service.cachemanagement;

import java.util.Properties;
 
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.xa.XAResource;

import net.sf.ehcache.transaction.manager.TransactionManagerLookup;
import net.sf.ehcache.transaction.xa.EhcacheXAResource;

public class LFIMSTransactionManagerLookup implements TransactionManagerLookup {

	public void register(EhcacheXAResource arg0) {
		try {
			getTransactionManager().getTransaction().enlistResource(arg0);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub

	}

	public TransactionManager getTransactionManager() {
		return LFIMSCacheManagementService.getTxnManager();
	}

	public void unregister(EhcacheXAResource arg0) {
		try {
			getTransactionManager().getTransaction().delistResource(arg0,XAResource.TMSUCCESS);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
