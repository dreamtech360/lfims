package com.dreamtech360.lfims.model.api.impl.courtmaster;

import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.api.courtmaster.CourtMaster;
import com.dreamtech360.lfims.model.api.courtmaster.MutableCourtMaster;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class MutableCourtMasterImpl implements MutableCourtMaster{

	
	private int id;
	private String name;
	private String fullName;
	private String advAddress1;
	private String advAddress2;
	private String cityPin;
	private String contactPhone;
	
	
	public MutableCourtMasterImpl(){
	
		}
	
	public MutableCourtMasterImpl(int id, String name,String fullName,String advAddress1, String advAddress2,String cityPin,String contactPhone){
		this.id=id;
		this.name=name;
		this.fullName=fullName;
		this.advAddress1=advAddress1;
		this.advAddress2=advAddress2;
		this.cityPin=cityPin;
		this.contactPhone=contactPhone;
	}
	
	
	
public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdvAddress1() {
		return advAddress1;
	}

	public void setAdvAddress1(String advAddress1) {
		this.advAddress1 = advAddress1;
	}

	public String getAdvAddress2() {
		return advAddress2;
	}

	public void setAdvAddress2(String advAddress2) {
		this.advAddress2 = advAddress2;
	}

	public String getCityPin() {
		return cityPin;
	}

	public void setCityPin(String cityPin) {
		this.cityPin = cityPin;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	
public String getJSONString() throws LFIMSModelException {
		
		return getJSON().toString();
	}

public JSONObject getJSON() throws LFIMSModelException {
	JSONObject object= new JSONObject();
	try {
		object.put("id", this.id);
		object.put("name", this.name);
		object.put("fullName", this.fullName);
		object.put("advAddress1", this.advAddress1);
		object.put("advAddress2", this.advAddress2);
		object.put("cityPin", this.cityPin);
		object.put("contactPhone", this.contactPhone);
	
	} catch (Exception e) {
		throw new LFIMSModelException(e);
	}
	return object;
}

public int compareTo(CourtMaster o) {
	// TODO Auto-generated method stub
	return 0;
}

public String getFullName() {
	// TODO Auto-generated method stub
	return fullName;
}

public void setFullName(String fullName) {
	this.fullName=fullName;
	
}


	
	
}
