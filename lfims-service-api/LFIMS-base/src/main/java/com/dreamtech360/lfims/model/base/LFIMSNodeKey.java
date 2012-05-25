package com.dreamtech360.lfims.model.base;

import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;

public interface LFIMSNodeKey<T> {

	public String getKeyName();
	public int getKeyType();
	public LFIMSAttributeMapper<T> getSearchCriteria();
}
