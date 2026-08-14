package int_JavaPrograms_Tricky_2024;

import java.util.stream.IntStream;

public class Print1To100_WithoutLoops {

	public static void main(String[] args) {
		
		printNum(1);
//		System.out.println("___________Approach 1 Generic Way____________");
//		printNumber(1,100);
		
		/**
		 * Approach2 - Java Streams
		 */
		System.out.println("___________Approach2 - Java Streams____________");
		IntStream.range(1, 101).forEach(e -> System.out.println(e));
	}
	
	
	/**
	 * Recursive Approach1
	 * Function calling inside selfFunction
	 * @param num
	 */
	public static void printNum(int num) {
		if(num<=100) {
			System.out.println(num);
			num++;
			printNum(num);
		}
	}
	
	/**
	 * Approach1 in generic way
	 * @param num
	 */
	public static void printNumber(int strtNum, int endNum) {
		if(strtNum<=endNum) {
			System.out.println(strtNum);
			strtNum++;
			printNumber(strtNum, endNum);
		}
	}

}
