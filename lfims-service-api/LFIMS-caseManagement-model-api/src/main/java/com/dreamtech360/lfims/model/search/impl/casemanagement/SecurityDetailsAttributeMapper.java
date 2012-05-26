package com.dreamtech360.lfims.model.search.impl.casemanagement;

import com.dreamtech360.lfims.model.api.casemanagement.SecurityDetails;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;

public enum SecurityDetailsAttributeMapper implements LFIMSAttributeMapper<SecurityDetails>  {

	//Make sure the attribute name matched with that in the View Layer (Model attribute names)
	 
	
	SECURITY_DETAILS ("lfims:securityDetails","securityDetails"),
	ID("lfims:id","id");

	private final String fieldName;
	private final String attributeName;

	public String getFieldName(){
		return fieldName;
	}
	public String getAttributeName(){
		return attributeName;
	}
	SecurityDetailsAttributeMapper(String fieldName,String attributeName){
		this.fieldName=fieldName;
		this.attributeName=attributeName;
	}
	
	public String getKeyName() {
		// TODO Auto-generated method stub
		return this.attributeName;
	}
};
