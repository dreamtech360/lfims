package com.dreamtech360.lfims.model.api.courtmaster;

import com.dreamtech360.lfims.model.base.MutableLFIMSEntityObject;

public interface MutableCourtMaster extends CourtMaster, MutableLFIMSEntityObject<CourtMaster> {
	
	void setName(String name);
	void setFullName(String fullName);
	void setAdvAddress1(String advAddress1);
	void setAdvAddress2(String advAddress2);
	void setCityPin(String cityPin);
	void setContactPhone(String contactPhone);
	}
