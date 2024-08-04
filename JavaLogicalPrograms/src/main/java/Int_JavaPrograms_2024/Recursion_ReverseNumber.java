package Int_JavaPrograms_2024;

public class Recursion_ReverseNumber {

	
//	Recursion = Function calling inside function
	public static void main(String[] args) {
		
		reverseNumber(1);
		reverseNumber(121);
	}

	public static void reverseNumber(int num) {
		if(num<10) {
			System.out.println(num);
//			return;
		}
		else {
			System.out.print(num%10);
			reverseNumber(num/10);
		}
	}
}
