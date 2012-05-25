package com.dreamtech360.lfims.model.api.impl.bankmaster;

import java.util.HashMap;
import java.util.Map;


import org.codehaus.jettison.json.JSONObject;
import com.dreamtech360.lfims.model.api.bankmaster.BankMaster;
import com.dreamtech360.lfims.model.base.LFIMSObjectReference;
import com.dreamtech360.lfims.model.base.Referenceable;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class BankMasterImpl implements BankMaster{
	
	private int id;
	private String name;
	private String fullName;
	private String headOffice;
	private String contactPerson;
	private String contactPhone;
		
	public BankMasterImpl(int id, String name,String fullName, String headOffice,String contactPerson,String contactPhone){
		this.id=id;
		this.name=name;
		this.fullName=fullName;
		this.headOffice=headOffice;
		this.contactPerson=contactPerson;
		this.contactPhone=contactPhone;
	}
	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getFullName() {
		return this.fullName;
	}
	public String getHeadOffice() {
		return this.headOffice;
	}
	public String getContactPerson() {
		return this.contactPerson;
	}
	public String getContactPhone() {
		return this.contactPhone;
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
		
		return 0;
	}
	
	
	
}
