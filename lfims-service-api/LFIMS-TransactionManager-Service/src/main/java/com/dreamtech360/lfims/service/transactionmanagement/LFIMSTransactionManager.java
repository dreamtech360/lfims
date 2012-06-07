package com.dreamtech360.lfims.service.transactionmanagement;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.TransactionManager;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.FactoryConfiguration;
import net.sf.ehcache.constructs.blocking.BlockingCache;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;


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
	
		try {
			Context ictx = new InitialContext();
			manager= (TransactionManager)ictx.lookup("TransactionManager");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	
	}


}
