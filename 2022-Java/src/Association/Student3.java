package Association;

public class Student3 {
	private String sname;
	private String sid;
	private String sadd;
	
	private Course1 courses;

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSadd() {
		return sadd;
	}

	public void setSadd(String sadd) {
		this.sadd = sadd;
	}

	public Course1 getCourses() {
		return courses;
	}

	public void setcourses(Course1 courses) {
		this.courses = courses;
	}

public void getstudentdetails()
{
	System.out.println("Student Details");
	System.out.println("---------------");
	System.out.println("stduent id :" +sid +"\t");
	System.out.println("student Name :"+sname +"\t");
	System.out.println("Student Adress :"+sadd +"\t");
System.out.println("---------------------------------");
	System.out.println("--------------------");
	System.out.println("CID :" +courses.getCid() +"\t");
	System.out.println("CNAME :" +courses.getCname() +"\t");
	System.out.println("tCCOST"+courses.getCcost() +"\t");
	System.out.println("---------------------");
 

System.out.println();
	  
	

}
}
