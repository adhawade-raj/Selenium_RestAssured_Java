package Practise;

public class Mock3 {

	public static void main(String[] args) {
		
		
		for(int i=2; i<=8; i++)
		{
			for(int j=2; j<=i; j++)
			{
				System.out.print(" ");
			}
			for(int k=2; k<=(8-i); k++)
			{
				System.out.print("* "+k);
				
			}
			System.out.println();
		}
		

	}

}
