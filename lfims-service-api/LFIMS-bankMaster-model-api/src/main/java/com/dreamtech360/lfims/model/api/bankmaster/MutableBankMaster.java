package com.dreamtech360.lfims.model.api.bankmaster;

import com.dreamtech360.lfims.model.base.MutableLFIMSEntityObject;

public interface MutableBankMaster extends BankMaster, MutableLFIMSEntityObject<BankMaster> {
	
	void setName(String name);
	void setFullName(String fullName);
	void setHeadOffice(String headOffice);
	void setContactPerson(String contactPerson);
	void setContactPhone(String contactPhone);
}
