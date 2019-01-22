package com.chen.coupons.exceptions;

import com.chen.coupons.enums.ErrorType;

public class ApplicationException extends Exception {
	
	private ErrorType errorType;
	
	
	// -------------------- WE'RE THE FIRST TO THROW THE EXCEPTION -----------------------------
	// We will use this constructor in situations when WE are the ones throwing the exception
	// for example : a user tries to withdraw money, yet we discover he doesn't have enough money
	public ApplicationException (ErrorType errorType, String message) {
		super(message);
		this.errorType = errorType;
	}
	
	//-------------- A 3RD PARTY CLASS THREW AN EXCEPTION AND WE WRAP IT ------------------------------ 
		// We will use this constructor when we WRAP another exception (3rd party)
		// from a code which is not ours
		// for example : We call a function of an object from a class we downloaded from the internet, and that function fails and throws an XYZException
	public ApplicationException (Exception e, ErrorType errorType, String message) {
		super(message, e);
		this.errorType = errorType;
	}
	
	
	public ErrorType getErrorType() {
		return errorType;
	}
		
	public void setErrorType ( ErrorType errorType) {
		this.errorType = errorType;
	}
	

}
