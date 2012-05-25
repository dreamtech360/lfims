package com.dreamtech360.lfims.model.api.expensesmaster;

import com.dreamtech360.lfims.model.base.MutableLFIMSEntityObject;

public interface MutableExpensesMaster extends ExpensesMaster, MutableLFIMSEntityObject<ExpensesMaster> {
	
	void setReason(String reaon);
	void setDescription(String description);
	
	}
