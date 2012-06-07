package com.dreamtech360.lfims.services;

import java.util.HashMap;
import java.util.Map;
import com.dreamtech360.lfims.service.base.LFIMSGenericServiceFactory;

public  class LFIMSServiceFactoryLocator<T> {
	
	private  Map<ServiceEnum,LFIMSGenericServiceFactory<T>> servicesRef=new  HashMap<ServiceEnum,LFIMSGenericServiceFactory<T>>();
	
	public   LFIMSGenericServiceFactory<T> getServiceFactory(ServiceEnum key){
		
		return servicesRef.get(key);
	}
	
	public  void registerServiceFactory(ServiceEnum key,LFIMSGenericServiceFactory<T> serviceFactory){
		servicesRef.put(key, serviceFactory);
	}
	
}
