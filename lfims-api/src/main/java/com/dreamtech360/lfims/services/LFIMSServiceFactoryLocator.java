package com.dreamtech360.lfims.services;

import java.util.HashMap;
import java.util.Map;
import com.dreamtech360.lfims.service.base.LFIMSServiceFactory;

public  class LFIMSServiceFactoryLocator {
	
	private static Map<ServiceEnum,LFIMSServiceFactory> servicesRef=new  HashMap<ServiceEnum,LFIMSServiceFactory>();
	
	public static LFIMSServiceFactory getServiceFactory(ServiceEnum key){
		
		return servicesRef.get(key);
	}
	
	public static void registerServiceFactory(ServiceEnum key,LFIMSServiceFactory serviceFactory){
		servicesRef.put(key, serviceFactory);
	}
	
}
