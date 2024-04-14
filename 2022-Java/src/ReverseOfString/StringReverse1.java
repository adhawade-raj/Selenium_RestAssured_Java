package ReverseOfString;

public class StringReverse1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String a="velocity";
	    int b=a.length();
	    String rev="";
	
	for(int i=(b-1); i>=0; i--)
	{
		rev=rev+a.charAt(i);
	}
System.out.println("reverse is"+rev);	
	}

}

