package Elevator;
import java.util.*;

public class RequireQueue {
	
	private ArrayList<RequireItem> requirementsList;
	
	public RequireQueue() {
		requirementsList = new ArrayList<RequireItem>();
		this.requirementsList.clear();
	}
	
	public void append(RequireItem temp) {
		requirementsList.add(temp);
		return;
	}
	
	public RequireItem dequeue() {
		RequireItem temp = requirementsList.get(0);
		requirementsList.remove(0);
		return temp;
	}
	
	public boolean isEmpty() {
		return requirementsList.isEmpty();
	}
	
	
	private boolean checkString(String temp) {           //用正则表达式判断输入是否合法      (待补全）
		return true;
	}
	
	public void getRequireQueue() {                          //获取字符串并做判断输入格式以及处理，将其变为RequireItem;
		
		System.out.println("Please input your reQuirements,end with 'GO'...");
		
		Scanner scanner = new Scanner(System.in);
		String temp;
		temp = scanner.nextLine();
		String []tempArray = new String[3]; 
		
		while(!temp.contentEquals("GO")) {
			if(!checkString(temp)) {
				System.out.println("INPUT ERROR");
				System.exit(0);
			}
			
			temp = temp.replace("(","");
			temp = temp.replace(")","");
			tempArray = temp.split(",");
			
			if(tempArray[0].contentEquals("E_R")) {
				requirementsList.add(new RequireItem(tempArray[0],Integer.parseInt(tempArray[1])));
			}
			else{
				int floorNum = Integer.parseInt(tempArray[1]);
				if(floorNum==1&&tempArray[2].contentEquals("DOWN")) {                 //判断是否存在1楼向下以及六楼向上的情况
					System.out.println("floor 1 have not DOWN button");
					temp = scanner.nextLine();
					continue;
				}
				else if(floorNum==6&&tempArray[2].contentEquals("UP")) {
					System.out.println("floor 6 have not UP button");
					temp = scanner.nextLine();
					continue;
				}
				requirementsList.add(new RequireItem(tempArray[0],Integer.parseInt(tempArray[1]),tempArray[2]));
			}
			
			System.out.println(tempArray[0]+"___"+tempArray[1]+"___");
			if(tempArray[0].contentEquals("F_R")) {
				System.out.println(tempArray[2]);
			}
			
			temp = scanner.nextLine();
		}
		return;
	}
	
	public static void main(String args[]) {                   //测试队列
		RequireQueue reQueue  =new RequireQueue();
		reQueue.append(new RequireItem("F_R",2,"UP"));
		reQueue.append(new RequireItem("E_R",2));
		reQueue.append(new RequireItem("E_R",3));
		reQueue.append(new RequireItem("F_R",5,"DOWN"));
		while(!reQueue.isEmpty()) {
			RequireItem temp = reQueue.dequeue();
			temp.print();
		}
	}
	
	
	
}
