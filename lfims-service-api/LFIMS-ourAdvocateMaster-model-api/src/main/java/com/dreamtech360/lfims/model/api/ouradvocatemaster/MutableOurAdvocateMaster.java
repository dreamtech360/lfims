package com.dreamtech360.lfims.model.api.ouradvocatemaster;

import com.dreamtech360.lfims.model.base.MutableLFIMSEntityObject;

public interface MutableOurAdvocateMaster extends OurAdvocateMaster, MutableLFIMSEntityObject<OurAdvocateMaster> {
	
	void setAdvName(String advName);
	void setAdvAddress1(String advAddress1);
	void setAdvAddress2(String advAddress2);
	void setCityPin(String cityPin);
	void setContactPhone(String contactPhone);
	void setEmail(String email);
}
