package JavaPrograms;

import java.util.Arrays;
import java.util.Collections;

public class SortingElements {

	public static void main(String[] args) {

		int a[] = {200,30,10,40,50};
		System.out.println("Array elements before sorting "+Arrays.toString(a));
//		First Approach
		Arrays.sort(a);
		System.out.println("Array elements after sorting "+Arrays.toString(a));
		System.out.println("==========================================================");
		
        int b[] = {20,300,100,40,50};
		System.out.println("Array elements before sorting "+Arrays.toString(b));
//		Second Approach
		Arrays.parallelSort(b);
		System.out.println("Array elements after sorting "+Arrays.toString(b));
		System.out.println("==========================================================");
		
         
		Integer c[] = {200,30,10,40,50};
		System.out.println("Array elements before sorting "+Arrays.toString(c));
//		Third Approach
		Arrays.sort(c, Collections.reverseOrder());
		System.out.println("Array elements after sorting "+Arrays.toString(c));
		
	}

}
