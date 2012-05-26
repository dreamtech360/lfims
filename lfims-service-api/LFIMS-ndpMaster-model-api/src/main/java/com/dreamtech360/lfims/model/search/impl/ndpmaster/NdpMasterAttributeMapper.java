package com.dreamtech360.lfims.model.search.impl.ndpmaster;

import com.dreamtech360.lfims.model.api.ndpmaster.NdpMaster;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;

public enum NdpMasterAttributeMapper implements LFIMSAttributeMapper<NdpMaster>  {

	//Make sure the attribute name matched with that in the View Layer (Model attribute names)
	 
	PURPOSE ("lfims:purpose","purpose"),
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
	NdpMasterAttributeMapper(String fieldName,String attributeName){
		this.fieldName=fieldName;
		this.attributeName=attributeName;
	}
	
	public String getKeyName() {
		// TODO Auto-generated method stub
		return this.attributeName;
	}
};
