package com.dreamtech360.lfims.service.impl.casemgmtmaintenance;
import javax.jcr.Repository;

import org.apache.jackrabbit.core.TransientRepository;
import org.apache.jackrabbit.rmi.client.ClientRepositoryFactory;
import org.osgi.framework.BundleContext;
import com.dreamtech360.lfims.model.api.casemgmtmaintenance.CaseMgmtMaintenance;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.impl.casemgmtmaintenance.CaseMgmtMaintenanceService;
import com.dreamtech360.lfims.service.base.LFIMSServiceFactory;

public class CaseMgmtMaintenanceServiceFactory extends LFIMSServiceFactory<CaseMgmtMaintenance>  {

	private BundleContext context=null;
	protected Repository repository=null;
	public static final String RMI="RMI";
	public static final String STANDALONE="STANDALONE";

	public CaseMgmtMaintenanceServiceFactory(){}
	/*public AdvocateMasterServiceFactory(boolean launchRepository,String type){
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

	public CaseMgmtMaintenanceServiceFactory(BundleContext context){
		this.context=context;
	}


	@Override
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSModelService<CaseMgmtMaintenance> lookupService() {
		// TODO Auto-generated method stub
		service=(LFIMSModelService<CaseMgmtMaintenance>)context.getService(context.getServiceReference(CaseMgmtMaintenanceService.class.getName()));
		return service;
	}

	@Override
	public LFIMSModelService<CaseMgmtMaintenance> createService() {
		// TODO Auto-generated method stub
		return new CaseMgmtMaintenanceService(repository);
	}




}
