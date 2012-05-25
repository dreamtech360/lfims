package com.dreamtech360.lfims.model.search.impl.casemanagement;

import com.dreamtech360.lfims.model.api.casemanagement.SecurityDetails;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.SearchParams;


public class SecurityDetailsSearchParams extends SearchParams<SecurityDetails> {

	
	protected final LFIMSAttributeMapper<SecurityDetails> getSearchCriteriaKey(String attributeName){

		LFIMSAttributeMapper<SecurityDetails> key=null;
		if(attributeName.equals(SecurityDetailsAttributeMapper.ID.getAttributeName()))
			key=SecurityDetailsAttributeMapper.ID;
		else if(attributeName.equals(SecurityDetailsAttributeMapper.SECURITY_DETAILS.getAttributeName()))
			key=SecurityDetailsAttributeMapper.SECURITY_DETAILS;
		
				
		System.out.println(key.getKeyName());
		return key;
	}




}
