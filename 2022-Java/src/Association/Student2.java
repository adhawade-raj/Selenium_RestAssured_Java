package Association;

public class Student2 {

	private String sname;
	private String sid;
	private String sadd;
	
	private Branch2 branch;
	
	public String getsname()
	{
		return sname;
	}
	public void setsname(String sname)
	{
		this.sname=sname;
	}
	public String getsid()
	{
		return sid;
	}
	public void setsid(String sid)
	{
		this.sid=sid;
	}
	public String sadd()
	{
		return sadd;
	}
	public void setsadd(String sadd)
	{
		this.sadd=sadd;
	}
	public Branch2 getbranch()
	{
		return branch;
	}
	public void setbranch(Branch2 branch)
	{
		this.branch=branch;
	}


public void getstudentdetails() {
System.out.println("Student Details");
System.out.println("---------------");
System.out.println("stduent id :" +sid);
System.out.println("student Name :"+sname);
System.out.println("Student Adress :"+sadd);
System.out.println("---------------------");
System.out.println("Branch id :"+branch.getbid());
System.out.println("Branch name :"+branch.getbname());

}
}


























