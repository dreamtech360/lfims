package com.dreamtech360.lfims.model.api.impl.casemanagement;

import java.util.Date;

import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.api.casemanagement.ImportantDocuments;
import com.dreamtech360.lfims.model.api.casemanagement.MutableImportantDocuments;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class MutableImportantDocumentsImpl implements MutableImportantDocuments{

	private int id;
	private String uuid;
	private String documentName;
	private boolean originalYN;
	private String receivedFrom;
	private Date receivedDate;
	private String receivedBy;
	private String keptIn;
	private String otherDetailsReceipt;
	private Date returnDate;
	private String returnTo;
	private String returnBy;
	private String otherDetailsReturn;
	
	public MutableImportantDocumentsImpl(
			 int id,
		//	 String uuid,
			 String documentName,
			 boolean originalYN,
			 String receivedFrom,
			 Date receivedDate,
			 String receivedBy,
			 String keptIn,
			 String otherDetailsReceipt,
			 Date returnDate,
			 String returnTo,
			 String returnBy,
			 String otherDetailsReturn
			){
		this.id=id;
	//	this.uuid=uuid;
		this.documentName=documentName;
		this.originalYN=originalYN;
		this.receivedFrom=receivedFrom;
		this.receivedDate=receivedDate;
		this.receivedBy=receivedBy;
		this.keptIn=keptIn;
		this.otherDetailsReceipt=otherDetailsReceipt;
		this.returnDate=returnDate;
		this.returnTo=returnTo;
		this.returnBy=returnBy;
		this.otherDetailsReturn=otherDetailsReturn;
	}
	
	
	public MutableImportantDocumentsImpl(){}
	
public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public boolean isOriginalYN() {
		return originalYN;
	}

	public void setOriginalYN(boolean originalYN) {
		this.originalYN = originalYN;
	}

	public String getReceivedFrom() {
		return receivedFrom;
	}

	public void setReceivedFrom(String receivedFrom) {
		this.receivedFrom = receivedFrom;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	public String getKeptIn() {
		return keptIn;
	}

	public void setKeptIn(String keptIn) {
		this.keptIn = keptIn;
	}

	public String getOtherDetailsReceipt() {
		return otherDetailsReceipt;
	}

	public void setOtherDetailsReceipt(String otherDetailsReceipt) {
		this.otherDetailsReceipt = otherDetailsReceipt;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getReturnTo() {
		return returnTo;
	}

	public void setReturnTo(String returnTo) {
		this.returnTo = returnTo;
	}

	public String getReturnBy() {
		return returnBy;
	}

	public void setReturnBy(String returnBy) {
		this.returnBy = returnBy;
	}

	public String getOtherDetailsReturn() {
		return otherDetailsReturn;
	}

	public void setOtherDetailsReturn(String otherDetailsReturn) {
		this.otherDetailsReturn = otherDetailsReturn;
	}

public String getJSONString() throws LFIMSModelException {
		
		return getJSON().toString();
	}
	public JSONObject getJSON() throws LFIMSModelException {
		JSONObject object= new JSONObject();
		try {
			object.put("id", this.id);
			object.put("uuid", this.uuid);
			object.put(" documentName",this.documentName);
			object.put(" originalYN",this.originalYN);
			object.put(" receivedFrom",this.receivedFrom);
			object.put(" receivedDate",this.receivedDate);
			object.put(" receivedBy",this.receivedBy);
			object.put(" keptIn",this.keptIn);
			object.put(" otherDetailsReceipt",this.otherDetailsReceipt);
			object.put(" returnDate",this.returnDate);
			object.put(" returnTo",this.returnTo);
			object.put(" returnBy",this.receivedBy);
			object.put(" otherDetailsReturn",this.otherDetailsReturn);
			
			
		} catch (Exception e) {
			throw new LFIMSModelException(e);
		}
		return object;
	}
	public int compareTo(ImportantDocuments o) {
		
		return 0;
	}


	public String getUuid() {
		
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid=uuid;
		
	}
}