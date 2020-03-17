package masystem1;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonTest {
	public static void main(String args[]) {
		
		Logger.getGlobal().setLevel(Level.OFF);
		
		if(args[0].contentEquals("cid")) {
			if(args.length!=2) {
				System.out.println("Input error");
				return ;
			}
			else {
				System.out.println(IDNum.checkIDNum(args[1]));
				return ;
			}
		}
		else if(args[0].contentEquals("np")) {
			if(args.length!=3) {
				System.out.println("Input error");
				return ;
			}
			Person person = Person.newPerson(args[1],args[2]);
			if(person == null) {
				System.out.println("ID illegal");
				return;
			}
			else {
				System.out.println(person);
				return;
			}
		}
		else {
			System.out.println("Input error");
			return;
		}
		
	}
}
