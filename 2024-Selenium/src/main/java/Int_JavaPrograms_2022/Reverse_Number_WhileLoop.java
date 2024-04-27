package Int_JavaPrograms_2022;

public class Reverse_Number_WhileLoop {

	public static void main(String[] args) {

		int number = 1234;
		int rev = 0;
		
		while(number !=0)
		{
			rev = rev*10 + number%10;
			number = number/10;
		}
		System.out.println("Reverse no is " + rev );
		
		
	}

}
