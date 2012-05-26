package com.dreamtech360.lfims.model.search.impl.branchmaster;

import com.dreamtech360.lfims.model.api.branchmaster.BranchMaster;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.SearchParams;

public class BranchMasterSearchParams extends SearchParams<BranchMaster> {
	
	
	protected final LFIMSAttributeMapper<BranchMaster> getSearchCriteriaKey(String attributeName){
		
		LFIMSAttributeMapper<BranchMaster> key=null;
		if(attributeName.equals(BranchMasterAttributeMapper.ID.getAttributeName()))
			key=BranchMasterAttributeMapper.ID;
		else if(attributeName.equals(BranchMasterAttributeMapper.BANK_NAME.getAttributeName()))
			key=BranchMasterAttributeMapper.BANK_NAME;
		else if(attributeName.equals(BranchMasterAttributeMapper.NAME.getAttributeName()))
			key=BranchMasterAttributeMapper.NAME;
		else if (attributeName.equals(BranchMasterAttributeMapper.REGION.getAttributeName()))
			key=BranchMasterAttributeMapper.REGION;
		else if (attributeName.equals(BranchMasterAttributeMapper.ADDRESS.getAttributeName()))
			key=BranchMasterAttributeMapper.ADDRESS;
		else if (attributeName.equals(BranchMasterAttributeMapper.CONTACT_PERSON.getAttributeName()))
			key=BranchMasterAttributeMapper.CONTACT_PERSON;
		else if(attributeName.equals(BranchMasterAttributeMapper.CONTACT_PHONE.getAttributeName()))
			key=BranchMasterAttributeMapper.CONTACT_PHONE;
		System.out.println(key.getKeyName());
		return key;
	}

	
	
	
}
