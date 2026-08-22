package com.booksCart.TechVeritoAssignment;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	LoginPage loginPage;
	AddBooksInCart_ValidateTotal addBooksInCart_ValidateTotal;
	
	
	@BeforeTest()
    public void setup() {
		df = new DriverFactory();
      driver = df.initDriver();
      loginPage = new LoginPage(driver);
      addBooksInCart_ValidateTotal = new AddBooksInCart_ValidateTotal(driver);
    }
	
	@AfterTest()
	public void tearDown() {
		driver.quit();
	}
}
