package com.person.system;

import java.util.*;
import java.util.regex.*;
import java.util.Date;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;


public class IDNum {
	private String idNum;
	private static final int mod = 11;
	
	public IDNum() {
		idNum = "";
	}
	
	
	public void setIdNum(String idNum) {
		this.idNum = idNum;
		this.idNum = this.idNum.replaceAll("x", "X");
		Logger.getGlobal().info("idNum = "+idNum);
	}
	public String getIdNum() {
		return this.idNum;
	}
	
	
	public static boolean calJudge(String id) {
		
		int re = 0;
		int w = 1;
		int len = id.length();
		if(id.charAt(len-1) == 'x'||id.charAt(len-1)=='X') {
			re = 10;
		}
		else re = Integer.parseInt(String.valueOf(id.charAt(len-1)));
		w = (w*2)%IDNum.mod;
		for(int i=len-2;i>=0;i--) {
			int a = Integer.parseInt(String.valueOf(id.charAt(i)));
			re = ( re + a*w ) ;
			w = (w*2) % IDNum.mod;
		}
		Logger.getGlobal().info("re="+re);
		if(re%IDNum.mod == 1) return true;
		else return false;
	}
	
	
	public static boolean checkIDNum(String id) {
		if(id.length()!=18) {
//			System.out.println("error0");
			Logger.getGlobal().info("length error:"+ id.length());
			return false;
		}
		
		//利用正则表达式匹配,检查前17位是否全为数字,最后一位是否是数字或x或X
		String temp = "\\d{17}[0-9xX]";
		Pattern pattern = Pattern.compile(temp);
		Matcher matcher = pattern.matcher(id);
		if(matcher.find()) {
			if(!matcher.group(0).contentEquals(id)) {
				Logger.getGlobal().info("not matching  error");
//				System.out.println("error1");
				return false;
			}
		}else {
			Logger.getGlobal().info("not all number error");
//			System.out.println("error2");
			return false;
		}
		
		//检查日期的合法性
		try {
			String bir = id.substring(6,14);
			Logger.getGlobal().info(bir);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			sdf.setLenient(false);
			Date date = sdf.parse(bir);
		}catch(Exception ex) {
			Logger.getGlobal().info("date error");
//			System.out.println("error3");
//			ex.printStackTrace();
			return false;
		}
		
		//判断校验位的合法性
		if(IDNum.calJudge(id)==false) {
			Logger.getGlobal().info("judgebyte error");
//			System.out.println("error4");
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return idNum;
	}

}
