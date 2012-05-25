package com.dreamtech360.lfims.model.search.impl.casemgmtmaintenance;

import com.dreamtech360.lfims.model.api.casemgmtmaintenance.CaseMgmtMaintenance;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;

public enum CaseMgmtMaintenanceAttributeMapper implements LFIMSAttributeMapper<CaseMgmtMaintenance>  {

	//Make sure the attribute name matched with that in the View Layer (Model attribute names)
	 
	NAME ("lfims:name","name"),
	CODE ("lfims:code","code"),
	DESCRIPTION ("lfims:description","description"),
	ID("lfims:id","id");

	private final String fieldName;
	private final String attributeName;

	public String getFieldName(){
		return fieldName;
	}
	public String getAttributeName(){
		return attributeName;
	}
	CaseMgmtMaintenanceAttributeMapper(String fieldName,String attributeName){
		this.fieldName=fieldName;
		this.attributeName=attributeName;
	}
	
	public String getKeyName() {
		// TODO Auto-generated method stub
		return this.attributeName;
	}
};
