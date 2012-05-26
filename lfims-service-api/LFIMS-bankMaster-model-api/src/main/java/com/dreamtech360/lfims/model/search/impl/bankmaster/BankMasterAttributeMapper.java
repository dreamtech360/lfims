package com.dreamtech360.lfims.model.search.impl.bankmaster;

import com.dreamtech360.lfims.model.api.bankmaster.BankMaster;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;

public enum BankMasterAttributeMapper implements LFIMSAttributeMapper<BankMaster>  {

	//Make sure the attribute name matched with that in the View Layer (Model attribute names)
	
	NAME ("lfims:name","name"),
	FULL_NAME("lfims:fullName","fullName"),
	HEAD_OFFICE("lfims:headOffice","headOffice"),
	CONTACT_PERSON("lfims:contactPerson","contactPerson"),
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
	BankMasterAttributeMapper(String fieldName,String attributeName){
		this.fieldName=fieldName;
		this.attributeName=attributeName;
	}
	
	public String getKeyName() {
		// TODO Auto-generated method stub
		return this.attributeName;
	}
};
