package ReverseOfString;

public class Palndrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String a="nayan";
		int b=a.length();
		
		String rev="";
		
		for(int i=(b-1); i>=0; i--)
		{
			rev=rev+a.charAt(i);
		}
		System.out.println("original string is " +a);
		System.out.println(("reverse string is " +rev));
		
		if(a.equals(rev))
		{
		System.out.println("string is palndrome");	
		}
		else
		{
			System.out.println("string is not palandrome");
		}
	}
}
