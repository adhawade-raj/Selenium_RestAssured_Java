package Int_JavaPrograms_2022;

public class Max_No_FromArray {

	public static void main(String[] args) {

		int ar[]= {2,200,4,500,30};
		
		int max = Integer.MIN_VALUE;
		for(int e: ar)
		{
			if(e>max)
			{
				max=e;
			}
		}
		System.out.println("Max element is:" +max);
	}

}
