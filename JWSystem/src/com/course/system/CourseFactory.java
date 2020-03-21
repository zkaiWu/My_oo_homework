package com.course.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;




/*
 * update : 2020/03/18
 * 工厂类
 * 1）用来产生course
 * 2）用来判断输入是否正确，是否能产生合法的course
 */

public class CourseFactory {
	
	public static boolean contentCheck(int maxContent) {
		if(maxContent<0) {
			return false;
		}
		return true;
	}
	
	public static boolean courseNameCheck(String name) {
		String rex = "^[0-9a-zA-Z]*$";               //用*匹配则可以支持为空
		return name.matches(rex);
	}
	
	public static boolean teachersNameCheck(ArrayList<String> nameList) {
		String rex = "^[a-zA-Z]*$";                 //用+匹配则可以支持为空
		for(String name:nameList) {
			if(!name.matches(rex)) {
				return false;
			}
		}
		return true;
	}
	
	public static Course getNewCourse(String cid,String name,String teachersName,String maxContent) throws InputErrorException{
		/*
		 * 真正的工厂模式
		 * 将字符串信息打包成Course类型
		 * 感觉会有BUG   ฅ(๑ ̀ㅅ ́๑)ฅ
		 */
		
		Course c = new Course();
		c.setCid(cid);
		if(CourseFactory.courseNameCheck(name)==false) {
			throw new InputErrorException();
		}
		c.setCourseName(name);
		
		int len = teachersName.length();
		
		
		
		//去除'['以及']';
		if(teachersName.charAt(0)!='['||teachersName.charAt(len-1)!=']') {
			throw new InputErrorException();             //若不已[,]开头，则不对
		}
		teachersName = teachersName.substring(1,len);
		len = teachersName.length();
		teachersName = teachersName.substring(0,len-1);
		
		
		String[] temp = teachersName.split(",");
		for(int i=0;i<temp.length;i++) {
			temp[i] = temp[i].replaceFirst("\\s+", "");
		}
		ArrayList<String> nameList = new ArrayList<>();
		nameList.addAll(Arrays.asList(temp));
		if(CourseFactory.teachersNameCheck(nameList)==false) {
			throw new InputErrorException();
		}
		c.setTeachersName(nameList);
		
		try {
			int mc = Integer.parseInt(maxContent);
			if(CourseFactory.contentCheck(mc)==false) throw new InputErrorException();
			c.setMaxContent(mc);
		}catch(NumberFormatException ex){
			throw new InputErrorException();
		}
		
		return c;
	}

}
