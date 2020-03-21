package com.test.system;


import com.person.system.*;
import com.course.system.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class mainTest {
	
	public static void printCourseList(ArrayList<Course> cList) {
		for(int i=0;i<cList.size();i++) {
			System.out.println((i+1)+"."+cList.get(i));
		}
	}
	
	public static void main(String re[]) {
		
		/*
		 * 模拟控制台输入输出
		 */
//		Logger.getGlobal().setLevel(Level.OFF);
		
		Scanner in = new Scanner(System.in);
		CourseList cList = new CourseList();
		
		while(in.hasNext()) {
			
			String temp = in.nextLine().replaceAll("\\s+", " ");
			String[] res = temp.split(" ");
			
			//QUIT分支
			if(res[0].contentEquals("QUIT")) {
				break;
			}
			
			
			//cid分支
			else if(res[0].contentEquals("cid")) {
				if(res.length!=2) {
					System.out.println("Input illegal.");
					continue;
				}
				else {
					System.out.println(IDNum.checkIDNum(res[1]));
					continue;
				}
			}
			
			
			//np分支
			else if(res[0].contentEquals("np")) {
				if(res.length!=3) {
					System.out.println("Input illegal.");
					continue;
				}
				else {
					Person p = Person.newPerson(res[1],res[2]);
					if(p == null) {
						System.out.println("ID illegal");
					}
					else {
						System.out.println(p);
					}
				}
			}
			
			
			//gc分支
			else if(res[0].contentEquals("gc")) {
				//-id
				if(res[1].contentEquals("-id")) {
					if(res.length!=3) {
						System.out.println("Input illegal.");
						continue;
					}
					try {
						Course c = cList.getCourseById(res[2]);
						System.out.println(c);
						continue;
					}catch(CourseNotExistException ex){
						System.out.println("Course does not exist.");
					}
				}
				//-key
				else if(res[1].contentEquals("-key")||res[1].contentEquals("-all")) {
					if(res[1].contentEquals("-all")&&res.length!=4) {
						System.out.println("Input illegal");
						continue;
					}
					if(res[1].contentEquals("-key")&&res.length!=5) {
						System.out.println("Input illegal");
						continue;
					}
					else {
						int page = 0;
						int pageContent = 0;
						ArrayList<Course> tempList=new ArrayList<Course>();
						ArrayList<Course> pageList=new ArrayList<Course>();
						try {		
							if(res[1].contentEquals("-key")) {
								page = Integer.parseInt(res[3]);
								pageContent = Integer.parseInt(res[4]);
								tempList = cList.getCoursesByKeyword(res[2]);
							}
							else if(res[1].contentEquals("-all")) {
								page = Integer.parseInt(res[2]);
								pageContent = Integer.parseInt(res[3]);
								tempList = cList.getAllCourses();
							}
							Logger.getGlobal().info("page = "+page+" pagecontent = "+pageContent);
							pageList = CourseList.getNewPage(tempList,page,pageContent);
							System.out.println("Page:"+page);
							mainTest.printCourseList(pageList);
						}catch(NumberFormatException ex){
							System.out.println("Input illegal.");
							continue;                                     //在输入不是数字的时候直接退出,这个时候没有意义    
						}catch(CourseNotExistException ex) {
							System.out.println("Course does not exist.");
						}
						System.out.println("n-next page, l-last page, q-quit");
						String s;
						while(true) {
							try {
								s = in.nextLine();
								if(s.contentEquals("q")) {
									break;
								}
								else if(s.contentEquals("n")) {
									page++;
									pageList = CourseList.getNewPage(tempList,page,pageContent);
									System.out.println("Page:"+page);
									mainTest.printCourseList(pageList);
								}
								else if(s.contentEquals("l")) {
									page--;
									pageList = CourseList.getNewPage(tempList,page,pageContent);
									System.out.println("Page:"+page);
									mainTest.printCourseList(pageList);
								}
								else {
									System.out.println("Input illegal.");
									break;
								}
							}catch (CourseNotExistException ex) {
								System.out.println("Course does not exist.");
							}
							System.out.println("n-next page, l-last page, q-quit");
						}
					}
				}
				else {
					System.out.println("Input illegal");
				}
			}
			
			
			//udc分支
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
					System.out.println("Update fail.");
				}
			}
					
			
			//nc分支
			else if(res[0].contentEquals("nc")) {
				if(res.length!=5) {
					System.out.println("Input illegal.");
					continue;
				}
				try {
					Course c = CourseFactory.getNewCourse(res[1], res[2], res[3], res[4]);
					Logger.getGlobal().info(c.getCid());
					cList.addCourse(c.getCid(), c);
					System.out.println("Add success.");
				} catch(InputErrorException ex) {
					System.out.println("Course add illegal.");
				} catch (CourseExistException ex) {
					System.out.println("Course exist s.");
				}
				
			}
			
			
			//全部不对应，错误分支
			else {
				System.out.println("Input illegal.");
			}
		}
		
	}

}