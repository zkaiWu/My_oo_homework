package mainpack;


import java.util.*;
import java.util.logging.Logger;

public class CourseList {
	private HashMap<String,Course> courseMap;
	
	public CourseList() {
		courseMap = new HashMap<>();
	}
	
	public Course getCourseById(String cid) throws CourseNotExistException {
		Course cTemp = courseMap.get(cid);
		if(cTemp == null) {
			throw new CourseNotExistException();
		}
		return cTemp;
	}
	
	public ArrayList<Course> getCoursesByKeyword(String keyword) throws CourseNotExistException{
		
		/*
		 * 此函数可通过改变遍历方式提升效率
		 * 若加入时间限制，则要改此处
		 */
		
		
		ArrayList<Course>  cList = new ArrayList<>();
		for(String key : this.courseMap.keySet()) {
			if(courseMap.get(key).getCourseName().contains(keyword)) {
				cList.add(courseMap.get(key));
			}
		}
		
		if(cList.size() == 0) {
			throw new CourseNotExistException();
		}
		
		
		Collections.sort(cList);
		return cList;
	}
	
	public boolean addCourse(String cid,Course cor) throws InputErrorException,CourseExistException {
		
		
		/*
		 * 添加课程之前，预检查数据的合法性
		 * 决定用抛出异常的方式处理错误
		 * 错误分为：1.输入错误(容量小于零等非格式错误），2.已存在错误
		 * 
		 * bug有可能从此处开始
		 */
		
		if(courseMap.get(cid) == null) {
			if(Course.courseCheck(cor) == true) {
				courseMap.put(cid,cor);
				return true;
			}
			else {
				throw new InputErrorException();
			}
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
			c.setCourseName(inputs);
			return true;
		}
		
		else if(command.contentEquals("-t")) {
			
			int len = inputs.length();
			inputs = inputs.substring(1,len-1);
			String []temp = inputs.split(",");
			for(int i=0;i<temp.length;i++) {
				Logger.getGlobal().info("teacher list "+i+": "+ temp[i]);
			}
			ArrayList<String> nameList = new ArrayList<>();
			nameList.addAll(Arrays.asList(temp));
			
			c.setTeachersName(nameList);
			return true;
		}
		
		else if(command.contentEquals("-c")) {
			int maxContents = Integer.parseInt(inputs);
			int oldContents = c.getMaxContent();
			c.setMaxContent(maxContents);
			if(Course.courseCheck(c) == false) {
				c.setMaxContent(oldContents);
				throw new InputErrorException();
			}
			else {
				return true;
			}
		}
		throw new InputErrorException();
		
	}
	
}
