package dsa_RSA;

public class Section02_Recursion01_ArrayAddition {

	static int arr[] = {12,2,30,13,5};
	
	public static void main(String[] args) {
		
		int sum = sumOfDigits(arr.length-1);
		System.out.println(sum);

	}
	
	public static int sumOfDigits(int n) {
		
		if(n==0) 
			return arr[n];
			return arr[n] + sumOfDigits(n-1);
	}

}
