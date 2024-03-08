package ReverseOfString;

public class Reverse2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	String a="121";
	int b=a.length();
	String rev="";
	
	
	for(int i=(b-1); i>=0; i--)
	{
		rev=rev+a.charAt(i);
	}
	
	
		System.out.println(rev);	
	}

}
