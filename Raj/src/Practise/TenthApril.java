package Practise;

public class TenthApril {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		int a[]= {1,2,3,4,5};
		System.out.println(a[0]);
		
		System.out.println(a[1]+a[2]);
		System.out.println("length is "+a.length);
		
		for(int i=0; i<a.length; i++)
		{
			System.out.println(a[i]);
		}
		System.out.println("new code");
		System.out.println();
		
		int b[] = new int[3];
		b[0]=10;
		b[1]=20;
		b[1]=30;
		System.out.println(a[0]);
		System.out.println();
		
		for(int j=0; j<b.length; j++)
		{
			System.out.println(a[j]);
		}
		
		System.out.println(" multidimensional array");
		
		int c[][]= {{2,4,6},{8,10,12}};
		
		for(int k=0; k<2; k++)
		{
			for(int l=0; l<3; l++)
			{
			    System.out.print(" "+c[k][l]);	
			}
			System.out.println();
		}
	}
}
