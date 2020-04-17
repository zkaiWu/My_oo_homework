package com.person.system;

import java.util.*;
import java.util.logging.Logger;

public class PersonList {
	
	HashMap<String,Person> pListByID;
	HashMap<String,Person> pListBySTID;           //用来保证SID，和TID的唯一性
	
	public PersonList() {
		pListByID = new HashMap<String, Person>();
		pListBySTID = new HashMap<String, Person>();
	}
	
	
	/* 学生登录，如果登录成功则返回一个用户。
	 * 教师登录
	 * @param id 身份证号
	 * @param pwd 密码
	 * @return Person 返回登录成功的用户
	 */
	public Person teaLoginCheck(String id,String pwd) throws PersonException{
		
		//判断id以及密码是否合法
		if((IDNum.checkIDNum(id)==false)||Person.checkPwd(pwd)==false) {
			throw new PersonException(PersonErrorCode.LOGIN_ERROR);
		}
		
		//判断课程是否存在
		if(pListByID.get(id)==null) {
			throw new PersonException(PersonErrorCode.LOGIN_ERROR);
		}
		
		//判断密码以及Person具体类型
		Person person = pListByID.get(id);
		if(!person.getPassWord().contentEquals(pwd)||!(person instanceof Teacher)) {
			throw new PersonException(PersonErrorCode.LOGIN_ERROR);
		}
		return person;
	}
	
	/*
	 * 学生登录,如果登录成功则返回一个用户
	 * @param id 身份证号
	 * @param pwd 密码
	 * @return Person 返回登录成功的用户
	 */
	public Person stuLoginCheck(String id,String pwd) throws PersonException{
		//判断id以及密码是否合法
		if((IDNum.checkIDNum(id)==false)||Person.checkPwd(pwd)==false) {
			throw new PersonException(PersonErrorCode.LOGIN_ERROR);
		}
				
		//判断课程是否存在
		if(pListByID.get(id)==null) {
			throw new PersonException(PersonErrorCode.LOGIN_ERROR);
		}
				
		//判断密码以及Person具体类型
		Person person = pListByID.get(id);
		if(!person.getPassWord().contentEquals(pwd)||!(person instanceof Student)) {
			throw new PersonException(PersonErrorCode.LOGIN_ERROR);
		}
		return person;
	}
	
	
	/*
	 * 添加老师
	 * @param name 老师名
	 * @param id 身份证
	 * @param Tid 老师号
	 */
	public boolean addTeacher(String name,String id,String TID) throws PersonException{
		
		if(IDNum.checkIDNum(id)==false) {
			throw new PersonException(PersonErrorCode.ID_ILLEGAL_ERROR);
		}
		if(pListByID.get(id.toUpperCase())!=null) {
			throw new PersonException(PersonErrorCode.ID_EXIST_ERROR);
		}
		
		//newInstance方法里已经有对name，id，Tid的检查
		Teacher teacher = Teacher.newInstance(name, id, TID);   
		if(this.pListBySTID.get(TID)!=null) {
			throw new PersonException(PersonErrorCode.TID_EXIST_ERROR);
		}
		pListByID.put(teacher.getId().toString(),teacher);
		pListBySTID.put(teacher.getTID(),teacher);
		return true;
	}
	
	
	/*
	 * 添加学生
	 * @param name 学生名
	 * @param id 身份证
	 * @oaram Tid 老师号
	 */
	public boolean addStudent(String name,String id,String SID) throws PersonException {
		
		
		if(IDNum.checkIDNum(id)==false) {
			throw new PersonException(PersonErrorCode.ID_ILLEGAL_ERROR);
		}
		if(pListByID.get(id.toUpperCase())!=null) {                  //最后一位的X模糊匹配
			throw new PersonException(PersonErrorCode.ID_EXIST_ERROR);
		}
		
		//newInstance方法里已经有对name，id，Sid的检查
		Student student = Student.newInstance(name, id, SID);    
		if(this.pListBySTID.get(SID)!=null) {
			throw new PersonException(PersonErrorCode.SID_EXIST_ERROR);
		}
		pListByID.put(student.getId().toString(),student);     //注意ID的最后一位若为x则是大写。
		pListBySTID.put(student.getSID(),student);
		return true;
	}
	
	
	public Person getPersonByID(String id) throws PersonException{
		if(pListByID.get(id)==null) {
			throw new PersonException(PersonErrorCode.ID_NOT_EXIST_ERROR);
		}
		Person person = pListByID.get(id.toUpperCase());
		return person;
	}
	
	public void chgPersonPwd(String firstPwd,String secondPwd,Person teacher) throws PersonException{
		if(!firstPwd.contentEquals(secondPwd)) {
			throw new PersonException(PersonErrorCode.PASSWORD_FIRSTPASSWORD_NOT_SAME_WITH_SECONDPASSWORD_ERROR);
		}
		teacher.setPassWord(firstPwd);
	}
	
	
	/*
	 * 单元测试
	 */
	public static void main(String[] args) {
		
		PersonList pList = new PersonList();
		Scanner in = new Scanner(System.in);
		String name = "";
		String id = "";
		String sid = "";
		while(in.hasNext()) {
			name = in.nextLine();
			id = in.nextLine();
			sid = in.nextLine();
			try {
				pList.addStudent(name, id, sid);
				System.out.println("Add student success.");
			} catch(PersonException ex) {
				System.out.println(ex.getCodeDescription());
			}
		}
	}
	
}
