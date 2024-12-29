package dsa_ScottBarret;

public class Section2_BigO_P09 {

	/**
	 * O(n^2 + n)
	 * O(n^2) -> dropping non dominant
	 * n^2 = dominant
	 * n = non non dominant
	 * @param n
	 */
	
	public static void printStream(int n) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
			System.out.println(i+ " "+j);
		}
		}
		for(int k=0; k<n; k++) {
		System.out.println(k);	
		}
		}

	
	public static void main(String[] args) {
		
		printStream(5);
	}


}
