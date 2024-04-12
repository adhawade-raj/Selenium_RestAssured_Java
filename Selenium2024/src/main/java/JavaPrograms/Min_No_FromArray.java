package JavaPrograms;

public class Min_No_FromArray {

	public static void main(String[] args) {

		int ar[] = {2,400,500,3000,1};
		int min = Integer.MAX_VALUE;
		for(int e: ar)
		{
			System.out.println(e);
			if(e<min)
			{
				min=e;
			}
		}
		System.out.println("Minimum value is:"+min);
		
	}

}
