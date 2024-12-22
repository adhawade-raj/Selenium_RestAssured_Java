package javaStreams_2024;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Part08_A02_PrimeNumberWithParalleStream {

	public static void main(String[] args) {

//		System.out.println(isPrime(24)); //false

		long t1, t2, t3, t4;
		t1 = System.currentTimeMillis();
		long count = Stream.iterate(0, n -> n + 1).limit(100000).filter(Part08_A02_PrimeNumberWithParalleStream::isPrime)
				.peek(x -> System.out.println(x)).count();

		System.out.println("Total Prime numbers in sequential Stream: " + count);
		t2 = System.currentTimeMillis();
		System.out.println("Total time taken : " + (t2 - t1) / 100000);
		System.out.println("----------------------------------------------------------------");

		t3 = System.currentTimeMillis();
		long count2 = Stream.iterate(0, n -> n + 1).limit(100000).parallel()
				.filter(Part08_A02_PrimeNumberWithParalleStream::isPrime).peek(x -> System.out.println(x)).count();

		System.out.println("Total Prime numbers parallel Stream : " + count2);

		t4 = System.currentTimeMillis();
		System.out.println("Total time taken : " + (t4 - t3) / 100000);

	}

	public static boolean isPrime(int number) {

		if (number <= 1)
			return false;

		return !IntStream.rangeClosed(2, number / 2).anyMatch(x -> number % x == 0);
	}

}
