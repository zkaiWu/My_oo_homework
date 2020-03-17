package test.masystem1;

import java.util.*;
import java.io.*;

public class DataGenerator {
	
	private static final int bit = 17;
	private static Random rand = new java.util.Random();
	
	public static ArrayList<String> generator(int maxNum) {
		ArrayList<String> list = new ArrayList<>();
		for(int i=1;i<=maxNum;i++) {
			
			String temp;
			long nn = Math.round((rand.nextDouble() * Math.pow(10, bit)) - 0.5);
			temp = String.valueOf(nn);
			System.out.println("nn is:"+nn);
//			nn = (long)(Math.random()*11);
//			System.out.println("nn is:"+nn);
//			if(nn == 10) {
//				temp = temp + "X";
//			}else {
//				temp = temp + String.valueOf(nn);
//			}
			temp += "X";
			
			System.out.println(temp);
			list.add(temp);
			
		}
		
		return list;
	}
	
	public static void generateData(int maxNum) {
		
		String filePath = "/Users/wzk1998/Desktop/AllCode/My_oo_homework/Homework1/masystem1/src/test/masystem1/";
		String fileName = "data.csv";
		File csvFile = null ;
		BufferedWriter csvWriter = null;
		ArrayList<String> idList = DataGenerator.generator(maxNum);
		
		try {
			csvFile = new File(filePath+fileName);
			csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);
			for(String s: idList) {
				csvWriter.write(s);
				csvWriter.newLine();
			}
			csvWriter.flush();
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}finally {
			try {
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
		
	}
	
	public static void main(String args[]) {
		DataGenerator.generateData(1000);
	}
}
