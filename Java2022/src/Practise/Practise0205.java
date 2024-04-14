package Practise;

public class Practise0205 {

	public static void main(String[] args) {
	int l=0;
for(int i=0; i<=5; i++)
{
	for(int j=0; j<=i; j++)
	{
		System.out.print(" ");
	}
	for(int k=0; k<=(5-i); k++, l++)
	{
		System.out.print((char)(65+l) +" ");
	}
	System.out.println();
}
}
}
