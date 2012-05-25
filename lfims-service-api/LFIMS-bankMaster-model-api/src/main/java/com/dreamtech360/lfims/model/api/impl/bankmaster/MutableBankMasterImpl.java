package com.dreamtech360.lfims.model.api.impl.bankmaster;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.api.bankmaster.BankMaster;
import com.dreamtech360.lfims.model.api.bankmaster.MutableBankMaster;
import com.dreamtech360.lfims.model.base.LFIMSObjectReference;
import com.dreamtech360.lfims.model.base.Referenceable;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class MutableBankMasterImpl implements MutableBankMaster{

	
	private int id;
	private String name;
	private String fullName;
	private String headOffice;
	private String contactPerson;
	private String contactPhone;
	//private Map<String,String> reference;
	
	public MutableBankMasterImpl(){
	//	this.reference=new HashMap<String,String>();
		}
	
	public MutableBankMasterImpl(int id, String name,String fullName, String headOffice,String contactPerson,String contactPhone){
		this.id=id;
		this.name=name;
		this.fullName=fullName;
		this.headOffice=headOffice;
		this.contactPerson=contactPerson;
		this.contactPhone=contactPhone;
		//this.reference=new HashMap<String,String>();
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
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getHeadOffice() {
		return headOffice;
	}
	public void setHeadOffice(String headOffice) {
		this.headOffice = headOffice;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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
		object.put("headOffice", this.headOffice);
		object.put("contactPerson", this.contactPerson);
		object.put("contactPhone", this.contactPhone);
	} catch (Exception e) {
		throw new LFIMSModelException(e);
	}
	return object;
}

public int compareTo(BankMaster o) {
	// TODO Auto-generated method stub
	return 0;
}
	
	
}
