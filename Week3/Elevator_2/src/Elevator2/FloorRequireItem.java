package Elevator2;

public class FloorRequireItem extends RequireItem{
	private int upOrDown;   //up为1，Down为-1
	
	public FloorRequireItem(int targetFloor,int upOrDown,int gotTime,int eleState) {
		this.targetFloor = targetFloor;
		this.upOrDown = upOrDown;
		this.gotTime = gotTime;
		this.eleState = eleState;
	}
	
	public int getUpOrDown() {
		return this.upOrDown;
	}
	
}
