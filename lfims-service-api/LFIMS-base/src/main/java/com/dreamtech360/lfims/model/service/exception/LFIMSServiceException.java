package com.dreamtech360.lfims.model.service.exception;

public class LFIMSServiceException extends Exception {

	public LFIMSServiceException(Throwable e){
		super(e);
	}
	public LFIMSServiceException (String message){
		super(message);
	}
}
