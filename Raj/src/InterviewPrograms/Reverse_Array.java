package InterviewPrograms;

public class Reverse_Array {

	public static void main(String[] args) {

		int a[]= {2,4,3,1};
		int b=a.length-1;
		
//		for(int i=0; i<b; i++)
		for(int i=b; i>=0;i--)
		{
			System.out.print(a[i]+" ");
		}
		

	}

}
