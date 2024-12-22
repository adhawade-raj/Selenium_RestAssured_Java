package javaStreams_2024;

import java.util.Arrays;
import java.util.stream.Stream;

public class Part06_A01_StreamWithFlatMap {

	public static void main(String[] args) {

		String data[][] = new String[][] {

				{ "a", "b" }, { "c", "d" }, { "e", "f" } };

		Stream<String[]> dataStream = Arrays.stream(data);

		/** This Will print Nothing */
		/** As Stream cant be applied direct with filter */
//		Stream<String[]> dataStreamFilter = dataStream.filter(x -> "a".equals(x.toString()));
//		dataStreamFilter.forEach(System.out::println);

		/** This will print the o/p as We have used flatMap first and then filter */
		System.out.println("----------Approach 1----------");
		Stream<String> streamFlatMap = dataStream.flatMap(x -> Arrays.stream(x));
		Stream<String> dataStreamFilter2 = streamFlatMap.filter(x -> "a".equals(x.toString()));
		dataStreamFilter2.forEach(System.out::println);

		/** Approach2 of above code */
		System.out.println("------Approach2 of above same code-----");
		Stream<String> streamFlatMap2 = Arrays.stream(data).flatMap(x -> Arrays.stream(x))
				.filter(x -> "c".equals(x.toString()));
		streamFlatMap2.forEach(System.out::println);

	}

}
