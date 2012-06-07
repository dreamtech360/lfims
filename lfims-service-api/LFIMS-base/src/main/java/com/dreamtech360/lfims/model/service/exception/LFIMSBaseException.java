package com.dreamtech360.lfims.model.service.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class LFIMSBaseException extends Exception {

	public LFIMSBaseException(Throwable e){
		super(e);
	}
	public LFIMSBaseException (String message){
		super(message);
	}
	
	  public List<StackTraceElement> getErrorTrace() {
		   List<StackTraceElement> errorList=new ArrayList(Arrays.asList(super.getStackTrace()));
		   Iterator<StackTraceElement> iterator=errorList.iterator();
		   
		   while(iterator.hasNext())
		   {
			   StackTraceElement key=iterator.next();
			   if(!key.toString().startsWith("com.dreamtech360"))
				   iterator.remove();
		   }
		   return errorList;
	    }
  public List<Throwable> getCauseTrace(){
		 
		 List<Throwable> causeTrace=new ArrayList<Throwable>();
		 Throwable thowable=this.getCause();
		 while(thowable!=null){
			 causeTrace.add(thowable);
			 thowable=thowable.getCause();
		 }
		 return causeTrace;
	 }
  
  public String getLocalizedMessage() {
      return getMessage();
  }
  public abstract Class<?> getExceptionType();
}
