package com.dreamtech360.lfims.model.service.exception;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LFIMSModelException extends LFIMSBaseException {

	public LFIMSModelException(Throwable e){
		super(e);
	}
	public LFIMSModelException (String message){
		super(message);
	}
	@Override
	public Class<?> getExceptionType() {
		// TODO Auto-generated method stub
		return this.getClass();
	}
	
	 
}
