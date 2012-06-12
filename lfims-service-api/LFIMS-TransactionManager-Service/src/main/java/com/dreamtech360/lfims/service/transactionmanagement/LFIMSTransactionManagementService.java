package com.dreamtech360.lfims.service.transactionmanagement;

import javax.transaction.Synchronization;
import javax.transaction.xa.XAResource;



import com.dreamtech360.lfims.model.service.base.LFIMSGenericService;
import com.dreamtech360.lfims.model.service.base.LFIMSJCRSessionThreadLocal;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;

public class LFIMSTransactionManagementService implements LFIMSGenericService<LFIMSTransactionManager> {

	 
	/*public static TransactionManager getTransactionManager(){
		
		return  LFIMSTransactionManager.getTxManager();
		
	}*/
	
	public boolean inTransaction() throws LFIMSServiceException{
		try{
		if (LFIMSTransactionManager.getTxManager() ==null || LFIMSTransactionManager.getTxManager().getTransaction()==null)
			return false;
		else
			return true;
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}
	
	public String getTransactionanagerLookup(){
		return LFIMSTransactionManagerLookup.class.getName();
	}
	 
	
	public void beginTransaction() throws LFIMSServiceException   {
		try{
		LFIMSTransactionManager.getTxManager().begin();
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}
	
	public void commitTransaction()  throws LFIMSServiceException {
		try{
		LFIMSTransactionManager.getTxManager().commit();
		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
	}
	
	public void rollbackTransaction() throws LFIMSServiceException{
		try{
		LFIMSTransactionManager.getTxManager().rollback();
	}catch(Exception e){
		throw new LFIMSServiceException(e);
	}
		 
	
	}
	
	public void registerXAResource(XAResource resource) throws LFIMSServiceException{
		try{
		LFIMSTransactionManager.getTxManager().getTransaction().enlistResource(resource);
	}catch(Exception e){
		throw new LFIMSServiceException(e);
	}
	}


	public void deRegisterXAResource(XAResource resource)throws LFIMSServiceException{
		try{
		LFIMSTransactionManager.getTxManager().getTransaction().delistResource(resource,1);
	}catch(Exception e){
		throw new LFIMSServiceException(e);
	}
	}
	
	public void deRegisterXAResource(XAResource resource,int status)throws LFIMSServiceException{
		try{
		LFIMSTransactionManager.getTxManager().getTransaction().delistResource(resource,status);
	}catch(Exception e){
		throw new LFIMSServiceException(e);
	}
	}
	
	public void registerSynchronization(){
		
		try{
			LFIMSTransactionManager.getTxManager().getTransaction().registerSynchronization(	 
		 new Synchronization(){
			 public void afterCompletion(int arg0) {
				
					System.out.println("Transaction completion called");

					if(LFIMSJCRSessionThreadLocal.get() !=null){
						LFIMSJCRSessionThreadLocal.get().getSession().logout();
						LFIMSJCRSessionThreadLocal.unset();
					}

				}

				public void beforeCompletion() {
					// TODO Auto-generated method stub

				}

			 
		 });
		 
		 
		}catch(Exception e){
			
		}
		 
	}
	
	
	public int getStatus() throws LFIMSServiceException{
		try{
		return LFIMSTransactionManager.getTxManager().getTransaction()==null?-1: LFIMSTransactionManager.getTxManager().getStatus();
	}catch(Exception e){
		throw new LFIMSServiceException(e);
	}
	}
	
}
