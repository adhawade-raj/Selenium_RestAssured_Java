package dsa_ScottBarret;

public class Section2_BigO_P08 {
	/**
	 * O(n^2)
	 * square of n (n*n)
	 * @param n
	 */
	
	public static void printStream(int n) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
			System.out.println(i+ " "+j);
		}
		}
	}
	
	public static void main(String[] args) {
		
		printStream(5);
	}

}
