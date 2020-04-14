package com.course.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;




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
	
	public static boolean courseNameCheck(String name) {
		String rex = "^[0-9a-zA-Z]+$";               //用*匹配则可以支持为空
		return name.matches(rex);
	}
	
	public static boolean teachersNameCheck(ArrayList<String> nameList) {
		String rex = "^[a-zA-Z]*$";                 //用*匹配则可以支持为空
		for(String name:nameList) {
			if(!name.matches(rex)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean teachersNameStringCheck(String names){
		String rex = "^\\[([^,]+,)*([^,]+)\\]$";
		if(!names.matches(rex)&&!names.contentEquals("[]")){
			return false;
		}
		return true;
	}
	
	
	
	/*
	 * 工厂模式
	 * 将字符串信息打包成Course类型
	 * Course生成中所有的检测将会在这儿做
	 * 
	 * @param cid 课程id
	 * @param name 课程名
	 * @param teachersName 教师列表
	 * @param maxContent 课程容量字符串表示
	 * @return 一个Course类实例
	 */
	public static Course getNewCourse(String cid,String name,String teachersName,String maxContent) throws InputErrorException{
		
		
		Course c = new Course();
		c.setCid(cid);
		if(CourseFactory.courseNameCheck(name)==false) {
			throw new InputErrorException();
		}
		c.setCourseName(name);
		
		int len = teachersName.length();
		
		
		//对教师名字串的判断
		if(teachersName.charAt(0)!='['||teachersName.charAt(len-1)!=']') {
			throw new InputErrorException();             //若不已[开头]结尾，则不对
		}
		if(!CourseFactory.teachersNameStringCheck(teachersName)){
			throw new InputErrorException(); 
		}
		teachersName = teachersName.substring(1,len);
		len = teachersName.length();
		teachersName = teachersName.substring(0,len-1);
		
		//对教师名字的判断
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
		
		//对数字字符的判断
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
