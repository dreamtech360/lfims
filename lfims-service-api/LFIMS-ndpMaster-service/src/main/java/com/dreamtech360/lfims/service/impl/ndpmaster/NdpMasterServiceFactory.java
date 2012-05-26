package com.dreamtech360.lfims.service.impl.ndpmaster;
import javax.jcr.Repository;

import org.apache.jackrabbit.core.TransientRepository;
import org.apache.jackrabbit.rmi.client.ClientRepositoryFactory;
import org.osgi.framework.BundleContext;
import com.dreamtech360.lfims.model.api.ndpmaster.NdpMaster;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.impl.ndpmaster.NdpMasterMaintenanceService;
import com.dreamtech360.lfims.service.base.LFIMSServiceFactory;

public class NdpMasterServiceFactory extends LFIMSServiceFactory<NdpMaster>  {

	private BundleContext context=null;
	protected Repository repository=null;
	public static final String RMI="RMI";
	public static final String STANDALONE="STANDALONE";

	public NdpMasterServiceFactory(){}
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

	public NdpMasterServiceFactory(BundleContext context){
		this.context=context;
	}


	@Override
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSModelService<NdpMaster> lookupService() {
		// TODO Auto-generated method stub
		service=(LFIMSModelService<NdpMaster>)context.getService(context.getServiceReference(NdpMasterMaintenanceService.class.getName()));
		return service;
	}

	@Override
	public LFIMSModelService<NdpMaster> createService() {
		// TODO Auto-generated method stub
		return new NdpMasterMaintenanceService(repository);
	}




}
