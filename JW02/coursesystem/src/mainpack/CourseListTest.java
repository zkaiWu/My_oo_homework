package mainpack;

import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class CourseListTest {
	
	public static void printCourseList(ArrayList<Course> cList) {
		System.out.println("Total:"+cList.size());
		for(int i=0;i<cList.size();i++) {
			System.out.println((i+1)+"."+cList.get(i));
		}
	}
	
	public static void main(String re[]) {
		
		/*
		 * 模拟控制台输入输出
		 */
		
		Scanner in = new Scanner(System.in);
		CourseList cList = new CourseList();
		
		while(in.hasNext()) {
			
			String temp = in.nextLine().replaceAll("\\s+", " ");
			String[] res = temp.split(" ");
			
			if(res[0].contentEquals("gc")) {
				if(res.length!=3) {
					System.out.println("Input illegal.");
					continue;
				}
				try {
					if(res[1].contentEquals("-id")) {
						Course c = cList.getCourseById(res[2]);
						System.out.println(c);
					}
					else if(res[1].contentEquals("-key")) {
						ArrayList<Course> c = cList.getCoursesByKeyword(res[2]);
						CourseListTest.printCourseList(c);
					}
					else {
						System.out.println("Input illegal.");
					}
				}catch(CourseNotExistException ex) {
					System.out.println("Course does not exist.");
				}
			}
			
			else if(res[0].contentEquals("udc")) {
				if(res.length!=4) {
					System.out.println("Input illegal.");
					continue;
				}
				try {
					cList.modCourse(res[1],res[2],res[3]);
					System.out.println("Update success.");
				}catch(CourseNotExistException ex) {
					System.out.println("Course does not exist.");
				}catch(InputErrorException ex) {
					System.out.println("Input illegal.");
				}
			}
					
			else if(res[0].contentEquals("nc")) {
				if(res.length!=5) {
					System.out.println("Input illegal.");
					continue;
				}
				try {
					Course c = Course.newCourse(res[1], res[2], res[3], res[4]);
					Logger.getGlobal().info(c.getCid());
					cList.addCourse(c.getCid(), c);
					System.out.println("Add success.");
				} catch(InputErrorException ex) {
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

}
