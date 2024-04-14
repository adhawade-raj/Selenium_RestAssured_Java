package JavaPrograms;

public class NumberOfDigit {

	public static void main(String[] args) {

		int number = 70382;
		int count = 0;
		int temp=number;
		
		System.out.println("given no is:"+number);
		
		while(number>0)
		{
		number = number/10;
		count = count+1;
		}
		
		System.out.println("Number of digit present in: "+temp+" is "+count);
		
		
	}

}
