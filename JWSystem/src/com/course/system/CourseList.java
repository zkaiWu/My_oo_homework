package com.course.system;


import java.util.*;
import java.util.logging.Logger;


public class CourseList {
	private HashMap<String,Course> courseMap;
	
	public CourseList() {
		courseMap = new HashMap<>();
	}
	
	public Course getCourseById(String cid) throws CourseNotExistException {
		cid = cid.toUpperCase();
		Course cTemp = courseMap.get(cid);
		if(cTemp == null) {
			throw new CourseNotExistException();
		}
		return cTemp;
	}
	
	public ArrayList<Course> getCoursesByKeyword(String keyword) throws CourseNotExistException{
		
		/*
		 * 2020/03/18 ：匹配改为不分大小写
		 */
		
		
		ArrayList<Course>  cList = new ArrayList<>();
		for(String key : this.courseMap.keySet()) {
			if(courseMap.get(key).getCourseName().toLowerCase().contains(keyword.toLowerCase())) {                //修改成不分大小写的匹配      
				cList.add(courseMap.get(key));
			}
		}
		
		if(cList.size() == 0) {
			Logger.getGlobal().info("in getCoursesByKeyword");
			throw new CourseNotExistException();
		}
		
		
		Collections.sort(cList);
		return cList;
	}
	
	public ArrayList<Course> getAllCourses(){
		ArrayList<Course> allList = new ArrayList<Course>();
		for(String key : this.courseMap.keySet()) {
			allList.add(courseMap.get(key));
		}
		
		Collections.sort(allList);
		return allList;
	}
	
	
	public boolean addCourse(String cid,Course cor) throws CourseExistException {
		
		
		/*
		 * 从工厂生产出来的实例一定合法
		 * 只有已存在错误
		 */
		
		if(courseMap.get(cid) == null) {
			this.courseMap.put(cid,cor);
			return true;
		}
		else {
			throw new CourseExistException();
		}
	}
	
	public boolean modCourse(String cid,String command,String inputs) throws CourseNotExistException,InputErrorException{
		
		/*
		 * 用抛出异常来解决改变课程时的错误
		 * 错误分为：1.输入错误，2.不存在错误
		 * 
		 * 因为抛出错误对效率影响较大
		 * 在后期如果追求效率可以使用0，1，2，3等数字表示更改状态
		 */
		
		if(courseMap.get(cid) == null) {
			throw new CourseNotExistException();
		}
		Course c = courseMap.get(cid);
		if(command.contentEquals("-n")) {
			if(CourseFactory.courseNameCheck(inputs)==false) {
				throw new InputErrorException();
			}
			c.setCourseName(inputs);
			return true;
		}
		
		else if(command.contentEquals("-t")) {
			
			int len = inputs.length();
			
			if(inputs.charAt(0)!='['||inputs.charAt(len-1)!=']') {
				throw new InputErrorException();
			}
			inputs = inputs.substring(1,len-1);
			String []temp = inputs.split(",");
			for(int i=0;i<temp.length;i++) {
				Logger.getGlobal().info("teacher list "+i+": "+ temp[i]);
			}
			ArrayList<String> nameList = new ArrayList<>();
			nameList.addAll(Arrays.asList(temp));
			if(CourseFactory.teachersNameCheck(nameList)==false) {
				throw new InputErrorException();
			}
			
			c.setTeachersName(nameList);
			return true;
		}
		
		else if(command.contentEquals("-c")) {
			int maxContents=0;
			try {
				maxContents = Integer.parseInt(inputs);
			} catch (NumberFormatException e) {
				throw new InputErrorException();             //若容量不为数字
			}
			if(CourseFactory.contentCheck(maxContents)==false) {
				throw new InputErrorException();          //若容量小于-1.
			}
			else {
				return true;
			}
		}
		throw new InputErrorException();	
	}
	
	public static ArrayList<Course> getNewPage(ArrayList<Course> courseList,int page,int pageContent) throws CourseNotExistException{
		/*
		 * update : 2020/03/18
		 * 静态方法
		 * 用来将courseList分割成多个page
		 * 可能会有很多bug (๑•̀ㅂ•́)و✧
		 * 不需要深拷贝，因为没有改变值。
		 */
		
		ArrayList<Course> pageList = new ArrayList<>();
		int len = courseList.size();
		for(int i=(page-1)*pageContent;i<(page)*pageContent&&i<len;i++) {
			pageList.add(courseList.get(i));
		}
		if(pageList.size()==0) {
			throw new CourseNotExistException();
		}
		return pageList;
	}
	
}
