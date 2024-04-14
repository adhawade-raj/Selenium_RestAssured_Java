package Practise;

public class Mock1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		String s= "mom";
		int x=s.length();
		String rev="";
		for(int i=(x-1); i>=0; i--)
		{
			rev=rev+s.charAt(i);
		}
		System.out.println("original " +s);
		System.out.println("palandrome " +rev);
		System.out.println("----------------");
			
		if(s.equals(rev))
		{
			System.out.println("palandrome");
		}
		else
		{
			System.out.println("not palandrome ");
		}
	}

}
