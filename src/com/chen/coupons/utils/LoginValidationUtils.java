package com.chen.coupons.utils;

import java.util.regex.Pattern;

public class LoginValidationUtils {
	
	//email must contain at least one char before @ and at least one char after.then dot and 
	final static Pattern EMAIL_PATTERN = Pattern.compile("^([\\w\\-\\.]+)@([\\w\\-\\.]+)\\.([a-zA-Z]{2,5})$");
	
	//password must be between 8-10 characters,can include only letters and numbers. and must contain at least 1 uppercase, 1 lowercase and 1 digit.
	final static Pattern PASSWORD_PATTERN = Pattern.compile("^(?=[A-Za-z0-9]*[a-z])(?=[A-Za-z0-9]*[A-Z])(?=[A-Za-z0-9]*\\d)[A-Za-z0-9]{8,10}$");
	
	public static boolean emailValidation (String email){		
		    return (EMAIL_PATTERN.matcher(email).matches());
	}
	
	
	
	public static boolean passwordValidation (String password){		
	    return (PASSWORD_PATTERN.matcher(password).matches());
	}

	

	
	

}
