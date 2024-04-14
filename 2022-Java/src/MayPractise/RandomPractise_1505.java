package MayPractise;

public class RandomPractise_1505 {

	public static void main(String[] args) {

		for(int i=0; i<5; i++)
		{
			for(int j=0; j<=i; j++)
			{
				System.out.print("*");
			}
			System.out.println();
		}
		
		for(int k=0; k<4; k++)
		{
			for(int l=0; l<=(3-k); k++)
			{
				System.out.print("*");
			}
			System.out.println();
		}
		
}
}
