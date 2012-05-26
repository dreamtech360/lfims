package com.dreamtech360.lfims.model.api.impl.branchmaster;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.jcr.Node;

import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.api.branchmaster.BranchMaster;
import com.dreamtech360.lfims.model.api.branchmaster.MutableBranchMaster;
import com.dreamtech360.lfims.model.base.LFIMSNode;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSObjectReference;
import com.dreamtech360.lfims.model.base.Referenceable;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class MutableBranchMasterImpl implements MutableBranchMaster {

	
	private int id;
	private String name;
	private String region;
	private String address;
	private String contactPerson;
	private String contactPhone;
	private String bankName;
	private Map<Referenceable,String> reference;
	
	public MutableBranchMasterImpl(){
		super();
		this.reference=new HashMap<Referenceable,String>();
	}
	
	public MutableBranchMasterImpl(int id, String name,String region, String address,String contactPerson,String contactPhone,String bankName){
		this.id=id;
		this.name=name;
		this.region=region;
		this.address=address;
		this.contactPerson=contactPerson;
		this.contactPhone=contactPhone;
		this.bankName=bankName;
		this.reference=new HashMap<Referenceable,String>();
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
		object.put("bankName", this.bankName);
		object.put("name", this.name);
		object.put("region", this.region);
		object.put("address", this.address);
		object.put("contactPerson", this.contactPerson);
		object.put("contactPhone", this.contactPhone);
	} catch (Exception e) {
		throw new LFIMSModelException(e);
	}
	return object;
}

public int compareTo(BranchMaster o) {
	// TODO Auto-generated method stub
	return 0;
}

public String getBankName() {
	
	return this.bankName;
}

public void setBankName(String bankName) {
	this.bankName=bankName;
	
}

public Set<Referenceable> getAllReferences() {
		return reference.keySet();
		
	}

public Referenceable getReference(String name) {
	Iterator<Referenceable> iterator=reference.keySet().iterator();
	Referenceable toReturn=null;
	while(iterator.hasNext()){
		Referenceable item=iterator.next();
		LFIMSObjectReference ref=item.getReferenceMetaDetails();
		System.out.println(ref.getRefNodeKey());
		if(item.getReferenceMetaDetails().getRefNodeKey().equals(name))
		{
			toReturn=item;
			break;
		}
	}
	return toReturn;
}

	public String getReferenceKey(Referenceable name) {
		// TODO Auto-generated method stub
		return reference.get(name);
	}
	//Here the reference is nothing but the UUID of the node
	public void setReferenceKey(Referenceable name, String reference) {
		this.reference.put(name,reference);
		
	}

	
	
}
