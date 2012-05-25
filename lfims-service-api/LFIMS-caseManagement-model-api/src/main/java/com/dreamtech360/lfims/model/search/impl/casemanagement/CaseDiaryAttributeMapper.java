package com.dreamtech360.lfims.model.search.impl.casemanagement;

import java.util.Date;

import com.dreamtech360.lfims.model.api.casemanagement.CaseDiary;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;

public enum CaseDiaryAttributeMapper implements LFIMSAttributeMapper<CaseDiary>  {

	//Make sure the attribute name matched with that in the View Layer (Model attribute names)
	 
	ID("lfims:id","id"),
	HEARING_DATE("lfims:hearingDate","hearingDate"),
	BEFORE_BENCH_NAME("lfims:beforeBenchName","beforeBenchName"),
	OUR_ADVOCATE_NAME("lfims:ourAdvocateName","ourAdvocateName"),
	ADVOCATE_NAME("lfims:advocateName","advocateName"),
	OUR_WITNESS_DETAILS("lfims:ourWitnessDetails","ourWitnessDetails"),
	OTHERS_WITNESS_DETAILS("lfims:othersWitnessDetails","othersWitnessDetails"),
	OUR_DOCUMENTS("lfims:ourDocuments","ourDocuments"),
	OTHERS_DOCUMENTS("lfims:othersDocuments","othersDocuments"),
	CASE_COMPLETION_METHOD("lfims:caseCompletionMethod","caseCompletionMethod"),
	OTHER_DETAILS("lfims:otherDetails","otherDetails"),
	QUICKY("lfims:quicky","quicky"),
	NEXT_HEARING_DATE("lfims:nextHearingDate","nextHearingDate"),
	NEXT_DATE_PURPOSE("lfims:nextDatePurpose","nextDatePurpose");
	
	
	private final String fieldName;
	private final String attributeName;

	public String getFieldName(){
		return fieldName;
	}
	public String getAttributeName(){
		return attributeName;
	}
	CaseDiaryAttributeMapper(String fieldName,String attributeName){
		this.fieldName=fieldName;
		this.attributeName=attributeName;
	}
	
	public String getKeyName() {
		// TODO Auto-generated method stub
		return this.attributeName;
	}
};
