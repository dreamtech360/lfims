package com.dreamtech360.lfims.model.api.casemanagement;

import java.util.Date;

import com.dreamtech360.lfims.model.base.MutableLFIMSEntityObject;


public interface  MutableImportantDocuments extends ImportantDocuments, MutableLFIMSEntityObject<ImportantDocuments> {
	
	
	public void setDocumentName(String documentName);
	public void setOriginalYN(boolean originalYN) ;
	public void setReceivedFrom(String receivedFrom);
	public void setReceivedDate(Date receivedDate);
	public void setReceivedBy(String receivedBy);
	public void setKeptIn(String keptIn);
	public void setOtherDetailsReceipt(String otherDetailsReceipt) ;
	public void setReturnDate(Date returnDate) ;
	public void setReturnTo(String returnTo);
	public void setReturnBy(String returnBy); 
	public void setOtherDetailsReturn(String otherDetailsReturn);
	
	
	
	}
