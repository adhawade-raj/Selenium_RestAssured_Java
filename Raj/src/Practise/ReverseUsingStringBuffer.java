package Practise;

public class ReverseUsingStringBuffer {

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		String str1="mom";
		StringBuffer sb= new StringBuffer(str1);
		
		System.out.println(sb);
		System.out.println("-------------------");
		StringBuffer sb2=sb.reverse();
		
		System.out.println(sb2);
		System.out.println("-------------------");
		
		
		
		if(str1.equals(sb2))
				{
			System.out.println("palndrome");
				}
		else
		{
			System.out.println("not");
		}
	}

}
