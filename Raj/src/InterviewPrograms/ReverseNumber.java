package InterviewPrograms;

public class ReverseNumber {

	public static void main(String[] args) {
		int number =24626;
		int temp,reverse=0;
		int a=number;
		
		//By Using StringBufer
		StringBuffer sb= new StringBuffer(String.valueOf(a));
		StringBuffer b=sb.reverse();
		System.out.println(b);
		
		//Wrong Output
		System.out.println("---------------");
		while(number>0)
		{
			temp=number%10;
			reverse=number*10+temp;
			number=number/10;
		}
		System.out.println(reverse);
	}

}
