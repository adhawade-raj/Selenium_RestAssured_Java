package JavaStreams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Part02_A05_ConsumerInterface {

	public static void main(String[] args) {
		
		
		Consumer<String> func = x -> System.out.println(x);
		func.accept("This is Raj");
		
		System.out.println("-------------------------------------");
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		list.forEach(x -> System.out.println(x));

	}

}
