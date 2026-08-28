package int_JavaPrograms_2022;

public class ForLoop5_UsingAsciiValue {

	public static void main(String[] args) {

		for(int i=0; i<5 ; i++)
		{
			for(int j=0; j<5; j++)
			{
				System.out.print((char)(65+i)+" ");
			}
			System.out.println();
		}
		
	}

}
