package InterviewPrograms;

public class DuplicateElementsInArray {

	public static void main(String[] args) {

		String arr[] = {"java", "python", "ruby", "java"};
		
		boolean flag = false;
		
		for(int i=0; i<arr.length; i++)
		{
		for(int j=(i+1); j<arr.length; j++)
		{
			if(arr[i] == arr[j])
			System.out.println("Duplicate element found is "+arr[i]);
			flag = true;
		}
		}
		
		if(flag==false)
		{
			System.out.println("Duplicate element not found");
		}
	
	System.out.println("=====================================");
	int a[] = {1,2,2,3,4};
	
	boolean flag2 = false;
	int count =0;
	
	for(int i=0; i<a.length; i++)
	{
	for(int j=(i+1); j<a.length; j++)
	{
		if(a[i] == a[j])
		System.out.println("Duplicate element found is "+a[i]);
		flag2 = true;
	}
	
	}
	
	
	if(flag2==false)
	{
		System.out.println("Duplicate element not found");
	}
}
}

