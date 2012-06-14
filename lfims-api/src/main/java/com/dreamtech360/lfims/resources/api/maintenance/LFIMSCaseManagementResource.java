package com.dreamtech360.lfims.resources.api.maintenance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dreamtech360.lfims.model.api.casemanagement.CaseDiary;
import com.dreamtech360.lfims.model.api.casemanagement.CaseMaster;
import com.dreamtech360.lfims.model.api.casemanagement.DefendentDetails;
import com.dreamtech360.lfims.model.api.casemanagement.ImportantDocuments;
import com.dreamtech360.lfims.model.api.casemanagement.SecurityDetails;
import com.dreamtech360.lfims.model.api.impl.casemanagement.MutableCaseMasterImpl;
import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;
import com.dreamtech360.lfims.model.search.impl.casemanagement.CaseMasterSearchParams;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;

import com.dreamtech360.lfims.resources.api.activator.LFIMSAPIContext;
import com.dreamtech360.lfims.service.base.LFIMSGenericServiceFactory;
import com.dreamtech360.lfims.service.base.LFIMSModelServiceFactory;
import com.dreamtech360.lfims.service.cachemanagement.LFIMSCacheService;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

import com.dreamtech360.lfims.services.ServiceEnum;
import com.dreamtech360.lfims.util.LFIMSJSONStringer;
import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("/casemanagement/")
public class LFIMSCaseManagementResource {
 
	private  LFIMSModelService<CaseMaster>   caseMasterService=null;
	private  LFIMSModelService<DefendentDetails>  caseDefendentDetailsService =null;
	private  LFIMSModelService<CaseDiary>  caseDiaryService=null;
	private  LFIMSModelService<ImportantDocuments>  caseImportantDocumentsService=null;
	private  LFIMSModelService<SecurityDetails>  caseSecurityDetailsService=null;
	private LFIMSTransactionManagementService txnService=null;
	private LFIMSCacheService cacheService=null;
	
	public LFIMSCaseManagementResource(){
		System.out.println("LFIMSCaseManagementResource Constructor called");
		LFIMSModelServiceFactory<CaseMaster>  caseMasterServiceFactory= LFIMSAPIContext.getService(ServiceEnum.CASE_MASTER);
		LFIMSModelServiceFactory<DefendentDetails> caseDefendentDetailsServiceFactory= LFIMSAPIContext.getService(ServiceEnum.CASE_DEFENDENT_DETAILS);
		LFIMSModelServiceFactory<ImportantDocuments> caseImportantDocumentsServiceFactory= LFIMSAPIContext.getService(ServiceEnum.CASE_IMPORTANT_DOCUMENTS);
		LFIMSModelServiceFactory<SecurityDetails> caseSecurityDetailsServiceFactory= LFIMSAPIContext.getService(ServiceEnum.CASE_SECURITY_DETAILS);
		LFIMSModelServiceFactory<CaseDiary> caseDiaryServiceFactory= LFIMSAPIContext.getService(ServiceEnum.CASE_DIARY);
		LFIMSGenericServiceFactory<LFIMSTransactionManagementService> txnServiceFactory=LFIMSAPIContext.getGenericService(ServiceEnum.TRANSACTION_MANAGEMENT_SERVICE);
		LFIMSGenericServiceFactory<LFIMSCacheService> cacheServiceFactory=LFIMSAPIContext.getGenericService(ServiceEnum.CACHE_MANAGEMENT_SERVICE);
		
		caseMasterService=caseMasterServiceFactory.createTxnService();
		caseDefendentDetailsService =caseDefendentDetailsServiceFactory.createTxnService();
		caseDiaryService=caseDiaryServiceFactory.createTxnService();
		caseImportantDocumentsService=caseImportantDocumentsServiceFactory.createTxnService();
		caseSecurityDetailsService=caseSecurityDetailsServiceFactory.createTxnService();
		txnService=(LFIMSTransactionManagementService)txnServiceFactory.createService();
		cacheService=(LFIMSCacheService)cacheServiceFactory.createService();
	} 
	

	
	
