package com.dreamtech360.lfims.model.api.impl.expensesmaster;

import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.api.expensesmaster.ExpensesMaster;
import com.dreamtech360.lfims.model.api.expensesmaster.MutableExpensesMaster;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class MutableExpensesMasterImpl implements MutableExpensesMaster{

	
	private int id;
	private String reason;
	private String description;
	
	
	
	public MutableExpensesMasterImpl(){
	
		}
	
	public MutableExpensesMasterImpl(int id, String reason,String description){
		this.id=id;
		this.reason=reason;
		this.description=description;
		
	}
	
	

	
public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
		object.put("reason", this.reason);
		object.put("description", this.description);
			
	} catch (Exception e) {
		throw new LFIMSModelException(e);
	}
	return object;
}

public int compareTo(ExpensesMaster o) {
	// TODO Auto-generated method stub
	return 0;
}	
}
