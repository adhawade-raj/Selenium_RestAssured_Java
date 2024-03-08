package Practise;

public class tenthApril3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String a="nayan";
		int b=a.length();
		
		String rev="";
		
		for(int i=(b-1); i>=0; i--)
		{
			rev=rev+a.charAt(i);
		}
		System.out.println("original " +a);
		System.out.println("reverse " +rev);
		
		if(a.equals(rev))
		{
			System.out.println("string is pal");
		}
		else
		{
		System.out.println("string iis not pal");
		}
		
		
		
	}

}
