package com.dreamtech360.lfims.model.search.impl.casemanagement;

import com.dreamtech360.lfims.model.api.casemanagement.ImportantDocuments;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;

public enum ImportantDocumentsAttributeMapper implements LFIMSAttributeMapper<ImportantDocuments>  {

	//Make sure the attribute name matched with that in the View Layer (Model attribute names)
	 
	ID("lfims:id","id"),
	DOCUMENT_NAME("lfims:documentName","documentName"),
	ORIGINAL_YN("lfims:originalYN","originalYN"),
	RECEIVED_FROM("lfims:receivedFrom","receivedFrom"),
	RECEIVED_DATE("lfims:receivedDate","receivedDate"),
	RECEIVED_BY("lfims:receivedBy","receivedBy"),
	KEPT_IN("lfims:keptIn","keptIn"),
	OTHER_DETAILS_RECEIPT("lfims:otherDetailsReceipt","otherDetailsReceipt"),
	RETURN_DATE("lfims:returnDate","returnDate"),
	RETURN_TO("lfims:returnTo","returnTo"),
	RETURN_BY("lfims:returnBy","returnBy"),
	OTHER_DETAILS_RETURN("lfims:otherDetailsReturn","otherDetailsReturn");

	private final String fieldName;
	private final String attributeName;

	public String getFieldName(){
		return fieldName;
	}
	public String getAttributeName(){
		return attributeName;
	}
	ImportantDocumentsAttributeMapper(String fieldName,String attributeName){
		this.fieldName=fieldName;
		this.attributeName=attributeName;
	}
	
	public String getKeyName() {
		// TODO Auto-generated method stub
		return this.attributeName;
	}
};
