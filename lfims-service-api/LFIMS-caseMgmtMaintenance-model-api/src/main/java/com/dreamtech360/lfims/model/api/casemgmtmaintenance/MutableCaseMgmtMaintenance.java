package com.dreamtech360.lfims.model.api.casemgmtmaintenance;

import com.dreamtech360.lfims.model.base.MutableLFIMSEntityObject;

public interface MutableCaseMgmtMaintenance extends CaseMgmtMaintenance, MutableLFIMSEntityObject<CaseMgmtMaintenance> {
	
	void setCode(String code);
	void setName(String name);
	void setDescription(String description);
	
	}
