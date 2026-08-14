package int_JavaPrograms_2022;

public class Largest_Of_ThreeNumbers_TernaryOperator {

	public static void main(String[] args) {
		int a=10,b=40,c=30;
		
		int largest = c>(a>b?a:b)?c:(a>b?a:b);
		System.out.println(largest+ " is Largest");
	}

}
