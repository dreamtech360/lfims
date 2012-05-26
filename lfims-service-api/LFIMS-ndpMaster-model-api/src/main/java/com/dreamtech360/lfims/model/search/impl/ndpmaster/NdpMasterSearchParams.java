package com.dreamtech360.lfims.model.search.impl.ndpmaster;

import com.dreamtech360.lfims.model.api.ndpmaster.NdpMaster;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.SearchParams;


public class NdpMasterSearchParams extends SearchParams<NdpMaster> {

	
	protected final LFIMSAttributeMapper<NdpMaster> getSearchCriteriaKey(String attributeName){

		LFIMSAttributeMapper<NdpMaster> key=null;
		if(attributeName.equals(NdpMasterAttributeMapper.ID.getAttributeName()))
			key=NdpMasterAttributeMapper.ID;
		else if(attributeName.equals(NdpMasterAttributeMapper.PURPOSE.getAttributeName()))
			key=NdpMasterAttributeMapper.PURPOSE;
		else if(attributeName.equals(NdpMasterAttributeMapper.DESCRIPTION.getAttributeName()))
			key=NdpMasterAttributeMapper.DESCRIPTION;
				
		System.out.println(key.getKeyName());
		return key;
	}




}
