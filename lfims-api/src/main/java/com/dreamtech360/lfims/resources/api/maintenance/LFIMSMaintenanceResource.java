package com.dreamtech360.lfims.resources.api.maintenance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dreamtech360.lfims.model.api.advocatemaster.AdvocateMaster;
import com.dreamtech360.lfims.model.api.bankmaster.BankMaster;
import com.dreamtech360.lfims.model.api.branchmaster.BranchMaster;
import com.dreamtech360.lfims.model.api.casemgmtmaintenance.CaseMgmtMaintenance;
import com.dreamtech360.lfims.model.api.courtmaster.CourtMaster;
import com.dreamtech360.lfims.model.api.expensesmaster.ExpensesMaster;
import com.dreamtech360.lfims.model.api.ndpmaster.NdpMaster;
import com.dreamtech360.lfims.model.api.ouradvocatemaster.OurAdvocateMaster;

import com.dreamtech360.lfims.model.api.impl.advocatemaster.MutableAdvocateMasterImpl;
import com.dreamtech360.lfims.model.api.impl.bankmaster.MutableBankMasterImpl;
import com.dreamtech360.lfims.model.api.impl.branchmaster.MutableBranchMasterImpl;
import com.dreamtech360.lfims.model.api.impl.casemgmtmaintenance.MutableCaseMgmtMaintenanceImpl;
import com.dreamtech360.lfims.model.api.impl.courtmaster.MutableCourtMasterImpl;
import com.dreamtech360.lfims.model.api.impl.expensesmaster.MutableExpensesMasterImpl;
import com.dreamtech360.lfims.model.api.impl.ndpmaster.MutableNdpMasterImpl;
import com.dreamtech360.lfims.model.api.impl.ouradvocatemaster.MutableOurAdvocateMasterImpl;

import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.search.LFIMSAttributeMapper;

import com.dreamtech360.lfims.model.search.impl.bankmaster.BankMasterAttributeMapper;
import com.dreamtech360.lfims.model.search.impl.casemgmtmaintenance.CaseMgmtMaintenanceAttributeMapper;

import com.dreamtech360.lfims.model.search.impl.casemgmtmaintenance.CaseMgmtMaintenanceSearchParams;
import com.dreamtech360.lfims.model.search.impl.courtmaster.CourtMasterSearchParams;
import com.dreamtech360.lfims.model.search.impl.expensesmaster.ExpensesMasterSearchParams;
import com.dreamtech360.lfims.model.search.impl.ndpmaster.NdpMasterSearchParams;
import com.dreamtech360.lfims.model.search.impl.ouradvocatemaster.OurAdvocateMasterSearchParams;
import com.dreamtech360.lfims.model.search.impl.bankmaster.BankMasterSearchParams;
import com.dreamtech360.lfims.model.search.impl.branchmaster.BranchMasterSearchParams;
import com.dreamtech360.lfims.model.search.impl.advocatemaster.AdvocateMasterSearchParams;

import com.dreamtech360.lfims.model.service.base.LFIMSModelJCRService;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;
import com.dreamtech360.lfims.resources.api.activator.LFIMSAPIContext;
import com.dreamtech360.lfims.service.base.LFIMSGenericServiceFactory;
import com.dreamtech360.lfims.service.base.LFIMSModelServiceFactory;
import com.dreamtech360.lfims.services.ServiceEnum;
import com.dreamtech360.lfims.util.LFIMSJSONStringer;
import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("/maintenance/")
public class LFIMSMaintenanceResource {
 
 
	private LFIMSModelService<BankMaster> bankMasterService=null;
	private  LFIMSModelService<BranchMaster> branchMasterService=null;
	private  LFIMSModelService<AdvocateMaster> advocateMasterService=null;
	private  LFIMSModelService<OurAdvocateMaster> ourAdvocateMasterService=null;
	private  LFIMSModelService<CourtMaster> courtMasterService=null;
	private  LFIMSModelService<ExpensesMaster> expensesMasterService=null;
	private  LFIMSModelService<NdpMaster> ndpMasterService=null;
	private  LFIMSModelService<CaseMgmtMaintenance> caseMgmtMaintenanceService=null;
	//private  LFIMSGenericService<CaseMgmtMaintenance> caseMgmtMaintenanceService=null;
	//private  LFIMSGenericService<CaseMgmtMaintenance> caseMgmtMaintenanceService=null;
 
