package com.dreamtech360.lfims.service.impl.advocatemaster;
import javax.jcr.Repository;

import org.osgi.framework.BundleContext;

import com.dreamtech360.lfims.model.api.advocatemaster.AdvocateMaster;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.impl.advocatemaster.AdvocateMasterMaintenanceService;
import com.dreamtech360.lfims.service.base.LFIMSModelServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class AdvocateMasterServiceFactory extends LFIMSModelServiceFactory<AdvocateMaster>  {

	private BundleContext context=null;
	private Repository repository=null;
	private volatile LFIMSModelService<AdvocateMaster> service=null;
	private LFIMSTransactionManagementService txnService=null;
	

	public AdvocateMasterServiceFactory(BundleContext context,Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=context;
		this.txnService=txnService;
	}
	 
	public AdvocateMasterServiceFactory(Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=null;
		this.txnService=txnService;
	}
	
	
	@Override
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSModelService<AdvocateMaster> lookupModelService() {
		
		/*service=(LFIMSModelService<AdvocateMaster>)context.getService(context.getServiceReference(AdvocateMasterMaintenanceService.class.getName()));
		return service;*/
		return createModelService();
	}

	@Override
	public LFIMSModelService<AdvocateMaster> createModelService() {
		if(service==null){
			synchronized(this){
			service= new AdvocateMasterMaintenanceService(repository);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<AdvocateMaster> createTxnModelService() {
		if(service==null){
			synchronized(this){
			service= new AdvocateMasterMaintenanceService(repository,txnService);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<AdvocateMaster> lookupTxnModelService() {
		
		return createTxnModelService();
	}





}
