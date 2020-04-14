package com.person.system;

import java.text.SimpleDateFormat;

public class Student extends Person{
	private String SID;
	
	
	public Student() {
		super();
		this.SID = "";
	}
	
	public void setSID(String sid) {
		this.SID = sid;
	}
	public String getSID() {
		return this.SID;
	}
	
	public static boolean checkSID(String sid) {
		return sid.matches("[0-9]{8}");
	}
	
	
	
	public static Student newInstance(String name,String id,String SID) throws PersonException{
		/*用了一个很笨的方法来简化代码555.
		 *@param name 学生名
		 *@param id 学生身份证号
		 *@param SID 学生学号
		 *@return Student 返回一个学生实例
		 */
		Person p = Person.newPerson(name,id);
		Student stu = new Student();
		stu.setId(p.getId());
		stu.setBirthday(p.getBirthday());
		stu.setName(p.getName());
		stu.setSex(p.getSex());
		
		if(Student.checkSID(SID)==false) {
			throw new PersonException(PersonErrorCode.SID_ILLEGAL_ERROR);
		}
		
		stu.setSID(SID);
		
		return stu;
	}
	
	@Override
	public String toString() {
		StringBuilder ss = new StringBuilder("");
		ss.append("Name:"+this.getName()+"\n");
		ss.append("IDNum:"+this.getId()+"\n");
		ss.append("SID:"+this.getSID()+"\n");
		String sex = this.getSex()==0 ? "F":"M";
		ss.append("Sex:"+sex+"\n");
		ss.append("Birthday:"+new SimpleDateFormat("yyyy/MM/dd").format(this.getBirthday()));
		return ss.toString();
		
	}
	
	
	public static void main(String[] args) {
		try {
			Person stu = Student.newInstance("wzk","460103199811291210","12345678");
			System.out.println(stu);
		}catch (PersonException e) {
			System.out.println(e.getCodeDescription());
		}
	}
}



