package dsa_RSA_2024;

public class Section02_Recursion03_Fibonacci {

	public static void main(String[] args) {
		
		int fibValue = fibNo(8);
		System.out.println(fibValue);

	}

	private static int fibNo(int n) {
	if(n ==0 || n==1)
		return n;
	
	return fibNo(n-1)+ fibNo(n-2);
	}

}
