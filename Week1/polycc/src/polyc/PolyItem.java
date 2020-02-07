package polyc;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolyItem
{
	private String origString;
	private String stringArray[] = new String[60];
	private int exp[] = new int[60];
	
	public PolyItem(String origString) {
		this.origString = origString;
	}
	public String getString() {
		return this.origString;
	}
	public String removeSpace(String ss) {
		String temp = ss.replace(" ","");
		return temp;
	}
	
	public boolean checkString() {
		if(this.origString.isEmpty()) {
			System.out.println("No inputs");              //处理空串
			System.exit(0);
		}
		if(this.origString.charAt(0) == '{') {                //为了正则表达式简便，在前面加入+号
			this.origString = "+" +this.origString;
		}
		
		String tempString = new String();
		int polyNum = 0;
		String pattern ="[+-]\\{(\\([+-]?\\d{1,}\\,\\d{1,}\\)\\,){0,49}\\([+-]?\\d{1,}\\,\\d{1,}\\)\\}";
		Pattern patterner = Pattern.compile(pattern);
		Matcher mat = patterner.matcher(origString);
		while(mat.find()) {
			polyNum++;
			if(polyNum>20) {
				System.out.println("Input Error_1.");
				System.exit(0);
			}
			if(polyNum == 0) {
				tempString = mat.group();
			}
			else {
				tempString = tempString + mat.group();
			}
		}
		if(!tempString.contentEquals(origString)) {
			System.out.println("Input Error_2.");
			System.exit(0);
		}
		return true;
	}
	
	public void Calculate() {
		
		stringArray = this.origString.split("}");
		
		for(int i=0; i<stringArray.length;i++) {
			String tempArray[] = new String[60];
			String tempString = stringArray[i];
			
			char op = tempString.charAt(0);
			tempString = tempString.replaceFirst("[+-]","");
			tempString = tempString.replace("{","");
			tempString = tempString.replace("(","");
			tempString = tempString.replace(")","");
			tempArray = tempString.split(",");
			
			for(int j=0;j<tempArray.length-1;j+=2) {
				int c = Integer.parseInt(tempArray[j]);
				int n = Integer.parseInt(tempArray[j+1]);
				if(op == '+') {
					exp[n] = exp[n]+c;
				}
				else {
					exp[n] = exp[n]-c;
				}
			}
		}
	}
	
	public void print() {
		System.out.print("{");
		for(int i=0; i<60; i++) {
			if(exp[i]!=0) {
				System.out.print("("+exp[i]+","+i+")");
			}
		}
		System.out.print("}");
	}
}
