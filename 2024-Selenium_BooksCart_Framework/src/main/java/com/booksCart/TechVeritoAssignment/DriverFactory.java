package com.booksCart.TechVeritoAssignment;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriverFactory {
    
	WebDriver driver;
	
	public WebDriver initDriver() {
		driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.get("https://www.99bookscart.com/");
	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	    
	    return driver;
	}
	
    
}
