package com.dreamtech360.lfims.resources.api.exceptions;

import java.util.Iterator;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.dreamtech360.lfims.model.service.exception.LFIMSBaseException;

@Provider
public class LFIMSExceptionMapper implements ExceptionMapper<LFIMSBaseException> {

	@Override
	public Response toResponse(LFIMSBaseException exception) {
		// TODO Auto-generated method stub
		Iterator<StackTraceElement> stackTraceIterator=exception.getErrorTrace().iterator();
		Iterator<Throwable> causeTraceIterator=exception.getCauseTrace().iterator();
		
		System.out.println("Stack Trace");
		System.out.println("===========");
		while(stackTraceIterator.hasNext())
		{
			StackTraceElement element=stackTraceIterator.next();
			System.out.println(element.toString());
		}
		

		
		System.out.println("Cause Trace");
		System.out.println("===========");
		final StringBuilder sbuilder=new StringBuilder();
		while(causeTraceIterator.hasNext())
		{
			Throwable element=causeTraceIterator.next();
			sbuilder.append(element.toString());
			System.out.println(element.toString());
		}
		
		return Response.status(new StatusType(){

			@Override
			public Family getFamily() {
				// TODO Auto-generated method stub
				return Family.SERVER_ERROR;
			}

			@Override
			public String getReasonPhrase() {
				// TODO Auto-generated method stub
				return sbuilder.toString();
			}

			@Override
			public int getStatusCode() {
				// TODO Auto-generated method stub
				return Status.ACCEPTED.getStatusCode();
			}
			
		}).build();
		
 	
	}

}
