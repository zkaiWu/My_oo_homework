package coursesystem;

public enum CourseErrorCode {
	
	
	//课程存在类错误
	COURSE_EXISTS_ERROR("Course exists."),
	COURSE_NOT_EXISTS_ERROR("Course does not exist."),
	
	//输入错误
	INPUT_ILLEGAL_ERROR("Input illegal."),
	
	//课程选择时错误
	COURSE_HAS_BEEN_SELECTED_ERROR("The course has been selected"),
	COURSE_HAS_NOT_BEEN_SELECTED_ERROR("The course has not been selected."),
	COURSE_IS_FULL("The course is full."),
	
	//课程修改错误
	COURSE_UPDATE_ERROR("Update fail."),
	
	
	//课程增加错误
	COURSE_ADD_ERROR("Course add illegal."),
	
	//记录不存在错误
	RECORD_NOT_EXISTS_ERROR("Record does not exist."),
	
	//数据非法
	DATA_ILLEGAL_ERROR("Data illegal.");
	
	private String description;
	
	private CourseErrorCode(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
