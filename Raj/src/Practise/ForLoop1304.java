package Practise;

public class ForLoop1304 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(int i=0; i<=5; i++)
		{
			for(int j=0; j<=(4-i); j++)     //4-0     j=1  4-1  4<=3
			{
				System.out.print(" ");
			}
			for(int k=0; k<=i; k++)                   //2<=1
			{
				System.out.print("*");	
			}
			System.out.println();
		}
		
	}

}
