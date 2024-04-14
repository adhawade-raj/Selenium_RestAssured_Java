package Practise;

class Employee {

	String eid = "aaa";
	String empname= "bbb";
	String eadd="ccc";
	
	public void display_emp_details()
	{
		System.out.println("emp details");
		System.out.println("-----------");
		
		System.out.println(eid);
		System.out.println(empname);
		System.out.println(eadd);
}
 
public static void main(String[] args)
{
	Employee emp = new Employee();
	emp.display_emp_details();
}
}