	public LFIMSMaintenanceResource(){
		System.out.println("LFIMSMaintenanceResource Constructor called");
		LFIMSModelServiceFactory<BankMaster>  bankServiceFactory= LFIMSAPIContext.getService(ServiceEnum.BANK_MASTER_SERVICE);
		LFIMSModelServiceFactory<BranchMaster> branchServiceFactory=LFIMSAPIContext.getService(ServiceEnum.BRANCH_MASTER_SERVICE);
		LFIMSModelServiceFactory<AdvocateMaster> advocateServiceFactory=LFIMSAPIContext.getService(ServiceEnum.ADVOCATE_MASTER_SERVICE);
		LFIMSModelServiceFactory<CourtMaster> courtServiceFactory=LFIMSAPIContext.getService(ServiceEnum.COURT_MASTER_SERVICE);
		LFIMSModelServiceFactory<OurAdvocateMaster> ourAdvocateServiceFactory=LFIMSAPIContext.getService(ServiceEnum.OUR_ADVOCATE_MASTER_SERVICE);
		LFIMSModelServiceFactory<ExpensesMaster> expensesServiceFactory=LFIMSAPIContext.getService(ServiceEnum.EXPENSES_MASTER_SERVICE);
		LFIMSModelServiceFactory<NdpMaster> ndpServiceFactory=LFIMSAPIContext.getService(ServiceEnum.NDP_MASTER_SERVICE);
		LFIMSModelServiceFactory<CaseMgmtMaintenance> caseMgmtMaintenanceServiceFactory=LFIMSAPIContext.getService(ServiceEnum.CASE_MGMT_MAINTENANCE);
		bankMasterService=bankServiceFactory.lookupTxnService();
		branchMasterService= branchServiceFactory.lookupTxnService();
		advocateMasterService=advocateServiceFactory.lookupTxnService();
		courtMasterService=courtServiceFactory.lookupTxnService();
		ourAdvocateMasterService=ourAdvocateServiceFactory.lookupTxnService();
		expensesMasterService=expensesServiceFactory.lookupTxnService();
		ndpMasterService=ndpServiceFactory.lookupTxnService();
		caseMgmtMaintenanceService=caseMgmtMaintenanceServiceFactory.lookupTxnService();
	} 

	@GET  
	@Path("/banks")
	@Produces({MediaType.TEXT_PLAIN}) 
	public String getAllBankMasterRecordsPaginated(
			//@QueryParam("callback") String callbackName,
			@QueryParam("page") String page, 
			@QueryParam("start") String start,
			@QueryParam("limit") String limit,
			@QueryParam("filter") String filter,
			@QueryParam("sort") String sort) throws NumberFormatException, LFIMSServiceException, LFIMSModelException { 

		List<LFIMSObject<BankMaster>> records=null;
		BankMasterSearchParams searchParams=new BankMasterSearchParams();
		Map<String,String> jsonAttributes=new HashMap<String,String>();
		Map<LFIMSAttributeMapper<BankMaster>,List<String>> searchCriteria=null;

		System.out.println("IN GET");
		jsonAttributes.put("success", "true");
	//	jsonAttributes.put("results", String.valueOf(bankMasterService.getTotalRecords()));
		System.out.println("Page ==>"+page);
		System.out.println("Start ==>"+start);
		System.out.println("Limit==>"+limit);
		System.out.println("Filter==>"+filter);
		System.out.println("Sort==>"+sort);
		
		if(filter==null || "".equals(filter)){
			records= bankMasterService.loadAllRecord(Integer.parseInt(start), Integer.parseInt(limit));
			jsonAttributes.put("results", String.valueOf(bankMasterService.getTotalRecords()));
			System.out.println("Number of records ==>"+bankMasterService.getTotalRecords());
		}else{
			searchCriteria=searchParams.parseFilterParameters(filter);
			records= bankMasterService.searchRecords(searchCriteria , true, Integer.parseInt(start), Integer.parseInt(limit));
			jsonAttributes.put("results", String.valueOf(bankMasterService.getTotalRecords(searchCriteria)));
			System.out.println("Number of records ==>"+bankMasterService.getTotalRecords(searchCriteria));
		}


		LFIMSJSONStringer<BankMaster> stringer=new LFIMSJSONStringer<BankMaster> (records,jsonAttributes,"bankMaster");
		
		return stringer.getJSONString();
	}

