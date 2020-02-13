package Elevator2;

public abstract class Simulator {
	protected ElevatorItem elevator1;
	protected FloorItem []floorList;
	protected RequireQueue reQ;
	
	public Simulator() {
		elevator1 = new ElevatorItem(1);
		floorList = new FloorItem[7];
		reQ = new RequireQueue();
	}
	
	public abstract void simulate();
}
