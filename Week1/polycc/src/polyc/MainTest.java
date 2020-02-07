package polyc;
import PolyItem;
import java.util.*;
import java.util.Scanner;;

public class MainTest {
	public static void main(String args[]) {
		try {
			Scanner scanner = new Scanner(System.in);
			PolyItem poly = new PolyItem(scanner.nextLine());
			poly.checkString();
			poly.Calculate();
			poly.print();
			scanner.close();
		} catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
