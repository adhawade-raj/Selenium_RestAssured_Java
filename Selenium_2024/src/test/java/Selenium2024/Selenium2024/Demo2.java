package Selenium2024.Selenium2024;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class Demo2 {

	WebDriver driver;
	
//	@Before()
	public void setup() {
		 driver = new ChromeDriver();
		driver.get("https://www.google.com/");
	}
	
	@Test()
	public void test2() {
		System.out.println("two");
		Assert.assertEquals(false, false);
		Assert.assertEquals(false, false);
		Assert.assertEquals(false, false);
	}
	
//	@After()
//	public void tearDown() {
//		driver.quit();
//	}
}
