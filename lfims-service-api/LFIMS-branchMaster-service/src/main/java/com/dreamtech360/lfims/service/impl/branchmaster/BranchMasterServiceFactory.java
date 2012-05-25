package com.dreamtech360.lfims.service.impl.branchmaster;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.jcr.Repository;

import org.apache.jackrabbit.core.TransientRepository;
import org.apache.jackrabbit.rmi.client.ClientRepositoryFactory;
import org.osgi.framework.BundleContext;

import com.dreamtech360.lfims.model.api.branchmaster.BranchMaster;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.impl.branchmaster.BranchMasterMaintenanceService;
import com.dreamtech360.lfims.service.base.LFIMSServiceFactory;

public class BranchMasterServiceFactory extends LFIMSServiceFactory<BranchMaster>  {

	private BundleContext context=null;
	protected Repository repository=null;
	public static final String RMI="RMI";
	public static final String STANDALONE="STANDALONE";

	public BranchMasterServiceFactory(){}
	/*public BranchMasterServiceFactory(boolean launchRepository,String type){
		if(launchRepository){
			if(RMI.equals(type)){
				 ClientRepositoryFactory factory = new ClientRepositoryFactory();
				try {
					repository = factory.getRepository("rmi://localhost:1099/jackrabbit");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(STANDALONE.equals(type))
			{
				System.setProperty("disableCheckForReferencesInContentException","true");
				System.setProperty("org.apache.jackrabbit.repository.conf","D:\\jackrabbit\\repository.xml");
				System.setProperty("org.apache.jackrabbit.repository.home","D:\\jackrabbit");
				repository = new  TransientRepository(); 
			}

		}
	}*/

	public BranchMasterServiceFactory(BundleContext context){
		this.context=context;
	}


	@Override
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSModelService<BranchMaster> lookupService() {
		// TODO Auto-generated method stub
		service=(LFIMSModelService<BranchMaster>)context.getService(context.getServiceReference(BranchMasterMaintenanceService.class.getName()));
		return service;
	}

	@Override
	public LFIMSModelService<BranchMaster> createService() {
		// TODO Auto-generated method stub
		return new BranchMasterMaintenanceService(repository);
	}




}
