package Selenium;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FluentWaitConcept {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://classic.crmpro.com/index.html");
		
		final By username = By.name("username");
		By Password = By.name("password");
		By Login = By.xpath("//input[@class='btn btn-small']");
	
		Utility utility = new Utility(driver);
//		utility.waitForPresenceOfElementWithFluentWait(username, 5, 2);
		utility.waitForElementwithFluentWait(driver, username);
		utility.waitForElementwithFluentWait(driver, Login);
		utility.getElement(username).sendKeys("abc");
		utility.getElement(Password).sendKeys("12356");
		
		utility.doClick(Login);
		}
	
	
}




