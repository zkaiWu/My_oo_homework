package mainpack;

public class CourseExistException extends Exception{
	public CourseExistException() {
		
	}
	public CourseExistException(String grep) {
		super(grep);
	}
}