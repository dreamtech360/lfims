package com.dreamtech360.lfims.service.impl.branchmaster;
import javax.jcr.Repository;

import org.osgi.framework.BundleContext;

import com.dreamtech360.lfims.model.api.branchmaster.BranchMaster;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.impl.branchmaster.BranchMasterMaintenanceService;
import com.dreamtech360.lfims.service.base.LFIMSModelServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class BranchMasterServiceFactory extends LFIMSModelServiceFactory<BranchMaster>  {

	private BundleContext context=null;
	private Repository repository=null;
	private volatile LFIMSModelService<BranchMaster> service=null;
	private LFIMSTransactionManagementService txnService=null;
	

	public BranchMasterServiceFactory(BundleContext context,Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=context;
		this.txnService=txnService;
	}
	 
	public BranchMasterServiceFactory(Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=null;
		this.txnService=txnService;
	}
	
	
	@Override
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSModelService<BranchMaster> lookupModelService() {
		
		/*service=(LFIMSModelService<BranchMaster>)context.getService(context.getServiceReference(BranchMasterMaintenanceService.class.getName()));
		return service;*/
		return createModelService();
	}

	@Override
	public LFIMSModelService<BranchMaster> createModelService() {
		if(service==null){
			synchronized(this){
			service= new BranchMasterMaintenanceService(repository);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<BranchMaster> createTxnModelService() {
		if(service==null){
			synchronized(this){
			service= new BranchMasterMaintenanceService(repository,txnService);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<BranchMaster> lookupTxnModelService() {
		
		return createTxnModelService();
	}





}
