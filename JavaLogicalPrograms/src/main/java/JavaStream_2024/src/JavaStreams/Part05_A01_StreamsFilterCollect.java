package JavaStreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part05_A01_StreamsFilterCollect {

	public static void main(String[] args) {
		
		
	ArrayList<String> prodList = new ArrayList<String>(Arrays.asList("MackBook", "iPhone", "Shoes", "Bat"));
		
	
	//1.Using filter - which accepts predicate(Boolean Condition)
//	Shoes will be excluded and rest of the list will be printed
	
	System.out.println("-----B.Using Stream forEach-----");
	List<String> result = prodList.stream().filter(e -> !e.equals("Shoes")).collect(Collectors.toList());
	result.forEach(e -> System.out.println(e));
	
//	B.Using Method Reference
	System.out.println("-----B.Using forEach with Method Reference-----");
	result.forEach(System.out::println);
		
		
		

	}

}
