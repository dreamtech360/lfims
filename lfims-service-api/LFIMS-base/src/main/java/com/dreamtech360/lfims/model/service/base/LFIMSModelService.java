package com.dreamtech360.lfims.model.service.base;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;

public interface LFIMSModelService<T> extends LFIMSGenericService<T> {

	
	LFIMSObject<T> createRecord(int id);
	LFIMSObject<T> createRecord()throws LFIMSServiceException;
	Map<String,LFIMSObject<T>> storeRecord(LFIMSObject<T> record) throws LFIMSServiceException;
	void storeAllRecord(List<LFIMSObject<T>> records)throws LFIMSServiceException;
	LFIMSObject<T> loadRecord(int id)throws LFIMSServiceException;
	List<LFIMSObject<T>> loadAllRecord() throws LFIMSServiceException;
	List<LFIMSObject<T>> loadAllRecord(int offset,int limit) throws LFIMSServiceException;
	LFIMSObject<T> loadRecordToEdit(int id) throws LFIMSServiceException;
	List<LFIMSObject<T>> loadAllRecordToEdit() throws LFIMSServiceException;
	void removeRecords(List<Integer> id)  throws LFIMSServiceException;
	void removeRecord(int id)  throws LFIMSServiceException;
	void removeAllRecords() throws LFIMSServiceException;
	List<LFIMSObject<T>> searchRecords(Map<LFIMSAttributeMapper<T>,List<String>> searchCriteria,boolean readOnly) throws LFIMSServiceException;
	List<LFIMSObject<T>> searchRecords(Map<LFIMSAttributeMapper<T>,List<String>> searchCriteria,boolean readOnly,int offset,int limit) throws LFIMSServiceException;
	long getTotalRecords() throws LFIMSServiceException;
	long getTotalRecords(Map<LFIMSAttributeMapper<T>,List<String>> searchCriteria) throws LFIMSServiceException;
	void updateRecord(LFIMSObject<T> record) throws LFIMSServiceException;
}
