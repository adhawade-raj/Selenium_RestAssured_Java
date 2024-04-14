package Practise;

public class ForLoopPractise1404 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		int l=1;
	for(int i=1; i<=10; i++)
	{
		for(int j=1; j<=(10-i); j++)
		{
			System.out.print(" ");
		}
		for(int k=1; k<=i; k++, l++)
		{
			System.out.print(" " +l);
		}
	System.out.println();
	}
	}

}
