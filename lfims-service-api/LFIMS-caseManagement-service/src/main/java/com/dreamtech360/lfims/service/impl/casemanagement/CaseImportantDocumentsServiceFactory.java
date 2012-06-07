package com.dreamtech360.lfims.service.impl.casemanagement;
import javax.jcr.Repository;

import org.osgi.framework.BundleContext;

import com.dreamtech360.lfims.model.api.casemanagement.ImportantDocuments;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.impl.casemanagement.CaseImportantDocumentsService;
import com.dreamtech360.lfims.service.base.LFIMSModelServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class CaseImportantDocumentsServiceFactory extends LFIMSModelServiceFactory<ImportantDocuments>  {

	private BundleContext context=null;
	private Repository repository=null;
	private volatile LFIMSModelService<ImportantDocuments> service=null;
	private LFIMSTransactionManagementService txnService=null;
	

	public CaseImportantDocumentsServiceFactory(BundleContext context,Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=context;
		this.txnService=txnService;
	}
	 
	public CaseImportantDocumentsServiceFactory(Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=null;
		this.txnService=txnService;
	}
	
	
	@Override
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSModelService<ImportantDocuments> lookupModelService() {
		
		/*service=(LFIMSModelService<ImportantDocuments>)context.getService(context.getServiceReference(ImportantDocumentsMaintenanceService.class.getName()));
		return service;*/
		return createModelService();
	}

	@Override
	public LFIMSModelService<ImportantDocuments> createModelService() {
		if(service==null){
			synchronized(this){
			service= new CaseImportantDocumentsService(repository);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<ImportantDocuments> createTxnModelService() {
		if(service==null){
			synchronized(this){
			service= new CaseImportantDocumentsService(repository,txnService);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<ImportantDocuments> lookupTxnModelService() {
		
		return createTxnModelService();
	}





}
