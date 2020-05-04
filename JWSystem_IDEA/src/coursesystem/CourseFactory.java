package coursesystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.*;

import personsystem.*;




/*
 * 工厂类
 * 用来产生course
 * 用来判断输入是否正确，是否能产生合法的course
 */

public class CourseFactory {
	
	public static boolean contentCheck(int maxContent) {
		if(maxContent<0) {
			return false;
		}
		return true;
	}
	
	
	public static boolean courseIdCheck(String cid) {
		String rex = "^[bB][hH]\\d{8}$";
		return cid.matches(rex);
	}
	/**
	 * 检查课程名是否合法
	 * @param name
	 * @return
	 */
	public static boolean courseNameCheck(String name) {
		String rex = "^[0-9a-zA-Z]+$";               //用*匹配则可以支持为空
		return name.matches(rex);
	}
	
	/**
	 * 检查teachers
	 * @param nameList
	 * @return
	 */
	public static boolean teachersNameCheck(ArrayList<String> nameList) {
		String rex = "^[a-zA-Z]*$";                 //用*匹配则可以支持为空
		for(String name:nameList) {
			if(!name.matches(rex)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 检查name的字符串是否合法
	 * @param names
	 * @return
	 */
	public static boolean teachersNameStringCheck(String names){
		String rex = "^\\[([^,]+,)*([^,]+)\\]$";
		if(!names.matches(rex)&&!names.contentEquals("[]")){
			return false;
		}
		return true;
	}
	
	/**
	 * 检查各个tid是否合法
	 * @param tidsList
	 * @return
	 */
	public static boolean teachersTidCheck(ArrayList<String> tidsList) {
		
		//判断教师名合法
		for(String tid : tidsList) {
			if(Teacher.checkTID(tid)==false) {
				return false;
			}
		}
		
		//判断是否重复
		for(int i=0;i<tidsList.size();i++) {
			for(int j=0;j<tidsList.size();j++) {
				if(j!=i&&tidsList.get(i).contentEquals(tidsList.get(j))) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 
	 * 检查Tid的字符串是否合法
	 * @param Tids
	 * @return
	 */
	public static boolean teachersTidStringCheck(String Tids) {
		String rex = "^\\[([^,]+,)*([^,]+)\\]$";
		if(!Tids.matches(rex)&&!Tids.contentEquals("[]")){
			return false;
		}
		return true;
	}
	
	/**
	 * 设置课程cid的方法
	 * @param course
	 * @param cid
	 * @throws CourseException
	 */
	public static void setCIDForCourse(Course course,String cid) throws CourseException{
		if(CourseFactory.courseIdCheck(cid)==false) {
			throw new CourseException(CourseErrorCode.DATA_ILLEGAL_ERROR);
		}
		course.setCid(cid);
	}
	
	public static void setNameForCourse(Course course,String name) throws CourseException{
		if(courseNameCheck(name)==false) {
			throw new CourseException(CourseErrorCode.DATA_ILLEGAL_ERROR);
		}
		course.setCourseName(name);
	}
	
	
	 /**
	  * 设置课程号的方法
	  * @param course
	  * @param tidsList
	  * @throws CourseException
	  */
	public static void setTidsForCourse(Course course,ArrayList<String> tidsList) throws CourseException{
		if(CourseFactory.teachersTidCheck(tidsList)==false) {
			throw new CourseException(CourseErrorCode.DATA_ILLEGAL_ERROR);
		}
		Collections.sort(tidsList);
		course.setTeachersTid(tidsList);
	}
	public static void setTidsForCourse(Course course,String teachersTid) throws CourseException{
		
		
		//对教师名字串的判断
		int len = teachersTid.length();
		if(teachersTid.charAt(0)!='['||teachersTid.charAt(len-1)!=']') {
			throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);             //若不已[开头]结尾，则不对
		}
		if(!CourseFactory.teachersTidStringCheck(teachersTid)){
			throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR); 
		}
		teachersTid = teachersTid.substring(1,len);
		len = teachersTid.length();
		teachersTid = teachersTid.substring(0,len-1);
				
		//对教师名字的判断
		String[] temp = teachersTid.split(",");
		for(int i=0;i<temp.length;i++) {
			temp[i] = temp[i].replaceFirst("\\s+", "");
		}
		ArrayList<String> tidsList = new ArrayList<>();
		tidsList.addAll(Arrays.asList(temp));
		CourseFactory.setTidsForCourse(course,tidsList);
	}
	
	public static void setMaxContentForCourse(Course course,String maxContent) throws CourseException {
		int mc = 0;
		try {
			mc = Integer.parseInt(maxContent);
			if(CourseFactory.contentCheck(mc)==false) throw new CourseException(CourseErrorCode.DATA_ILLEGAL_ERROR);
		}catch(NumberFormatException ex){
			throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);
		}
		course.setMaxContent(mc);
	}
	
	
	
	/**
	 * 工厂模式
	 * 用工厂方法装配课程实例
	 * @param cid
	 * @param name
	 * @param teachersTid
	 * @param maxContent
	 * @return
	 * @throws CourseException
	 */
	public static Course getNewCourse(String cid,String name,String teachersTid,String maxContent) throws CourseException{
		
		//装配课程实例
		Course c = new Course();
		try {
			CourseFactory.setMaxContentForCourse(c,maxContent);
			CourseFactory.setCIDForCourse(c,cid);
			CourseFactory.setNameForCourse(c,name);
			CourseFactory.setTidsForCourse(c, teachersTid);
		} catch (CourseException ex) {
			if(ex.getCode()==CourseErrorCode.INPUT_ILLEGAL_ERROR) {
				throw ex;
			}
			else{
				throw new CourseException(CourseErrorCode.COURSE_ADD_ERROR);
			}
		}
		return c;
	}

}
