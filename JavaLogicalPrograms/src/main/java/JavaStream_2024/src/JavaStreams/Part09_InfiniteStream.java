package JavaStreams;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Part09_InfiniteStream {

	public static void main(String[] args) {

//		1. iterate()
		System.out.println("-----1.iterate()-----");
		List<Integer> collect = IntStream.iterate(0, n -> n + 2).mapToObj(Integer::valueOf).limit(10)
				.collect(Collectors.toList());
		System.out.println(collect);

		System.out.println("-----2.generate()-----");
		List<Integer> collect2 = Stream.generate(() -> new Random().nextInt(200)).limit(10)
				.collect(Collectors.toList());
		System.out.println(collect2);

	}

}
