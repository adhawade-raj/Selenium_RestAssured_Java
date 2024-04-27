package Int_JavaPrograms_Tricky_2024;

public class NullArgument_Object_String {

	
//	Which method will be called first if we provide null argument
//	Ans = String - as java will recognize it as most frequently used argument
	public static void main(String[] args) {
		
		test(null);
	}
	
	public static void test(Object o) {
		System.out.println("Object Argument");	
	}
	
	public static void test(String o) {
		System.out.println("String Argument");	
	}

//	null argument Not Allowed in Stringbuffer
//	public static void test(StringBuffer o) {
//		System.out.println("String Argument");	
//	}
}
