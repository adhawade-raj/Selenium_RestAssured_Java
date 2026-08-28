package int_JavaPrograms_2022;

public class ForLoop7_Pyramid {

	public static void main(String[] args) {
for(int i=0; i<5; i++)
{
	for(int j=0; j<(4-i); j++)
	{
		System.out.print(" ");
	}
	for(int k=0; k<=i; k++)
	{
		System.out.print("*"+" ");
	}
	System.out.println();
}
		
	}

}
