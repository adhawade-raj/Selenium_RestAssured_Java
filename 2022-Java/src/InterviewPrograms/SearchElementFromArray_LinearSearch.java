package InterviewPrograms;

public class SearchElementFromArray_LinearSearch {

	public static void main(String[] args) {

		int a[] = {10,20,30,30,40};
		int search = 40;
		
		boolean flag = false;
		for(int i=0; i<a.length; i++)
		{
			if(search==a[i])
			{
				System.out.println("Element found at "+i+ " index and no is "+a[i]);
				flag = true;
				break;
			}
		
			}
		
		if(flag==false)
		{
			System.out.println("Element not found");
		}
		}
	}


