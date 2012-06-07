package com.dreamtech360.lfims.service.impl.ndpmaster;
import javax.jcr.Repository;

import org.osgi.framework.BundleContext;

import com.dreamtech360.lfims.model.api.ndpmaster.NdpMaster;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.impl.ndpmaster.NdpMasterMaintenanceService;
import com.dreamtech360.lfims.service.base.LFIMSModelServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class NdpMasterServiceFactory extends LFIMSModelServiceFactory<NdpMaster>  {

	private BundleContext context=null;
	private Repository repository=null;
	private volatile LFIMSModelService<NdpMaster> service=null;
	private LFIMSTransactionManagementService txnService=null;
	

	public NdpMasterServiceFactory(BundleContext context,Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=context;
		this.txnService=txnService;
	}
	 
	public NdpMasterServiceFactory(Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=null;
		this.txnService=txnService;
	}
	
	
	@Override
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSModelService<NdpMaster> lookupModelService() {
		
		/*service=(LFIMSModelService<NdpMaster>)context.getService(context.getServiceReference(NdpMasterMaintenanceService.class.getName()));
		return service;*/
		return createModelService();
	}

	@Override
	public LFIMSModelService<NdpMaster> createModelService() {
		if(service==null){
			synchronized(this){
			service= new NdpMasterMaintenanceService(repository);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<NdpMaster> createTxnModelService() {
		if(service==null){
			synchronized(this){
			service= new NdpMasterMaintenanceService(repository,txnService);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<NdpMaster> lookupTxnModelService() {
		
		return createTxnModelService();
	}





}
