package com.dreamtech360.lfims.service.impl.casemanagement;
import javax.jcr.Repository;

import org.apache.jackrabbit.core.TransientRepository;
import org.apache.jackrabbit.rmi.client.ClientRepositoryFactory;
import org.osgi.framework.BundleContext;

import com.dreamtech360.lfims.model.api.casemanagement.DefendentDetails;
import com.dreamtech360.lfims.model.service.base.LFIMSModelService;
import com.dreamtech360.lfims.model.service.impl.casemanagement.CaseDefendentDetailsService;
import com.dreamtech360.lfims.service.base.LFIMSServiceFactory;

public class CaseDefendentDetailsServiceFactory extends LFIMSServiceFactory<DefendentDetails>  {

	private BundleContext context=null;
	protected Repository repository=null;
	public static final String RMI="RMI";
	public static final String STANDALONE="STANDALONE";

	public CaseDefendentDetailsServiceFactory(){}
	/*public DefendentDetailsServiceFactory(boolean launchRepository,String type){
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

	public CaseDefendentDetailsServiceFactory(BundleContext context){
		this.context=context;
	}


	@Override
	//More logic could be added here to instanciate te proper model implementation 
	//for example if a model is based on database or a model in based on jackrabbit
	public LFIMSModelService<DefendentDetails> lookupService() {
		// TODO Auto-generated method stub
		service=(LFIMSModelService<DefendentDetails>)context.getService(context.getServiceReference(CaseDefendentDetailsService.class.getName()));
		return service;
	}

	@Override
	public LFIMSModelService<DefendentDetails> createService() {
		// TODO Auto-generated method stub
		return new CaseDefendentDetailsService(repository);
	}




}
