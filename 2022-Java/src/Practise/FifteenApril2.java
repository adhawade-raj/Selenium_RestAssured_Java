package Practise;

public class FifteenApril2 {

	
	
	String eid;
	 String ename;
	 String eadd;
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEadd() {
		return eadd;
	}
	public void setEadd(String eadd) {
		this.eadd = eadd;
	}
	 
	public static void main(String[] args)
{
		
		FifteenApril2 emp=new	FifteenApril2();
		emp.setEname("e-111");
		emp.setEid("21");
		emp.setEadd("Pune");
		
		System.out.println("emp details");
		System.out.println("------------");
		System.out.println(emp.getEname());
		System.out.println(emp.getEid());
		System.out.println(emp.getEadd());
}
	
}
