package com.dreamtech360.lfims.model.api.impl.casemanagement;

import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.api.casemanagement.DefendentDetails;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class DefendentDetailsImpl implements DefendentDetails{
	
	private int id;
	private String uuid;
	private String defendentName;
	private int status;
	private boolean deadOrAlive;
	private String address1;
	private String address2;
	private String address3;
	
		public DefendentDetailsImpl(
				int id,
				String defendentName,
				int status,
				boolean deadOrAlive,
				String address1,
				String address2,
				String address3
				
				){
			this.id=id;
			this.defendentName=defendentName;
			this.status=status;
			this.deadOrAlive=deadOrAlive;
			this.address1=address1;
			this.address2=address2;
			this.address3=address3;
		}
	
		public int getId() {
			return id;
		}
		
	public String getDefendentName() {
		return defendentName;
	}
	public void setDefendentName(String defendentName) {
		this.defendentName = defendentName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isDeadOrAlive() {
		return deadOrAlive;
	}
	public void setDeadOrAlive(boolean deadOrAlive) {
		this.deadOrAlive = deadOrAlive;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getJSONString() throws LFIMSModelException {
		
		return getJSON().toString();
	}
	public JSONObject getJSON() throws LFIMSModelException {
		JSONObject object= new JSONObject();
		try {
			object.put("id", this.id);
			object.put("uuid", this.uuid);
					object.put("defendentName",this.defendentName);
					object.put("status",this.status);
					object.put("deadOrAlive",this.deadOrAlive);
					object.put("address1",this.address1);
					object.put("address2",this.address2);
					object.put("address3",this.address3);
			
			
		} catch (Exception e) {
			throw new LFIMSModelException(e);
		}
		return object;
	}
	public int compareTo(DefendentDetails o) {
		
		return 0;
	}

	public String getUuid() {
	
		return uuid;
	}


	public void setUuid(String Uuid) {
		this.uuid=Uuid;
	}

	
}
