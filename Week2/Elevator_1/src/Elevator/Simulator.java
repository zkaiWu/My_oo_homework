package Elevator;
import java.util.Scanner;
import java.util.*;

public class Simulator {
	
	private ElevatorItem elavator;
	private ArrayList<FloorItem> floorList;
	private RequireQueue reQueue;
	private ArrayList<String> re; 
	
	public Simulator() {
		elavator = new ElevatorItem(1);
		floorList = new ArrayList<FloorItem>();
		floorList.clear();
		for(int i=1;i<=6;i++) {
			floorList.add(new FloorItem(i));
		}
		reQueue = new RequireQueue();
	}
	
	public void solve() {                  //获取请求队列并且解决
		
		System.out.println("Solving...");
		
		reQueue.getRequireQueue();
		
		while(!reQueue.isEmpty()) {
			RequireItem tempRequire = reQueue.dequeue();
			int targetFloor = tempRequire.getFloor();
			elavator.movingFloor(targetFloor);
		}
		
		elavator.printRecord();
	}
	
	
	public static void main(String agrs[]) {
		Simulator simulator = new Simulator();
		System.out.println("Program ended");
	}
}
