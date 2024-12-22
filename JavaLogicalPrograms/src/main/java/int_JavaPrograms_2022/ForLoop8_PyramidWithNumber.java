package int_JavaPrograms_2022;

public class ForLoop8_PyramidWithNumber {

	public static void main(String[] args) {
		int z=1;
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<(4-i); j++)
			{
				System.out.print(" ");
			}
			for(int k=0; k<=i; k++, z++)
			{
				System.out.print( z+" ");
			}
			System.out.println();
		}
	}

}
