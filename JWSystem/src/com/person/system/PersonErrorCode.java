package com.person.system;

public enum PersonErrorCode {
	
	
	//ID类错误码
	ID_ILLEGAL_ERROR("ID illegal."),
	ID_EXIST_ERROR("ID exists."),
	ID_NOT_EXIST_ERROR("ID not exists."),
	
	
	//Name类错误码
	NAME_ILLEGAL_ERROR("Name illegal."),
	
	//教师号类错误码
	TID_ILLEGAL_ERROR("TID illegal."),
	TID_EXIST_ERROR("TID exists."),
	TID_NOT_EXIST_ERROR("Login Error. Your ID or password maybe wrong."),

	
	//学生号类错误码
	SID_ILLEGAL_ERROR("SID illegal."),
	SID_EXIST_ERROR("SID exists."),
	SID_NOT_EXIST_ERROR("Login Error. Your ID or password maybe wrong."),
	
	//密码类错误
	PASSWORD_ILLEGAL_ERROR("Password illegal."),
	PASSWORD_NOT_MATCH_ERROR("Login Error. Your ID or password maybe wrong."),
	PASSWORD_FIRSTPASSWORD_NOT_SAME_WITH_SECONDPASSWORD_ERROR("The password you entered must be the same as the former one."),
	
	//登录时错误
	LOGIN_ERROR("Login Error. Your ID or password maybe wrong.");
	
	
	private String description;
	
	private PersonErrorCode(String description) {
		this.description = description;
	}
	
	
	public String getDescription() {
		return this.description;
	}
}

