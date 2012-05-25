package com.dreamtech360.lfims.model.base;

import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class LFIMSPickListElement implements LFIMSObject<LFIMSPickListElement>{

	private int id;
	private String value;
	
	public LFIMSPickListElement(int id,String value){
		this.id=id;
		this.value=value;
	}
	public int getId() {
		return id;
	}
	
	public String getValue() {
		return value;
	}
	
	
	public boolean equals(Object object){
		
		LFIMSPickListElement input=null;
		if(object instanceof LFIMSPickListElement){
			input=(LFIMSPickListElement)object;
			if(this.id==input.getId())
				return true;
		}
		
		return false;
	}
	
	public int hashCode(){
		
		return 1;
	}
	public int compareTo(LFIMSPickListElement o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getJSONString() throws LFIMSModelException {
		// TODO Auto-generated method stub
		return null;
	}
	public JSONObject getJSON() throws LFIMSModelException {
		// TODO Auto-generated method stub
		return null;
	}
}
