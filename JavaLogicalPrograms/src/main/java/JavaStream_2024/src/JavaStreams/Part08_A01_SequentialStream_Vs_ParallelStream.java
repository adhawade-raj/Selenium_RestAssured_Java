package JavaStreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Part08_A01_SequentialStream_Vs_ParallelStream {

	public static void main(String[] args) {

		ForkJoinPool corePools = ForkJoinPool.commonPool();
		System.out.println(corePools.getParallelism());

		Stream stream = Stream.of("Naveen", "Tom", "Selvin", "Peter", "Raj", "Simon");
//		BaseStream parallelStream = stream.parallel();

		// 1.
		System.out.println("-----1.Sequential Stream-----");
		Stream.of("Naveen", "Tom", "Selvin", "Peter", "Raj", "Simon").forEach(System.out::println);
		System.out.println("***** 1.Parallel Stream *****");
		Stream.of("Naveen", "Tom", "Selvin", "Peter", "Raj", "Simon").parallel().forEach(System.out::println);

		// 2.
		System.out.println("-----2.use parallelStream() on Collection Stream-----");
		Arrays.asList("Naveen", "Tom", "Selvin", "Peter", "Raj", "Simon").parallelStream().forEach(System.out::println);

		/** 3. */
		System.out.println("-----3.Normal Stream Range 1 to 5------");
		IntStream.range(0, 06).forEach(System.out::println);
		System.out.println("***** 3.Parallel Stream Range 1 to 5 *****");
		IntStream.range(0, 06).forEach(System.out::println);

		/** 4. To Print alphabets -> 97 to 122 */
		/** Exception in thread "main" java.lang.OutOfMemoryError: Java heap space */
//		System.out.println("-----4. Normal Stream To Print alphabets -> 97 to 122 -----");
//		getList().stream().forEach(System.out::println);
//
//		System.out.println("***** 4. Normal Stream To Print alphabets -> 97 to 122 *****");
//		getList().parallelStream().forEach(System.out::println);

		/** 5 To check if Stream is parallel or not */
		System.out.println("-----5 To check if Stream is parallel or not-----");
		IntStream range = IntStream.range(1, 10);
		System.out.println(range.isParallel());

		IntStream range1 = IntStream.range(1, 10);
		range1.parallel();
		System.out.println(range1.isParallel());

	}

	/** Exception in thread "main" java.lang.OutOfMemoryError: Java heap space */
	public static List<String> getList() {
		int n = 97;
		List<String> alpha = new ArrayList<String>();

		while (n <= 122) {
			char c = (char) n;
			alpha.add(String.valueOf(c));
		}
		return alpha;

	}

}
