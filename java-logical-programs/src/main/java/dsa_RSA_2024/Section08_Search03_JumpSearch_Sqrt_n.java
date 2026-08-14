package dsa_RSA_2024;

public class Section08_Search03_JumpSearch_Sqrt_n {

//	Time Complexity =sqrt(n)
	public static int jumpSearch(int[] arr,int target)
	{
		int blocksize = (int) Math.sqrt(arr.length);
		System.out.println("BlockSize = "+blocksize);
		int start = 0;
		int last = blocksize - 1;
		while(arr[last]<target && start <arr.length)
		{
			start = last + 1;
			last = last + blocksize;
			if(last>arr.length-1)
				last = arr.length-1;
		}
		for(int i =start;i<=last;i++)
		{
			if(arr[i] == target)
				return i;
		}
		return -1;
	}
	
	
	public static void main(String[] args) {

		int[] arr = {1,2,4,6,9,12,13,14,15,17};
		
		System.out.println(jumpSearch(arr,13));
	 
	}
}
