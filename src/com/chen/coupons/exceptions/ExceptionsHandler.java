package com.chen.coupons.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.chen.coupons.beans.ErrorBeans;

@Provider
public class ExceptionsHandler implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable exception) {
		exception.getStackTrace();
		//if the exception we caught is an exception we thrown
		if (exception instanceof ApplicationException) {
			ApplicationException appException = (ApplicationException) exception;
			String errorMessage = appException.getErrorType().getErrorMessage();
			String internalMessage = appException.getMessage();
			int errorCode = appException.getErrorType().getErrorNumber();
			ErrorBeans errorBeans = new ErrorBeans(errorCode, internalMessage, errorMessage);
			return Response.status(errorCode).entity(errorBeans).build();
		}
		
		//here we handle an exception that we didn't caught and warped 
		String internalMessage = exception.getMessage();
		ErrorBeans errorBeans = new ErrorBeans(601, internalMessage, "General Exception");
		return Response.status(601).entity(errorBeans).build();
		
	}
	
	

}
