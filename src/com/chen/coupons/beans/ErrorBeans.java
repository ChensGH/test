package com.chen.coupons.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorBeans {

	private int errorCode;
	private String internalMessage;
	private String errorMessage;
	
	
	public int getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}


	public String getInternalMessage() {
		return internalMessage;
	}


	public void setInternalMessage(String internalMessage) {
		this.internalMessage = internalMessage;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	

	public ErrorBeans() {
		super();
	}
	
	
	public ErrorBeans(int errorCode, String internalMessage, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.internalMessage = internalMessage;
		this.errorMessage = errorMessage;
	}
	
	
		
		

	

}
