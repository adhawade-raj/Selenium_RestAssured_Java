package javaStreams_2024;

import java.util.function.Supplier;

public class Part02_A06_SupplierInterface {

	public static void main(String[] args) {
	
		
		getText(() -> "Java");
		getText(() -> "Raj Adhawade");

	}
	
	
	public static void getText(Supplier<String> text) {
		System.out.println(text.get().length());
	}
 
}
