package newLearnings_2025;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class InjectAnnotation {

	@Inject
	private Students student;

	public static void main(String[] args) {

		Injector injector = Guice.createInjector();
		InjectAnnotation app = injector.getInstance(InjectAnnotation.class);

		app.student.setName("Raj");
		app.student.setCity("Pune");
		System.out.println("Student Name: " + app.student.getName());
		System.out.println("Student City: " + app.student.getCity());

	}

}
