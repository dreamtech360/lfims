package com.dreamtech360.lfims.model.search.impl.bankmaster;

import com.dreamtech360.lfims.model.api.bankmaster.BankMaster;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.SearchParams;


public class BankMasterSearchParams extends SearchParams<BankMaster> {

	
	protected final LFIMSAttributeMapper<BankMaster> getSearchCriteriaKey(String attributeName){

		LFIMSAttributeMapper<BankMaster> key=null;
		if(attributeName.equals(BankMasterAttributeMapper.ID.getAttributeName()))
			key=BankMasterAttributeMapper.ID;
		else if(attributeName.equals(BankMasterAttributeMapper.NAME.getAttributeName()))
			key=BankMasterAttributeMapper.NAME;
		else if(attributeName.equals(BankMasterAttributeMapper.FULL_NAME.getAttributeName()))
			key=BankMasterAttributeMapper.FULL_NAME;
		else if (attributeName.equals(BankMasterAttributeMapper.HEAD_OFFICE.getAttributeName()))
			key=BankMasterAttributeMapper.HEAD_OFFICE;
		else if (attributeName.equals(BankMasterAttributeMapper.CONTACT_PERSON.getAttributeName()))
			key=BankMasterAttributeMapper.CONTACT_PERSON;
		else if(attributeName.equals(BankMasterAttributeMapper.CONTACT_PHONE.getAttributeName()))
			key=BankMasterAttributeMapper.CONTACT_PHONE;
		System.out.println(key.getKeyName());
		return key;
	}




}
