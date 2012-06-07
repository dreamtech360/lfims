package com.dreamtech360.lfims.service.impl.bankmaster;
import javax.jcr.Repository;

import org.osgi.framework.BundleContext;

import com.dreamtech360.lfims.model.api.bankmaster.BankMaster;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.impl.bankmaster.BankMasterMaintenanceService;
import com.dreamtech360.lfims.service.base.LFIMSModelServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class BankMasterServiceFactory extends LFIMSModelServiceFactory<BankMaster>  {

	private BundleContext context=null;
	private Repository repository=null;
	private volatile LFIMSModelService<BankMaster> service=null;
	private LFIMSTransactionManagementService txnService=null;
	

	public BankMasterServiceFactory(BundleContext context,Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=context;
		this.txnService=txnService;
	}
	 
	public BankMasterServiceFactory(Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=null;
		this.txnService=txnService;
	}
	
	
	@Override
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSModelService<BankMaster> lookupModelService() {
		
		/*service=(LFIMSModelService<BankMaster>)context.getService(context.getServiceReference(BankMasterMaintenanceService.class.getName()));
		return service;*/
		return createModelService();
	}

	@Override
	public LFIMSModelService<BankMaster> createModelService() {
		if(service==null){
			synchronized(this){
			service= new BankMasterMaintenanceService(repository);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<BankMaster> createTxnModelService() {
		if(service==null){
			synchronized(this){
			service= new BankMasterMaintenanceService(repository,txnService);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<BankMaster> lookupTxnModelService() {
		
		return createTxnModelService();
	}





}
