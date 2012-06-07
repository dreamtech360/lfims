package com.dreamtech360.lfims.service.base;

import com.dreamtech360.lfims.model.service.base.LFIMSGenericService;

public interface LFIMSGenericServiceFactory<T> {

	public  LFIMSGenericService<T> createService();
	public  LFIMSGenericService<T> lookupService();
//	public LFIMSGenericService createTxnService();
//	public LFIMSGenericService lookupTxnService();
}
