package JavaStreams;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Part07_SequentialStream {

	public static void main(String[] args) {

		/** 1. */
		System.out.println("-----1.------");
		Stream stream = Stream.of("Raj", "Tom", "Selvin", "Peter");
//		stream.forEach(System.out::println);
//		stream.findFirst().ifPresent(System.out::println);

		/** 2. */
		System.out.println("-----2.------");
		Arrays.asList("a1", "a2", "a3").stream().findFirst().ifPresent(System.out::println);

		/** 3. */
		System.out.println("-----3.Range 1 to 5------");
		IntStream.range(0, 06).forEach(System.out::println);

		System.out.println("-----4.------");
		Arrays.stream(new int[] { 1, 2, 3, 4, 5 }).map(n -> 2 * n + 1).average().ifPresent(System.out::println);

		System.out.println("-----5.String Data Objects------");
		Arrays.asList("a1", "a2", "a3", "a111").stream().map(s -> s.substring(1)).mapToInt(Integer::parseInt).max()
				.ifPresent(System.out::println);

		System.out.println("-----6.Doubles to String Objects------");
		Stream.of(1.0, 2.0, 3.0).mapToInt(Double::intValue).mapToObj(i -> "a" + i).forEach(System.out::println);

		System.out.println("-----7.Limit------");
		Stream.iterate(0, n -> n + 1).limit(5).forEach(System.out::println);

		System.out.println("-----8.Odd Numbers------");
		Stream.iterate(0, n -> n + 1).filter(x -> x % 2 != 0).limit(5).forEach(System.out::println);

	}

}
