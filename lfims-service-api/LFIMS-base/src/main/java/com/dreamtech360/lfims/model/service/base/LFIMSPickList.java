package com.dreamtech360.lfims.model.service.base;

import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSPickListElement;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;

public interface LFIMSPickList  {
	
	String getListJSON()throws LFIMSServiceException; 
	void setElement(LFIMSPickListElement e)throws LFIMSServiceException;
	
}
