package com.course.system;

import java.util.*;
import java.util.logging.Logger;

public class Course implements Comparable<Course>{
	
	private String cid;
	private String courseName;
	private ArrayList<String> teachersName;
	private int maxContent;
	
	public Course() {
		this.cid = "";
		this.courseName = "";
		this.teachersName = new ArrayList<>();
	}
	
	public void setCid(String cid) {
		
		/*
		 * 要求前两位大写
		 */
		this.cid = "BH"+cid.substring(2);
	}
	public String getCid() {
		return this.cid;
	}
	
	public void setCourseName(String name) {
		this.courseName = name;
	}
	public String getCourseName() {
		return this.courseName;
	}
	
	public void setMaxContent(int mmax) {
		this.maxContent = mmax;
	}
	public int getMaxContent() {
		return this.maxContent;
	}
	
	public void setTeachersName(ArrayList<String> nameList) {
		this.teachersName = nameList;
	}
	
	
	@Override
	public String toString() {
		
		String temp = "";
		temp += "CID:" + this.cid + ",";
		temp += "Name:" + this.courseName + ",";
		temp += "Teachers:";
		
		String s = "[";
		if(this.teachersName.size()!=0) {
			for(int i=0;i<this.teachersName.size()-1;i++) {
				s += this.teachersName.get(i) + ",";
			}
			s += this.teachersName.get(this.teachersName.size()-1);
		}
		s +="]";
		
		Logger.getGlobal().info("s == "+s);
		
		temp += s+",";
		temp += "Capacity:" + this.maxContent;
		return temp;
	}

	
	public int compareTo(Course b) {
		return this.getCid().compareTo(b.getCid());
	}
	
}
