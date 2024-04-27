package Int_JavaPrograms_2024;

import java.util.Arrays;

public class BubbleSort_NumbersInAscendingOrder {

//	Bubble sort algorithm is simplest algorithm in which array is traversed 
//	from first element to last element. 
//	We will compare first element with next element if it is greater then we will swap it. 
//	e.g 19,3 -  19 is greater so 3 will be in 1st postion then 19 will be compared with next element and so on
//	o/p = 0 3 3 19 38 91 181 
//	Time Complexisity = o(N^2)- very expensive(time will be taken) 
//	we use Bubble Sort for small set of array
	
	public static void main(String[] args) {
		int[] arr={19,3,181,3,38,0,91};
	    int temp;
	    for(int i=0;i<arr.length;i++)
	    {
		for(int j=1;j<arr.length-1;j++)
		{
//			unless and until condition does not becomes false or arr length is finished
//			this will keep on excecuting
		    if(arr[j]<arr[j-1])
		    {
			 temp=arr[j]; 
			arr[j]=arr[j-1]; 
			arr[j-1]=temp; 
	            }
		    
		}
//		if u want to check every iteration & ordering sequesnce
//		System.out.print(Arrays.toString(arr)+" ");
		
	    }
	    System.out.print(Arrays.toString(arr)+" ");
	    
	}

}
