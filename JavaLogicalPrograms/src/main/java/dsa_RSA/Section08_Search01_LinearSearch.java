package dsa_RSA;

public class Section08_Search01_LinearSearch {

	public static void main(String[] args) {
	
		int[] arr = {1,2,4,6,9,12,13,14,15,17};
		//O(1)  O(n)   O(n)
		System.out.println(LinearSearch(arr,50));
		System.out.println(LinearSearch(arr,6));
		//Goal - pass the input //output the index
	}
	
	public static int LinearSearch(int[] arr, int target)
	{
		for(int i =0;i<arr.length;i++)
		{
			if(arr[i] == target)
				return i;
		}
		return -1;
	}

}
