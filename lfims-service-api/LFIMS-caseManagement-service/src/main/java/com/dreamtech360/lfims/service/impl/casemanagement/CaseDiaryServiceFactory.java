package com.dreamtech360.lfims.service.impl.casemanagement;
import javax.jcr.Repository;

import org.osgi.framework.BundleContext;

import com.dreamtech360.lfims.model.api.casemanagement.CaseDiary;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.impl.casemanagement.CaseDiaryService;
import com.dreamtech360.lfims.service.base.LFIMSModelServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class CaseDiaryServiceFactory extends LFIMSModelServiceFactory<CaseDiary>  {

	private BundleContext context=null;
	private Repository repository=null;
	private volatile LFIMSModelService<CaseDiary> service=null;
	private LFIMSTransactionManagementService txnService=null;
	

	public CaseDiaryServiceFactory(BundleContext context,Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=context;
		this.txnService=txnService;
	}
	 
	public CaseDiaryServiceFactory(Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=null;
		this.txnService=txnService;
	}
	
	
	@Override
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSModelService<CaseDiary> lookupModelService() {
		
		/*service=(LFIMSModelService<CaseDiary>)context.getService(context.getServiceReference(CaseDiaryMaintenanceService.class.getName()));
		return service;*/
		return createModelService();
	}

	@Override
	public LFIMSModelService<CaseDiary> createModelService() {
		if(service==null){
			synchronized(this){
			service= new CaseDiaryService(repository);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<CaseDiary> createTxnModelService() {
		if(service==null){
			synchronized(this){
			service= new CaseDiaryService(repository,txnService);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<CaseDiary> lookupTxnModelService() {
		
		return createTxnModelService();
	}





}
