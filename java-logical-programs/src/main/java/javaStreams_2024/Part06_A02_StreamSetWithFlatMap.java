package javaStreams_2024;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Part06_A02_StreamSetWithFlatMap {

	public static void main(String[] args) {

		Part06_A02_Employee emp1 = new Part06_A02_Employee();
		emp1.setName("Naveen");

		emp1.addDevices("iPhone8");
		emp1.addDevices("Samsung9");
		emp1.addDevices("iPhone9");
		emp1.addDevices("macbook pro");

		Part06_A02_Employee emp2 = new Part06_A02_Employee();
		emp2.setName("Tom");

		emp2.addDevices("lenovo windows10");
		emp2.addDevices("Samsung9");
		emp2.addDevices("iPhone9");
		emp2.addDevices("macbook air");

		List<Part06_A02_Employee> empLists = new ArrayList<Part06_A02_Employee>();

		empLists.add(emp1);
		empLists.add(emp2);

		List<String> devicesList = empLists.stream().map(x -> x.getDevices()) // Stream<Set<String>>
				.flatMap(x -> x.stream()) // Stream<String>
				.distinct().collect(Collectors.toList());

		devicesList.forEach(x -> System.out.println(x));

	}

}
