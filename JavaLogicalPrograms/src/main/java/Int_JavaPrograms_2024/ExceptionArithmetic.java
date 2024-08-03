package Int_JavaPrograms_2024;
public class ExceptionArithmetic {

	public static void main(String[] args) {
		

		// O/P = Infinity
//		if u divide by Floating or Double num it will give Infinity output
		System.out.println(9.0/0);     
		System.out.println(9.0f/0);
		System.out.println(9/0.0);
		
		//Try catch handles the exception and continues the further flow
		//And prints the exception if it occurs and jumps to next flow
				try {
					System.out.println(9/0);
				}catch(ArithmeticException e) {
					e.printStackTrace();
				}
		
		System.out.println(9/0);
//		Exception in thread "main" java.lang.ArithmeticException: / by zero
		
		
	}

}
