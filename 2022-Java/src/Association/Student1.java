package Association;

public class Student1 {

	String sid;
	String sname;
	String add;
	Branch1 branch;
	Student1(String sid, String sname, String add, Branch1 branch)
	{
		this.sid=sid;
		this.sname=sname;
		this.add=sname;
		this.branch=branch;
	}
	public void getStudentDetails()
	{
		System.out.println("std details");
		System.out.println("------------");
		System.out.println("student id :"+sid);
		System.out.println("Student name :" +sname);
		System.out.println("student address : "+add);
		System.out.println("-----------------------");
		
		System.out.println("branch id :"+branch.bid);
		System.out.println("Branch Name :"+branch.bname);
	}
	
	
	
}
