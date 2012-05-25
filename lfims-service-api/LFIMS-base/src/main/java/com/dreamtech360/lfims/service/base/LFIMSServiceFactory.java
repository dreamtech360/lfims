package com.dreamtech360.lfims.service.base;

import com.dreamtech360.lfims.model.service.base.LFIMSModelService;

public abstract class LFIMSServiceFactory<T> {

	protected LFIMSModelService<T> service=null;
	
	
	public abstract LFIMSModelService<T> createService();
	public abstract LFIMSModelService<T> lookupService();
	
	
	

}
