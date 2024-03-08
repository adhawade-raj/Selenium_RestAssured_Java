package InterviewPrograms;

public class ReverseString {

	public static void main(String[] args) {

		String s= "MOM1";
		String reverse="";
		char[] a =s.toCharArray();
		
		System.out.println("Given String is:" +s);
		
		for(int i=s.length()-1; i>0; i--)
		{
			reverse=reverse+a[i];
		}
		System.out.println(reverse+"Revsre is:");
		
		System.out.println("-------------");
		StringBuffer sb =new StringBuffer(String.valueOf(s));
		
		StringBuffer b=sb.reverse();
		System.out.println(b);
		
		
		
		
	}

}
