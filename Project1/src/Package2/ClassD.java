package Package2;

public class ClassD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int l=1;
	for(int i=0; i<10; i++)
	{
		for(int j=0; j<(10-i); j++)
         {
	System.out.print(" ");
          }
		for(int k=0; k<=i; k++, l++)
		{
			System.out.print(" "+l);
		}
		System.out.println();
	}
		
	}
}
