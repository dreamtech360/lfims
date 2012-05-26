 package com.dreamtech360.lfims.model.api.branchmaster;

import com.dreamtech360.lfims.model.base.HasReferences;
import com.dreamtech360.lfims.model.base.MutableLFIMSEntityObject;

public interface MutableBranchMaster extends BranchMaster, MutableLFIMSEntityObject<BranchMaster>, HasReferences{
	
	void setName(String name);
	void setRegion(String region);
	void setAddress(String address);
	void setContactPerson(String contactPerson);
	void setContactPhone(String contactPhone);
	void setBankName(String bankName);

}
