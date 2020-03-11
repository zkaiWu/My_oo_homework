package pAndC;

public class Consumer implements Runnable{
	private Storage storage;
	private int id;
	
	public Consumer(Storage temp,int id) {
		this.storage = temp;
		this.id = id;
	}
	
	public void run() {
		for(int i=1;i<=5;i++) {
			int value = storage.get();
			System.out.println("Consumer "+this.id+" consumed"+":"+value);
		}
	}
}
