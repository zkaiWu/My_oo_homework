package masystem1;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IDNumtest {
	public static void main(String args[]) {
//		Logger.getGlobal().setLevel(Level.OFF);
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String id = scanner.nextLine();
			System.out.println(IDNum.checkIDNum(id));
		}
	}
}
