package pAndC;

import java.util.*;

public class Storage {
	private ArrayList<Integer> list;
	
	private int mmax=7;
	public Storage() {
		list = new ArrayList<Integer>();
		list.clear();
		this.mmax = 7;
	}
	public synchronized void add(int num) {
		while(list.size()>=7) {
			try {
				this.wait();
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		list.add(num);
		System.out.println("add"+list.size());
		this.notifyAll();
		return ;
	}
	
	public synchronized int get() {
		while(list.isEmpty()) {
			try {
				this.wait();
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		int temp = list.get(0);
		list.remove(0);
		System.out.println("get"+list.size());
		this.notifyAll();
		return temp;
	}
}