	@GET  
	@Path("/cases/{id}")
	@Produces({MediaType.TEXT_PLAIN}) 
	public String getAllCaseMasterRecordsPaginated(
			@PathParam("id") String id) throws NumberFormatException, LFIMSServiceException, LFIMSModelException { 

		System.out.println("IN GET for record ==>"+id);
		LFIMSObject<CaseMaster> record=null;
		Map<String,String> jsonAttributes=new HashMap<String,String>();
		jsonAttributes.put("success", "true");
		record= caseMasterService.loadRecord(Integer.parseInt(id)) ;
		jsonAttributes.put("results", "1");
		LFIMSJSONStringer<CaseMaster> stringer=new LFIMSJSONStringer<CaseMaster> (record,jsonAttributes,"cases");
		System.out.println(stringer.getJSONString());
		return stringer.getJSONString();
	}
	
	
	
	@GET  
	@Path("/cases")
	@Produces({MediaType.TEXT_PLAIN}) 
	public String getAllCaseMasterRecordsPaginated(
			@DefaultValue("1") @QueryParam("page") String page, 
			@DefaultValue("0") @QueryParam("start") String start,
			@DefaultValue("1") @QueryParam("limit") String limit,
			@QueryParam("filter") String filter,
			@QueryParam("sort") String sort) throws NumberFormatException, LFIMSServiceException, LFIMSModelException { 

		List<LFIMSObject<CaseMaster>> records=null;
		CaseMasterSearchParams searchParams=new CaseMasterSearchParams();
		Map<String,String> jsonAttributes=new HashMap<String,String>();
		Map<LFIMSAttributeMapper<CaseMaster>,List<String>> searchCriteria=null;

		System.out.println("IN GET");
		jsonAttributes.put("success", "true");
		System.out.println("Page ==>"+page);
		System.out.println("Start ==>"+start);
		System.out.println("Limit==>"+limit);
		System.out.println("Filter==>"+filter);
		System.out.println("Sort==>"+sort);
					
		if(filter==null || "".equals(filter)){
			records= caseMasterService.loadAllRecord(Integer.parseInt(start), Integer.parseInt(limit));
			jsonAttributes.put("results", String.valueOf(caseMasterService.getTotalRecords()));
			System.out.println("Number of records ==>"+caseMasterService.getTotalRecords());
		}else{
			searchCriteria=searchParams.parseFilterParameters(filter);
			records= caseMasterService.searchRecords(searchCriteria , true, Integer.parseInt(start), Integer.parseInt(limit));
			jsonAttributes.put("results", String.valueOf(caseMasterService.getTotalRecords(searchCriteria)));
			System.out.println("Number of records ==>"+caseMasterService.getTotalRecords(searchCriteria));
		}
		LFIMSJSONStringer<CaseMaster> stringer=new LFIMSJSONStringer<CaseMaster> (records,jsonAttributes,"cases");
		
		return stringer.getJSONString();
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("cases/{id}")
	public Response updateCaseMasterRepord(@PathParam("id") String id,MutableCaseMasterImpl caseMasterRecord) throws LFIMSServiceException {

		System.out.println("IN UPDATE");
		caseMasterService.updateRecord(caseMasterRecord);
		return Response.status(200).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("cases")
	public Response createCaseMasterRecord(MutableCaseMasterImpl caseMasterRecord) throws LFIMSServiceException, LFIMSModelException {
		System.out.println("IN POST");
		System.out.println("===================");
		System.out.println(caseMasterRecord.getJSONString());
		System.out.println("===================");
		txnService.beginTransaction();
		Map<String,LFIMSObject<CaseMaster>> data=caseMasterService.storeRecord(caseMasterRecord);
		cacheService.cacheObject(data.get(data.keySet().iterator().next()));
		txnService.commitTransaction();

		return Response.status(200).build();
	} 
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("cases/{id}")
	public Response deleteCaseMasterRepord(@PathParam("id") String id) throws NumberFormatException, LFIMSServiceException {

		System.out.println("IN DELETE");
		caseMasterService.removeRecord(Integer.parseInt(id));
		return Response.status(200).build();
	}
}