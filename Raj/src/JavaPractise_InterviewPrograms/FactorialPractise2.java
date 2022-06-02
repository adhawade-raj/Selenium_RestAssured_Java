package JavaPractise_InterviewPrograms;

public class FactorialPractise2 {

	public static void main(String[] args) {

		int number =5;
		int factorial = 1;
		System.out.println("number is:" +number);
		for(int i=number; i>0; i--)
		{
			factorial = factorial*i;
		}
		System.out.println("factorial is:"+factorial);
		
	}

}
