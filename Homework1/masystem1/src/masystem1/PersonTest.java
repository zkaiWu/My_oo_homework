package masystem1;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonTest {
	public static void main(String args[]) {
		
		Logger.getGlobal().setLevel(Level.OFF);
		
		Scanner scanner = new Scanner(System.in);
		String[] require = new String[3];
		
		while(scanner.hasNext()) {
			
			String temp = scanner.nextLine();
			temp = temp.replaceAll("\\s+"," ");
			require = temp.split(" ");
			for(int i=0;i<require.length;i++) {
				Logger.getGlobal().info("require "+i+" = "+require[i]);
			}

			if(require[0].contentEquals("cid")) {
				System.out.println(IDNum.checkIDNum(require[1]));
			}
			else if(require[0].contentEquals("np")) {
				Person person = Person.newPerson(require[1],require[2]);
				if(person == null) {
					System.out.println("ID illegal");
				}
				else {
					System.out.println(person);
				}
			}
			else {
				System.out.println("input error");
			}
		}
	}
}
