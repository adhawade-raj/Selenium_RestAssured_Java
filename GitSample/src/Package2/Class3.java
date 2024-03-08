package Package2;

public class Class3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String a="mom";
		int x=a.length();
		String rev="";
		
		for(int i=(x-1); i>=0; i--)
		{
			rev=rev+a.charAt(i);
		}
		System.out.println("original");
		System.out.println("palandrome");
		System.out.println("-----------");
		
		if(a.equals(rev))
		{
			System.out.println("palandrome");
		}
		else
		{
			System.out.println("not palnadrome");
		}
				
		
		
	}

}
