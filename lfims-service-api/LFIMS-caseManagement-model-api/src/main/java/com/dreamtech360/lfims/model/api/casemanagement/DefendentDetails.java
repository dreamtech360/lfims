package com.dreamtech360.lfims.model.api.casemanagement;

import com.dreamtech360.lfims.model.base.LFIMSCompositObject;

public interface DefendentDetails extends LFIMSCompositObject<DefendentDetails> {
	
	
	public String getDefendentName() ;
	public int getStatus() ;
	public boolean isDeadOrAlive() ;
	public String getAddress1() ;
	public String getAddress2() ;
	public String getAddress3() ;

	
	
}
