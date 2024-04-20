package JavaPrograms_2022;

public class ForLoop9_Pyaramid_WithAsciiValue {

	public static void main(String[] args) {
		int z=0;
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<(4-i); j++)
			{
				System.out.print(" ");
			}
			for(int k=0; k<=i; k++, z++)
			{
				System.out.print( (char)(65+z)+" ");
			}
			System.out.println();
		}
	}

}
