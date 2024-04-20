package JavaPrograms_2024;

public class ExceptionNullPointer {

	public static void main(String[] args) {
	
		String a =null;
		String b= "Adhawade";
		String c =a.concat(b);
		System.out.println(c);
		
//		Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.concat(String)"
	}

}
