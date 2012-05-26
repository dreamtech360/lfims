package com.dreamtech360.lfims.model.api.ndpmaster;

import com.dreamtech360.lfims.model.base.MutableLFIMSEntityObject;

public interface MutableNdpMaster extends NdpMaster, MutableLFIMSEntityObject<NdpMaster> {
	
	void setPurpose(String purpose);
	void setDescription(String description);
	
	}
