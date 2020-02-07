package Elevator;

public class FloorItem {
	
	private int floorNum;       //楼层数
	private boolean isUp;       //是否向上的请求
	private boolean isDown;      //是否是向下的请求
	
	public FloorItem(int num) {
		this.floorNum = num;
		this.isUp = false;
		this.isDown = false;
	}
	
	public void receiveRequired(int upOrDown,boolean state) {
		if(upOrDown == 1) this.isUp = state ;
		if(upOrDown == 0) this.isDown = state;
	}
}
