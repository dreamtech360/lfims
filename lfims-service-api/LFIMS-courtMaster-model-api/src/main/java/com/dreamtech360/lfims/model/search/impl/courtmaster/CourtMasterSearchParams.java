package com.dreamtech360.lfims.model.search.impl.courtmaster;

import com.dreamtech360.lfims.model.api.courtmaster.CourtMaster;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.SearchParams;


public class CourtMasterSearchParams extends SearchParams<CourtMaster> {

	
	protected final LFIMSAttributeMapper<CourtMaster> getSearchCriteriaKey(String attributeName){

		LFIMSAttributeMapper<CourtMaster> key=null;
		if(attributeName.equals(CourtMasterAttributeMapper.ID.getAttributeName()))
			key=CourtMasterAttributeMapper.ID;
		else if(attributeName.equals(CourtMasterAttributeMapper.ADDRESS_1.getAttributeName()))
			key=CourtMasterAttributeMapper.ADDRESS_1;
		else if(attributeName.equals(CourtMasterAttributeMapper.ADDRESS_2.getAttributeName()))
			key=CourtMasterAttributeMapper.ADDRESS_2;
		else if (attributeName.equals(CourtMasterAttributeMapper.NAME.getAttributeName()))
			key=CourtMasterAttributeMapper.NAME;
		else if (attributeName.equals(CourtMasterAttributeMapper.FULL_NAME.getAttributeName()))
			key=CourtMasterAttributeMapper.FULL_NAME;
		else if (attributeName.equals(CourtMasterAttributeMapper.CITY_PIN.getAttributeName()))
			key=CourtMasterAttributeMapper.CITY_PIN;
		else if(attributeName.equals(CourtMasterAttributeMapper.CONTACT_PHONE.getAttributeName()))
			key=CourtMasterAttributeMapper.CONTACT_PHONE;
		
		System.out.println(key.getKeyName());
		return key;
	}




}
