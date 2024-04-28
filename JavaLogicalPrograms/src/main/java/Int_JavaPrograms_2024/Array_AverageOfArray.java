package Int_JavaPrograms_2024;

import java.util.Arrays;
import java.util.OptionalDouble;

public class Array_AverageOfArray {

	public static void main(String[] args) {
		
		double num[] = {1,2,3,4,5,6};
		double total =0;
		
//		Approach 1 : for Each loop
		for(double e: num) {
			total = total+e;
		}
		System.out.println("Average of an array using ForEach Loop : "+total/num.length);
		
//		Approach 2 : Java 8 Streams
	OptionalDouble avg = Arrays.stream(num).average();
	System.out.println("Average of an array using Java 8 Streams: "+avg.getAsDouble()); //o/p = 3.5
	System.out.println("Average of an array using Java 8 Streams : "+avg); // o/p = OptionalDouble[3.5]
	}

}
