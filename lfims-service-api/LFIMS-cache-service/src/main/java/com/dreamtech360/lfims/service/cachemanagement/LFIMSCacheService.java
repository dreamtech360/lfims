package com.dreamtech360.lfims.service.cachemanagement;

import java.util.Collection;
import java.util.Set;

import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;
import com.dreamtech360.lfims.service.cachemanagement.LFIMSCacheManager.LFIMSCache;
import com.dreamtech360.lfims.service.cachemanagement.LFIMSCacheManager.LFIMSCache.Entry;




public interface LFIMSCacheService {

	public LFIMSCache getCache(String cacheName);
	public <T,V> Set<LFIMSObject<Entry<T,V>>> getCacheEntries(String cacheName) throws LFIMSServiceException;
	public <T> Collection<T> getCacheKeys(String cacheName) throws LFIMSServiceException;
	public <T,V> Collection<V> getCacheObject(String cacheName, T key) throws LFIMSServiceException;
	public <T> void cacheObject(LFIMSObject<T> object) throws LFIMSServiceException ;
	public <T> void updateCacheObject(LFIMSObject<T> oldObject,LFIMSObject<T> newObject) throws LFIMSServiceException;

}