	@GET  
	@Path("/branches")
	@Produces({MediaType.TEXT_PLAIN}) 
	public String getAllBranchMasterRecordsPaginated(
			//@QueryParam("callback") String callbackName,
			@QueryParam("page") String page, 
			@QueryParam("start") String start,
			@QueryParam("limit") String limit,
			@QueryParam("filter") String filter,
			@QueryParam("sort") String sort) throws NumberFormatException, LFIMSServiceException, LFIMSModelException { 

		List<LFIMSObject<BranchMaster>> records=null;
		BranchMasterSearchParams searchParams=new BranchMasterSearchParams();
		Map<String,String> jsonAttributes=new HashMap<String,String>();
		Map<LFIMSAttributeMapper<BranchMaster>,List<String>> searchCriteria=null;

		System.out.println("IN GET");
		jsonAttributes.put("success", "true");
	//	jsonAttributes.put("results", String.valueOf(branchMasterService.getTotalRecords()));
		System.out.println("Page ==>"+page);
		System.out.println("Start ==>"+start);
		System.out.println("Limit==>"+limit);
		System.out.println("Filter==>"+filter);
		System.out.println("Sort==>"+sort);
		
		if(filter==null || "".equals(filter)){
			records= branchMasterService.loadAllRecord(Integer.parseInt(start), Integer.parseInt(limit));
			jsonAttributes.put("results", String.valueOf(branchMasterService.getTotalRecords()));
		}else{
			searchCriteria=searchParams.parseFilterParameters(filter);
			records= branchMasterService.searchRecords(searchCriteria , true, Integer.parseInt(start), Integer.parseInt(limit));
			jsonAttributes.put("results", String.valueOf(branchMasterService.getTotalRecords(searchCriteria)));
		}


		LFIMSJSONStringer<BranchMaster> stringer=new LFIMSJSONStringer<BranchMaster> (records,jsonAttributes,"branchMaster");
		return stringer.getJSONString();
	}

	
	
