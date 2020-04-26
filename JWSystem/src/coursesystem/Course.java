package coursesystem;

import java.util.*;
import java.util.logging.Logger;


import personsystem.*;


public class Course implements Comparable<Course>{
	
	private String cid;
	private String courseName;
	private ArrayList<String> teachersTID;
	private int maxContent;
	private int stuNum;                //记录选课的学生人数
	
	private HashMap<String, Student> stusOfCourse;                  //以学号来记录选课的学生
	
	private PersonList personList = PersonList.getInstance();      //用来将学号转变成名字输出
	
	
	
	public Course() {
		this.cid = "";
		this.courseName = "";
		this.teachersTID = new ArrayList<>();
		this.stusOfCourse = new HashMap<String, Student>();
	}
	
	public void setCid(String cid) {
		
		this.cid = cid;
	}
	public String getCid() {
		return this.cid;
	}
	
	public void setCourseName(String name) {
		this.courseName = name;
	}
	public String getCourseName() {
		return this.courseName;
	}
	
	public void setMaxContent(int mmax) {
		this.maxContent = mmax;
	}
	public int getMaxContent() {
		return this.maxContent;
	}
	
	public void setTeachersTid(ArrayList<String> TIDList) {
		this.teachersTID = TIDList;
	}
	public ArrayList<String> getTeachersTid() {
		return this.teachersTID;
	}
	
	public boolean isFull() {
		return this.stuNum>=this.maxContent;
	}
	
	/**
	 * 在学生选课的时候调用，增加课程的选课
	 * 为课程的行为,放在课程类里没问题，
	 * @param student
	 */
	public void chosenByStudent(Student student) {
		boolean flag=false;
		if(this.stusOfCourse.get(student.getSID())==null) {               //若选课表中有则人数不变
			flag = true;
		}
		this.stusOfCourse.put(student.getSID(), student);
		this.stuNum += flag ? 1 : 0;
	}
	
	
	/**
	 * 在学生退课的时候调用，减少课程的选课
	 * 为课程的行为,放在课程类里，问题不大
	 * @param student
	 */
	public void dropedByStudent(Student student) {
		boolean flag=false;
		if(this.stusOfCourse.get(student.getSID())!=null) {               //若选课表中没有则人数不变
			flag = true;
		}
		this.stusOfCourse.remove(student.getSID());
		this.stuNum -= flag ? 1 : 0 ;     
	}
	
	/**
	 * 获得列表类型的students
	 * @return
	 */
	public ArrayList<Student> getStudentsOfCourse(){
		ArrayList<Student> students = new ArrayList<Student>();
		for(String key : this.stusOfCourse.keySet()) {
			students.add(this.stusOfCourse.get(key));
		}
		Collections.sort(students);                   //students按照SID的列表排序
		return students;
	}
	
	
	
	
	@Override
	public String toString() {
		
		String temp = "";
		temp += "CID:" + this.cid + ",";
		temp += "Name:" + this.courseName + ",";
		temp += "Teachers:";
		
		String s = "[";
		if(this.teachersTID.size()!=0) {
			for(int i=0;i<this.teachersTID.size()-1;i++) {
				try {
					s += this.personList.getPersonBySTID(this.teachersTID.get(i)).getName() + ",";
				} catch(PersonException ex) {
					s += "noPerson"+",";                   //教师号不存在时的处理
				}
			}
			try {
				s += this.personList.getPersonBySTID(this.teachersTID.get(this.teachersTID.size()-1)).getName();
			} catch(PersonException ex) {
				s += "noPerson";                    //教师号不存在时的处理
			}
			
		}
		s +="]";
		
		Logger.getGlobal().info("s == "+s);
		
		temp += s+",";
		temp += "Capacity:" + this.stuNum+"/"+this.maxContent;
		return temp;
	}

	
	public int compareTo(Course b) {
		return this.getCid().compareTo(b.getCid());
	}
	
}
