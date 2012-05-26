package com.dreamtech360.lfims.model.api.impl.casemanagement;

import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.api.casemanagement.SecurityDetails;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class SecurityDetailsImpl implements SecurityDetails{
	
	private int id;
	private String uuid;
	private String securityDetails;
	
	
		
	public int getId() {
		return id;
	}



	public String getSecurityDetails() {
		return securityDetails;
	}




	public SecurityDetailsImpl(int id,
			String securityDetails){
		this.id=id;
		this.securityDetails=securityDetails;
			
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

	public void setUuid(String Uuid) {
		this.uuid=Uuid;
	}

}
