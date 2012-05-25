package com.dreamtech360.lfims.model.search.impl.casemgmtmaintenance;

import com.dreamtech360.lfims.model.api.casemgmtmaintenance.CaseMgmtMaintenance;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.SearchParams;


public class CaseMgmtMaintenanceSearchParams extends SearchParams<CaseMgmtMaintenance> {

	
	protected final LFIMSAttributeMapper<CaseMgmtMaintenance> getSearchCriteriaKey(String attributeName){

		LFIMSAttributeMapper<CaseMgmtMaintenance> key=null;
		if(attributeName.equals(CaseMgmtMaintenanceAttributeMapper.ID.getAttributeName()))
			key=CaseMgmtMaintenanceAttributeMapper.ID;
		else if(attributeName.equals(CaseMgmtMaintenanceAttributeMapper.CODE.getAttributeName()))
			key=CaseMgmtMaintenanceAttributeMapper.CODE;
		else if(attributeName.equals(CaseMgmtMaintenanceAttributeMapper.NAME.getAttributeName()))
			key=CaseMgmtMaintenanceAttributeMapper.NAME;
		else if(attributeName.equals(CaseMgmtMaintenanceAttributeMapper.DESCRIPTION.getAttributeName()))
			key=CaseMgmtMaintenanceAttributeMapper.DESCRIPTION;
				
		System.out.println(key.getKeyName());
		return key;
	}




}
