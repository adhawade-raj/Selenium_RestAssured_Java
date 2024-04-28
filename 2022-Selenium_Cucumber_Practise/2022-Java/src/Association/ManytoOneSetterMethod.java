package Association;

public class ManytoOneSetterMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Branch2 branch=new Branch2();
		branch.setbid("121");
		branch.setbname("mech");
		
Student2 std1=new Student2();
std1.setsid("1");
std1.setsname("Raj");
std1.setsadd("pune");
std1.setbranch(branch);
std1.getstudentdetails();
System.out.println();


		






	}

}
