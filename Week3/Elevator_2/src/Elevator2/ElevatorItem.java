package Elevator2;
import java.util.*;

public class ElevatorItem {
	private int num;            //电梯的编号
	private int currentFloor;
	private int currentDir;     //当前方向，1为上，-1为下，0为停止
	private ArrayList<String> reList;
	private int[] button;            //表示按钮状态，1表示按下，0表示没按
	private int currentMaxFloor;     //向上的最大楼层
	private int currentMinFloor;     //向下的最小楼层
	
	public ElevatorItem(int num) {
		this.num = num;
		this.currentFloor = 1;
		this.currentDir = 0; //初始状态为停止
		reList = new ArrayList<String> ();
		reList.clear();
		button = new int[7];
	}
	
	public void setDir(int dir) {
		this.currentDir = dir;
	}
	public int getDir() {
		return this.currentDir;
	}
	public int getFloor() {
		return this.currentFloor;
	}
	
	public void setMaxFloor(int floorNum) {
		if(floorNum>this.currentMaxFloor) {
			this.currentMaxFloor=floorNum;
		}
	}
	
	public void setButton(int index,int state) {
		this.button[index] = state;
	}
	public boolean noPress() {              //判断是否没有按钮按下
		for(int i=1;i<=6;i++) {
			if(button[i]==1) return false;
		}
		return true;
	}
	public boolean isPress(int index) {
		if(button[index]==1) return true;
		else return false;
	}
	
	
	public boolean goUpOne() {              //电梯向上并且判断是否到达楼顶
		if(this.currentFloor>=6) {
			return false;
		}
		else {
			currentFloor++;
			return true;
		}
	}
	public boolean goDownOne() {          //电梯向下并且判断是否达到楼顶
		if(this.currentFloor<=1) {
			return false;
		}
		else {
			currentFloor--;
			return true;
		}
	}
	
	
	
	public void recorder(int floor,int dir,String state) {
		String temp = "";
		temp = temp+"("+Integer.toString(floor)+",";
		if(dir == 1) {
			temp = temp+"UP"+",";
		}
		else {
			temp = temp+"DOWN"+",";
		}
		temp = temp+state+")";
		reList.add(temp);
	}
	
	
}
