package personsystem;

import java.text.SimpleDateFormat;

public class Teacher extends Person{
	
	private String TID;
	
	public Teacher() {
		super();
		TID = new String();
	}
	
	public void setTID(String TID) {
		this.TID = TID;
	}
	public String getTID() {
		return this.TID;
	}
	
	
	
	public static boolean checkTID(String TID) {
		return TID.matches("[0-9]{5}");
	}
	
	
	/**
	 * 用了一个比较笨的方法创建实例
	 * @param name
	 * @param id
	 * @param TID
	 * @return
	 * @throws PersonException
	 */
	public static Teacher newInstance(String name,String id,String TID) throws PersonException{
		
		//在创建person方法的时候有对name和id进行判断
		Person p = Person.newPerson(name,id);
		Teacher tea = new Teacher();
		tea.setName(p.getName());
		tea.setId(p.getId());
		tea.setBirthday(p.getBirthday());
		tea.setSex(p.getSex());
		
		//对教师号进行判断
		if(Teacher.checkTID(TID)==false) {
			throw new PersonException(PersonErrorCode.TID_ILLEGAL_ERROR);
		}
		tea.setTID(TID);
		return tea;
	}
	
	@Override
	public String toString() {
		StringBuilder ss = new StringBuilder("");
		ss.append("Name:"+this.getName()+"\n");
		ss.append("IDNum:"+this.getId()+"\n");
		ss.append("TID:"+this.getTID()+"\n");
		String sex = this.getSex()==0 ? "F":"M";
		ss.append("Sex:"+sex+"\n");
		ss.append("Birthday:"+new SimpleDateFormat("yyyy/MM/dd").format(this.getBirthday()));
		return ss.toString();
	}
	
	
	/*
	 * 小测试
	 */
	public static void main(String[] args) {
		try {
			Teacher stu = Teacher.newInstance("wzk","460103199811291210","12345");
			System.out.println(stu);
		}catch (PersonException e) {
			System.out.println(e.getCodeDescription());
		}
	}
}
