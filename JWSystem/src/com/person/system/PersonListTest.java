package com.person.system;

import java.util.*;
import java.util.Scanner;

public class PersonListTest {
	
	private PersonList pList = new PersonList();
	public static final int LEVEL_1_STATE = 1;
	public static final int SUDO_STATE = 2;
	public static final int TEACHER_LOGIN_STATE = 3;
	public static final int STUDENT_LOGIN_STATE = 4;
	private static int level=1;
	
	private static String user;
	
	public static void setLevel(int state) {
		PersonListTest.level = state;
	}
	public static int getLevel() {
		return PersonListTest.level;
	}
	
	public static void setUser(String user) {
		PersonListTest.user = user;
	}
	
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String temp = "";
		String[] inputs;
		
		
		while(in.hasNext()) {
			temp = in.nextLine();
			temp = temp.replaceAll("\\s+"," ");
			inputs = temp.split(" ");
			
			
			//QUIT分支
			if(inputs[0].contentEquals("QUIT")) {
				break;
			}
			
			//一级命令行
			else if(PersonListTest.getLevel() == PersonListTest.LEVEL_1_STATE) {
				
				//SUDO分支
				if(inputs[0].contentEquals("SUDO")&&inputs.length==1) {
					PersonListTest.setLevel(PersonListTest.SUDO_STATE);
				}
				
				
				//login分支
				else if(inputs[0].contentEquals("login")) {
					if(inputs.length!=4) {
						System.out.println("Input illegal");
						continue;
					}
					else if(inputs[1].contentEquals("-t")) {
						boolean judge=cList.teaLoginCheck(inputs[2],inputs[3]);
						if(judge == true) {
							System.out.println("Login success.");
							PersonListTest.setLevel(STUDENT_LOGIN_STATE);
						}
						else {
							System.out.println("Login Error. Your ID or password maybe wrong.");
						}
					}
					else if(inputs[1].contentEquals("-s")) {
						boolean judge=cList.stuLoginCheck(inputs[2],inputs[3]);
						if(judge == true) {
							System.out.println("Login success.");
							PersonListTest.setLevel(TEACHER_LOGIN_STATE);
						}
						else {
							System.out.println("Login Error. Your ID or password maybe wrong.");
						}
					}
				}
				
				else {
					System.out.println("Input illegal");
				}
				
			}
			
			
			//SUDO状态下的命令行
			else if(PersonListTest.getLevel() == PersonListTest.SUDO_STATE) {
				
				//back分支
				if(inputs[0].contentEquals("back")&&inputs.length==1) {
					PersonListTest.setLevel(PersonListTest.LEVEL_1_STATE);
					continue;
				}
				
				//np分支
				else if(inputs[0].contentEquals("np")) {
					if(inputs.length != 5) {
						System.out.println("Input illegal");
						continue;
					}
					else if(inputs[1].contentEquals("-t")) {
						try {
							cList.addTeacher(inputs[2],inputs[3],inputs[4]);
							System.out.println("Add teacher success.");
						}catch(PersonException ex) {
							System.out.println(ex.getCodeDescription());
						}
					}
					else if(inputs[1].contentEquals("-s")) {
						try {
							cList.addStudent(inputs[2],inputs[3],inputs[4]);
							System.out.println("Add student success.");
						}catch(PersonException ex) {
							System.out.println(ex.getCodeDescription());
						}
					}
				}
				
				else {
					System.out.println("Input illegal");
				}
			}
			
			//学生登录下的命令行
			else if(PersonListTest.getLevel() == PersonListTest.STUDENT_LOGIN_STATE) {
				
				//back分支
				if(inputs[0].contentEquals("back")&&inputs.length==1) {
					PersonListTest.setLevel(PersonListTest.LEVEL_1_STATE);
					continue;
				}
				
				//myinfo分支
				else if(inputs[0].contentEquals("myinfo")&&inputs.length==1) {
					Person c = pList.getStudentByID(user);
					if(c == null) {
						System.out.println("user not exist");
						continue;
					}
					else {
						System.out.println(c);
					}
				}
				
				//chgpw分支
				else if(inputs[0].contentEquals("chgpw")&&inputs.length==3) {
					if(!inputs[1].contentEquals(inputs[2])) {
						System.out.println("The password you entered must be the same as the former one.");
						continue;
					}
					else {
						try {
							pList.chgStudentPassWord(user,inputs[1],inputs[2]);
							System.out.println("Password changed successfully.");
						}catch(PersonException ex) {
							System.out.println(ex.getCodeDescription());
						}
					}
				}
				
				else {
					System.out.println("Input illegal.");
				}
				
			}
			
			
			else if(PersonListTest.getLevel() == PersonListTest.TEACHER_LOGIN_STATE) {
				//back分支
				if(inputs[0].contentEquals("back")&&inputs.length==1) {
					PersonListTest.setLevel(PersonListTest.LEVEL_1_STATE);
					continue;
				}
				
				//myinfo分支
				else if(inputs[0].contentEquals("myinfo")&&inputs.length==1) {
					Person c = pList.getTeacherByID(user);
					if(c == null) {
						System.out.println("user not exist");
						continue;
					}
					else {
						System.out.println(c);
					}
				}
				
				//chgpw分支
				else if(inputs[0].contentEquals("chgpw")&&inputs.length==3) {
					if(!inputs[1].contentEquals(inputs[2])) {
						System.out.println("The password you entered must be the same as the former one.");
						continue;
					}
					else {
						try {
							pList.chgTeacherPassWord(user,inputs[1],inputs[2]);
							System.out.println("Password changed successfully.");
						}catch(PersonException ex) {
							System.out.println(ex.getCodeDescription());
						}
					}
				}
				
				else {
					System.out.println("Input illegal");
				}
				
			}
			
		}
		
	}

}
