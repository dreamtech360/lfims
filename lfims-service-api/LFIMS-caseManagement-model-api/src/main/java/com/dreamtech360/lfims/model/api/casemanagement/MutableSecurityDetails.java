package com.dreamtech360.lfims.model.api.casemanagement;

import com.dreamtech360.lfims.model.base.MutableLFIMSEntityObject;


public interface MutableSecurityDetails extends SecurityDetails, MutableLFIMSEntityObject<SecurityDetails> {
	
	void setSecurityDetails(String securityDetails);
	
	
	}
