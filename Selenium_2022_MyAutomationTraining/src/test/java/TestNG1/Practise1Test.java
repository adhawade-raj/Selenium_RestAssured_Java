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

public class Practise1Test {

  @BeforeSuite
  public void m1Test() {
	  System.out.println("Before Suite");
  }
  
  @BeforeTest()
  public void m2Test() {
	  System.out.println("Before Test");
  }
  
//  @BeforeClass()
//  public void m3Test() {
//	  System.out.println("Before Class");
//  }
  
  @BeforeMethod()
  public void m4Test() {
	  System.out.println("Before Method");
  }
  
  @Test(invocationCount=5, priority=1)
  public void m5Test() {
	  System.out.println("Test");
  }
  
  @Test(enabled=false)
  public void test2() {
	  System.out.println("Test2");
  }
  
  @Test(priority=-1)
  public void test3() {
	  System.out.println("Test3");
  }
  
  @AfterMethod()
  public void m6Test() {
	  System.out.println("After Method");
  }
  
//  @AfterClass()
//  public void m7() {
//	  System.out.println("after Class");
//  }
 
  @AfterTest()
  public void m8() {
	  System.out.println("After test");
  }
  
  @AfterSuite()
  public void m9() {
	  System.out.println("After Suite");
  }
  
  
}
