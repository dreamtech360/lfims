package com.dreamtech360.lfims.model.service.base;

public class LFIMSJCRSessionThreadLocal {
	
	public static final ThreadLocal<LFIMSJCRSession> jcrSessioLocal=new ThreadLocal<LFIMSJCRSession>();
	
	public static void set(LFIMSJCRSession session){
		jcrSessioLocal.set(session);
	}
	
	public static LFIMSJCRSession get(){
		return  jcrSessioLocal.get();
	}
	
	public static void unset(){
		jcrSessioLocal.remove();
	}

}
