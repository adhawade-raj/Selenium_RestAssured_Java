package TestNG1;

import org.testng.annotations.Test;

public class DependsOnMethod {

//	CASE1: 1st Case is Failed but Others passed. But It is Wrong That login Failed And HomePageTest and SearchTest passed
//	@Test(priority=1)
//	public void LoginTest()
//	{
//		System.out.println("Login Test");
//	     int i=9/0;
//	}
//	@Test(priority=2)
//	public void HomePageTest()
//	{
//		System.out.println("HomePageTest");
//	    
//	}
//	@Test(priority=3)
//	public void SearchTest()
//	{
//		System.out.println("SearchTest");   
//	}
//--------------------------------------------------------------------------------------------------------------------------	
//	CASE2: 1st Case is passed & Others also passed.those are dependant on Test Case1
//	@Test(priority=1)
//	public void LoginTest()
//	{
//		System.out.println("Login Test");
//	     int i=9/0;
//	}
//	@Test(priority=2, dependsOnMethods="LoginTest")
//	public void HomePageTest()
//	{
//		System.out.println("HomePageTest");
//	    
//	}
//	@Test(priority=3, dependsOnMethods="LoginTest")
//	public void SearchTest()
//	{
//		System.out.println("SearchTest");   
//	}
	
//--------------------------------------------------------------------------------------------------------------------------	
//	CASE3: 1st Case is passed & Others also passed.those are dependant on Test Case1
//	@Test(priority=1)
//	public void LoginTest()
//	{
//		System.out.println("Login Test");
//	    
//	}
//	@Test(priority=2, dependsOnMethods="LoginTest")
//	public void HomePageTest()
//	{
//		System.out.println("HomePageTest");
//	    
//	}
//	@Test(priority=3, dependsOnMethods={"LoginTest","HomePageTest"})
//	public void SearchTest()
//	{
//		System.out.println("SearchTest");   
//	}
	
//--------------------------------------------------------------------------------------------------------------------------	
//	CASE4: 1st Case is passed 2nd =Failed And 3rd= Skipped
	@Test(priority=1)
	public void LoginTest()
	{
		System.out.println("Login Test");
	    
	}
	@Test(priority=2, dependsOnMethods="LoginTest")
	public void HomePageTest()
	{
		System.out.println("HomePageTest");
		int i=9/0;
	    
	}
	@Test(priority=3, dependsOnMethods={"LoginTest","HomePageTest"})
	public void SearchTest()
	{
		System.out.println("SearchTest");   
	}
//--------------------------------------------------------------------------------------------------------------------------	
//	Valid
	@Test(priority=1,invocationCount=5, dependsOnMethods="LoginTest")
	public void HomePageTest2()
	{
		System.out.println("HomePageTest2");
		int i=9/0;
	    
	}	
	
}
