package TestNG1;

import org.testng.annotations.Test;

public class InvocationConcept {

	
//	Same Test will be executed for count that we have Provided i.e=5
//	Use Case : Back to back hitting required on API
	@Test(invocationCount=5)
	public void createUSer()
	{
		System.out.println("Create User");	
	}
	
}
