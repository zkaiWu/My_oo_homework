package Elevator2;

public class FloorItem {
	private int upButton;
	private int downButton;                //为楼层添加按钮，1为按下，0为没按
	
	
	public FloorItem() {
		this.upButton = 0;
		this.downButton = 0;
	}
	
	public void SetUpButton(int state) {
		this.upButton = state;
	}
	public int GetUpButton(){
		return this.upButton;
	}
	
	
	public void SetDownButton(int state) {
		this.downButton = state;
	}
	public int GetDownButton() {
		return this.downButton;
	}
	
	
	
}
