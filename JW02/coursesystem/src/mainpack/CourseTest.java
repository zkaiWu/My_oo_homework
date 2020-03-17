package mainpack;

import java.util.*;

public class CourseTest {
	
	
	public static void printCourseList(ArrayList<Course> cList) {
		System.out.println("Total:"+cList.size());
		for(int i=0;i<cList.size();i++) {
			System.out.println((i+1)+"."+cList.get(i));
		}
	}
	
	public static void main(String args[]) {
		
		/*
		 * 模拟控制台输入输出
		 */
		
		CourseList cList = new CourseList();
		
		
		if(args[0].contentEquals("gc")) {
			if(args.length!=3) {
				System.out.println("Input illegal.");
				return;
			}
			try {
				if(args[1].contentEquals("-id")) {
					Course c = cList.getCourseById(args[2]);
					System.out.println(c);
				}
				else if(args[1].contentEquals("-key")) {
					ArrayList<Course> c = cList.getCoursesByKeyword(args[2]);
					CourseTest.printCourseList(c);
				}
				else {
					System.out.println("Input illegal.");
				}
			}catch(CourseNotExistException ex) {
				System.out.println("Course does not exist.");
			}
		}
		
		
		
		else if(args[0].contentEquals("udc")) {
			if(args.length!=4) {
				System.out.println("Input illegal.");
				return;
			}
			try {
				cList.modCourse(args[1],args[2],args[3]);
				System.out.println("Update success.");
			}catch(CourseNotExistException ex) {
				System.out.println("Course does not exist.");
			}catch(InputErrorException ex) {
				System.out.println("Input illegal.");
			}
		}
		
		
		
		
		else if(args[0].contentEquals("nc")) {
			if(args.length!=5) {
				System.out.println("Input illegal.");
			}
			try {
				Course c = Course.newCourse(args[1], args[2], args[3], args[4]);
				cList.addCourse(c.getCid(), c);
				System.out.println("Add success.");
			}catch(InputErrorException ex) {
				System.out.println("Input illegal.");
			} catch (CourseExistException ex) {
				System.out.println("Course exist s.");
			}
			
		}
		
		
		else {
			System.out.println("Input illegal.");
		}
		
		
	}
}