	@GET  
	@Path("/advocates")
	@Produces({MediaType.TEXT_PLAIN}) 
	public String getAllAdvocateMasterRecordsPaginated(
			//@QueryParam("callback") String callbackName,
			@QueryParam("page") String page, 
			@QueryParam("start") String start,
			@QueryParam("limit") String limit,
			@QueryParam("filter") String filter,
			@QueryParam("sort") String sort) throws NumberFormatException, LFIMSServiceException, LFIMSModelException { 

		List<LFIMSObject<AdvocateMaster>> records=null;
		AdvocateMasterSearchParams searchParams=new AdvocateMasterSearchParams();
		Map<String,String> jsonAttributes=new HashMap<String,String>();
		Map<LFIMSAttributeMapper<AdvocateMaster>,List<String>> searchCriteria=null;

		System.out.println("IN GET");
		jsonAttributes.put("success", "true");
	//	jsonAttributes.put("results", String.valueOf(branchMasterService.getTotalRecords()));
		System.out.println("Page ==>"+page);
		System.out.println("Start ==>"+start);
		System.out.println("Limit==>"+limit);
		System.out.println("Filter==>"+filter);
		System.out.println("Sort==>"+sort);
		
		if(filter==null || "".equals(filter)){
			records= advocateMasterService.loadAllRecord(Integer.parseInt(start), Integer.parseInt(limit));
			jsonAttributes.put("results", String.valueOf(advocateMasterService.getTotalRecords()));
		}else{
			searchCriteria=searchParams.parseFilterParameters(filter);
			records= advocateMasterService.searchRecords(searchCriteria , true, Integer.parseInt(start), Integer.parseInt(limit));
			jsonAttributes.put("results", String.valueOf(advocateMasterService.getTotalRecords(searchCriteria)));
		}


		LFIMSJSONStringer<AdvocateMaster> stringer=new LFIMSJSONStringer<AdvocateMaster> (records,jsonAttributes,"advocateMaster");
		return stringer.getJSONString();
	}

	
	@GET  
	@Path("/ouradvocates")
	@Produces({MediaType.TEXT_PLAIN}) 
	public String getAllOurAdvocateMasterRecordsPaginated(
			//@QueryParam("callback") String callbackName,
			@QueryParam("page") String page, 
			@QueryParam("start") String start,
			@QueryParam("limit") String limit,
			@QueryParam("filter") String filter,
			@QueryParam("sort") String sort) throws NumberFormatException, LFIMSServiceException, LFIMSModelException { 

		List<LFIMSObject<OurAdvocateMaster>> records=null;
		OurAdvocateMasterSearchParams searchParams=new OurAdvocateMasterSearchParams();
		Map<String,String> jsonAttributes=new HashMap<String,String>();
		Map<LFIMSAttributeMapper<OurAdvocateMaster>,List<String>> searchCriteria=null;

		System.out.println("IN GET");
		jsonAttributes.put("success", "true");
	//	jsonAttributes.put("results", String.valueOf(branchMasterService.getTotalRecords()));
		System.out.println("Page ==>"+page);
		System.out.println("Start ==>"+start);
		System.out.println("Limit==>"+limit);
		System.out.println("Filter==>"+filter);
		System.out.println("Sort==>"+sort);
		
		if(filter==null || "".equals(filter)){
			records= ourAdvocateMasterService.loadAllRecord(Integer.parseInt(start), Integer.parseInt(limit));
			jsonAttributes.put("results", String.valueOf(ourAdvocateMasterService.getTotalRecords()));
		}else{
			searchCriteria=searchParams.parseFilterParameters(filter);
			records= ourAdvocateMasterService.searchRecords(searchCriteria , true, Integer.parseInt(start), Integer.parseInt(limit));
			jsonAttributes.put("results", String.valueOf(ourAdvocateMasterService.getTotalRecords(searchCriteria)));
		}


		LFIMSJSONStringer<OurAdvocateMaster> stringer=new LFIMSJSONStringer<OurAdvocateMaster> (records,jsonAttributes,"ourAdvocateMaster");
		return stringer.getJSONString();
	}

