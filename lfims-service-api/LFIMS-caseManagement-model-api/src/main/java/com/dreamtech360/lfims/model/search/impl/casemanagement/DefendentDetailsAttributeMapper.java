package com.dreamtech360.lfims.model.search.impl.casemanagement;

import com.dreamtech360.lfims.model.api.casemanagement.DefendentDetails;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;

public enum DefendentDetailsAttributeMapper implements LFIMSAttributeMapper<DefendentDetails>  {

	//Make sure the attribute name matched with that in the View Layer (Model attribute names)
	 
	ID("lfims:id","id"),
	DEFENDENT_NAME("lfims:defendentName","defendentName"),
	STATUS("lfims:status","status"),
	DEAD_OR_ALIVE("lfims:deadOrAlive","deadOrAlive"),
	ADDRESS1("lfims:address1","address1"),
	ADDRESS2("lfims:address2","address2"),
	ADDRESS3("lfims:address3","address3");
	
	private final String fieldName;
	private final String attributeName;

	public String getFieldName(){
		return fieldName;
	}
	public String getAttributeName(){
		return attributeName;
	}
	DefendentDetailsAttributeMapper(String fieldName,String attributeName){
		this.fieldName=fieldName;
		this.attributeName=attributeName;
	}
	
	public String getKeyName() {
		// TODO Auto-generated method stub
		return this.attributeName;
	}
};
