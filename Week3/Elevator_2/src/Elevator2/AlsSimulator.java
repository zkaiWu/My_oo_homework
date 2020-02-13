package Elevator2;

import java.math.*;

public class AlsSimulator extends Simulator{
	
	public boolean noFloorPress() {
		for(int i=1;i<=6;i++) {
			if(!this.floorList[i].noPress()) {
				return false;
			}
		}
		return true;
	}
	
	public int getEleMaxFloor() {
		int mmax=1;
		for(int i=1;i<=6;i++) {
			if(!this.floorList[i].noPress()) {
				mmax = Math.max(mmax,i);
			}
			else if(this.elevator1.isPress(i)) {
				mmax = Math.max(mmax,i);
			}
		}
		return mmax;
	}
	public int getEleMinFloor() {
		int mmin=6;
		for(int i=6;i>=1;i--) {
			if(!this.floorList[i].noPress()) {
				mmin = Math.min(mmin,i);
			}
			else if(this.elevator1.isPress(i)) {
				mmin = Math.min(mmin,i);
			}
		}
		return mmin;
	}
	
	
	public void execute(RequireItem temp) {
		if(temp instanceof EleRequireItem) {
			this.elevator1.setButton(temp.targetFloor, 1);    
		}
		else if(temp instanceof FloorRequireItem) {
			if(((FloorRequireItem) temp).getUpOrDown()==1)
				this.floorList[temp.targetFloor].setUpButton(1);
			else if(((FloorRequireItem) temp).getUpOrDown()==-1) {
				this.floorList[temp.targetFloor].setDownButton(1);
			}
		}
	}
	
	
	public void simulate() {
		this.reQ.getRequireQueue();         //获取指令
		
		while(this.reQ.size()==0&&this.elevator1.getDir()==0) {            //队列里没有请求且电梯空闲
			int maxFloor = getEleMaxFloor();
			int minFloor = getEleMinFloor();
			if(this.elevator1.getDir()==1) {                    //向上
				while(this.elevator1.getFloor()<Math.min(maxFloor,6)) {
					int nowFloor = this.elevator1.getFloor();
					maxFloor = getEleMaxFloor();
					this.elevator1.goUpOne();
					
					if(this.floorList[this.elevator1.getFloor()].getUpButton()==1||this.elevator1.isPress(this.elevator1.getFloor())) {
						this.elevator1.recorder(this.elevator1.getFloor(), this.elevator1.getDir(), "S");
					}else {
						this.elevator1.recorder(this.elevator1.getFloor(), this.elevator1.getDir(), "p");
					}
					this.elevator1.setButton(this.elevator1.getFloor(),0);           //在电梯到达新的楼层时，熄灭目标楼层的按钮
					this.floorList[this.elevator1.getFloor()].setDownButton(0);
					this.floorList[this.elevator1.getFloor()].setUpButton(0);
					
					RequireItem temp = reQ.requireCheck(nowFloor, 1);                //获取符合要求的请求
					if(temp!=null) {                                   //执行请求
						execute(temp);
					}
				}
			}
			
			else if(this.elevator1.getDir()==-1) {
				while(this.elevator1.getFloor()>Math.max(minFloor,1)) {
					int nowFloor = this.elevator1.getFloor();
					minFloor = getEleMinFloor();
					this.elevator1.goDownOne();
					
					if(this.floorList[this.elevator1.getFloor()].getDownButton()==1||this.elevator1.isPress(this.elevator1.getFloor())) {
						this.elevator1.recorder(this.elevator1.getFloor(), this.elevator1.getDir(), "S");
					}else {
						this.elevator1.recorder(this.elevator1.getFloor(), this.elevator1.getDir(), "p");
					}
					this.elevator1.setButton(this.elevator1.getFloor(),0);           //在电梯到达新的楼层时，熄灭目标楼层的按钮
					this.floorList[this.elevator1.getFloor()].setDownButton(0);
					this.floorList[this.elevator1.getFloor()].setUpButton(0);
					
					RequireItem temp = reQ.requireCheck(nowFloor, -1);                //获取符合要求的请求
					if(temp!=null) {                                   //执行请求
						execute(temp);
					}
				}
			}
			
			else if(this.elevator1.getDir()==0) {              //空闲
				RequireItem temp = reQ.deQueue();
				if(temp!=null) this.execute(temp);
				if(this.floorList[this.elevator1.getFloor()].getUpButton()==1) {             //取得
					this.floorList[this.elevator1.getFloor()].setUpButton(0);
				}
				if(this.floorList[this.elevator1.getFloor()].getDownButton()==1) {
					this.floorList[this.elevator1.getFloor()].setDownButton(0);
				}
				int targetFloor = temp.getTargetFloor();
				if(targetFloor>this.elevator1.getFloor()) this.elevator1.setDir(1);
				else if(targetFloor<this.elevator1.getFloor()) this.elevator1.setDir(-1);
			}
		}
	}
}
