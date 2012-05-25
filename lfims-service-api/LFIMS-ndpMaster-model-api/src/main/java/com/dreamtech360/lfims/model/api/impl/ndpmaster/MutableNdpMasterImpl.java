package com.dreamtech360.lfims.model.api.impl.ndpmaster;

import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.api.ndpmaster.NdpMaster;
import com.dreamtech360.lfims.model.api.ndpmaster.MutableNdpMaster;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class MutableNdpMasterImpl implements MutableNdpMaster{

	
	private int id;
	private String purpose;
	private String description;
	
	
	
	public MutableNdpMasterImpl(){
	
		}
	
	public MutableNdpMasterImpl(int id, String purpose,String description){
		this.id=id;
		this.purpose=purpose;
		this.description=description;
		
	}
	
	

	
public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
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
		object.put("purpose", this.purpose);
		object.put("description", this.description);
			
	} catch (Exception e) {
		throw new LFIMSModelException(e);
	}
	return object;
}

public int compareTo(NdpMaster o) {
	// TODO Auto-generated method stub
	return 0;
}	
}
