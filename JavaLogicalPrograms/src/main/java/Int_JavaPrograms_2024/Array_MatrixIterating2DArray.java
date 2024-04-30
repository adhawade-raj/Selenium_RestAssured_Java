package Int_JavaPrograms_2024;

import java.util.Arrays;

public class Array_MatrixIterating2DArray {

	public static void main(String[] args) {
		
		int a[][] = {{1,2,3,4,5},{5,4,3,2,1}};
		
		
//		Approach 1
		System.out.println("________Aproach 1__________");
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a[1].length; j++) {
				System.out.print(a[i][j] +"\t");
			}
			System.out.println();
		}
		
		System.out.println("________Aproach 2__________");
//		Approach 2
		for(int[] e1: a) {
			for(int e2: e1) {
				System.out.print(e2 +"\t");
			}
			System.out.println();
		}
		
		System.out.println("________Aproach 3__________");
//		Approach 2
		for(int[] e1: a) {
		System.out.println(Arrays.toString(e1));	
		}
		}

	}


