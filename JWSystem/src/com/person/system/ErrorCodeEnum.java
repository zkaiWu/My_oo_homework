package com.person.system;

public enum ErrorCodeEnum {
	
	
	//ID类错误码
	ID_ILLEGAL_ERROR("ID illegal."),
	ID_EXIST_ERROR("ID exists"),
	
	
	//Name类错误码
	NAME_ILLEGAL_ERROR("Name illegal."),
	
	//教师号类错误码
	TID_ILLEGAL_ERROR("TID illegal."),
	TID_EXIST_ERROR("TID exists."),

	
	//学生号类错误码
	SID_ILLEGAL_ERROR("SID illegal."),
	SID_EXIST_ERROR("SID exists."),
	
	//密码类错误
	PASSWORD_ILLEGAL_ERROR("Password illegal.");
	
	
	private String description;
	
	private ErrorCodeEnum(String description) {
		this.description = description;
	}
	
	
	public String getDescription() {
		return this.description;
	}
}
