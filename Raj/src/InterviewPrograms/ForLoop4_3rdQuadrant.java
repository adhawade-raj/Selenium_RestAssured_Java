package InterviewPrograms;

public class ForLoop4_3rdQuadrant {

	public static void main(String[] args) {
for(int i=0; i<5; i++)
{
	for(int j=1; j<(i+1); j++)
	{
		System.out.print(" ");
	}
	for(int k=0; k<(4-i); k++)
	{
		System.out.print("*");
	}
	System.out.println();
}
	}

}
