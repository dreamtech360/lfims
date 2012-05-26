package com.dreamtech360.lfims.model.api.impl.ndpmaster;

import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.api.ndpmaster.NdpMaster;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class NdpMasterImpl implements NdpMaster{
	
	private int id;
	private String purpose;
	private String description;
	
	
		
	public int getId() {
		return id;
	}



	public String getPurpose() {
		return purpose;
	}



	public String getDescription() {
		return description;
	}



	public NdpMasterImpl(int id, String purpose,String description){
		this.id=id;
		this.purpose=purpose;
		this.description=description;
		
		
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
		
		return 0;
	}
	
}
