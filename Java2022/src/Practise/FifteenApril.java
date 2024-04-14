package Practise;

public class FifteenApril 
{
	public class Account
	{
		String AccNo;
		String AccName;
		Account(String ACcNo, String AccName)
		{
			this.AccNo=AccNo;
			this.AccName=AccName;
	}
	}
public class Employee 
{
	String Eid;
	String EName;
	Account Acc;
	Employee(String Eid, String Ename, Account Acc)
	{
		this.Eid=Eid;
		this.EName=Ename;
		this.Acc=Acc;
	}

public void getempdetails()
{
	System.out.println("Emp detals");
	System.out.println("-----------");
	System.out.println("employee id :" +Eid);
	System.out.println("Employee NAme :" +EName);
	System.out.println("Account Details :" +Acc);
	System.out.println();
	System.out.println("-----------");
	System.out.println("Acc name :"+Acc.AccName);
	System.out.println("AccNo :" +Acc.AccNo);
}
}
		
		public static void main(String[] args)
	{
			
			Account  acc=new Account("abc","123");
			Employee emp=new Employee("Raj", "121", acc);
			emp.getempdetails();
		
		
		}
		
		{
			
		}

}
