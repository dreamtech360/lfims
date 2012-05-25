package com.dreamtech360.lfims.model.search.impl.advocatemaster;

import com.dreamtech360.lfims.model.api.advocatemaster.AdvocateMaster;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.SearchParams;


public class AdvocateMasterSearchParams extends SearchParams<AdvocateMaster> {

	
	protected final LFIMSAttributeMapper<AdvocateMaster> getSearchCriteriaKey(String attributeName){

		LFIMSAttributeMapper<AdvocateMaster> key=null;
		if(attributeName.equals(AdvocateMasterAttributeMapper.ID.getAttributeName()))
			key=AdvocateMasterAttributeMapper.ID;
		else if(attributeName.equals(AdvocateMasterAttributeMapper.ADDRESS_1.getAttributeName()))
			key=AdvocateMasterAttributeMapper.ADDRESS_1;
		else if(attributeName.equals(AdvocateMasterAttributeMapper.ADDRESS_2.getAttributeName()))
			key=AdvocateMasterAttributeMapper.ADDRESS_2;
		else if (attributeName.equals(AdvocateMasterAttributeMapper.ADV_NAME.getAttributeName()))
			key=AdvocateMasterAttributeMapper.ADV_NAME;
		else if (attributeName.equals(AdvocateMasterAttributeMapper.CITY_PIN.getAttributeName()))
			key=AdvocateMasterAttributeMapper.CITY_PIN;
		else if(attributeName.equals(AdvocateMasterAttributeMapper.CONTACT_PHONE.getAttributeName()))
			key=AdvocateMasterAttributeMapper.CONTACT_PHONE;
		else if(attributeName.equals(AdvocateMasterAttributeMapper.EMAIL.getAttributeName()))
			key=AdvocateMasterAttributeMapper.EMAIL;
		System.out.println(key.getKeyName());
		return key;
	}




}
