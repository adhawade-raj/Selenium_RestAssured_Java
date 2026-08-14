package TestNG1;

import org.testng.annotations.Test;

public class TestNG_Practise02 {

	
	 @Test(invocationCount =5)
     public void LoginPageTest()
     {
   	  System.out.println("Test");
     }
}

