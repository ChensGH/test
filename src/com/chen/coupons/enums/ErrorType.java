package com.chen.coupons.enums;

public enum ErrorType {

	
	GENERAL_ERROR(602),
	INVALID_PARAMETER(603),
	INVALID_LOGIN(604),
	SYSTEM_ERROR(605),
	SERVER_RESTSRT(606),
	UNAUTHORIZED_ACTION(401),
	SESSION_TIMEOUT(607),
	Empty_List(3),
	ID_Does_Not_Exist(4),
	ID_IS_ALREADY_EXISTS(5),
	NULL_OBJECT(6),
	Title_ALREADY_EXISTS(7);
	
	
	
	
	
	private int errorNumber;
	
	private ErrorType(int errorNumber) {
		this.errorNumber = errorNumber;
	}
	
	public int getErrorNumber() {
		return errorNumber;
	}
	
	public static ErrorType fromString(final String s) {
		return ErrorType.valueOf(s);
	}
	
	public String getErrorMessage() {
		return this.name();
	}
	
}
