package dsa_ScottBarret;

public class Section2_BigO_P07_DropConstants {
	/**
	 * O(n)
	 * n+n = 2n
	 * O(n)
	 * dropped constants i.e. 2
	 * @param n
	 */
	
//	n+n = 2n
//	O(n)
//	dropped constants i.e. 2
	
	public static void printStream(int n) {
		/**n*/
		for(int i=0; i<n; i++) {
			System.out.println(i);
		}
		/**n*/
		for(int i=0; i<n; i++) {
			System.out.println(i);
		}
	}
	
	public static void main(String[] args) {
		
		printStream(5);
	}
}
