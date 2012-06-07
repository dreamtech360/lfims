package com.dreamtech360.lfims.service.impl.casemanagement;
import javax.jcr.Repository;

import org.osgi.framework.BundleContext;

import com.dreamtech360.lfims.model.api.casemanagement.SecurityDetails;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.impl.casemanagement.CaseSecurityDetailsService;
import com.dreamtech360.lfims.service.base.LFIMSModelServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class CaseSecurityDetailsServiceFactory extends LFIMSModelServiceFactory<SecurityDetails>  {

	private BundleContext context=null;
	private Repository repository=null;
	private volatile LFIMSModelService<SecurityDetails> service=null;
	private LFIMSTransactionManagementService txnService=null;
	

	public CaseSecurityDetailsServiceFactory(BundleContext context,Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=context;
		this.txnService=txnService;
	}
	 
	public CaseSecurityDetailsServiceFactory(Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=null;
		this.txnService=txnService;
	}
	
	
	@Override
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSModelService<SecurityDetails> lookupModelService() {
		
		/*service=(LFIMSModelService<SecurityDetails>)context.getService(context.getServiceReference(SecurityDetailsMaintenanceService.class.getName()));
		return service;*/
		return createModelService();
	}

	@Override
	public LFIMSModelService<SecurityDetails> createModelService() {
		if(service==null){
			synchronized(this){
			service= new CaseSecurityDetailsService(repository);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<SecurityDetails> createTxnModelService() {
		if(service==null){
			synchronized(this){
			service= new CaseSecurityDetailsService(repository,txnService);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<SecurityDetails> lookupTxnModelService() {
		
		return createTxnModelService();
	}





}
