package Practise;

public class Account {

	private String accNo;
	private String accHolderName;

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccHolderName() {
		return accHolderName;
	}

	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

	public class employee{
		private int eno;
		private String enmae;
		private Account acc;
		public int getEno() {
			return eno;
		}
		public void setEno(int eno) {
			this.eno = eno;
		}
		public String getEnmae() {
			return enmae;
		}
		public void setEnmae(String enmae) {
			this.enmae = enmae;
		}
		public Account getAcc() {
			return acc;
		}
		public void setAcc(Account acc) {
			this.acc = acc;
		}
		
		public void getEmpDetails()
		{
			System.out.println("Emp Details");
			System.out.println("-----------");
			System.out.println(" Emp no :" +eno);
			System.out.println(" Emp ename :" +enmae);
			System.out.println();
			System.out.println("Acc Details");
			System.out.println("------------");
			System.out.println("acc Name :" +acc.getAccHolderName());
			System.out.println("Acc no :" +acc.getAccNo());
		}
	}

	public static void main(String[] args)
	{
		Account acc=new Account();
		acc.setAccHolderName("abc");
		acc.setAccNo("123");
		
		Employee emp=new Employee();
	
		emp.display_emp_details();
		
	}

}
