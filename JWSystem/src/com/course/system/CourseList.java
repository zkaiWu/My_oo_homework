package com.course.system;


import java.util.*;
import java.util.logging.Logger;




/*
 * 将courseList作为容器，只支持数据的增删查改，
 * 对数据更新之前应该调用CourseFactory来检查合法性
 * 动态的显示逻辑建议不要在此实现
 * 若要实现任何功能更加完善的显示逻辑
 * 后期可以增加一个控制器类。
 * 
 * 将接口接受统一变成了字符串
 */
public class CourseList {
	private HashMap<String,Course> courseMap;
	
	public CourseList() {
		courseMap = new HashMap<>();
	}
	
	
	/*
	 * 匹配为不区分大小写的匹配
	 * @param keyword 查询的课程号
	 * @return Course 符合条件的课程
	 */
	public Course getCourseById(String cid) throws CourseNotExistException {
		cid = cid.toUpperCase();
		Course cTemp = courseMap.get(cid.toUpperCase());
		if(cTemp == null) {
			throw new CourseNotExistException();
		}
		return cTemp;
	}
	
	
	
	/*
	 * 匹配为不区分大小写的匹配
	 * @param keyword 查询的关键字
	 * @return ArrayList 符合条件的列表
	 */
	public ArrayList<Course> getCoursesByKeyword(String keyword) throws CourseNotExistException{
		
		
		ArrayList<Course>  cList = new ArrayList<>();
		for(String key : this.courseMap.keySet()) {
			if(courseMap.get(key).getCourseName().toUpperCase().contains(keyword.toUpperCase())) {                //修改成不分大小写的匹配      
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
	
	

	/* 大小写为相同
	 * 从工厂生产出来的实例一定合法
	 * 只有已存在错误
	 * 在存键值的时候，存入大写
	 * 
	 * @param cid 课程号
	 * @param name 课程名
	 * @param 老师名列表
	 * @maxContent 最大人数
	 * @return boolean 是否成功加入
	 */
	public boolean addCourse(String cid,String name,String teachersName,String maxContent) throws CourseExistException,InputErrorException{
		
		
		if(courseMap.get(cid.toUpperCase())!=null) {
			throw new CourseExistException();
		}
		
		Logger.getGlobal().info(cid);
		Course cor = CourseFactory.getNewCourse(cid, name, teachersName, maxContent);
		Logger.getGlobal().info(cid);
		Logger.getGlobal().info(cor.toString());
		this.courseMap.put(cid.toUpperCase(),cor);
		return true;
		
	}
	
	
	/*
	 * 用抛出异常来解决改变课程时的错误
	 * 查找课程的时候是不区分大小写的匹配
	 * 错误分为：1.输入错误，2.不存在错误
	 * 
	 * 因为抛出错误对效率影响较大
	 * 在后期如果追求效率可以使用0，1，2，3等数字表示更改状态
	 * 
	 * @param cid 课程号
	 * @param command 命令 -t,-n,-c
	 * @param inputs 对应输入
	 * @return boolean 是否成功加入
	 */
	public boolean modCourse(String cid,String command,String inputs) throws CourseNotExistException,InputErrorException{
		
		
		if(courseMap.get(cid.toUpperCase()) == null) {
			throw new CourseNotExistException();
		}
		Course c = courseMap.get(cid.toUpperCase());
		
		
		if(command.contentEquals("-n")) {
			if(CourseFactory.courseNameCheck(inputs)==false) {
				throw new InputErrorException();
			}
			c.setCourseName(inputs);
			return true;
		}
		
		else if(command.contentEquals("-t")) {
			
			int len = inputs.length();
		
			//对教师字符串的解析
			if(!CourseFactory.teachersNameStringCheck(inputs)){
				throw new InputErrorException(); 
			}
			//对于教师名的解析
			inputs = inputs.substring(1,len-1);
			String []temp = inputs.split(",");
			ArrayList<String> nameList = new ArrayList<>();
			nameList.addAll(Arrays.asList(temp));
			if(CourseFactory.teachersNameCheck(nameList)==false) {     //调用CourseFactory中的静态方法来进行检测
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
				c.setMaxContent(maxContents);
				return true;
			}
		}
		throw new InputErrorException();	
	}
	
	
	/*
	 * 用来将courseList分割成多个page
	 * 可能会有很多bug (๑•̀ㅂ•́)و✧
	 * 不需要深拷贝，因为没有改变值
	 * 
	 * @param courseList 课程列表
	 * @param page 页码
	 * @param pageContent 一页容纳多少个课程
	 * @return 这一页的课程列表
	 */
	public static ArrayList<Course> getNewPage(ArrayList<Course> courseList,int page,int pageContent) throws CourseNotExistException{
	
		if(page<1) throw new CourseNotExistException();
		
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
