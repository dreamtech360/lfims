package com.dreamtech360.lfims.model.search.impl.casemanagement;

import com.dreamtech360.lfims.model.api.casemanagement.DefendentDetails;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.SearchParams;


public class DefendentDetalsSearchParams extends SearchParams<DefendentDetails> {

	
	
	protected final LFIMSAttributeMapper<DefendentDetails> getSearchCriteriaKey(String attributeName){

		LFIMSAttributeMapper<DefendentDetails> key=null;
		if(attributeName.equals(DefendentDetailsAttributeMapper.ID.getAttributeName()))
			key=DefendentDetailsAttributeMapper.ID;
		else if(attributeName.equals(DefendentDetailsAttributeMapper.DEFENDENT_NAME.getAttributeName()))
			key=DefendentDetailsAttributeMapper.DEFENDENT_NAME;
		else if(attributeName.equals(DefendentDetailsAttributeMapper.STATUS.getAttributeName()))
			key=DefendentDetailsAttributeMapper.STATUS;
		else if(attributeName.equals(DefendentDetailsAttributeMapper.DEAD_OR_ALIVE.getAttributeName()))
			key=DefendentDetailsAttributeMapper.DEAD_OR_ALIVE;
		else if(attributeName.equals(DefendentDetailsAttributeMapper.ADDRESS1.getAttributeName()))
			key=DefendentDetailsAttributeMapper.ADDRESS1;
		else if(attributeName.equals(DefendentDetailsAttributeMapper.ADDRESS2.getAttributeName()))
			key=DefendentDetailsAttributeMapper.ADDRESS2;
		else if(attributeName.equals(DefendentDetailsAttributeMapper.ADDRESS3.getAttributeName()))
			key=DefendentDetailsAttributeMapper.ADDRESS3;
		
				
		System.out.println(key.getKeyName());
		return key;
	}




}
