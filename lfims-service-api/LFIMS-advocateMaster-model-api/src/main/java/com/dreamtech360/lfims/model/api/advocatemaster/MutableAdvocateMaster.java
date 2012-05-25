package com.dreamtech360.lfims.model.api.advocatemaster;

import com.dreamtech360.lfims.model.base.MutableLFIMSEntityObject;

public interface MutableAdvocateMaster extends AdvocateMaster, MutableLFIMSEntityObject<AdvocateMaster> {
	
	void setAdvName(String advName);
	void setAdvAddress1(String advAddress1);
	void setAdvAddress2(String advAddress2);
	void setCityPin(String cityPin);
	void setContactPhone(String contactPhone);
	void setEmail(String email);
}
