package com.dreamtech360.lfims.model.api.impl.ouradvocatemaster;

import org.codehaus.jettison.json.JSONObject;


import com.dreamtech360.lfims.model.api.ouradvocatemaster.OurAdvocateMaster;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class OurAdvocateMasterImpl implements OurAdvocateMaster{
	
	private int id;
	private String advName;
	private String advAddress1;
	private String advAddress2;
	private String cityPin;
	private String contactPhone;
	private String email;
		
	public OurAdvocateMasterImpl(int id, String advName,String advAddress1, String advAddress2,String cityPin,String contactPhone,String email){
		this.id=id;
		this.advName=advName;
		this.advAddress1=advAddress1;
		this.advAddress2=advAddress2;
		this.cityPin=cityPin;
		this.contactPhone=contactPhone;
		this.email=email;
	}
		
	public int getId() {
		return id;
	}
	public String getAdvName() {
		return advName;
	}

	public String getAdvAddress1() {
		return advAddress1;
	}

	public String getAdvAddress2() {
		return advAddress2;
	}
	public String getCityPin() {
		return cityPin;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public String getEmail() {
		return email;
	}
	public String getJSONString() throws LFIMSModelException {
		
		return getJSON().toString();
	}
	public JSONObject getJSON() throws LFIMSModelException {
		JSONObject object= new JSONObject();
		try {
			object.put("id", this.id);
			object.put("advName", this.advName);
			object.put("advAddress1", this.advAddress1);
			object.put("advAddress2", this.advAddress2);
			object.put("cityPin", this.cityPin);
			object.put("contactPhone", this.contactPhone);
			object.put("email", this.email);
		} catch (Exception e) {
			throw new LFIMSModelException(e);
		}
		return object;
	}
	public int compareTo(OurAdvocateMaster o) {
		
		return 0;
	}
	
}
