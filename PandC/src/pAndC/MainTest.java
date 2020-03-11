package pAndC;

public class MainTest {
	public static void main(String args[]) {
		Storage storage = new Storage();
		for(int i=1;i<=3;i++) {
			Consumer consumer = new Consumer(storage,i);
			Producer producer = new Producer(storage,i);
			Thread tp = new Thread(producer);
			Thread tc = new Thread(consumer);
			tp.start();
			tc.start();
		}
	}
}
