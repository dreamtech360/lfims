package com.dreamtech360.lfims.model.api.impl.casemanagement;

import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.api.casemanagement.MutableSecurityDetails;
import com.dreamtech360.lfims.model.api.casemanagement.SecurityDetails;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class MutableSecurityDetailsImpl implements MutableSecurityDetails{

	
	private int id;
	private String uuid;
	private String securityDetails;
	
	
	public MutableSecurityDetailsImpl(){
	
		}
	
	public MutableSecurityDetailsImpl(int id, //String uuid,
			String securitDetails){
		this.id=id;
		//this.uuid=uuid;
		this.securityDetails=securitDetails;
				
	}
	
	

	
public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSecurityDetails() {
		return securityDetails;
	}

	public void setSecurityDetails(String securitDetails) {
		this.securityDetails = securitDetails;
	}


public String getJSONString() throws LFIMSModelException {
		
		return getJSON().toString();
	}

public JSONObject getJSON() throws LFIMSModelException {
	JSONObject object= new JSONObject();
	try {
		object.put("id", this.id);
		object.put("uuid", this.uuid);
		object.put("securityDetails", this.securityDetails);
				
	} catch (Exception e) {
		throw new LFIMSModelException(e);
	}
	return object;
}

public int compareTo(SecurityDetails o) {
	return 0;
}

public String getUuid() {
		return uuid;
}

public void setUuid(String uuid) {
	this.uuid=uuid;
	
}


}
