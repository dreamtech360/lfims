package com.dreamtech360.lfims.service.transactionmanagement;

import javax.transaction.TransactionManager;

import org.objectweb.jotm.Jotm;


/* It is the Application's cache manager
 * Any Class of Attribute marked with LFIMSCacheElement will be cached into the caching layer of the application
 * If the annotation is at a TYPE then the complete object needs to be cached
 * If the anotation is at a FIELD then only the field values needs to be cached this is suitable for caching the field value to appear in PickLists 
 */
public class LFIMSTransactionManager {

	private static TransactionManager manager = null;

	public static TransactionManager getTxManager(){
		return manager;
		
	}
	static { 
	
		
		
		       Jotm jotm;
			try {
				jotm = new Jotm(true, false);
				 manager=jotm.getTransactionManager();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
		  
		
		
	/*	try {
			Context ictx = new InitialContext();
			manager= (TransactionManager)ictx.lookup("TransactionManager");
			
			System.out.println("Found the manager");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
	
	}


}
