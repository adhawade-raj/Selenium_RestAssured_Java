package JavaStreams;

import java.util.Arrays;
import java.util.List;


public class Part05_A02_StreamsFilterFindAny_OrElse {

	
	public static void main(String[] args) {
		
		
		
		List<Part05_A02_Customer> customerList =  Arrays.asList(
				 new Part05_A02_Customer("Tom", 46),
				 new Part05_A02_Customer("Peter", 26),
				 new Part05_A02_Customer("Simon", 31),
				 new Part05_A02_Customer("Harry", 46)
				);
				
		System.out.println("-----1.For Positive case-----");
		Part05_A02_Customer customer = customerList.stream()
				.filter(x -> "Peter".equals(x.getName()))
				.findAny()
				.orElse(null);
		
		System.out.println(customer.getName()+ " : "+customer.getAge());

		System.out.println("-----2.For Negative case(Null Check)-----");
		Part05_A02_Customer customer1 = customerList.stream()
				.filter(x -> "Raj".equals(x.getName()))
				.findAny()
				.orElse(null);
		System.out.println(customer1);

		/**This will throw exception*/
//		System.out.println(customer1.getName());
		

		
		
		System.out.println("-----3.Filter with multiple conditions-----");
		Part05_A02_Customer customer3 = customerList.stream()
				.filter(x -> "Peter".equals(x.getName()) && 26==x.getAge() )
				.findAny()
				.orElse(null);
		
		System.out.println(customer3.getName()+ " : "+customer3.getAge());
		
		
	

	}
		
}
