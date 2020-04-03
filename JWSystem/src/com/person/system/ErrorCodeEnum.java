package com.person.system;

public enum ErrorCodeEnum {
	
	
	//ID类错误码
	ID_Illegal_Error("ID illegal."),
	ID_Exist_Error("ID exists"),
	
	
	//Name类错误码
	Name_Illegal_Error("Name illegal."),
	
	//教师号类错误码
	TID_Illegal_Error("TID illegal."),
	TID_Exist_Error("TID exists."),
	
	
	//学生号类错误码
	SID_Illegal_Error("SID illegal."),
	SID_Exist_Error("SID exists.");
	
	private String description;
	
	private ErrorCodeEnum(String description) {
		this.description = description;
	}
	
	
	public String getDescription() {
		return this.description;
	}
}
