import java.util.ArrayList;
import java.util.Scanner;

import coursesystem.*;
import personsystem.*;
import java.math.*;

/**
 * 处理所有的查询，简化代码
 * @author wzk1998
 *
 */
public class QueryHelper {
	
	private PersonList pList ;
	private CourseList cList ;
	
	
	public QueryHelper(PersonList pList,CourseList cList) {
		this.pList = pList ;
		this.cList = cList ;
	}
	
	/**
	 * 查询对应课程的选课学生，分页输出
	 * 
	 * @param cid 课程号
	 * @param page	字符串形式的page页码
	 * @param pageContext 字符串形式的每页最大容量
	 */
	public void queryForClist(String cid,String pageString,String pageContextString) throws CourseException{
		
		//获取选课列表
		Course course = cList.getCourseById(cid.toString());
		ArrayList<Student> stuList= course.getStudentsOfCourse();
		Scanner in = new Scanner(System.in);
		
		
		//获得参数
		int len = stuList.size();
		int page = 0 , pageContext = 0;
		try {
			page = Integer.parseInt(pageString);
			pageContext = Integer.parseInt(pageContextString);
		} catch(NumberFormatException ex) {
			in.close();
			throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);              //	输入错误	
		}
		
		
		//分页输出
		while(true) {
			int start = (page-1)*pageContext;
			int end = Math.min(pageContext*page,len);
			
			//页面为空
			if(end<start) {
				in.close();
				throw new CourseException(CourseErrorCode.RECORD_NOT_EXISTS_ERROR);
			}
			//输出页面
			System.out.println("page:"+page);
			for(int i=start; i<end; i++) {
				System.out.println((i-start+1)+"."+stuList.get(i).getSID()+stuList.get(i).getName());
			}
			//输出选择界面
			System.out.println("n-next page, l-last page, q-quit");
			
			String opt = in.nextLine();
			if(opt.contentEquals("n")) {
				page++;
			} else if(opt.contentEquals("-l")) {
				page--;
			} else if(opt.contentEquals("q")) {
				in.close();
				return;
			} else {
				in.close();
				throw new CourseException(CourseErrorCode.INPUT_ILLEGAL_ERROR);
			}
		}
	}
	
	/**
	 * 通过在课程里查找的方式，查看教师的课程
	 * 
	 * @param tid
	 * @param page
	 * @param pageContext
	 */
//	public void QueryForCourseOfTeacher(String tid,String page,String pageContext) {
//		ArrayList<Course> allCourse = cList.getAllCourses();
//		ArrayList<Course> coursesOfTeacher = new ArrayList<Course>();
//		
//		for(int i=0;i<allCourse.size();i++) {
//			Course course = allCourse.get(i);
//			for(int j=0;)
//		}
//		
//	}
	
	
}
