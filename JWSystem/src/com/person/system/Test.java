package com.person.system;

import java.util.*;
import java.util.Scanner;
import java.util.logging.*;;



interface State{
	public void handle(String[] inputs);
}


/*
 * 初始状态
 */
class LevelOneState implements State {
	private PersonList pList;
	public LevelOneState(PersonList pList) {
		this.pList = pList;
	}
	public void handle(String[] inputs) {
		
		//sudo分支
		if(inputs.length==1&&inputs[0].contentEquals("SUDO")) {
			Test.setNowState(Test.sudoState);
		} 
		
		//QUIT分支
		else if(inputs.length==1&&inputs[0].contentEquals("QUIT")) {
			System.exit(0);
		} 
		
		//login分支
		else if(inputs.length==4&&inputs[0].contentEquals("login")) {
			if(inputs[1].contentEquals("-t")) {
				try {
					Person user = pList.teaLoginCheck(inputs[2], inputs[3]);     //如果登录成功则返回一个用户
					System.out.println("Login success.");
					Test.setUser(user);                           //设置用户
					Test.setNowState(Test.TeacherLoginState);              //转换状态
				} catch(PersonException ex) {
					System.out.println(ex.getCodeDescription());
				}
			}
			else if(inputs[1].contentEquals("-s")) {
				try {
					Person user = pList.stuLoginCheck(inputs[2], inputs[3]);     //如果登录成功则返回一个用户
					System.out.println("Login success.");
					Test.setUser(user);                           //设置用户
					Test.setNowState(Test.StudentLoginState);              //转换状态
				} catch(PersonException ex) {
					System.out.println(ex.getCodeDescription());
				}
			}
			else {
				System.out.println("Input illegal.");
			}
		}
		
		else {
			System.out.println("Input illegal.");
		}
	}
}


/*
 * sudo登录状态
 */
class SudoState implements State {
	private PersonList pList;
	public SudoState(PersonList pList) {
		this.pList = pList;
	}
	public void handle(String[] inputs) {
		
		
		//QUIT分支
		if(inputs.length==1&&inputs[0].contentEquals("QUIT")) {
			System.exit(0);
		}
		
		//back分支
		else if(inputs.length==1&&inputs[0].contentEquals("back")) {
			Test.setNowState(Test.levelOneState);
		}
		
		else if(inputs.length==5&&inputs[0].contentEquals("np")) {
			if(inputs[1].contentEquals("-t")) {
				try {
					pList.addTeacher(inputs[2], inputs[3], inputs[4]);
					System.out.println("Add teacher success.");
				} catch(PersonException ex) {
					System.out.println(ex.getCodeDescription());
				}
			}
			else if(inputs[1].contentEquals("-s")) {
				try {
					pList.addStudent(inputs[2], inputs[3], inputs[4]);
					System.out.println("Add student success.");
				} catch(PersonException ex) {
					System.out.println(ex.getCodeDescription());
				}
			}
			else {
				System.out.println("Input illegal.");
			}
		}
		
		else {
			System.out.println("Input illegal.");
		}
	}
}


/*
 * 教师登录状态
 */
class TeacherLoginState implements State {
	private PersonList pList;
	public TeacherLoginState(PersonList pList) {
		this.pList = pList;
	}
	public void handle(String[] inputs) {
		
		
		//QUIT分支
		if(inputs.length==1&&inputs[0].contentEquals("QUIT")) {
			System.exit(0);
		}
		
		//back分支
		else if(inputs.length==1&&inputs[0].contentEquals("back")) {
			Test.setNowState(Test.levelOneState);
		}
		
		//myinfo分支
		else if(inputs.length==1&&inputs[0].contentEquals("myinfo")) {
			System.out.println(Test.getUser());
		}
		
		//chgpw分支
		else if(inputs.length==3&&inputs[0].contentEquals("chgpw")) {
			try {
				pList.chgPersonPwd(inputs[1], inputs[2], Test.getUser());
				System.out.println("Password changed successfully.");
			} catch (PersonException e) {
				System.out.println(e.getCodeDescription());
			}
		}
		
		//错误分支
		else {
			System.out.println("Inputs illegal.");
		}
	}
}


/*
 * 学生登录状态
 */
class StudentLoginState implements State{
	private PersonList pList;
	public StudentLoginState(PersonList pList) {
		this.pList = pList;
	}
	public void handle(String[] inputs) {
		//QUIT分支
		if(inputs.length==1&&inputs[0].contentEquals("QUIT")) {
			System.exit(0);
		}
		
		//back分支
		else if(inputs.length==1&&inputs[0].contentEquals("back")) {
			Test.setNowState(Test.levelOneState);
		}
		
		//myinfo分支
		else if(inputs.length==1&&inputs[0].contentEquals("myinfo")) {
			System.out.println(Test.getUser());
		}
		
		//chgpw分支
		else if(inputs.length==3&&inputs[0].contentEquals("chgpw")) {
			try {
				pList.chgPersonPwd(inputs[1], inputs[2], Test.getUser());
				System.out.println("Password changed successfully.");
			} catch (PersonException e) {
				System.out.println(e.getCodeDescription());
			}
		}		
		
		//错误分支
		else {
			System.out.println("Input illegal.");
		}
	}
}


/*
 * 主要测试类
 */
public class Test {
	
	
	public static PersonList pList = new PersonList();
	public static State levelOneState = new LevelOneState(pList);
	public static State sudoState = new SudoState(pList);
	public static State TeacherLoginState = new TeacherLoginState(pList);
	public static State StudentLoginState = new StudentLoginState(pList);
	
	private static State nowState = levelOneState;
	private static Person user;
	
	
	
	public static State getNowState() {
		return nowState;
	}
	public static void setNowState(State nowState) {
		Test.nowState = nowState;
	}
	
	public static void setUser(Person user) {
		Test.user =  user;
	}
	public static Person getUser() {
		return Test.user;
	}
	
	public static void handle(String[] inputs) {
		nowState.handle(inputs);
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String temp = "";
		String[] inputs;
		Logger.getGlobal().setLevel(Level.OFF);;
		
		
		while(in.hasNext()) {
			temp = in.nextLine();
			temp = temp.replaceAll("\\s+"," ");
			inputs = temp.split(" ");
			Test.handle(inputs);
		}
		
	}
}
