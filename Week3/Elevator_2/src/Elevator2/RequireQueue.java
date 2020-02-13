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
	
	public RequireItem deQueue() {
		RequireItem temp = reQueue.get(0);
		reQueue.remove(0);
		return temp;
	}
	
	public int size() {
		return this.reQueue.size();
	}
	
	public RequireItem requireCheck(int getTime,int eleState) {             //在队列里查找看是否请求符合当先的电梯状态，若有
		RequireItem temp = null;											//多个相同的请求，返回一个并将其他相同请求删除，来达到去重效果
		for(int i=0;i<reQueue.size();i++) {
			if(reQueue.get(i).getGotTime()==getTime&&eleState==reQueue.get(i).getEleState()) {
				temp = reQueue.get(i);
				reQueue.remove(i);   
				i--;
			}
		}
		return temp;
	}
	
	public void getRequireQueue() {
		
		Scanner scanner = new Scanner(System.in);
		Pattern pattern1 = Pattern.compile("");         //楼层请求
		Pattern pattern2 = Pattern.compile("");         //电梯按键请求
		
		String temp;
		temp = scanner.nextLine();
		String []tempString  = new String[6];
		while(!temp.contentEquals("GO")) {
			if(temp.isBlank()) {
				continue;
			}
			
			Matcher matcher1 = pattern1.matcher(temp);
			Matcher matcher2 = pattern2.matcher(temp);
			temp = temp.replace("(", "");
			temp = temp.replace(")", "");
			temp = temp.replace("UP", "1");
			temp = temp.replace("DOWN", "-1");
			temp = temp.replace("IDLE","0");
			tempString = temp.split(",");
			
			if(matcher1.groupCount()==0&&matcher2.groupCount()==0) {
				System.out.println("Error Input");
			}
			else if(matcher1.group(0).contentEquals(temp)) {
				if(tempString[1].contentEquals("1")&&tempString[2].contentEquals("-1")) {
					System.out.println("Floor 1 Haven't Down Button");
				}
				else if(tempString[1].contentEquals("6")&&tempString[2].contentEquals("1")) {
					System.out.println("Floor 6 Haven't Up Button");
				}
				else {
					this.append(new FloorRequireItem(Integer.parseInt(tempString[1]),Integer.parseInt(tempString[2]),
								Integer.parseInt(tempString[3]),Integer.parseInt(tempString[4])));
				}
			}
			else if(matcher2.group(0).contentEquals(temp)) {
				this.append(new EleRequireItem(Integer.parseInt(tempString[1]),Integer.parseInt(tempString[2]),
							Integer.parseInt(tempString[3])));
			}
			temp = scanner.nextLine();
		}
		scanner.close();
	}
	
	public static void main(String []args) {                //test RequireQueue
		RequireQueue reQ = new RequireQueue();
		reQ.getRequireQueue();
	}
}
