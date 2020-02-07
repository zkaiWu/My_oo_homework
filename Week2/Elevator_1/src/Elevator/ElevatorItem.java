package Elevator;

import java.util.*;

public class ElevatorItem {
	
	private String direction;                
	private int currentFloor;
	private int targetFloor;
	private final int maxFloor = 6;           //最大楼层为6
	private ArrayList<String> answerList;
	
	public ElevatorItem(int currentFloor) {
		this.direction = "UP";
		this.currentFloor = 1;
		this.targetFloor = 1;
		this.answerList = new ArrayList<String>();
		this.answerList.clear();
	}
	
	public void movingFloor(int target) {
		this.targetFloor = target;
		int addIndex = 1;
		if(this.targetFloor == this.currentFloor) return;
		if(this.targetFloor > this.currentFloor) {
			this.direction = "UP";
			
		}
		if(this.targetFloor < this.currentFloor) {
			this.direction = "DOWN";
			addIndex = -1;
		}
		for(int i=this.currentFloor+addIndex;i*addIndex<target*addIndex;i+=addIndex) {     //移动电梯并记录答案
			
			String tempString =new String();
			
			tempString = tempString + "(";
			tempString = tempString + Integer.toString(i)+",";
			tempString = tempString + this.direction+",";
			tempString = tempString + "P";
			tempString = tempString + ")";
			
			this.answerList.add(tempString);
		}
		this.answerList.add("("+Integer.toString(target)+","+this.direction+","+"s)");
		
		this.currentFloor = this.targetFloor;                      //更新当前的楼层
		return ;
	}
	
	public void printRecord() {
		for(String answerString : this.answerList) {
			System.out.println(answerString);
		}
	}
	
}