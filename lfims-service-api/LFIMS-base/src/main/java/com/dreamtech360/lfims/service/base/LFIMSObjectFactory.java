package com.dreamtech360.lfims.service.base;

import com.dreamtech360.lfims.model.base.LFIMSObject;

public interface LFIMSObjectFactory<T> {

	
	
	LFIMSObject<T> create();
	void destroy();
}
