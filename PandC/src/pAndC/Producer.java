package pAndC;

public class Producer implements Runnable{
	private Storage storage;
	private int id;
	public Producer(Storage temp,int id) {
		this.storage = temp;
		this.id = id;
	}
	
	public void run() {
		for(int i=1;i<=5;i++) {
			storage.add(i);
			System.out.println("Producer "+this.id+" produce"+":"+i);
		}
	}
}
