package javaStreams_2024;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part05_A03_StreamMap {

	public static void main(String[] args) {

		
		List<Part05_A02_Customer> customerList =  Arrays.asList(
				 new Part05_A02_Customer("Tom", 46),
				 new Part05_A02_Customer("Peter", 26),
				 new Part05_A02_Customer("Simon", 31),
				 new Part05_A02_Customer("Harry", 46)
				);

		
		System.out.println("-----1.mapping using streams-----");
		String name = customerList.stream()
				.filter(x -> "Peter".equals(x.getName()))
				.map(Part05_A02_Customer :: getName)
				.findAny()
				.orElse(null);
		System.out.println(name);
		
		
		
		System.out.println("-----2.Printing all name list - mapping using streams-----");
		List<String> custList = customerList.stream()
				.map(Part05_A02_Customer :: getName)
				.collect(Collectors.toList());
		
		custList.forEach(System.out::println);
			
		
	}

}
