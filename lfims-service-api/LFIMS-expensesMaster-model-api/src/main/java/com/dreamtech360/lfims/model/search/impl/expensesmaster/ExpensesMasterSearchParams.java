package com.dreamtech360.lfims.model.search.impl.expensesmaster;

import com.dreamtech360.lfims.model.api.expensesmaster.ExpensesMaster;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.SearchParams;


public class ExpensesMasterSearchParams extends SearchParams<ExpensesMaster> {

	
	protected final LFIMSAttributeMapper<ExpensesMaster> getSearchCriteriaKey(String attributeName){

		LFIMSAttributeMapper<ExpensesMaster> key=null;
		if(attributeName.equals(ExpensesMasterAttributeMapper.ID.getAttributeName()))
			key=ExpensesMasterAttributeMapper.ID;
		else if(attributeName.equals(ExpensesMasterAttributeMapper.REASON.getAttributeName()))
			key=ExpensesMasterAttributeMapper.REASON;
		else if(attributeName.equals(ExpensesMasterAttributeMapper.DESCRIPTION.getAttributeName()))
			key=ExpensesMasterAttributeMapper.DESCRIPTION;
				
		System.out.println(key.getKeyName());
		return key;
	}




}
