package javaStreams_2024;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class Part02_A02_BinaryOperator_BiFunctionInterface {

	
	public static void main(String[] args) {
		
		
		
		/**BinaryOperator Interface*/
		BinaryOperator<Integer> func = (x1, x2) -> x1+x2;
		int t = func.apply(2, 2);
		System.out.println("Result of Binary Operator Interface -> "+t);
		
		
		/**BiFunction Interface*/
		BiFunction<Integer, Integer, Integer> func2 = (x1, x2) -> x1+x2;
		int t2 = func2.apply(3, 3);
		System.out.println("Result of BiFunction Operator Interface -> "+t2);
		
		
	}
	
}
