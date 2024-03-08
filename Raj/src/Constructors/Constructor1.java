package Constructors;

class Constructor1 {
String eid;
String Eadd;
String Ename;

	Constructor1(String e_id, String E_add, String E_name)
	{
	eid=e_id;
	Eadd=E_add;
	Ename=E_name;
	}
	
	public void getempdetails()
	{
		System.out.println("Employee details");
		System.out.println("----------------");
		System.out.println(eid);
		System.out.println(Eadd);
		System.out.println(Ename);
	}


	public static void main(String[] args)
	{
		Constructor1 emp1=new Constructor1("E-11", "Pune", "Raj");
		emp1.getempdetails();
		System.out.println();
		
		Constructor1 emp2=new Constructor1("E-12", "Pun", "Sank");
		emp2.getempdetails();
		System.out.println();
	}
}