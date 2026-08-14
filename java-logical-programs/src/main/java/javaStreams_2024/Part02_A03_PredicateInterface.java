package javaStreams_2024;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Part02_A03_PredicateInterface {

	public static void main(String[] args) {

		Predicate<Integer> func = x -> x>5;
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		List<Integer> collList = list.stream().filter(func).collect(Collectors.toList());
		System.out.println("Predicate with single booolean condition "+collList);
		
		
		/**Predicate with &&*/
		Predicate<Integer> func2 = x -> x>5 && x<9;
		List<Integer> list2 = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		List<Integer> collList2 = list2.stream().filter(func2).collect(Collectors.toList());
		System.out.println("Predicate with && -> "+collList2);
		
		
		/**Predicate with negate*/
		
		List<String> nameList = Arrays.asList("Naveen", "Navee", "Nave", "Java", "Jav", "Ja", "J");
		Predicate<String> nameExp = x -> x.startsWith("Naveen");
		
		
		List<String> newNameList = nameList.stream().filter(nameExp.negate()).collect(Collectors.toList());
		System.out.println("Predicate with negate function -> "+newNameList);
		
		
		
		
		

	}

}
