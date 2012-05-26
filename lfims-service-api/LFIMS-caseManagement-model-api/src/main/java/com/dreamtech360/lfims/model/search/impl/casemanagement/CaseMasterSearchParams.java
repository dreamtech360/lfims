package com.dreamtech360.lfims.model.search.impl.casemanagement;

import com.dreamtech360.lfims.model.api.casemanagement.CaseMaster;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.SearchParams;


public class CaseMasterSearchParams extends SearchParams<CaseMaster> {
	
	protected final LFIMSAttributeMapper<CaseMaster> getSearchCriteriaKey(String attributeName){

	
		
		LFIMSAttributeMapper<CaseMaster> key=null;
		if(attributeName.equals(CaseMasterAttributeMapper.ID.getAttributeName()))
			key=CaseMasterAttributeMapper.ID;
		else if(attributeName.equals(CaseMasterAttributeMapper.RESPONDENT_NAME.getAttributeName()))
			key=CaseMasterAttributeMapper.RESPONDENT_NAME;
		else if(attributeName.equals(CaseMasterAttributeMapper.CASE_NO.getAttributeName()))
			key=CaseMasterAttributeMapper.CASE_NO;
		else if(attributeName.equals(CaseMasterAttributeMapper.RPMA_NO.getAttributeName()))
			key=CaseMasterAttributeMapper.RPMA_NO;
		else if(attributeName.equals(CaseMasterAttributeMapper.BANK_NAME.getAttributeName()))
			key=CaseMasterAttributeMapper.BANK_NAME;
		else if(attributeName.equals(CaseMasterAttributeMapper.BRANCH_NAME.getAttributeName()))
			key=CaseMasterAttributeMapper.BRANCH_NAME;
		else if(attributeName.equals(CaseMasterAttributeMapper.COURT_NAME.getAttributeName()))
			key=CaseMasterAttributeMapper.COURT_NAME;
		else if(attributeName.equals(CaseMasterAttributeMapper.LIMIT_DATE.getAttributeName()))
			key=CaseMasterAttributeMapper.LIMIT_DATE;
		else if(attributeName.equals(CaseMasterAttributeMapper.CASE_NATURE.getAttributeName()))
			key=CaseMasterAttributeMapper.CASE_NATURE;
		else if(attributeName.equals(CaseMasterAttributeMapper.CASE_AMOUNT.getAttributeName()))
			key=CaseMasterAttributeMapper.CASE_AMOUNT;
		else if(attributeName.equals(CaseMasterAttributeMapper.FILING_DATE.getAttributeName()))
			key=CaseMasterAttributeMapper.FILING_DATE;
		else if(attributeName.equals(CaseMasterAttributeMapper.JUDGEMENT_DATE.getAttributeName()))
			key=CaseMasterAttributeMapper.JUDGEMENT_DATE;
		else if(attributeName.equals(CaseMasterAttributeMapper.CIRTIFICATE_DATE.getAttributeName()))
			key=CaseMasterAttributeMapper.CIRTIFICATE_DATE;
		else if(attributeName.equals(CaseMasterAttributeMapper.CLOSING_DATE.getAttributeName()))
			key=CaseMasterAttributeMapper.CLOSING_DATE;
		else if(attributeName.equals(CaseMasterAttributeMapper.OLD_COURT_NAME.getAttributeName()))
			key=CaseMasterAttributeMapper.OLD_COURT_NAME;
		else if(attributeName.equals(CaseMasterAttributeMapper.ODD_COURT_NUMBER.getAttributeName()))
			key=CaseMasterAttributeMapper.ODD_COURT_NUMBER;
		else if(attributeName.equals(CaseMasterAttributeMapper.CASE_POSITION.getAttributeName()))
			key=CaseMasterAttributeMapper.CASE_POSITION;
		else if(attributeName.equals(CaseMasterAttributeMapper.CASE_FORUM.getAttributeName()))
			key=CaseMasterAttributeMapper.CASE_FORUM;
		else if(attributeName.equals(CaseMasterAttributeMapper.OTHER_ADVOCATE_NAME.getAttributeName()))
			key=CaseMasterAttributeMapper.OTHER_ADVOCATE_NAME;
		else if(attributeName.equals(CaseMasterAttributeMapper.OTHER_ADVOCATE_PHONE.getAttributeName()))
			key=CaseMasterAttributeMapper.OTHER_ADVOCATE_PHONE;
		else if(attributeName.equals(CaseMasterAttributeMapper.SPECIAL_OFFICER_APPOINTED.getAttributeName()))
			key=CaseMasterAttributeMapper.SPECIAL_OFFICER_APPOINTED;
		else if(attributeName.equals(CaseMasterAttributeMapper.CASE_TRANSFER_DATE.getAttributeName()))
			key=CaseMasterAttributeMapper.CASE_TRANSFER_DATE;
				
		System.out.println(key.getKeyName());
		return key;
	}




}
