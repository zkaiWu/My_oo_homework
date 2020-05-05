package coursesystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.*;
import personsystem.*;

import java.util.*;




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
		
		//判断教师名合法,允许为空
		for(String tid : tidsList) {
			if(Teacher.checkTID(tid)==false&&!tid.contentEquals("")) {
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
	 * 检查课程字符串是否合法
	 * @param timeString
	 * @return
	 */
	public static boolean timeStringCheck(String timeString) {
		String rex = "^\\[[0-9]+\\-[0-9]+\\][0-9]+\\,[0-9]+";
		if(!timeString.matches(rex)) {
			return false;
		}
		return true;
	}
	/**
	 * 检查课程的各个时间是否合法
	 * @param startWeek
	 * @param endWeek
	 * @param day
	 * @param classNum
	 * @return
	 */
	public static boolean timeCheck(int startWeek,int endWeek,int day,int classNum) {
		if(endWeek<startWeek) return false;
		if(startWeek<1||endWeek>18) return false;
		if(day<1||day>7) return false;
		if(classNum<1||classNum>10) return false;
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
	  * 设置课程教师号的方法
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
			throw new CourseException(CourseErrorCode.COURSE_UPDATE_ERROR); 
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
	
	
	/**
	 * 设置课程最大用量的方法
	 * @param course
	 * @param maxContent
	 * @throws CourseException
	 */
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
	 * 为课程装配时间信息
	 * @param course
	 * @param timeString
	 * @throws CourseException
	 */
	public static void setTimeForCourse(Course course,String timeString) throws CourseException {
		
		//判断字符串是否合法
		if(CourseFactory.timeStringCheck(timeString)==false) {
			throw new CourseException(CourseErrorCode.DATA_ILLEGAL_ERROR);
		}
		timeString = timeString.replace("[","");
		timeString = timeString.replace("]",",");
		timeString = timeString.replace("-",",");
		String[] time = timeString.split(",");
		
		//解析成数字
		int startWeek = 0, endWeek = 0, day = 0, classNum = 0;
		try {
			startWeek = Integer.parseInt(time[0]);
			endWeek = Integer.parseInt(time[1]);
			day = Integer.parseInt(time[2]);
			classNum = Integer.parseInt(time[3]);
		} catch (NumberFormatException e) {
			throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);
		}
		
		if(CourseFactory.timeCheck(startWeek, endWeek, day, classNum)==false) {
			throw new CourseException(CourseErrorCode.DATA_ILLEGAL_ERROR);
		}
		//装配
		course.setStartWeek(startWeek);
		course.setEndWeek(endWeek);
		course.setDay(day);
		course.setClassNum(classNum);
		
		return;
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
	public static Course getNewCourse(String cid,String name,String teachersTid,String maxContent,String timeString) throws CourseException{
		
		//装配课程实例
		Course c = new Course();
		try {
			CourseFactory.setMaxContentForCourse(c,maxContent);
			CourseFactory.setCIDForCourse(c,cid);
			CourseFactory.setNameForCourse(c,name);
			CourseFactory.setTidsForCourse(c, teachersTid);
			CourseFactory.setTimeForCourse(c, timeString);
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
	
	/**
	 * 单元测试
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			String timeString = in.nextLine();
			timeString = timeString.replace("[","");
			timeString = timeString.replace("]",",");
			timeString = timeString.replace("-",",");
			String[] time = timeString.split(",");
			
			//解析成数字
			int startWeek = 0, endWeek = 0, day = 0, classNum = 0;
			try {
				startWeek = Integer.parseInt(time[0]);
				endWeek = Integer.parseInt(time[1]);
				day = Integer.parseInt(time[2]);
				classNum = Integer.parseInt(time[3]);
			} catch (NumberFormatException e) {
				System.out.println(e);
				continue;
			}
			
			System.out.println(CourseFactory.timeCheck(startWeek, endWeek, day, classNum));
		}
	}
}
