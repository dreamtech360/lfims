package com.dreamtech360.lfims.model.search.impl.ouradvocatemaster;

import com.dreamtech360.lfims.model.api.ouradvocatemaster.OurAdvocateMaster;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.SearchParams;


public class OurAdvocateMasterSearchParams extends SearchParams<OurAdvocateMaster> {

	
	protected final LFIMSAttributeMapper<OurAdvocateMaster> getSearchCriteriaKey(String attributeName){

		LFIMSAttributeMapper<OurAdvocateMaster> key=null;
		if(attributeName.equals(OurAdvocateMasterAttributeMapper.ID.getAttributeName()))
			key=OurAdvocateMasterAttributeMapper.ID;
		else if(attributeName.equals(OurAdvocateMasterAttributeMapper.ADDRESS_1.getAttributeName()))
			key=OurAdvocateMasterAttributeMapper.ADDRESS_1;
		else if(attributeName.equals(OurAdvocateMasterAttributeMapper.ADDRESS_2.getAttributeName()))
			key=OurAdvocateMasterAttributeMapper.ADDRESS_2;
		else if (attributeName.equals(OurAdvocateMasterAttributeMapper.ADV_NAME.getAttributeName()))
			key=OurAdvocateMasterAttributeMapper.ADV_NAME;
		else if (attributeName.equals(OurAdvocateMasterAttributeMapper.CITY_PIN.getAttributeName()))
			key=OurAdvocateMasterAttributeMapper.CITY_PIN;
		else if(attributeName.equals(OurAdvocateMasterAttributeMapper.CONTACT_PHONE.getAttributeName()))
			key=OurAdvocateMasterAttributeMapper.CONTACT_PHONE;
		else if(attributeName.equals(OurAdvocateMasterAttributeMapper.EMAIL.getAttributeName()))
			key=OurAdvocateMasterAttributeMapper.EMAIL;
		System.out.println(key.getKeyName());
		return key;
	}




}
