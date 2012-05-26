package com.dreamtech360.lfims.model.api.casemanagement;

import com.dreamtech360.lfims.model.base.MutableLFIMSEntityObject;



public interface MutableDefendentDetails extends DefendentDetails, MutableLFIMSEntityObject<DefendentDetails> {
	
	
	public void setDefendentName(String defendentName) ;
	public void setStatus(int status); 
	public void setDeadOrAlive(boolean deadOrAlive) ;
	public void setAddress1(String address1);
	public void setAddress2(String address2) ;
	public void setAddress3(String address3) ;	
	
	
	}
