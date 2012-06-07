package com.dreamtech360.lfims.service.impl.expensesmaster;
import javax.jcr.Repository;

import org.osgi.framework.BundleContext;

import com.dreamtech360.lfims.model.api.expensesmaster.ExpensesMaster;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.impl.expensesmaster.ExpensesMasterMaintenanceService;
import com.dreamtech360.lfims.service.base.LFIMSModelServiceFactory;
import com.dreamtech360.lfims.service.transactionmanagement.LFIMSTransactionManagementService;

public class ExpensesMasterServiceFactory extends LFIMSModelServiceFactory<ExpensesMaster>  {

	private BundleContext context=null;
	private Repository repository=null;
	private volatile LFIMSModelService<ExpensesMaster> service=null;
	private LFIMSTransactionManagementService txnService=null;
	

	public ExpensesMasterServiceFactory(BundleContext context,Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=context;
		this.txnService=txnService;
	}
	 
	public ExpensesMasterServiceFactory(Repository repository,LFIMSTransactionManagementService txnService){
		this.repository=repository;
		this.context=null;
		this.txnService=txnService;
	}
	
	
	@Override
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSModelService<ExpensesMaster> lookupModelService() {
		
		/*service=(LFIMSModelService<ExpensesMaster>)context.getService(context.getServiceReference(ExpensesMasterMaintenanceService.class.getName()));
		return service;*/
		return createModelService();
	}

	@Override
	public LFIMSModelService<ExpensesMaster> createModelService() {
		if(service==null){
			synchronized(this){
			service= new ExpensesMasterMaintenanceService(repository);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<ExpensesMaster> createTxnModelService() {
		if(service==null){
			synchronized(this){
			service= new ExpensesMasterMaintenanceService(repository,txnService);
			}
		}
	
		return service;
	}


	@Override
	public LFIMSModelService<ExpensesMaster> lookupTxnModelService() {
		
		return createTxnModelService();
	}





}
