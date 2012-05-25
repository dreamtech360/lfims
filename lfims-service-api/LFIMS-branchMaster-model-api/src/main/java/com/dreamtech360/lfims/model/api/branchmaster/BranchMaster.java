package com.dreamtech360.lfims.model.api.branchmaster;

import com.dreamtech360.lfims.model.base.HasReferences;
import com.dreamtech360.lfims.model.base.LFIMSEntityObject;

public interface BranchMaster extends LFIMSEntityObject<BranchMaster>, HasReferences {
	 
	
	String getName();
	String getRegion();
	String getAddress();
	String getContactPerson();
	String getContactPhone();
	String getBankName();

}
