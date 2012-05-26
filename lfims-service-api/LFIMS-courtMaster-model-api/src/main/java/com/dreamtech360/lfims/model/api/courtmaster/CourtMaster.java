package com.dreamtech360.lfims.model.api.courtmaster;

import com.dreamtech360.lfims.model.base.LFIMSEntityObject;

public interface CourtMaster extends LFIMSEntityObject<CourtMaster>  {
	
	
	String getName();
	String getFullName();
	String getAdvAddress1();
	String getAdvAddress2();
	String getCityPin();
	String getContactPhone();
}
