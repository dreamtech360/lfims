package com.dreamtech360.lfims.service.impl.casemanagement;
import javax.jcr.Repository;

import org.osgi.framework.BundleContext;

import com.dreamtech360.lfims.model.api.casemanagement.CaseMaster;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.impl.casemanagement.CaseMasterService;
import com.dreamtech360.lfims.service.base.LFIMSModelServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class CaseMasterServiceFactory extends LFIMSModelServiceFactory<CaseMaster>  {

	private BundleContext context=null;
	private Repository repository=null;
	private volatile LFIMSModelService<CaseMaster> service=null;
	private LFIMSTransactionManagementService txnService=null;
	

	public CaseMasterServiceFactory(BundleContext context,Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=context;
		this.txnService=txnService;
	}
	 
	public CaseMasterServiceFactory(Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=null;
		this.txnService=txnService;
	}
	
	
	@Override
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSModelService<CaseMaster> lookupModelService() {
		
		/*service=(LFIMSModelService<CaseMaster>)context.getService(context.getServiceReference(CaseMasterMaintenanceService.class.getName()));
		return service;*/
		return createModelService();
	}

	@Override
	public LFIMSModelService<CaseMaster> createModelService() {
		if(service==null){
			synchronized(this){
			service= new CaseMasterService(repository);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<CaseMaster> createTxnModelService() {
		if(service==null){
			synchronized(this){
			service= new CaseMasterService(repository,txnService);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<CaseMaster> lookupTxnModelService() {
		
		return createTxnModelService();
	}





}
