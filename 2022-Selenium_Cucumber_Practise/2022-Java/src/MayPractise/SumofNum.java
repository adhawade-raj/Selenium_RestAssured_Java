package MayPractise;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class SumofNum {

	public static void main(String[] args) {
		
		
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		
		Optional<Integer> sum = list.stream().reduce((a,b) -> a+b);
		System.out.println(sum.get());

	}

}
