package Elevator2;

import java.util.*;

public class MainTest {
	public static void main(String []args) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(2);
		arr.add(2);
		arr.add(2);
		arr.add(2);
		int count = 0;
		for(int i=0;i<arr.size();i++) {
			if(arr.get(i)==2) {
				count++;
				arr.remove(i);
				i--;
			}
		}
		System.out.print(count);
	}
}
