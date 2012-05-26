package com.dreamtech360.lfims.model.api.impl.advocatemaster;

import org.codehaus.jettison.json.JSONObject;
import com.dreamtech360.lfims.model.api.advocatemaster.AdvocateMaster;
import com.dreamtech360.lfims.model.api.advocatemaster.MutableAdvocateMaster;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class MutableAdvocateMasterImpl implements MutableAdvocateMaster{

	
	private int id;
	private String advName;
	private String advAddress1;
	private String advAddress2;
	private String cityPin;
	private String contactPhone;
	private String email;
	
	
	public MutableAdvocateMasterImpl(){
	
		}
	
	public MutableAdvocateMasterImpl(int id, String advName,String advAddress1, String advAddress2,String cityPin,String contactPhone,String email){
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

	public void setId(int id) {
		this.id = id;
	}

	public String getAdvName() {
		return advName;
	}

	public void setAdvName(String advName) {
		this.advName = advName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

public int compareTo(AdvocateMaster o) {
	// TODO Auto-generated method stub
	return 0;
}


	
	
}
