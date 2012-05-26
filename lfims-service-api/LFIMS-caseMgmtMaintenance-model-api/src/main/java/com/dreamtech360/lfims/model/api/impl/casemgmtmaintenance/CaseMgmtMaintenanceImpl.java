package com.dreamtech360.lfims.model.api.impl.casemgmtmaintenance;

import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.api.casemgmtmaintenance.CaseMgmtMaintenance;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class CaseMgmtMaintenanceImpl implements CaseMgmtMaintenance{
	
	private int id;
	private String code;
	private String name;
	private String description;
	
	
	
	public CaseMgmtMaintenanceImpl(int id, String code,String name,String description){
		this.id=id;
		this.code=code;
		this.name=name;
		this.description=description;
		
		
	}
		
	
	
	public int getId() {
		return id;
	}



	public String getCode() {
		return code;
	}



	public String getName() {
		return name;
	}



	public String getDescription() {
		return description;
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
		
		return 0;
	}
	
}
