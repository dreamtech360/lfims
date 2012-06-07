package com.dreamtech360.lfims.service.impl.ouradvocatemaster;
import javax.jcr.Repository;

import org.osgi.framework.BundleContext;

import com.dreamtech360.lfims.model.api.ouradvocatemaster.OurAdvocateMaster;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.impl.ouradvocatemaster.OurAdvocateMasterMaintenanceService;
import com.dreamtech360.lfims.service.base.LFIMSModelServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class OurAdvocateMasterServiceFactory extends LFIMSModelServiceFactory<OurAdvocateMaster>  {

	private BundleContext context=null;
	private Repository repository=null;
	private volatile LFIMSModelService<OurAdvocateMaster> service=null;
	private LFIMSTransactionManagementService txnService=null;
	

	public OurAdvocateMasterServiceFactory(BundleContext context,Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=context;
		this.txnService=txnService;
	}
	 
	public OurAdvocateMasterServiceFactory(Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=null;
		this.txnService=txnService;
	}
	
	
	@Override
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSModelService<OurAdvocateMaster> lookupModelService() {
		
		/*service=(LFIMSModelService<OurAdvocateMaster>)context.getService(context.getServiceReference(OurAdvocateMasterMaintenanceService.class.getName()));
		return service;*/
		return createModelService();
	}

	@Override
	public LFIMSModelService<OurAdvocateMaster> createModelService() {
		if(service==null){
			synchronized(this){
			service= new OurAdvocateMasterMaintenanceService(repository);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<OurAdvocateMaster> createTxnModelService() {
		if(service==null){
			synchronized(this){
			service= new OurAdvocateMasterMaintenanceService(repository,txnService);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<OurAdvocateMaster> lookupTxnModelService() {
		
		return createTxnModelService();
	}





}
