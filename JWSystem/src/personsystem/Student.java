package personsystem;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.*;

import coursesystem.*;

public class Student extends Person implements Comparable<Student>{
	
	private String SID;
	
	
	//已选课列表,注意String是大写
	private HashMap<String,Course> coursesOfStudent; 
	
	//课程列表单例，用于查找课程并选课
	private CourseList courseList = CourseList.getInstance();
	
	
	public Student() {
		super();
		this.SID = "";
	}
	
	public void setSID(String sid) {
		this.SID = sid;
	}
	public String getSID() {
		return this.SID;
	}
	
	public ArrayList<Course> getCourses(){
		ArrayList<Course> cList = new ArrayList<Course>();
		for(String key : this.coursesOfStudent.keySet()) {
			cList.add(this.coursesOfStudent.get(key));
		}
		
		//排序
		Collections.sort(cList);
		//
		return cList;
	}
	
	
	
	/**
	 * 学生的选课方法，模糊匹配
	 * @param cid 待选课程课程号
	 * @throws CourseException
	 */
	public void chooseCourse(String cid) throws CourseException {
		Course course = this.courseList.getCourseById(cid);            //抛出COURSE_NOT_EXISTS_ERROR异常
		if(this.coursesOfStudent.get(cid.toUpperCase())!=null) {         //已经选过
			throw new CourseException(CourseErrorCode.COURSE_HAS_BEEN_SELECTED_ERROR);
		}
		if(course.isFull()==true) {
			throw new CourseException(CourseErrorCode.COURSE_IS_FULL);
		}
		course.chosenByStudent(this);                    //课程将当前学生加入
		this.coursesOfStudent.put(course.getCid().toUpperCase(),course);
	}
	
	/**
	 * 学生的退课方法，模糊匹配
	 * @param cid
	 * @throws CourseException
	 */
	public void dropCourse(String cid) throws CourseException {
		Course course = this.courseList.getCourseById(cid);            //抛出COURSE_NOT_EXISTS_ERROR异常
		if(this.coursesOfStudent.get(cid.toUpperCase())==null) {         //没有选过
			throw new CourseException(CourseErrorCode.COURSE_HAS_NOT_BEEN_SELECTED_ERROR);
		}
		course.dropedByStudent(this);                    //课程将当前学生加入
		this.coursesOfStudent.remove(course.getCid().toUpperCase());
	}
	
	/**
	 * 对学生号的检查
	 * @param sid
	 * @return
	 */
	public static boolean checkSID(String sid) {
		return sid.matches("[0-9]{8}");
	}
	
	
	/**
	 * 用了一个比较笨的方法来创建Person实例
	 * @param name
	 * @param id
	 * @param SID
	 * @return
	 * @throws PersonException
	 */
	public static Student newInstance(String name,String id,String SID) throws PersonException{
		Person p = Person.newPerson(name,id);
		Student stu = new Student();
		stu.setId(p.getId());
		stu.setBirthday(p.getBirthday());
		stu.setName(p.getName());
		stu.setSex(p.getSex());
		
		if(Student.checkSID(SID)==false) {
			throw new PersonException(PersonErrorCode.SID_ILLEGAL_ERROR);
		}
		
		stu.setSID(SID);
		
		return stu;
	}
	
	
	@Override
	public String toString() {
		StringBuilder ss = new StringBuilder("");
		ss.append("Name:"+this.getName()+"\n");
		ss.append("IDNum:"+this.getId()+"\n");
		ss.append("SID:"+this.getSID()+"\n");
		String sex = this.getSex()==0 ? "F":"M";
		ss.append("Sex:"+sex+"\n");
		ss.append("Birthday:"+new SimpleDateFormat("yyyy/MM/dd").format(this.getBirthday()));
		return ss.toString();
		
	}
	
	@Override
	public int compareTo(Student stu) {
		return this.getSID().compareTo(stu.getSID());
	}
	
	
	public static void main(String[] args) {
		try {
			Person stu = Student.newInstance("wzk","460103199811291210","12345678");
			System.out.println(stu);
		}catch (PersonException e) {
			System.out.println(e.getCodeDescription());
		}
	}
}



