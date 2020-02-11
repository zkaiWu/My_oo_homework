package Elevator2;

import java.util.*;
import java.util.Scanner;
import java.util.regex.*;

public class RequireQueue {
	
	private ArrayList<RequireItem> reQueue;
	
	public RequireQueue() {
		reQueue = new ArrayList<RequireItem>();
		reQueue.clear();
	}
	
	public void append(RequireItem temp) {
		reQueue.add(temp);
	}
	
	public RequireItem requireCheck(int getTime,int eleState) {             //在队列里查找看是否有在这个
		RequireItem temp;
		for(int i=0;i<reQueue.size();i++) {
			temp = reQueue.get(i);
			if(temp.getGotTime()==getTime&&eleState==temp.getEleState()) {
				reQueue.remove(i);
				break;
			}
		}
		return null;
	}
	
	public void getRequireQueue() {
		
		Scanner scanner = new Scanner(System.in);
		Pattern pattern1 = Pattern.compile("");         //楼层请求
		Pattern pattern2 = Pattern.compile("");         //电梯按键请求
		
		String temp;
		temp = scanner.nextLine();
		while(temp.contentEquals("GO")) {
			if(temp.isBlank()) {
				continue;
			}
			Matcher matcher1 = pattern1.matcher(temp);
			Matcher matcher2 = pattern1.matcher(temp);
			if(matcher1.group(0).contentEquals(temp)) {
				
				this.append(new EleRequireItem());
			}
		}
	}
}
