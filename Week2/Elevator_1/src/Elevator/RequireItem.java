package Elevator;

import java.util.*;

public class RequireItem {
	
	private String type;     //0为F_R，1为E_R
	private int floor;      //当类型为F_R时，为楼层请求，当类型为E_R时，为目标楼层请求
	private boolean direction;   //1为向上，0为向下
	
	public RequireItem(String type,int n) {
		this.type = type;
		this.floor = n;
	}
	
	public RequireItem(String type,int n,String direction) {
		this(type,n);
		if(type.contentEquals("F_R")) {
			if(direction.contentEquals("UP")) {
				this.direction = true; 
			}
			else if(direction.contentEquals("DOWN")){
				this.direction = false;
			}
		}
	}
	
	public void print() {
		System.out.println(this.type);
		System.out.println(this.floor);
		if(this.type.contentEquals("F_R")) {
			System.out.println(this.direction);
		}
	}
	
	public int getFloor() {
		return this.floor;
	}
	
	public static void main(String args[]) {             
		RequireItem  singleRequire = new RequireItem("F_R",2,"UP");
		singleRequire.print();
		
	}
}
