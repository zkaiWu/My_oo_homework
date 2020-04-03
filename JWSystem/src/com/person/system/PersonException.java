package com.person.system;

public class PersonException extends Exception{
	
	private ErrorCodeEnum errorCode;
	
	public PersonException(ErrorCodeEnum ec) {
		this.errorCode = ec;
	}
	
	public String getCodeDescription() {
		return this.errorCode.getDescription();
	}
}
