package com.person.system;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Logger;

public class Person {
	private IDNum id;
	private String name;
	private int sex;     //1为男性，2为女性
	private Date birthday;
	
	public Person() {
		setId(new IDNum());
		setName("");
		setSex(0);
		setBirthday(new Date());
	}
	
	public IDNum getId() {
		return id;
	}

	public void setId(IDNum id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public static ArrayList<String> decode(String inputs){
		ArrayList<String> list = new ArrayList<String>();
		int len = inputs.length();
		int mid = inputs.length()-18;
		if(mid<0) mid = 0;
		String name = inputs.substring(0, mid);
		String id = inputs.substring(mid, len);
		list.add(name);
		list.add(id);
		return list;
	}
	

	public static Person newPerson(String ...inputs) {
		
		//检查是否合法
		String id = "";
		String name = "";
		if(inputs == null) return null;
		else if(inputs.length == 1) {
			ArrayList<String> list = decode(inputs[0]);
			name = list.get(0);
			id = list.get(1);
		}
		else if(inputs.length == 2){
			id = inputs[1];
			name = inputs[0];
		}
		Logger.getGlobal().info("id = "+id);
		if(IDNum.checkIDNum(id) == false) {
			return null;
		}
		
		//若合法,新建一个Person类,设置属性。‘
		Person person = new Person();
		
		IDNum idNum = new IDNum();
		idNum.setIdNum(id);
		
		person.setId(idNum);
		person.setName(name);
		person.setSex(Integer.parseInt(id.substring(14,17))%2);
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			sdf.setLenient(false);
			Date dir = sdf.parse(id.substring(6,14));
			person.setBirthday(dir);
		}catch(Exception ex) {
			Logger.getGlobal().info("get data false");
//			System.out.println("false");
			return null;
		}
		
		return person;
	}
	
	@Override
	public String toString() {
		String temp = "";
		temp = temp+"Name:"+this.getName()+"\n";
		temp = temp+"IDNum:"+this.getId().toString()+"\n";
		temp = temp+"Sex:";
		temp += this.getSex()==0 ? "F":"M";
		temp += "\n";
		temp = temp+"Birthday:"+new SimpleDateFormat("yyyy/MM/dd").format(this.getBirthday());
		return temp;
	}
}
