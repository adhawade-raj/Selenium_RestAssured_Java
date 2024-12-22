package TestNG1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AnnoationConcepts {

	@BeforeSuite
	public void DBConnection()
	{
		System.out.println("BS--DBConnection");
	}
	
	@BeforeTest 
	public void CreateUser()
	{
		System.out.println("BT--CreateUser");
	}
	
	@BeforeClass
	public void LaunchBrowser()
	{
		System.out.println("BC--LaunchBrowser");
	}
	
	@BeforeMethod
	public void LoginToApp()
	{
		System.out.println("BM--Login to App");
	}
	
	@Test
	public void UserInfoTest()
	{
		System.out.println("T--USer Info Test");
	}
	@Test
	public void AccountInfoTest()
	{
		System.out.println("T--AccountInfoTest");
	}
	
//	------->This will not be executed as we have disabled it
	@Test(priority=2, enabled=false) 
	public void AccountInfoTest2()
	{
		System.out.println("T--AccountInfoTest");
	}
	
	@AfterMethod
	public void logout()
	{
		System.out.println("AM--logout");
	}
	
	@AfterClass
	public void CloseBrowser()
	{
		System.out.println("AC--Close Browser");
	}
	@AfterTest
	public void DeleteUser()
	{
		System.out.println("AT--Delete User");
	}
	
//	Innterview Que What is Sequence of Execution-----> Sequence of Execution
//	BS--@BeforeSuite
//	BT--@BeforeTest
//	BC--@BeforeClass
	
//	BM--@BeforeMethod
//	T--@Test                    Test@1
//	AM--@AfterMethod
	
//	BM--BeforeMethod
//	T--@Test                Test@2
//	AM--BeforeMethod
	
//	AC--@AfterClass
//	AT--@AfterTest
//	AS--@AfterSuite
}
