package com.dreamtech360.lfims.resources.api.maintenance;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.dreamtech360.lfims.model.api.casemanagement.CaseMaster;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.service.base.LFIMSGenericService;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;
import com.dreamtech360.lfims.resources.api.activator.LFIMSAPIContext;
import com.dreamtech360.lfims.service.base.LFIMSGenericServiceFactory;
import com.dreamtech360.lfims.service.base.LFIMSModelServiceFactory;
import com.dreamtech360.lfims.service.cachemanagement.LFIMSCacheManager.LFIMSCache.Entry;
import com.dreamtech360.lfims.service.cachemanagement.LFIMSCacheService;
import com.dreamtech360.lfims.services.ServiceEnum;
import com.dreamtech360.lfims.util.LFIMSJSONStringer;

import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("/picklist/")
public class LFIMSPickListResource {
 
	private  LFIMSModelService<CaseMaster>   caseMasterService=null;
	private LFIMSGenericService<LFIMSCacheService> cacheService=null;


	public LFIMSPickListResource() throws LFIMSServiceException{
		System.out.println("LFIMSCacheManagementResource Constructor called");
		LFIMSGenericServiceFactory<LFIMSCacheService> cacheServiceFactory= LFIMSAPIContext.getGenericService(ServiceEnum.CACHE_MANAGEMENT_SERVICE);
		cacheService=cacheServiceFactory.createService();
		
		LFIMSModelServiceFactory<CaseMaster>  caseMasterServiceFactory= LFIMSAPIContext.getService(ServiceEnum.CASE_MASTER);
		caseMasterService=caseMasterServiceFactory.createTxnService();
		
		List<LFIMSObject<CaseMaster>> allCases=caseMasterService.loadAllRecord();
		
		Iterator<LFIMSObject<CaseMaster>> iterator=allCases.iterator();
		
		while(iterator.hasNext()){
			LFIMSObject<CaseMaster> caseMaster=iterator.next();
			((LFIMSCacheService) cacheService).cacheObject(caseMaster);
			
		}
		
	}  
	
	@GET  
	@Path("/cases/respondentNameList")
	@Produces({MediaType.TEXT_PLAIN}) 
	public <T,V> String getRespondentNameList() throws NumberFormatException, LFIMSServiceException, LFIMSModelException { 

		Map<String,String> jsonAttributes=new HashMap<String,String>();
		jsonAttributes.put("success", "true");
	
		Set<LFIMSObject<Entry<T, V>>> keysList=((LFIMSCacheService) cacheService).getCacheEntries("respondentName");
		jsonAttributes.put("results", String.valueOf(keysList.size()));
		LFIMSJSONStringer<Entry<T, V>> stringer=new LFIMSJSONStringer<Entry<T, V>>(keysList,jsonAttributes,"respondentNameList");
		
		return stringer.getJSONString();
				
	}
	
	
	@GET  
	@Path("/cases/caseNoList")
	@Produces({MediaType.TEXT_PLAIN}) 
	public <T,V> String getCaseIdList() throws NumberFormatException, LFIMSServiceException, LFIMSModelException { 

		Map<String,String> jsonAttributes=new HashMap<String,String>();
		jsonAttributes.put("success", "true");
	
		Set<LFIMSObject<Entry<T, V>>> keysList=((LFIMSCacheService) cacheService).getCacheEntries("caseNo");
		jsonAttributes.put("results", String.valueOf(keysList.size()));
		LFIMSJSONStringer<Entry<T, V>> stringer=new LFIMSJSONStringer<Entry<T, V>>(keysList,jsonAttributes,"caseNoList");
		
		return stringer.getJSONString();
				
	}
	
	
	@GET  
	@Path("/cases/rpmNoList")
	@Produces({MediaType.TEXT_PLAIN}) 
	public <T,V> String getRpmNoList() throws NumberFormatException, LFIMSServiceException, LFIMSModelException { 

		Map<String,String> jsonAttributes=new HashMap<String,String>();
		jsonAttributes.put("success", "true");
	
		Set<LFIMSObject<Entry<T, V>>> keysList=((LFIMSCacheService) cacheService).getCacheEntries("rpmaNo");
	
		jsonAttributes.put("results", String.valueOf(keysList.size()));
		LFIMSJSONStringer<Entry<T, V>> stringer=new LFIMSJSONStringer<Entry<T, V>>(keysList,jsonAttributes,"rpmNoList");
		
		return stringer.getJSONString();
				
	}
	
	
}