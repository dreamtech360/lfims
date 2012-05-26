package com.dreamtech360.lfims.model.search.impl.courtmaster;

import com.dreamtech360.lfims.model.api.courtmaster.CourtMaster;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;

public enum CourtMasterAttributeMapper implements LFIMSAttributeMapper<CourtMaster>  {

	//Make sure the attribute name matched with that in the View Layer (Model attribute names)
	 
	NAME ("lfims:name","name"),
	FULL_NAME ("lfims:fullName","fullName"),
	ADDRESS_1("lfims:advAddress1","advAddress1"),
	ADDRESS_2("lfims:advAddress2","advAddress2"),
	CITY_PIN("lfims:cityPin","cityPin"),
	CONTACT_PHONE("lfims:contactPhone","contactPhone"),
	ID("lfims:id","id");

	private final String fieldName;
	private final String attributeName;

	public String getFieldName(){
		return fieldName;
	}
	public String getAttributeName(){
		return attributeName;
	}
	CourtMasterAttributeMapper(String fieldName,String attributeName){
		this.fieldName=fieldName;
		this.attributeName=attributeName;
	}
	
	public String getKeyName() {
		// TODO Auto-generated method stub
		return this.attributeName;
	}
};
