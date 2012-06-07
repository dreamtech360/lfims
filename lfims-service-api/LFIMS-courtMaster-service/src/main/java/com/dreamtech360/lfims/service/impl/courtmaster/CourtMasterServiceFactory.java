package com.dreamtech360.lfims.service.impl.courtmaster;
import javax.jcr.Repository;

import org.osgi.framework.BundleContext;

import com.dreamtech360.lfims.model.api.courtmaster.CourtMaster;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.impl.courtmaster.CourtMasterMaintenanceService;
import com.dreamtech360.lfims.service.base.LFIMSModelServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class CourtMasterServiceFactory extends LFIMSModelServiceFactory<CourtMaster>  {

	private BundleContext context=null;
	private Repository repository=null;
	private volatile LFIMSModelService<CourtMaster> service=null;
	private LFIMSTransactionManagementService txnService=null;
	

	public CourtMasterServiceFactory(BundleContext context,Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=context;
		this.txnService=txnService;
	}
	 
	public CourtMasterServiceFactory(Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=null;
		this.txnService=txnService;
	}
	
	
	@Override
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSModelService<CourtMaster> lookupModelService() {
		
		/*service=(LFIMSModelService<CourtMaster>)context.getService(context.getServiceReference(CourtMasterMaintenanceService.class.getName()));
		return service;*/
		return createModelService();
	}

	@Override
	public LFIMSModelService<CourtMaster> createModelService() {
		if(service==null){
			synchronized(this){
			service= new CourtMasterMaintenanceService(repository);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<CourtMaster> createTxnModelService() {
		if(service==null){
			synchronized(this){
			service= new CourtMasterMaintenanceService(repository,txnService);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<CourtMaster> lookupTxnModelService() {
		
		return createTxnModelService();
	}





}
