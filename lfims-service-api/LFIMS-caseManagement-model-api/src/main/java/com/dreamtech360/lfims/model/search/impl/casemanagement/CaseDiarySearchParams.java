package com.dreamtech360.lfims.model.search.impl.casemanagement;

import com.dreamtech360.lfims.model.api.casemanagement.CaseDiary;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.SearchParams;


public class CaseDiarySearchParams extends SearchParams<CaseDiary> {

	
	protected final LFIMSAttributeMapper<CaseDiary> getSearchCriteriaKey(String attributeName){

		
		
		LFIMSAttributeMapper<CaseDiary> key=null;
		if(attributeName.equals(CaseDiaryAttributeMapper.ID.getAttributeName()))
			key=CaseDiaryAttributeMapper.ID;
		else if(attributeName.equals(CaseDiaryAttributeMapper.HEARING_DATE.getAttributeName()))
			key=CaseDiaryAttributeMapper.HEARING_DATE;
		else if(attributeName.equals(CaseDiaryAttributeMapper.BEFORE_BENCH_NAME.getAttributeName()))
			key=CaseDiaryAttributeMapper.BEFORE_BENCH_NAME;
		else if(attributeName.equals(CaseDiaryAttributeMapper.OUR_ADVOCATE_NAME.getAttributeName()))
			key=CaseDiaryAttributeMapper.OUR_ADVOCATE_NAME;
		else if(attributeName.equals(CaseDiaryAttributeMapper.ADVOCATE_NAME.getAttributeName()))
			key=CaseDiaryAttributeMapper.ADVOCATE_NAME;
		else if(attributeName.equals(CaseDiaryAttributeMapper.OUR_WITNESS_DETAILS.getAttributeName()))
			key=CaseDiaryAttributeMapper.OUR_WITNESS_DETAILS;
		else if(attributeName.equals(CaseDiaryAttributeMapper.OTHERS_WITNESS_DETAILS.getAttributeName()))
			key=CaseDiaryAttributeMapper.OTHERS_WITNESS_DETAILS;
		else if(attributeName.equals(CaseDiaryAttributeMapper.OUR_DOCUMENTS.getAttributeName()))
			key=CaseDiaryAttributeMapper.OUR_DOCUMENTS;
		else if(attributeName.equals(CaseDiaryAttributeMapper.OTHERS_DOCUMENTS.getAttributeName()))
			key=CaseDiaryAttributeMapper.OTHERS_DOCUMENTS;
		else if(attributeName.equals(CaseDiaryAttributeMapper.CASE_COMPLETION_METHOD.getAttributeName()))
			key=CaseDiaryAttributeMapper.CASE_COMPLETION_METHOD;
		else if(attributeName.equals(CaseDiaryAttributeMapper.OTHER_DETAILS.getAttributeName()))
			key=CaseDiaryAttributeMapper.OTHER_DETAILS;
		else if(attributeName.equals(CaseDiaryAttributeMapper.QUICKY.getAttributeName()))
			key=CaseDiaryAttributeMapper.QUICKY;
		else if(attributeName.equals(CaseDiaryAttributeMapper.NEXT_HEARING_DATE.getAttributeName()))
			key=CaseDiaryAttributeMapper.NEXT_HEARING_DATE;
		else if(attributeName.equals(CaseDiaryAttributeMapper.NEXT_DATE_PURPOSE.getAttributeName()))
			key=CaseDiaryAttributeMapper.NEXT_DATE_PURPOSE;
				
		System.out.println(key.getKeyName());
		return key;
	}




}
