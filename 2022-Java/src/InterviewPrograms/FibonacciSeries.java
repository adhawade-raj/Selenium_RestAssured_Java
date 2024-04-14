package InterviewPrograms;

public class FibonacciSeries {

	public static void main(String[] args) {

		int num1=1;
		int num2=1;
		int count=10;
		int num = 0;
		
		System.out.print(num1+" "+num2+" ");
		
		for(int i=2; i<=count; i++)
		{
			num = num1+num2;
			num1=num2;
			num2=num;
			System.out.print(num +" ");
		}
		
	}

}
