package mainpack;

public class CourseNotExistException extends Exception{
	public CourseNotExistException() {
		
	}
	public CourseNotExistException(String grep) {
		super(grep);
	}
}
