package int_JavaPrograms_2022;

public class PrimeNumber {

	public static void main(String[] args) {

		int number = 3;
		int count =0;
		
		for(int i=2; i<number; i++)
		{
			if(number%i==0)
			{
				count=count+1;
			}
		}
		if(count>0)
		{
			System.out.println("Number is not prime");
		}
		else
		{
			System.out.println("Number is prime");
		}
		
		
		
		
	}

}
