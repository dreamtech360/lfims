package com.dreamtech360.lfims.model.api.casemanagement;

import java.util.Date;

import com.dreamtech360.lfims.model.base.LFIMSCompositObject;

public interface ImportantDocuments extends LFIMSCompositObject<ImportantDocuments>  {
	
	
	
	public String getDocumentName();
	public boolean isOriginalYN() ;
	public String getReceivedFrom() ;
	public Date getReceivedDate();
	public String getReceivedBy();
	public String getKeptIn();
	public String getOtherDetailsReceipt() ;
	public Date getReturnDate() ;
	public String getReturnTo() ;
	public String getReturnBy() ;
	public String getOtherDetailsReturn() ;
	
	

	
}
