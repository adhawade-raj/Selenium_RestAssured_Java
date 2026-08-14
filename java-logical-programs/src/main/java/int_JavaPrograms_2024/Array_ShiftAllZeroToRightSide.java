package int_JavaPrograms_2024;

import java.util.Arrays;

public class Array_ShiftAllZeroToRightSide {

	public static void main(String[] args) {
		
		int a[] = {1,2,0,4,5,0,6};
		System.out.println(Arrays.toString(shiftAllZerosToRight(a)));
	}

	public static int[] shiftAllZerosToRight(int num[]) {
		if(num.length==1) {
			return num;
		}
		int newArray[] = new int[num.length];
		int count=0;
		
		for(int number : num) {
			if(number!=0) {
				newArray[count] = number;
				count++;
			}
		}
		return newArray;
	}
}
