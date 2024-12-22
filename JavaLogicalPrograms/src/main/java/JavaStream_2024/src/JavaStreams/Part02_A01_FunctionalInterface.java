package JavaStreams;

import java.util.function.Function;

public class Part02_A01_FunctionalInterface {

	public static void main(String[] args) {

	Function<String, Integer> func = x -> x.length();
	int len = func.apply("My name is Raj");
	System.out.println("Result of First Function -> "+len);
	
	
	/**Chaining*/
	Function<Integer, Integer> func2 = x -> x*2;
	int result = func.andThen(func2).apply("This is raj and I am SDET");
	System.out.println("Result of Second Function after chaining -> "+result);
		
		
	}	
	
}
