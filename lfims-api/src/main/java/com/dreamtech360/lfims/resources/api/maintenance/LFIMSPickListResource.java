package com.dreamtech360.lfims.resources.api.maintenance;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.api.casemanagement.CaseMaster;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.base.LFIMSPickListElement;
import com.dreamtech360.lfims.model.service.base.LFIMSPickList;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;
import com.dreamtech360.lfims.model.service.impl.casemanagement.CaseMasterService;
import com.dreamtech360.lfims.service.impl.casemanagement.CaseMasterServiceFactory;
import com.dreamtech360.lfims.services.LFIMSServiceFactoryLocator;
import com.dreamtech360.lfims.services.ServiceEnum;

import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("/picklist/")
public class LFIMSPickListResource {
 
	private CaseMasterService caseMasterService=null;


	public LFIMSPickListResource(){
		System.out.println("LFIMSCaseManagementResource Constructor called");
		CaseMasterServiceFactory caseServiceFactory=(CaseMasterServiceFactory) LFIMSServiceFactoryLocator.getServiceFactory(ServiceEnum.CASE_MASTER);
		caseMasterService=(CaseMasterService) caseServiceFactory.lookupService();
	} 
	
	@GET  
	@Path("/cases/respondentNameList")
	@Produces({MediaType.TEXT_PLAIN}) 
	public String getRespondentNameList() throws NumberFormatException, LFIMSServiceException, LFIMSModelException { 

		LFIMSPickList respondentNameList= new LFIMSPickList(){

			private Set<LFIMSPickListElement> list=new HashSet<LFIMSPickListElement>();
			private JSONArray array=new JSONArray();
			
			
			@Override
			public String getListJSON() throws LFIMSServiceException {
				// TODO Auto-generated method stub
				return array.toString();
			}

			@Override
			public void setElement(LFIMSPickListElement element) throws LFIMSServiceException {
				// TODO Auto-generated method stub
				try{
				boolean isUnique=list.add(element);
				if(isUnique){
					JSONObject object=new JSONObject();
					object.put("id", element.getId());
					object.put("value", element.getValue());
					array.put(object);
				}
				}catch(Exception e){
					throw new LFIMSServiceException(e);
				}
			}
			
		};
		
		
		List<LFIMSObject<CaseMaster>> caseMasterList=caseMasterService.loadAllRecord();
		Iterator<LFIMSObject<CaseMaster>> iterator=caseMasterList.iterator();
		while (iterator.hasNext())
		{
			CaseMaster listObject=(CaseMaster)iterator.next();
		//	LFIMSPickListElement pickListElement=new LFIMSPickListElement();
			//pickListElement.setName(listObject.getRespondentName());
			//pickListElement.setValue(listObject.getRespondentName());
	//		respondentNameList.setElement(pickListElement);
		}
		return respondentNameList.getListJSON();
		
	}
	
	
	
}