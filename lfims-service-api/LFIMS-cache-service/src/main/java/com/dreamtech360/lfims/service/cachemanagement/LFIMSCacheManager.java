package com.dreamtech360.lfims.service.cachemanagement;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set; 

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
public class LFIMSCacheManager {

	private static CacheManager cachemanager = null;
//	private static TransactionManager txnManager=null;
	
	static { 
		net.sf.ehcache.config.Configuration cacheManagerConfig=new net.sf.ehcache.config.Configuration();
		FactoryConfiguration factoryConfig=new FactoryConfiguration();
	//	System.out.println(LFIMSTransactionManagerLookup.class.getName());
		factoryConfig.setClass(LFIMSCacheManagementService.getTxnManagerService().getTransactionanagerLookup());
		cacheManagerConfig.addTransactionManagerLookup(factoryConfig);
		cachemanager = new CacheManager(cacheManagerConfig);
	//	cachemanager = new CacheManager();
		
	
	}

	// This is the factory method the get the cache instance
	public static  LFIMSCache getCache(String name,boolean createIfNotFound) {

		LFIMSCache cache = null;

		Ehcache tempValue=cachemanager.getCache(name);

		if (tempValue==null && createIfNotFound)
			cache = new LFIMSCache(name);
		else if(tempValue!=null){
			cache = new LFIMSCache(tempValue);

		}
		return cache;
	}

	public static class LFIMSCache {

		private Ehcache cache = null;

		public LFIMSCache(Ehcache tempValue) {
			this.cache = new BlockingCache(tempValue);
		}

		public LFIMSCache(String cacheName) {
			CacheConfiguration config=new CacheConfiguration(cacheName, 1000);
			config.transactionalMode("xa");
			config.eternal(true).memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.FIFO);
			Cache tempCache = new Cache(config);
			
			cachemanager.addCache(tempCache);
			this.cache = new BlockingCache(tempCache);
		}

		public <T,V> void delete(LFIMSCacheElement<T,V> value) throws LFIMSServiceException{
			Element data=cache.get(value.key);
			if(data==null)
				throw new LFIMSServiceException("Key "+value.key+" not found in cache "+cache.getName()+" while deleting");
			
			((Set<V>)data.getObjectValue()).remove(value.value);
			
			
		}
		
		
		public <T,V> void put(LFIMSCacheElement<T,V> value) {

			Element data=cache.get(value.key);
			if(data==null){
				HashSet<V> content=new HashSet<V>();
				content.add(value.value);
				cache.put(new Element(value.key,content));
			}else{
				((Set<V>)data.getObjectValue()).add(value.value);
			}
		}

		public <T,V> Set<LFIMSObject<Entry<T,V>>> getEntries(){
			
			List<T> keys= getKeys();
			Set<LFIMSObject<Entry<T,V>>> entries=new HashSet<LFIMSObject<Entry<T,V>>>();
			Iterator<T> keyIterator=keys.iterator();
			
			while(keyIterator.hasNext()){
				
				final T key=keyIterator.next();
				final Set<V> value=(Set<V>) cache.getQuiet(key).getObjectValue();
				
				LFIMSObject<Entry<T,V>> entry=new Entry<T,V>(){

					public T getKey() {
			
						return key;
					}

					public Set<V> getValue() {
					
						return value;
					}

				};
				
				entries.add(entry);
			}
			
			return entries;
		}
			
		public <T> List<T> getKeys(){

			List<T> keys=(List<T>)cache.getKeys();
			return keys;
		}

		public <T,V> Collection<V> get(T key) throws LFIMSServiceException{

			Element data=cache.getQuiet(key);

			if(data != null)
				return (Collection<V>)data.getObjectValue();

			return Collections.emptySet();
		}

		public class LFIMSCacheElement<T,V> {

			private T key;
			private V value;

			public LFIMSCacheElement(T key, V value) {
				this.key = key;
				this.value = value;
			}

			public boolean equals(Object input) {
				if (input instanceof LFIMSCacheManager.LFIMSCache.LFIMSCacheElement) {
					LFIMSCacheElement<T,V> testObject = (LFIMSCacheElement<T,V>) input;
					if (testObject.key instanceof Object) {
						if (this.key.equals(testObject.key))
							return true;
					} else {
						if (this.key == testObject.key)
							return true;
					}
				}
				return false;
			}

		}

		public abstract class Entry<K,V> implements LFIMSObject<Entry<K,V>> {

			public abstract K getKey();
			public abstract Set<V> getValue();
		
			public int compareTo(Entry<K, V> o) {
				String key=(String)o.getKey();
				return ((String)this.getKey()).compareToIgnoreCase(key) ;
			}

			public JSONObject getJSON() throws LFIMSModelException {
				
				JSONArray array=new JSONArray();
				JSONObject object=new JSONObject();
				
				try {
					object.put("name", this.getKey());
					
					Iterator<V> iterator=getValue().iterator();
					while(iterator.hasNext()){
						V value=iterator.next();
						array.put(value);
					}
					object.put("value", array);
					
				} catch (Exception e) {
					throw new LFIMSModelException(e);
				}
				return object;
			}

			public String getJSONString() throws LFIMSModelException {
				
				return getJSON().toString();
			}
	
			public boolean equals(Object o){
				
				if(o instanceof Entry){
					Entry<K,V> entry=(Entry<K,V>)o;
					if(this.getKey().equals(entry.getKey()))
						return true;
				}
				return false;
				
			}

			public int hashCode(){
				return 1;
			}
		}

	}



}