	@GET  
	@Path("/courts")
	@Produces({MediaType.TEXT_PLAIN}) 
	public String getAllCortMasterRecordsPaginated(
			//@QueryParam("callback") String callbackName,
			@QueryParam("page") String page, 
			@QueryParam("start") String start,
			@QueryParam("limit") String limit,
			@QueryParam("filter") String filter,
			@QueryParam("sort") String sort) throws NumberFormatException, LFIMSServiceException, LFIMSModelException { 

		List<LFIMSObject<CourtMaster>> records=null;
		CourtMasterSearchParams searchParams=new CourtMasterSearchParams();
		Map<String,String> jsonAttributes=new HashMap<String,String>();
		Map<LFIMSAttributeMapper<CourtMaster>,List<String>> searchCriteria=null;

		System.out.println("IN GET");
		jsonAttributes.put("success", "true");
	//	jsonAttributes.put("results", String.valueOf(branchMasterService.getTotalRecords()));
		System.out.println("Page ==>"+page);
		System.out.println("Start ==>"+start);
		System.out.println("Limit==>"+limit);
		System.out.println("Filter==>"+filter);
		System.out.println("Sort==>"+sort);
		
		if(filter==null || "".equals(filter)){
			records= courtMasterService.loadAllRecord(Integer.parseInt(start), Integer.parseInt(limit));
			jsonAttributes.put("results", String.valueOf(courtMasterService.getTotalRecords()));
		}else{
			searchCriteria=searchParams.parseFilterParameters(filter);
			records= courtMasterService.searchRecords(searchCriteria , true, Integer.parseInt(start), Integer.parseInt(limit));
			jsonAttributes.put("results", String.valueOf(courtMasterService.getTotalRecords(searchCriteria)));
		}


		LFIMSJSONStringer<CourtMaster> stringer=new LFIMSJSONStringer<CourtMaster> (records,jsonAttributes,"courtMaster");
		return stringer.getJSONString();
	}

	
	@GET  
	@Path("/expenses")
	@Produces({MediaType.TEXT_PLAIN}) 
	public String getAllExpensesMasterRecordsPaginated(
			//@QueryParam("callback") String callbackName,
			@QueryParam("page") String page, 
			@QueryParam("start") String start,
			@QueryParam("limit") String limit,
			@QueryParam("filter") String filter,
			@QueryParam("sort") String sort) throws NumberFormatException, LFIMSServiceException, LFIMSModelException { 

		List<LFIMSObject<ExpensesMaster>> records=null;
		ExpensesMasterSearchParams searchParams=new ExpensesMasterSearchParams();
		Map<String,String> jsonAttributes=new HashMap<String,String>();
		Map<LFIMSAttributeMapper<ExpensesMaster>,List<String>> searchCriteria=null;

		System.out.println("IN GET");
		jsonAttributes.put("success", "true");
	//	jsonAttributes.put("results", String.valueOf(branchMasterService.getTotalRecords()));
		System.out.println("Page ==>"+page);
		System.out.println("Start ==>"+start);
		System.out.println("Limit==>"+limit);
		System.out.println("Filter==>"+filter);
		System.out.println("Sort==>"+sort);
		
		if(filter==null || "".equals(filter)){
			records= expensesMasterService.loadAllRecord(Integer.parseInt(start), Integer.parseInt(limit));
			jsonAttributes.put("results", String.valueOf(expensesMasterService.getTotalRecords()));
		}else{
			searchCriteria=searchParams.parseFilterParameters(filter);
			records= expensesMasterService.searchRecords(searchCriteria , true, Integer.parseInt(start), Integer.parseInt(limit));
			jsonAttributes.put("results", String.valueOf(expensesMasterService.getTotalRecords(searchCriteria)));
		}


		LFIMSJSONStringer<ExpensesMaster> stringer=new LFIMSJSONStringer<ExpensesMaster> (records,jsonAttributes,"expensesMaster");
		return stringer.getJSONString();
	}

	
	
	
	@GET  
	@Path("/nxtdatepurposes")
	@Produces({MediaType.TEXT_PLAIN}) 
	public String getAllNdpMasterRecordsPaginated(
			//@QueryParam("callback") String callbackName,
			@QueryParam("page") String page, 
			@QueryParam("start") String start,
			@QueryParam("limit") String limit,
			@QueryParam("filter") String filter,
			@QueryParam("sort") String sort) throws NumberFormatException, LFIMSServiceException, LFIMSModelException { 

		List<LFIMSObject<NdpMaster>> records=null;
		NdpMasterSearchParams searchParams=new NdpMasterSearchParams();
		Map<String,String> jsonAttributes=new HashMap<String,String>();
		Map<LFIMSAttributeMapper<NdpMaster>,List<String>> searchCriteria=null;

		System.out.println("IN GET");
		jsonAttributes.put("success", "true");
	//	jsonAttributes.put("results", String.valueOf(branchMasterService.getTotalRecords()));
		System.out.println("Page ==>"+page);
		System.out.println("Start ==>"+start);
		System.out.println("Limit==>"+limit);
		System.out.println("Filter==>"+filter);
		System.out.println("Sort==>"+sort);
		
		if(filter==null || "".equals(filter)){
			records= ndpMasterService.loadAllRecord(Integer.parseInt(start), Integer.parseInt(limit));
			jsonAttributes.put("results", String.valueOf(ndpMasterService.getTotalRecords()));
		}else{
			searchCriteria=searchParams.parseFilterParameters(filter);
			records= ndpMasterService.searchRecords(searchCriteria , true, Integer.parseInt(start), Integer.parseInt(limit));
			jsonAttributes.put("results", String.valueOf(ndpMasterService.getTotalRecords(searchCriteria)));
		}


		LFIMSJSONStringer<NdpMaster> stringer=new LFIMSJSONStringer<NdpMaster> (records,jsonAttributes,"ndpMaster");
		return stringer.getJSONString();
	}
	
	
	
