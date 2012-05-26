package com.dreamtech360.lfims.model.base;

import org.codehaus.jettison.json.JSONObject;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public interface LFIMSObject<T> extends Comparable<T> {

	
	String getJSONString() throws LFIMSModelException;
	JSONObject getJSON() throws LFIMSModelException;
	
	
	
}
