package newLearnings_2024;

import java.util.Arrays;
import java.util.List;

import javaStreams_2024.Part05_A02_Customer;
import lombok.SneakyThrows;

public class SneakyThrows_VS_TryCatch {

	static List<Part05_A02_Customer> customerList;

	public static void main(String[] args) {

		customerList = Arrays.asList(new Part05_A02_Customer("Tom", 46), new Part05_A02_Customer("Peter", 26),
				new Part05_A02_Customer("Simon", 31), new Part05_A02_Customer("Harry", 46));

		System.out.println("-----1.For Positive case-----");
		Part05_A02_Customer customer = customerList.stream().filter(x -> "Peter".equals(x.getName())).findAny()
				.orElse(null);
		System.out.println(customer.getName() + " : " + customer.getAge());

//		System.out.println("----------Try Catch Block---------");
//		getWithTryCatch();

		System.out.println("-----------Sneaky Thows------------");
		getWithSneakyThrows();

		/**
		 * Sneaky throws cant handle exceptiion so it wont proceed for further execution
		 * if exception occurs
		 */
		/**
		 * But Try catch will handle the exception and will proceed further even if
		 * exception occurs
		 */
		/**
		 * We have to use Sneaky Thorws for Checked Exception only like FileNotFound
		 * (error occured at compile time)
		 */

	}

	@SneakyThrows(Exception.class)
	public static void getWithSneakyThrows() {
		Part05_A02_Customer customer1 = customerList.stream().filter(x -> "Raj".equals(x.getName())).findAny()
				.orElse(null);
		System.out.println(customer1);
		System.out.println(customer1.getAge());
	}

	public static void getWithTryCatch() {
		Part05_A02_Customer customer1 = customerList.stream().filter(x -> "Raj".equals(x.getName())).findAny()
				.orElse(null);

		try {
			System.out.println(customer1);
			System.out.println(customer1.getAge());
		}

		catch (Exception e) {
//			System.out.println("-----Exception is thrown using printStack Trace-----");
//			e.printStackTrace();
//			System.out.println("----------------------------------------------------");
		}

	}
}
