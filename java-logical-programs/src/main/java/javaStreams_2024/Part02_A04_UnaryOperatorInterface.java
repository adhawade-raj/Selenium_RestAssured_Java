package javaStreams_2024;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Part02_A04_UnaryOperatorInterface {

	public static void main(String[] args) {
		
	
	UnaryOperator <Integer> func = x-> x*7;
	int result = func.apply(7);
	System.out.println(result);
	
	
	Function <Integer, Integer> func2 = x-> x*7;
	int result2 = func2.apply(10);
	System.out.println(result2);
	
	
	
	}
}
