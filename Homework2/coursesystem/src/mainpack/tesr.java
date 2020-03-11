package mainpack;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class tesr {
	public static void main(String args[]) {
		
		Logger.getGlobal().setLevel(Level.OFF);
		ArrayList<Course> list = new ArrayList<>();
		Course a = new Course();
		
		a.setCid("aaa");
		a.setCourseName("c1");
		a.setMaxContent(10);
		
		Course b = new Course();
		
		b.setCid("bbb");
		a.setCourseName("c1");
		a.setMaxContent(10);
		
		list.add(b);
		list.add(a);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
		}
		
		Collections.sort(list);

		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
		}
		
	}
}
