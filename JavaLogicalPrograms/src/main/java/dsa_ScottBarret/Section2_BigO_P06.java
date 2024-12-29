package dsa_ScottBarret;

public class Section2_BigO_P06 {

	/**
	 * O(n)
	 * @param n
	 */
	
	public static void printStream(int n) {
		for(int i=0; i<n; i++) {
			System.out.println(i);
		}
	}
	
	public static void main(String[] args) {
		
		printStream(10);
	}

}
