package dsa_RSA;

public class Section02_Recursion02_Factorial {

	public static void main(String[] args) {
		
		int factorialValue = factNo(5);
		System.out.println(factorialValue);

	}

	private static int factNo(int n) {
		if(n==1) {
		return 1;
	}
		else {
			return n*factNo(n-1);
		}
}
}
