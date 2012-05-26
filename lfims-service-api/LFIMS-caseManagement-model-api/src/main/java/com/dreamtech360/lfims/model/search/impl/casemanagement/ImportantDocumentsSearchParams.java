package com.dreamtech360.lfims.model.search.impl.casemanagement;

import com.dreamtech360.lfims.model.api.casemanagement.ImportantDocuments;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.SearchParams;


public class ImportantDocumentsSearchParams extends SearchParams<ImportantDocuments> {

	
	protected final LFIMSAttributeMapper<ImportantDocuments> getSearchCriteriaKey(String attributeName){

		LFIMSAttributeMapper<ImportantDocuments> key=null;
		if(attributeName.equals(ImportantDocumentsAttributeMapper.ID.getAttributeName()))
			key=ImportantDocumentsAttributeMapper.ID;
		else if(attributeName.equals(ImportantDocumentsAttributeMapper.DOCUMENT_NAME.getAttributeName()))
			key=ImportantDocumentsAttributeMapper.DOCUMENT_NAME;
		else if(attributeName.equals(ImportantDocumentsAttributeMapper.ORIGINAL_YN.getAttributeName()))
			key=ImportantDocumentsAttributeMapper.ORIGINAL_YN;
		else if(attributeName.equals(ImportantDocumentsAttributeMapper.RECEIVED_FROM.getAttributeName()))
			key=ImportantDocumentsAttributeMapper.RECEIVED_FROM;
		else if(attributeName.equals(ImportantDocumentsAttributeMapper.RECEIVED_DATE.getAttributeName()))
			key=ImportantDocumentsAttributeMapper.RECEIVED_DATE;
		else if(attributeName.equals(ImportantDocumentsAttributeMapper.RECEIVED_BY.getAttributeName()))
			key=ImportantDocumentsAttributeMapper.RECEIVED_BY;
		else if(attributeName.equals(ImportantDocumentsAttributeMapper.KEPT_IN.getAttributeName()))
			key=ImportantDocumentsAttributeMapper.KEPT_IN;
		else if(attributeName.equals(ImportantDocumentsAttributeMapper.OTHER_DETAILS_RECEIPT.getAttributeName()))
			key=ImportantDocumentsAttributeMapper.OTHER_DETAILS_RECEIPT;
		else if(attributeName.equals(ImportantDocumentsAttributeMapper.RETURN_DATE.getAttributeName()))
			key=ImportantDocumentsAttributeMapper.RETURN_DATE;
		else if(attributeName.equals(ImportantDocumentsAttributeMapper.RETURN_TO.getAttributeName()))
			key=ImportantDocumentsAttributeMapper.RETURN_TO;
		else if(attributeName.equals(ImportantDocumentsAttributeMapper.RETURN_BY.getAttributeName()))
			key=ImportantDocumentsAttributeMapper.RETURN_BY;
		else if(attributeName.equals(ImportantDocumentsAttributeMapper.OTHER_DETAILS_RETURN.getAttributeName()))
			key=ImportantDocumentsAttributeMapper.OTHER_DETAILS_RETURN;
		
				
		System.out.println(key.getKeyName());
		return key;
	}




}
