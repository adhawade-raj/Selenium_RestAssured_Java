package LoopingStatement;

public class ForLoop_123Pyramid {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			
			int l=1;
		for(int i=1; i<=10; i++)
		{
			for(int j=1; j<=(10-i); j++)            //j=9, j=8,j=7,j=6,5,4,3,2,1
			{
				System.out.print(" ");
			}
			for(int k=1; k<=i; k++, l++)            //i=2   k=3     l=4  
			{
				System.out.print(" "+l);
			}
		System.out.println(" ");
		}
			
	}

}
