package com.dreamtech360.lfims.service.cachemanagement;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;

import com.dreamtech360.lfims.annotations.LFIMSCacheEntry;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.service.base.LFIMSGenericService;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;
import com.dreamtech360.lfims.service.cachemanagement.LFIMSCacheManager.LFIMSCache;
import com.dreamtech360.lfims.service.cachemanagement.LFIMSCacheManager.LFIMSCache.Entry;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;


public class LFIMSCacheManagementService implements LFIMSCacheService,LFIMSGenericService<LFIMSCacheService> {

	private static LFIMSTransactionManagementService txnService=null;
	 
	public LFIMSCacheManagementService(LFIMSTransactionManagementService txnService){
		
		LFIMSCacheManagementService.txnService=txnService;
	}
	public static LFIMSTransactionManagementService getTxnManagerService(){
		return LFIMSCacheManagementService.txnService;
	}
	public LFIMSCache getCache(String cacheName){
		
		LFIMSCache cache=LFIMSCacheManager.getCache(cacheName,true);
		return cache;
		
	}
	
	public <T,V> Set<LFIMSObject<Entry<T,V>>> getCacheEntries(String cacheName) throws LFIMSServiceException{

		LFIMSCache cache=LFIMSCacheManager.getCache(cacheName,false);
	
		if(cache==null)
			throw new LFIMSServiceException("No cache by name "+cacheName+" found in the cacheManager");
		
		Set<LFIMSObject<Entry<T,V>>> dataCollection =cache.getEntries();

		return dataCollection;
	}
	
	public <T> Collection<T> getCacheKeys(String cacheName) throws LFIMSServiceException{

		LFIMSCache cache=LFIMSCacheManager.getCache(cacheName,false);
	
		if(cache==null)
			throw new LFIMSServiceException("No cache by name "+cacheName+" found in the cacheManager");
		
		Collection<T> dataCollection =cache.getKeys();

		return dataCollection;
	}
	
	public <T,V> Collection<V> getCacheObject(String cacheName, T key) throws LFIMSServiceException{

		LFIMSCache cache=LFIMSCacheManager.getCache(cacheName,false);
	
		if(cache==null)
			throw new LFIMSServiceException("No cache by name "+cacheName+" found in the cacheManager");
		
		Collection<V> dataCollection =cache.get(key);

		return dataCollection;
	}

	public <T> void cacheObject(LFIMSObject<T> object) throws LFIMSServiceException {

		LFIMSCacheEntry entry=object.getClass().getAnnotation(LFIMSCacheEntry.class);
		try{
			if(entry !=null){
				//Its a class level annotation hence the cache will store objects 
				LFIMSCache cache=LFIMSCacheManager.getCache(entry.cacheName(),true);
				cache.put(cache.new LFIMSCacheElement<String,LFIMSObject<T>>(entry.valueField(),object)); 
			}else{
				//Iterate through each attribute of the object and check if Cache annotation is present
				AnnotatedElement[] elements =object.getClass().getDeclaredFields();
				for (AnnotatedElement elem : elements) {
					if(elem !=null && elem.isAnnotationPresent(LFIMSCacheEntry.class)){

						entry=elem.getAnnotation(LFIMSCacheEntry.class);
						Field field=(Field)elem;
						field.setAccessible(true);
						Field valueField=(object.getClass()).getDeclaredField(entry.valueField());
						valueField.setAccessible(true);
						String value=valueField.get(object).toString();

						LFIMSCache cache=LFIMSCacheManager.getCache(entry.cacheName(),true);
						cache.put( cache.new LFIMSCacheElement<String,String>(field.get(object).toString(),value));
					}
				}
			}

		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}
	
	
	public <T> void updateCacheObject(LFIMSObject<T> oldObject,LFIMSObject<T> newObject) throws LFIMSServiceException {

		LFIMSCacheEntry entry=newObject.getClass().getAnnotation(LFIMSCacheEntry.class);
		try{
			if(entry !=null){
				//Its a class level annotation hence the cache will store objects 
				LFIMSCache cache=LFIMSCacheManager.getCache(entry.cacheName(),true);
				cache.put(cache.new LFIMSCacheElement<String,LFIMSObject<T>>(entry.valueField(),newObject)); 
			}else{
				//Iterate through each attribute of the object and check if Cache annotation is present
				AnnotatedElement[] elements =newObject.getClass().getDeclaredFields();
				for (AnnotatedElement elem : elements) {
					if(elem !=null && elem.isAnnotationPresent(LFIMSCacheEntry.class)){

						entry=elem.getAnnotation(LFIMSCacheEntry.class);
						Field field=(Field)elem;
						field.setAccessible(true);
						//For update cases compare the values
						Field valueField=(newObject.getClass()).getDeclaredField(entry.valueField());
						valueField.setAccessible(true);
						String value=valueField.get(newObject).toString();
						
						LFIMSCache cache=LFIMSCacheManager.getCache(entry.cacheName(),true);
						String newCacheKey=field.get(newObject).toString();
						String oldCacheKey=field.get(oldObject).toString();
						if(!newCacheKey.equals(oldCacheKey)){
							cache.delete(cache.new LFIMSCacheElement<String,String>(oldCacheKey,value));
							cache.put( cache.new LFIMSCacheElement<String,String>(newCacheKey,value));
						}
						
					}
				}
			}

		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}
}
