package com.dreamtech360.lfims.model.api.expensesmaster;

import com.dreamtech360.lfims.model.base.LFIMSEntityObject;

public interface ExpensesMaster extends LFIMSEntityObject<ExpensesMaster>  {
	
	
	String getReason();
	String getDescription();
	
}
