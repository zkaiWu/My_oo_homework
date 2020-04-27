package coursesystem;

public class CourseException extends Exception{
	
	private CourseErrorCode code;
	
	public CourseException(CourseErrorCode code) {
		this.code = code;
	}
	
	public String getCodeDescription() {
		return this.code.getDescription();
	}
	
}	