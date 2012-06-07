package com.dreamtech360.lfims.service.impl.casemgmtmaintenance;
import javax.jcr.Repository;

import org.osgi.framework.BundleContext;

import com.dreamtech360.lfims.model.api.casemgmtmaintenance.CaseMgmtMaintenance;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.impl.casemgmtmaintenance.CaseMgmtMaintenanceService;
import com.dreamtech360.lfims.service.base.LFIMSModelServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class CaseMgmtMaintenanceServiceFactory extends LFIMSModelServiceFactory<CaseMgmtMaintenance>  {

	private BundleContext context=null;
	private Repository repository=null;
	private volatile LFIMSModelService<CaseMgmtMaintenance> service=null;
	private LFIMSTransactionManagementService txnService=null;
	

	public CaseMgmtMaintenanceServiceFactory(BundleContext context,Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=context;
		this.txnService=txnService;
	}
	 
	public CaseMgmtMaintenanceServiceFactory(Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=null;
		this.txnService=txnService;
	}
	
	
	@Override
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSModelService<CaseMgmtMaintenance> lookupModelService() {
		
		/*service=(LFIMSModelService<CaseMgmtMaintenance>)context.getService(context.getServiceReference(CaseMgmtMaintenanceMaintenanceService.class.getName()));
		return service;*/
		return createModelService();
	}

	@Override
	public LFIMSModelService<CaseMgmtMaintenance> createModelService() {
		if(service==null){
			synchronized(this){
			service= new CaseMgmtMaintenanceService(repository);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<CaseMgmtMaintenance> createTxnModelService() {
		if(service==null){
			synchronized(this){
			service= new CaseMgmtMaintenanceService(repository,txnService);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<CaseMgmtMaintenance> lookupTxnModelService() {
		
		return createTxnModelService();
	}





}
