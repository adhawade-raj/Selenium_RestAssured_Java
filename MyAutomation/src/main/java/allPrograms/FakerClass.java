package allPrograms;

import com.github.javafaker.Faker;

public class FakerClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Faker faker = new Faker();
		System.out.println(faker.address().fullAddress());
		System.out.println(faker.name().firstName());
		System.out.println(faker.name().lastName());
		String abc = faker.name().title();
		System.out.println(abc);
		
		
	}

}
