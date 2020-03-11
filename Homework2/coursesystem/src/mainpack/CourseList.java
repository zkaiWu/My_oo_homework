package mainpack;


import java.util.*;
import java.util.logging.Logger;

public class CourseList {
	private Map<String,Course> courseMap;
	
	public CourseList() {
		courseMap = new HashMap<>();
	}
	
	public Course getCourseById(String cid) {
		Course cTemp = courseMap.get(cid);
		return cTemp;
	}
	
	public ArrayList<Course> getCoursesByKeyword(String keyword){
		
		/*
		 * 此函数可通过改变遍历方式提升效率
		 * 若加入时间限制，则要改此处
		 * 还要增加排序
		 */
		
		
		ArrayList<Course>  cList = new ArrayList<>();
		for(String key : this.courseMap.keySet()) {
			if(courseMap.get(key).getCourseName().contains(keyword)) {
				cList.add(courseMap.get(key));
			}
		}
		
		Collections.sort(cList);
		return cList;
	}
	
	public boolean addCourse(String cid,Course cor) {
		
		
		/*
		 * 添加课程之前，预检查数据的合法性
		 * 决定用抛出异常的方式处理错误
		 * 错误分为：1.输入错误，2.已存在错误
		 * 
		 * bug有可能从此处开始
		 */
		
		if(courseMap.get(cid)==null) {
			if(Course.courseCheck(cor) == true) {
				courseMap.put(cid,cor);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
		
		
	}
	
	public boolean modCourse(String cid,String command,String inputs) {
		
		/*
		 * 用抛出异常来解决改变课程时的错误
		 * 错误分为：1.输入错误，2.不存在错误
		 */
		
		if(courseMap.get(cid) == null) {
			return false;
		}
		Course c = courseMap.get(cid);
		if(command.contentEquals("-n")) {
			c.setCourseName(inputs);
			return true;
		}
		else if(command.contentEquals("-t")) {
			
			inputs = inputs.replace("[\\[\\]]","");
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
				return false;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
}
