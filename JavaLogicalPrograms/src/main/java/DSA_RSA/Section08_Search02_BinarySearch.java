package DSA_RSA;

public class Section08_Search02_BinarySearch {

	public static int binarySearch(int[] arr , int target)
	{
		
		int left = 0, right = arr.length-1; int middle;
		while(left<=right)
		{
			middle = (left+ right)/2;
			if(arr[middle] == target)
				return middle;
			
			 if (arr[middle]> target)
				right = middle-1;
			else 
				left = middle + 1;
			}
		return -1;
	}
	
	public static int binarySearchRecursion(int[] arr ,int target,int left,int right)
	{
			if (right < left)	
			return -1;
			int middle = (left+ right)/2;
			
			if(arr[middle] == target)
				return middle;
			
			 if (arr[middle]> target)
			 {
				right = middle-1;
			 return binarySearchRecursion(arr,target,left,right);
			 }
			 	
			else 
			{
				left = middle + 1;
				return binarySearchRecursion(arr,target,left,right);
			}

	}
	
	
	public static void main(String[] args) {
		int[] arr = {1,2,4,6,9,12,13,14,15,17};
		
	System.out.println(binarySearch(arr,13));
	System.out.println(binarySearchRecursion(arr,13,0,arr.length-1));

	}

}
