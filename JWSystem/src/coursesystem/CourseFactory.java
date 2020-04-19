package coursesystem;

import java.util.ArrayList;
import java.util.Arrays;
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
		for(String tid : tidsList) {
			if(!Teacher.checkTID(tid)==false) {
				return false;
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
	public static Course getNewCourse(String cid,String name,String teachersTid,String maxContent) throws CourseException{
		
		
		Course c = new Course();
		c.setCid(cid);
		if(CourseFactory.courseNameCheck(name)==false) {
			throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);
		}
		c.setCourseName(name);
		
		int len = teachersTid.length();
		
		
		//对教师名字串的判断
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
		ArrayList<String> nameList = new ArrayList<>();
		nameList.addAll(Arrays.asList(temp));
		if(CourseFactory.teachersTidCheck(nameList)==false) {
			throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);
		}
		c.setTeachersName(nameList);
		
		//对数字字符的判断
		try {
			int mc = Integer.parseInt(maxContent);
			if(CourseFactory.contentCheck(mc)==false) throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);
			c.setMaxContent(mc);
		}catch(NumberFormatException ex){
			throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);
		}
		
		return c;
	}

}
