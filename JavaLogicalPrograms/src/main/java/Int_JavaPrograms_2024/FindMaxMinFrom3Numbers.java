package Int_JavaPrograms_2024;

public class FindMaxMinFrom3Numbers {

	public static void findMaxNumber(int a, int b, int c) {
		int max =0;
		
		while(a>0 || b>0 || c>0) {
			a--;
			b--;
			c--;
			max++;
		}
		System.out.println(max);
	}
		
		public static void findMinNumber(int a, int b, int c) {
			int min =0;
			
			while(a>0 && b>0 && c>0) {
				a--;
				b--;
				c--;
				min++;
			}
			System.out.println(min);
		
	}
	public static void main(String[] args) {
		
//		findMaxNumber(5,3,4);
		findMinNumber(1,3,4);
		

	}

}
