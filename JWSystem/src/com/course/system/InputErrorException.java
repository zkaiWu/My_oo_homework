package com.course.system;

public class InputErrorException extends Exception{
	public InputErrorException() {
		
	}
	public InputErrorException(String grep) {
		super(grep);
	}
}
