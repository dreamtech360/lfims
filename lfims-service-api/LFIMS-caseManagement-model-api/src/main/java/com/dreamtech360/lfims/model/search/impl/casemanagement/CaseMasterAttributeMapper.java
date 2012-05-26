package com.dreamtech360.lfims.model.search.impl.casemanagement;

import java.util.Date;

import com.dreamtech360.lfims.model.api.casemanagement.CaseMaster;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;

public enum CaseMasterAttributeMapper implements LFIMSAttributeMapper<CaseMaster>  {

	//Make sure the attribute name matched with that in the View Layer (Model attribute names)
	 
	ID("lfims:id","id"),
	RESPONDENT_NAME("lfims:respondentName","respondentName"),
	CASE_NO("lfims:caseNo","caseNo"),
	RPMA_NO("lfims:rpmaNo","rpmaNo"),
	BANK_NAME("lfims:bankName","bankName"),
	BRANCH_NAME("lfims:branchName","branchName"),
	COURT_NAME("lfims:courtName","courtName"),
	LIMIT_DATE("lfims:limitDate","limitDate"),
	CASE_NATURE("lfims:caseNature","caseNature"),
	CASE_AMOUNT("lfims:caseAmount","caseAmount"),
	FILING_DATE("lfims:filingDate","filingDate"),
	JUDGEMENT_DATE("lfims:judgementDate","judgementDate"),
	CIRTIFICATE_DATE("lfims:certificateDate","certificateDate"),
	CLOSING_DATE("lfims:closingDate","closingDate"),
	OLD_COURT_NAME("lfims:oldCourtName","oldCourtName"),
	ODD_COURT_NUMBER("lfims:oldCourtNumber","oldCourtNumber"),
	CASE_POSITION("lfims:casePosition","casePosition"),
	CASE_FORUM("lfims:caseForum","caseForum"),
	OTHER_ADVOCATE_NAME("lfims:otherAdvocateName","otherAdvocateName"),
	OTHER_ADVOCATE_PHONE("lfims:otherAdvocatePhone","otherAdvocatePhone"),
	SPECIAL_OFFICER_APPOINTED("lfims:specialOfficerAppointed","specialOfficerAppointed"),
	CASE_TRANSFER_DATE("lfims:caseTransferDate","caseTransferDate");
	
	
	private final String fieldName;
	private final String attributeName;

	public String getFieldName(){
		return fieldName;
	}
	public String getAttributeName(){
		return attributeName;
	}
	CaseMasterAttributeMapper(String fieldName,String attributeName){
		this.fieldName=fieldName;
		this.attributeName=attributeName;
	}
	
	public String getKeyName() {
		// TODO Auto-generated method stub
		return this.attributeName;
	}
};
