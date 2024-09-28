package DSA_RSA;

import java.util.Arrays;

public class Section07_Sorting03_MergeSort {

	public static void main(String[] args) {

		int arr[] = {4,1,2,7,9};
		sort(arr);
		System.out.println(Arrays.toString(arr));
			}
	
	public static void sort(int[] arr)
	{
		if(arr.length<2)
			return;
		int middle = arr.length/2;
		int left[] = new int[middle];
		int right[] = new int[arr.length-middle];
		
		for(int i =0;i<middle;i++)
		{
			left[i] = arr[i];
		}
		for(int i =middle;i<arr.length;i++)
		{
			right[i-middle] = arr[i];
		}
		sort(left);
		sort(right);
		//merge the result
		merge(left, right,arr);
		
	}

	private static void merge(int[] left, int[] right, int[] arr) {
		// TODO Auto-generated method stub
		int i =0, j=0, k =0;
		while(i<left.length && j<right.length)
		{
			if (left[i] <= right[j])
			{
				arr[k] = left[i];
				i++;
				k++;
				}
			else
				arr[k++] = right[j++];
			
		}
		while(j<right.length)
			arr[k++] = right[j++];
		
		while(i<left.length)
			arr[k++] = left[i++];
		
	}
	
}