	@GET  
	@Path("/casemgmtmaintenance/{code}")
	@Produces({MediaType.TEXT_PLAIN}) 
	public String getAllCaseMgmtMaintenanceRecordsPaginated(
			//@QueryParam("callback") String callbackName,
			@PathParam("code") String maintCode,
			@QueryParam("page") String page, 
			@QueryParam("start") String start,
			@QueryParam("limit") String limit,
			@QueryParam("filter") String filter,
			@QueryParam("sort") String sort) throws NumberFormatException, LFIMSServiceException, LFIMSModelException { 

		List<LFIMSObject<CaseMgmtMaintenance>> records=null;
		CaseMgmtMaintenanceSearchParams searchParams=new CaseMgmtMaintenanceSearchParams();
		Map<String,String> jsonAttributes=new HashMap<String,String>();
		Map<LFIMSAttributeMapper<CaseMgmtMaintenance>,List<String>> searchCriteria=null;
		Map<LFIMSAttributeMapper<CaseMgmtMaintenance>,List<String>> completeSearchCriteria=new HashMap<LFIMSAttributeMapper<CaseMgmtMaintenance>,List<String>>();
		List<String> maintCodeList=new ArrayList<String>();
		maintCodeList.add(maintCode);
		completeSearchCriteria.put(CaseMgmtMaintenanceAttributeMapper.CODE, maintCodeList);

		System.out.println("IN GET");
		jsonAttributes.put("success", "true");
	//	jsonAttributes.put("results", String.valueOf(branchMasterService.getTotalRecords()));
		System.out.println("Page ==>"+page);
		System.out.println("Start ==>"+start);
		System.out.println("Limit==>"+limit);
		System.out.println("Filter==>"+filter);
		System.out.println("Sort==>"+sort);
		
		if(filter!=null && !"".equals(filter)){
		
			searchCriteria=searchParams.parseFilterParameters(filter);
			completeSearchCriteria.putAll(searchCriteria);
			
		}
		records= caseMgmtMaintenanceService.searchRecords(completeSearchCriteria , true, Integer.parseInt(start), Integer.parseInt(limit));
		jsonAttributes.put("results", String.valueOf(caseMgmtMaintenanceService.getTotalRecords(completeSearchCriteria)));

		LFIMSJSONStringer<CaseMgmtMaintenance> stringer=new LFIMSJSONStringer<CaseMgmtMaintenance> (records,jsonAttributes,"caseMgmtMaintenance");
		return stringer.getJSONString();
	}
	
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("banks/{id}")
	public Response updateBankMasterRecord(@PathParam("maintenanceType") String maintenanceType,@PathParam("id") String id,MutableBankMasterImpl bankMasterRecord) throws LFIMSServiceException {

		System.out.println("IN UPDATE");
		bankMasterService.updateRecord(bankMasterRecord);
		return Response.status(200).build();
	}

	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("branches/{id}")
	public Response updateBranchMasterRecord(@PathParam("id") String id,MutableBranchMasterImpl branchMasterRecord) throws LFIMSServiceException {

		System.out.println("IN UPDATE");
		branchMasterService.updateRecord(branchMasterRecord);
		return Response.status(200).build();
	}

	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("advocates/{id}")
	public Response updateAdvocateMasterRecord(@PathParam("id") String id,MutableAdvocateMasterImpl advocateMasterRecord) throws LFIMSServiceException {

		System.out.println("IN UPDATE");
		advocateMasterService.updateRecord(advocateMasterRecord);
		return Response.status(200).build();
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("ouradvocates/{id}")
	public Response updateOurAdvocateMasterRecord(@PathParam("id") String id,MutableOurAdvocateMasterImpl ourAdvocateMasterRecord) throws LFIMSServiceException {

		System.out.println("IN UPDATE");
		ourAdvocateMasterService.updateRecord(ourAdvocateMasterRecord);
		return Response.status(200).build();
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("courts/{id}")
	public Response updateCourtMasterRecord(@PathParam("id") String id,MutableCourtMasterImpl courtMasterRecord) throws LFIMSServiceException {

		System.out.println("IN UPDATE");
		courtMasterService.updateRecord(courtMasterRecord);
		return Response.status(200).build();
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("expenses/{id}")
	public Response updateExpensesMasterRecord(@PathParam("id") String id,MutableExpensesMasterImpl expensesMasterRecord) throws LFIMSServiceException {

		System.out.println("IN UPDATE");
		expensesMasterService.updateRecord(expensesMasterRecord);
		return Response.status(200).build();
	}
	
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("nxtdatepurposes/{id}")
	public Response updateNdpMasterRecord(@PathParam("id") String id,MutableNdpMasterImpl ndpMasterRecord) throws LFIMSServiceException {

		System.out.println("IN UPDATE");
		ndpMasterService.updateRecord(ndpMasterRecord);
		return Response.status(200).build();
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("casemgmtmaintenance/{code}/{id}")
	public Response updateCaseMgmtMaintenanceRecord(@PathParam("code") String code,@PathParam("id") String id,MutableCaseMgmtMaintenanceImpl caseMgmtMaintenanceRecord) throws LFIMSServiceException {

		System.out.println("IN UPDATE");
		caseMgmtMaintenanceService.updateRecord(caseMgmtMaintenanceRecord);
		return Response.status(200).build();
	}
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("banks")
	public Response createBankasterRecord(MutableBankMasterImpl bankMasterRecord) throws LFIMSServiceException {
		System.out.println("IN POST");
		System.out.println("Name is===>"+bankMasterRecord.getName());
		bankMasterService.storeRecord(bankMasterRecord);// .createRecord();

		//Map<String,String> jsonAttributes=new HashMap<String,String>();
		//jsonAttributes.put("success", "true");
		//jsonAttributes.put("results", String.valueOf(bankMasterService.getTotalRecords()));
		//LFIMSJSONStringer<BankMaster> stringer=new LFIMSJSONStringer<BankMaster> (record,jsonAttributes,"bankMaster");
		//System.out.println(stringer.getJSONString());
		//return stringer.getJSONString();
		return Response.status(200).build();
	} 

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("branches")
	public void createBranchMasterRecord(MutableBranchMasterImpl branchMasterRecord) throws LFIMSServiceException {
		System.out.println("IN POST");
		Map<LFIMSAttributeMapper<BankMaster>,List<String>> searchCriteria=new HashMap<LFIMSAttributeMapper<BankMaster>,List<String>>();
		List<String> id=new ArrayList<String>();
		id.add(branchMasterRecord.getBankName());
		searchCriteria.put(BankMasterAttributeMapper.ID, id);
		
		Map<String,LFIMSObject<BankMaster>> bankMasterNode= ((LFIMSModelJCRService<BankMaster>) bankMasterService).fetchNodeReferences(searchCriteria, true);
		Iterator<String> keyIterator=bankMasterNode.keySet().iterator();
		while(keyIterator.hasNext()){
			String key=keyIterator.next();
			BankMaster data= (BankMaster) bankMasterNode.get(key);
			//Branch master stores the UUID of the bank master it belongs to
			branchMasterRecord.setReferenceKey(new BankMaster.BankReference(data.getName()),key);
		}
		branchMasterService.storeRecord(branchMasterRecord);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("advocates")
	public Response createAdvocateMasterRecord(MutableAdvocateMasterImpl advocateMasterRecord) throws LFIMSServiceException {
		System.out.println("IN POST");
		advocateMasterService.storeRecord(advocateMasterRecord);
		return Response.status(200).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("ouradvocates")
	public Response createOurAdvocateMasterRecord(MutableOurAdvocateMasterImpl ourAdvocateMasterRecord) throws LFIMSServiceException {
		System.out.println("IN POST");
		ourAdvocateMasterService.storeRecord(ourAdvocateMasterRecord);
		return Response.status(200).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("courts")
	public Response createCourtMasterRecord(MutableCourtMasterImpl courtMasterRecord) throws LFIMSServiceException {
		System.out.println("IN POST");
		courtMasterService.storeRecord(courtMasterRecord);
		return Response.status(200).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("expenses")
	public Response createExpensesMasterRecord(MutableExpensesMasterImpl expensesMasterRecord) throws LFIMSServiceException {
		System.out.println("IN POST");
		expensesMasterService.storeRecord(expensesMasterRecord);
		return Response.status(200).build();
	}
	
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("nxtdatepurposes")
	public Response createNdpMasterRecord(MutableNdpMasterImpl ndpMasterRecord) throws LFIMSServiceException {
		System.out.println("IN POST");
		ndpMasterService.storeRecord(ndpMasterRecord);
		return Response.status(200).build();
	}

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("casemgmtmaintenance/{code}")
	public Response createNdpMasterRecord(@PathParam("code") String code,MutableCaseMgmtMaintenanceImpl caseMgmtMaintenance) throws LFIMSServiceException {
		System.out.println("IN POST");
		caseMgmtMaintenance.setCode(code);
		caseMgmtMaintenanceService.storeRecord(caseMgmtMaintenance);
		return Response.status(200).build();
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("banks/{id}")
	public Response deleteBankMasterRecord(@PathParam("id") String id,MutableBankMasterImpl bankMasterRecord) throws NumberFormatException, LFIMSServiceException {

		System.out.println("IN DELETE");
		//	System.out.println("Maintenance Type is "+maintenanceType);
		//	throw new LFIMSServiceException("Error");
		bankMasterService.removeRecord(Integer.parseInt(id));
		return Response.status(200).build();
	}

	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("branches/{id}")
	public Response deleteBranchMasterRecord(@PathParam("id") String id,MutableBranchMasterImpl branchMasterRecord) throws NumberFormatException, LFIMSServiceException {

		System.out.println("IN DELETE");
		branchMasterService.removeRecord(Integer.parseInt(id));
		return Response.status(200).build();
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("advocates/{id}")
	public Response deleteAvocateMasterRecord(@PathParam("id") String id,MutableAdvocateMasterImpl advocateMasterRecord) throws NumberFormatException, LFIMSServiceException {

		System.out.println("IN DELETE");
		advocateMasterService.removeRecord(Integer.parseInt(id));
		return Response.status(200).build();
	}

	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("ouradvocates/{id}")
	public Response deleteOurAvocateMasterRecord(@PathParam("id") String id,MutableOurAdvocateMasterImpl ourAdvocateMasterRecord) throws NumberFormatException, LFIMSServiceException {

		System.out.println("IN DELETE");
		ourAdvocateMasterService.removeRecord(Integer.parseInt(id));
		return Response.status(200).build();
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("courts/{id}")
	public Response deleteCourtMasterRecord(@PathParam("id") String id,MutableCourtMasterImpl courtMasterRecord) throws NumberFormatException, LFIMSServiceException {

		System.out.println("IN DELETE");
		courtMasterService.removeRecord(Integer.parseInt(id));
		return Response.status(200).build();
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("expenses/{id}")
	public Response deleteExpensesMasterRecord(@PathParam("id") String id,MutableExpensesMasterImpl expensesMasterRecord) throws NumberFormatException, LFIMSServiceException {

		System.out.println("IN DELETE");
		expensesMasterService.removeRecord(Integer.parseInt(id));
		return Response.status(200).build();
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("nxtdatepurposes/{id}")
	public Response deleteNdpMasterRecord(@PathParam("id") String id,MutableNdpMasterImpl ndpMasterRecord) throws NumberFormatException, LFIMSServiceException {

		System.out.println("IN DELETE");
		ndpMasterService.removeRecord(Integer.parseInt(id));
		return Response.status(200).build();
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({"application/xml", "application/json"})
	@Path("casemgmtmaintenance/{id}")
	public Response deleteCaseMgmtMaintenanceRecord(@PathParam("id") String id,MutableCaseMgmtMaintenanceImpl caseMgmtMaintenanceRecord) throws NumberFormatException, LFIMSServiceException {

		System.out.println("IN DELETE");
		caseMgmtMaintenanceService.removeRecord(Integer.parseInt(id));
		return Response.status(200).build();
	}


}