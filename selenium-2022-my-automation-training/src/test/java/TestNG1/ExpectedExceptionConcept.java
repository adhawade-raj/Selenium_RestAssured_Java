package TestNG1;

import org.testng.annotations.Test;

public class ExpectedExceptionConcept {

//	Case1:Exception---Test Failed.. Not Handled
//	@Test
//	public void LoginTest()
//	{
//		System.out.println("Login test---starting");
//		int i=9/0;
//		System.out.println("Login Test---Ending");
//	}
	
//-------------------------------------------------------------------	
	
//	Case2:Exception---Test Passed.. Execution Handled
//	@Test(expectedExceptions=ArithmeticException.class)
//	public void LoginTest()
//	{
//		System.out.println("Login test---starting");
//		int i=9/0;
//		System.out.println("Login Test---Ending");
//	}
//-------------------------------------------------------------------
//	Case3:Exception---Test Failed.. Execution not Handled due to Wrong exception.
//	@Test(expectedExceptions=NullPointerException.class)
//	public void LoginTest()
//	{
//		System.out.println("Login test---starting");
//		int i=9/0;
//		System.out.println("Login Test---Ending");
//	}
//-------------------------------------------------------------------
//	Case4:Exception---Test Passed.. Execution Handled. Exception is superclass
//	@Test(expectedExceptions=Exception.class)
//	public void LoginTest()
//	{
//		System.out.println("Login test---starting");
//		int i=9/0;
//		System.out.println("Login Test---Ending");
//	}
//-------------------------------------------------------------------
//	Case5:Exception---Test Passed.. Execution Handled. Throwable is superclass of all exception
//	@Test(expectedExceptions=Exception.class)
//	public void LoginTest()
//	{
//		System.out.println("Login test---starting");
//		int i=9/0;
//		System.out.println("Login Test---Ending");
//	}
	
//-------------------------------------------------------------------
//	Case6:Exception---Multiple Exception = Test Passed.. Execution Handled.
	@Test(expectedExceptions= {Exception.class, Throwable.class})
	public void LoginTest()
	{
		System.out.println("Login test---starting");
		int i=9/0;
		System.out.println("Login Test---Ending");
	}
	
}
