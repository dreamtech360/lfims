package com.dreamtech360.lfims.model.search.impl.advocatemaster;

import com.dreamtech360.lfims.model.api.advocatemaster.AdvocateMaster;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;

public enum AdvocateMasterAttributeMapper implements LFIMSAttributeMapper<AdvocateMaster>  {

	//Make sure the attribute name matched with that in the View Layer (Model attribute names)
	 
	ADV_NAME ("lfims:advName","advName"),
	ADDRESS_1("lfims:advAddress1","advAddress1"),
	ADDRESS_2("lfims:advAddress2","advAddress2"),
	CITY_PIN("lfims:cityPin","cityPin"),
	CONTACT_PHONE("lfims:contactPhone","contactPhone"),
	EMAIL("lfims:email","email"),
	ID("lfims:id","id");

	private final String fieldName;
	private final String attributeName;

	public String getFieldName(){
		return fieldName;
	}
	public String getAttributeName(){
		return attributeName;
	}
	AdvocateMasterAttributeMapper(String fieldName,String attributeName){
		this.fieldName=fieldName;
		this.attributeName=attributeName;
	}
	
	public String getKeyName() {
		// TODO Auto-generated method stub
		return this.attributeName;
	}
};
