package LoopingStatement;

public class ForPattern1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		int x=4;
		for(int i=1; i<=x; i++)                            
		{                                                   
			for(int j=4; j>=i; j--)
			{                                               
				System.out.print("* ");                     
			}
			System.out.println();
		}                                                   
		
 for(int i=1; i<=x; i++)
 {
	 for(int j=1; j<=i; j++)
	 {
		 System.out.print("* ");
	 }
	 System.out.println();
 }
	}

}
