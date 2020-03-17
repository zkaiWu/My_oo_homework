package test.masystem1;

import java.text.SimpleDateFormat;
public class IDNum {
    private String IDNum;

    public IDNum(){
    }
    public IDNum(String IDnum){
        if (IDnum.charAt(17)=='x') {
            StringBuilder strBuilder = new StringBuilder(IDnum);
            strBuilder.setCharAt(17, 'X');
            IDnum = strBuilder.toString();
        }
        if (checkIDNum(IDnum)) this.IDNum = IDnum;
    }

    public static boolean checkIDNum(String IDNum) {
        if (IDNum.length()!=18) {
            //System.out.println("长度不对");
            return false;
        }
        boolean result = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = IDNum.substring(6, 14);
        try {
            format.setLenient(false);
            format.parse(date);
        }catch (Exception e){
            result = false;
            //System.out.println("日期不对");
        }
        if (!result) return result;
        int[] w = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
        int sum = 0;
        for (int i=0; i<IDNum.length(); i++) {
            if (!Character.isDigit(IDNum.charAt(i))) {
                if (i==17) {
                    sum = sum + 10*w[i];
                    continue;
                }else {
                    return false;
                }
            }
            sum = sum + (IDNum.charAt(i) - '0') * w[i];
        }
        sum = sum % 11;
        if (sum==1) return true; else {
            //System.out.println("校验码不对");
            return false;
        }
    }

    public String toString() {
        return IDNum;
    }
}
