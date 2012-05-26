package com.dreamtech360.lfims.model.search.impl.expensesmaster;

import com.dreamtech360.lfims.model.api.expensesmaster.ExpensesMaster;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;

public enum ExpensesMasterAttributeMapper implements LFIMSAttributeMapper<ExpensesMaster>  {

	//Make sure the attribute name matched with that in the View Layer (Model attribute names)
	 
	REASON ("lfims:reason","reason"),
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
	ExpensesMasterAttributeMapper(String fieldName,String attributeName){
		this.fieldName=fieldName;
		this.attributeName=attributeName;
	}
	
	public String getKeyName() {
		// TODO Auto-generated method stub
		return this.attributeName;
	}
};
