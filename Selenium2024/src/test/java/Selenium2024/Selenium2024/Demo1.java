package Selenium2024.Selenium2024;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Demo1 {
	 WebDriver driver;
	 
//	@Before()
//	public void setup() {
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://www.google.com/");
//	}

	
	
	@Test()
	public void test1() throws InterruptedException {
		Assert.assertEquals(false, false);
		System.out.println("One");
		Thread.sleep(5000);
	}
	
	@Test()
	public void test2() throws InterruptedException {
		Thread.sleep(5000);
		Assert.assertEquals(false, false);
		System.out.println("One with same class");
	}
	
	@Ignore()
	@Test()
	public void test3() throws InterruptedException {
		Thread.sleep(5000);
		Assert.assertEquals(false, false);
		System.out.println("One with same class 2");
	}
	
	
//	@After()
//	public void tearDown() {
//		driver.quit();
//	}
}
