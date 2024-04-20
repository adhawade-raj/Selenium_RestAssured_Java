package JavaPrograms_2022;

import java.util.Random;

public class GenerateRandomNumbers {

	public static void main(String[] args) {

		Random ra = new Random();
		
		int random_int = ra.nextInt(10);
		System.out.println(random_int+" Random no");
		
		double random_dbl = ra.nextDouble(10);
		System.out.println(random_dbl+" Random no");
		
		System.out.println(Math.random()+ " is Random no");
		
	}

}
