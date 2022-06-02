package LoopingStatement;

public class ForPattern5 {

	public static void main(String[] args) {
	
				for(int a=0; a<=10; a++)
				{
					for(int b=0; b<=9-a; b++)
					{
						System.out.print(" ");
					}
					for(int c=0; c<=a; c++)
					{
						System.out.print("* ");
					}
					System.out.println();
				}
					
				
				for(int i=0; i<=9; i++)
				{
					for(int k=0; k<=i; k++)
					{
						System.out.print(" ");
					}
					for(int j=0; j<=9-i; j++)
					{
						System.out.print("* ");
					}
					System.out.println();
				}
					
				}		
		


	}


