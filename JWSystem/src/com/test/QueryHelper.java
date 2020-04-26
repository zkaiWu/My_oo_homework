package com.test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.*;

import coursesystem.*;
import personsystem.*;
import java.math.*;

/**
 * 处理所有的查询，简化代码
 * 合法的所有输出在方法里输出，不再返回数据
 * @author wzk1998
 *
 */
public class QueryHelper {
	
	private PersonList pList ;
	private CourseList cList ;
	
	
	public QueryHelper(PersonList pList,CourseList cList) {
		this.pList = pList ;
		this.cList = cList ;
	}
	
	/**
	 * 查询对应课程的选课学生，分页输出
	 * 
	 * @param cid 课程号
	 * @param pageString	字符串形式的page页码
	 * @param pageContentString 字符串形式的每页最大容量
	 */
	public void queryForClist(String cid,String pageString,String pageContentString) throws CourseException{
		
		//获取选课列表
		Course course = cList.getCourseById(cid);                 //模糊匹配,抛出异常
		ArrayList<Student> stuList= course.getStudentsOfCourse();
		Scanner in = new Scanner(System.in);
		
		
		//获得参数
		int len = stuList.size();
		int page = 0 , pageContext = 0;
		try {
			page = Integer.parseInt(pageString);
			pageContext = Integer.parseInt(pageContentString);
		} catch(NumberFormatException ex) {
			throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);              //	输入错误	
		}
		
		
		//分页输出
		while(true) {
			int start = (page-1)*pageContext;
			int end = Math.min(pageContext*page,len);
			
			//页面为空
			if(start<0) {
				throw new CourseException(CourseErrorCode.RECORD_NOT_EXISTS_ERROR);
			}
			if(end<=start) {
				throw new CourseException(CourseErrorCode.RECORD_NOT_EXISTS_ERROR);
			}
			//输出页面
			System.out.println("page:"+page);
			for(int i=start; i<end; i++) {
				System.out.println((i-start+1)+"."+stuList.get(i).getSID()+","+stuList.get(i).getName());
			}
			//输出选择界面
			System.out.println("n-next page, l-last page, q-quit");
			
			String opt = in.nextLine();
			if(opt.contentEquals("n")) {
				page++;
			} else if(opt.contentEquals("l")) {
				page--;
			} else if(opt.contentEquals("q")) {
				return;
			} else {
				throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);
			}
		}
	}
	
	/**
	 * 通过在课程里查找的方式，查看教师的课程
	 * 
	 * @param tid
	 * @param pageString
	 * @param pageContentString
	 */
	public void queryForCoursesOfTeacher(String tid,String pageString,String pageContentString) throws CourseException {
		ArrayList<Course> allCourse = cList.getAllCourses();
		ArrayList<Course> coursesOfTeacher = new ArrayList<Course>();
		Scanner in = new Scanner(System.in);
		
		//动态查找并添加课程，获取课表
		for(int i=0;i<allCourse.size();i++) {
			Course course = allCourse.get(i);
			ArrayList<String> teachersTid = course.getTeachersTid();
			for(int j=0; j<teachersTid.size(); j++) {
				if(teachersTid.get(j).contentEquals(tid)) {
					coursesOfTeacher.add(course);
					break;
				}
			}
		}
		//按照课程号排序
		Collections.sort(coursesOfTeacher);
		
		//获得参数
		int len = coursesOfTeacher.size();
		int page = 0 , pageContext = 0;
		try {
			page = Integer.parseInt(pageString);
			pageContext = Integer.parseInt(pageContentString);
		} catch(NumberFormatException ex) {
			throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);              //	输入错误	
		}
		
		
		while(true) {
			int start = (page-1)*pageContext;
			int end = Math.min(pageContext*page,len);
			
			//页面为空

			if(start<0) {
				throw new CourseException(CourseErrorCode.RECORD_NOT_EXISTS_ERROR);
			}
			if(end<=start) {
				throw new CourseException(CourseErrorCode.RECORD_NOT_EXISTS_ERROR);
			}
			
			//输出页面
			System.out.println("page:"+page);
			for(int i=start; i<end; i++) {
				System.out.println((i-start+1)+"."+coursesOfTeacher.get(i));
			}
			
			//输出选择界面
			System.out.println("n-next page, l-last page, q-quit");
			
			//响应输入
			String opt = in.nextLine();
			if(opt.contentEquals("n")) {
				page++;
			} else if(opt.contentEquals("l")) {
				page--;
			} else if(opt.contentEquals("q")) {
				return;
			} else {
				throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);
			}
		}
		
	}
	
	/**
	 * 输出选课学生的学号,
	 * 
	 * @param sid
	 * @param pageString
	 * @param pageContentString
	 */
	public void queryForCourseOfStudent(String sid,String pageString,String pageContentString) throws CourseException,PersonException{
		
		//获取学生选课列表
		Student student = (Student) pList.getPersonBySTID(sid);                    //抛出PersonException
		ArrayList<Course> coursesOfStudent = student.getCourses();
		Collections.sort(coursesOfStudent);
		Scanner in = new Scanner(System.in);
		
		int len = coursesOfStudent.size();
		int page = 0 , pageContent = 0;
		try {
			page = Integer.parseInt(pageString);
			pageContent = Integer.parseInt(pageContentString);
		} catch(NumberFormatException ex) {
			in.close();
			throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);              //	输入错误	
		}
		
		
		while(true) {
			int start = (page-1)*pageContent;
			int end = Math.min(pageContent*page,len);
			
			//页面为空
			if(start<0) {
				throw new CourseException(CourseErrorCode.RECORD_NOT_EXISTS_ERROR);
			}
			if(start>=end) {
				throw new CourseException(CourseErrorCode.RECORD_NOT_EXISTS_ERROR);
			}
			
			//输出页面
			System.out.println("page:"+page);
			for(int i=start; i<end; i++) {
				System.out.println((i-start+1)+"."+coursesOfStudent.get(i));
			}
			
			//输出选择界面
			System.out.println("n-next page, l-last page, q-quit");
			
			//响应输入
			String opt = in.nextLine();
			if(opt.contentEquals("n")) {
				page++;
			} else if(opt.contentEquals("l")) {
				page--;
			} else if(opt.contentEquals("q")) {
				return;
			} else {
				throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);
			}
		}
		
		
	}
	
	
	/**
	 * 根据课程号查询
	 * @param cid
	 */
	public void queryForCourseById(String cid) throws CourseException {
		Course course = cList.getCourseById(cid);   //throw COURSE_NOT_EXISTS异常,模糊匹配
		System.out.println(course);
	}
	
	/**
	 * 根据课程名查询，并且分页输出
	 * @param key
	 * @param pageString
	 * @param pageContentString
	 * @throws CourseException
	 */
	public void queryForCoursesByKey(String key,String pageString,String pageContentString) throws CourseException{
		
		ArrayList<Course> courseList = cList.getCoursesByKeyword(key);
		Scanner in = new Scanner(System.in);
		
		int len = courseList.size();
		int page = 0, pageContent = 0;
		try {
			page = Integer.parseInt(pageString);
			pageContent = Integer.parseInt(pageContentString);
		} catch (NumberFormatException e) {
			throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);
		}
		
		while(true) {
			int start = (page-1)*pageContent;
			int end = Math.min(pageContent*page,len);
			
			//页面为空
			if(start<0) {
				throw new CourseException(CourseErrorCode.RECORD_NOT_EXISTS_ERROR);
			}
			if(end<=start) {
				throw new CourseException(CourseErrorCode.RECORD_NOT_EXISTS_ERROR);
			}
			
			//输出页面
			System.out.println("page:"+page);
			for(int i=start; i<end; i++) {
				System.out.println((i-start+1)+"."+courseList.get(i));
			}
			
			System.out.println("n-next page, l-last page, q-quit");
			
			String opt = in.nextLine();
			
			if(opt.contentEquals("n")) {
				page++;
			} else if(opt.contentEquals("l")) {
				page--;
			} else if(opt.contentEquals("q")) {
				return;
			} else {
				throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);
			}
		}
	}
	
	public void queryForAllCourses(String pageString,String pageContentString) throws CourseException{
		
		ArrayList<Course> courseList = cList.getAllCourses();
		Scanner in = new Scanner(System.in);
		
		int len = courseList.size();
		int page = 0, pageContent = 0;
		try {
			page = Integer.parseInt(pageString);
			pageContent = Integer.parseInt(pageContentString);
		} catch (NumberFormatException e) {
			throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);
		}
		
		while(true) {
			int start = (page-1)*pageContent;
			int end = Math.min(pageContent*page,len);
			
			//页面为空
			if(start<0) {
				throw new CourseException(CourseErrorCode.RECORD_NOT_EXISTS_ERROR);
			}
			if(end<=start) {
				throw new CourseException(CourseErrorCode.RECORD_NOT_EXISTS_ERROR);
			}
			
			//输出页面
			System.out.println("page:"+page);
			for(int i=start; i<end; i++) {
				System.out.println((i-start+1)+"."+courseList.get(i));
			}
			
			System.out.println("n-next page, l-last page, q-quit");
			
			String opt = in.nextLine();
			
			if(opt.contentEquals("n")) {
				page++;
			} else if(opt.contentEquals("l")) {
				page--;
			} else if(opt.contentEquals("q")) {
				return;
			} else {
				throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);
			}
		}		
	}
	
}
