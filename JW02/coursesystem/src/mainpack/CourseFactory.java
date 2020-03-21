package mainpack;

import java.util.ArrayList;
import java.util.Arrays;

public class CourseFactory {
	
	public static Course getNewCourse(String cid,String name,String teachersName,String maxContent) throws InputErrorException{
		/*
		 * 真正的工厂模式
		 * 将字符串信息打包成Course类型
		 * 感觉会有BUG   ฅ(๑ ̀ㅅ ́๑)ฅ
		 */
		
		Course c = new Course();
		c.setCid(cid);
		c.setCourseName(name);
		
		int len = teachersName.length();
		
		
		
		//去除'['以及']';
		if(teachersName.charAt(0)!='['||teachersName.charAt(len-1)!=']') {
			throw new InputErrorException();
		}
		teachersName = teachersName.substring(1,len);
		len = teachersName.length();
		teachersName = teachersName.substring(0,len-1);
		
		
		String[] temp = teachersName.split(",");
		for(int i=0;i<temp.length;i++) {
			temp[i] = temp[i].replaceFirst("\\s+", "");
		}
		ArrayList<String> nameList = new ArrayList<>();
		nameList.addAll(Arrays.asList(temp));
		c.setTeachersName(nameList);
		
		try {
			int mc = Integer.parseInt(maxContent);
			c.setMaxContent(mc);
		}catch(NumberFormatException ex){
			throw new InputErrorException();
		}
		
		return c;
	}

}
