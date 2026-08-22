package com.booksCart.TechVeritoAssignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;
	private Utilities utilities;
	
	public 	LoginPage(WebDriver driver) {
	this.driver=driver;
	utilities = new Utilities(driver);
	}
	
	/**Locating Elements*/
	private By userProfile = (By.xpath("//a[@href='/profile']"));
	private By email = (By.xpath("//input[@required and @type='Email']"));
	private By password = (By.xpath("//input[@required and @type='Password']"));
	private By submitButton = (By.xpath("//button[@type='submit']"));
	private By successMsg = (By.xpath("//a[@href='/profile/setting']/parent::div"));
	
	
	/**
	 * 
	 */
	public String loginPage() {

	WebElement userProfileField = driver.findElement(userProfile);
	userProfileField.click();
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
    
     
     /**Steps To Enter Credentials*/
		doLogin();
   
     /**Step to validate the registration*/     
     utilities.addThreadSleep(2000);
     userProfileField.click();
    
    /**Step To capture the header Text*/
     utilities.addThreadSleep(1000);
	String userName = getRegisteredUsername();
	return userName;
}
	
	
	public void doLogin() {
		driver.findElement(email).sendKeys("adhawaderaj97@gmail.com");
	     driver.findElement(password).sendKeys("Raj@21101997");
	     driver.findElement(submitButton).click();
	}
	/**
	 * Function to capture text
	 * @return
	 */
	 public String getRegisteredUsername() {
	     WebElement successMessage = driver.findElement((successMsg));
	     String userName = successMessage.getText();
	     System.out.println(userName);
	     return userName;
	   }
}