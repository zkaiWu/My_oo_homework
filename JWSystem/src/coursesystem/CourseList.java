package coursesystem;


import java.util.*;
import java.util.logging.Logger;




/**
 * 将courseList作为容器，只支持数据的增删查改，
 * 对数据更新之前应该调用CourseFactory来检查合法性
 * 动态的显示逻辑建议不要在此实现
 * 若要实现任何功能更加完善的显示逻辑
 * 后期可以增加一个控制器类。
 * 
 * 接口统一为字符串
 * @author wzk1998
 */
public class CourseList {
	
	
	private HashMap<String,Course> courseMap;
	
	private static CourseList instance= new CourseList();
	private CourseList() {
		courseMap = new HashMap<>();
	}
	
	/**
	 * 单例模式的获取方法
	 * @return
	 */
	public static CourseList getInstance() {
		return CourseList.instance;
	}
	
	
	/**
	 * 匹配为不区分大小写的匹配
	 * @param cid
	 * @return
	 * @throws CourseException
	 */
	public Course getCourseById(String cid) throws CourseException {
		Course cTemp = courseMap.get(cid.toUpperCase());
		if(cTemp == null) {
			throw new CourseException(CourseErrorCode.COURSE_NOT_EXISTS_ERROR);
		}
		return cTemp;
	}
	
	
	
	/*
	 * 匹配为不区分大小写的匹配
	 * @param keyword 查询的关键字
	 * @return ArrayList 符合条件的列表
	 */
	public ArrayList<Course> getCoursesByKeyword(String keyword) throws CourseException{
		
		
		ArrayList<Course>  cList = new ArrayList<>();
		for(String key : this.courseMap.keySet()) {
			if(courseMap.get(key).getCourseName().toUpperCase().contains(keyword.toUpperCase())) {                //修改成不分大小写的匹配      
				cList.add(courseMap.get(key));
			}
		}
		
		if(cList.size() == 0) {
			Logger.getGlobal().info("in getCoursesByKeyword");
			throw new CourseException(CourseErrorCode.COURSE_NOT_EXISTS_ERROR);
		}
		
		
		Collections.sort(cList);
		return cList;
	}

	
	/**
	 * 获得所有课程的列表
	 * @return ArrayList<Course>
	 */
	public ArrayList<Course> getAllCourses(){
		ArrayList<Course> allList = new ArrayList<Course>();
		for(String key : this.courseMap.keySet()) {
			allList.add(courseMap.get(key));
		}
		
		Collections.sort(allList);
		return allList;
	}
	
	
	

	/**
	 * 添加课程
	 * 调用了工厂方法
	 * @param cid 课程号
	 * @param name 课程名
	 * @param teachersTid 教师Tid列表
	 * @param maxContent 最大容量
	 * @return
	 * @throws CourseException
	 */
	public boolean addCourse(String cid,String name,String teachersTid,String maxContent) throws CourseException{
		
		
		if(courseMap.get(cid.toUpperCase())!=null) {
			throw new CourseException(CourseErrorCode.COURSE_EXISTS_ERROR);
		}
		
		//抛出code为INPUT_ILLEGAL_ERROR的异常
		Course cor = CourseFactory.getNewCourse(cid, name, teachersTid, maxContent);
		
		this.courseMap.put(cid.toUpperCase(),cor);
		return true;
		
	}
	
	
	/**
	 * 更改课程的信息
	 * @param cid 课程号
	 * @param command 命令选项 -t,-c,-n
	 * @param inputs 对应命令选项的字符串
	 * @return
	 * @throws CourseException
	 */
	public boolean modCourse(String cid,String command,String inputs) throws CourseException{
		
		
		if(courseMap.get(cid.toUpperCase()) == null) {
			throw new CourseException(CourseErrorCode.COURSE_NOT_EXISTS_ERROR);
		}
		Course c = courseMap.get(cid.toUpperCase());
		
		
		if(command.contentEquals("-n")) {
			if(CourseFactory.courseNameCheck(inputs)==false) {
				throw new CourseException(CourseErrorCode.COURSE_UPDATE_ERROR);
			}
			c.setCourseName(inputs);
			return true;
		}
		
		else if(command.contentEquals("-t")) {
			
			int len = inputs.length();
		
			//对教师字符串的解析
			if(!CourseFactory.teachersTidStringCheck(inputs)){
				throw new CourseException(CourseErrorCode.COURSE_UPDATE_ERROR); 
			}
			//对于教师名的解析
			inputs = inputs.substring(1,len-1);
			String []temp = inputs.split(",");
			ArrayList<String> tidList = new ArrayList<>();
			tidList.addAll(Arrays.asList(temp));
			if(CourseFactory.teachersTidCheck(tidList)==false) {     //调用CourseFactory中的静态方法来进行检测
				throw new CourseException(CourseErrorCode.COURSE_UPDATE_ERROR);
			}
			
			c.setTeachersName(tidList);
			return true;
		}
		
		else if(command.contentEquals("-c")) {
			int maxContents=0;
			try {
				maxContents = Integer.parseInt(inputs);
			} catch (NumberFormatException e) {
				throw new CourseException(CourseErrorCode.COURSE_UPDATE_ERROR);             //若容量不为数字
			}
			if(CourseFactory.contentCheck(maxContents)==false) {
				throw new CourseException(CourseErrorCode.COURSE_UPDATE_ERROR);          //若容量小于-1.
			}
			else {
				c.setMaxContent(maxContents);
				return true;
			}
		}
		throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);	
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
	public static ArrayList<Course> getNewPage(ArrayList<Course> courseList,int page,int pageContent) throws CourseException{
	
		if(page<1) throw new CourseException(CourseErrorCode.COURSE_NOT_EXISTS_ERROR);
		
		ArrayList<Course> pageList = new ArrayList<>();
		int len = courseList.size();
		for(int i=(page-1)*pageContent;i<(page)*pageContent&&i<len;i++) {
			pageList.add(courseList.get(i));
		}
		if(pageList.size()==0) {
			throw new CourseException(CourseErrorCode.COURSE_NOT_EXISTS_ERROR);
		}
		return pageList;
	}
	
}
