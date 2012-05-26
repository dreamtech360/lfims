package com.dreamtech360.lfims.model.api.impl.casemgmtmaintenance;

import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.api.casemgmtmaintenance.CaseMgmtMaintenance;
import com.dreamtech360.lfims.model.api.casemgmtmaintenance.MutableCaseMgmtMaintenance;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class MutableCaseMgmtMaintenanceImpl implements MutableCaseMgmtMaintenance{

	
	private int id;
	private String code;
	private String name;
	private String description;
	
	
	public MutableCaseMgmtMaintenanceImpl(int id, String code,String name,String description){
		this.id=id;
		this.code=code;
		this.name=name;
		this.description=description;
		
		
	}
				
	public MutableCaseMgmtMaintenanceImpl(){}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

public String getJSONString() throws LFIMSModelException {
		
		return getJSON().toString();
	}

public JSONObject getJSON() throws LFIMSModelException {
	JSONObject object= new JSONObject();
	try {
		object.put("id", this.id);
		object.put("code", this.code);
		object.put("name", this.name);
		object.put("description", this.description);
			
	} catch (Exception e) {
		throw new LFIMSModelException(e);
	}
	return object;
}

public int compareTo(CaseMgmtMaintenance o) {
	// TODO Auto-generated method stub
	return 0;
}	
}
