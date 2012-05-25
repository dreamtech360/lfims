package com.dreamtech360.lfims.model.search.impl.branchmaster;

import com.dreamtech360.lfims.model.api.branchmaster.BranchMaster;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;

public enum BranchMasterAttributeMapper implements LFIMSAttributeMapper<BranchMaster>  {

	ID("lfims:id","id"),
	BANK_NAME("lfims:bankName","bankName"),
	NAME ("lfims:name","name"),
	REGION("lfims:region","region"),
	ADDRESS("lfims:address","address"),
	CONTACT_PERSON("lfims:contactPerson","contactPerson"),
	CONTACT_PHONE("lfims:contactPhone","contactPhone");

private final String fieldName;
private final String attributeName;

public String getFieldName(){
	return fieldName;
}
public String getAttributeName(){
	return attributeName;
}
BranchMasterAttributeMapper(String fieldName,String attributeName){
	this.fieldName=fieldName;
	this.attributeName=attributeName;
}
public String getKeyName() {
	// TODO Auto-generated method stub
	return this.attributeName;
}
};
