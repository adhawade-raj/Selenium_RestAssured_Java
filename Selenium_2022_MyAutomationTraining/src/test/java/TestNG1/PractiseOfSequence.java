package TestNG1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PractiseOfSequence {
	
      @BeforeSuite
	public void BeforeSuite()
	{
		System.out.println("Before Suite");
	}
      
      @BeforeTest
      public void BeforeTest()
      {
    	  System.out.println("Before Test");
      }
      
      @BeforeClass
      public void BeforeClass()
      {
    	  System.out.println("BeforeClass");
      }
      
      @BeforeMethod
      public void BeforeMethod2()
      {
    	  System.out.println("BeforeMethod");
      }
      
      @Test
      public void LoginPageTest()
      {
    	  System.out.println("Test");
      }
      
      @AfterMethod
      public void AfterMethodNaame()
      {
    	  System.out.println("AfterMethod");
      }
      
      
      @AfterClass
      public void AfterClassName()
      {
    	  System.out.println("AfterClass");
      }

      @AfterTest
      public void AfterTestName()
      {
    	  System.out.println("AfterTest");
      }
      
      @AfterSuite
      public void AfterSuiteName()
      {
    	  System.out.println("AfterSuite");
      }
      
//      Before Suite
//      Before Test
//      BeforeClass
//      BeforeMethod
//      Test
//      AfterMethod
//      AfterClass
//      AfterTest
      
      
	
}
