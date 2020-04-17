package com.person.system;

public class PersonException extends Exception{
	
	private PersonErrorCode errorCode;
	
	public PersonException(PersonErrorCode ec) {
		this.errorCode = ec;
	}
	
	public String getCodeDescription() {
		return this.errorCode.getDescription();
	}
}
