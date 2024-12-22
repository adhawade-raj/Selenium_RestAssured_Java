package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatorById {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");		
		WebDriver driver =new ChromeDriver();
		
    	driver.get("https://app.hubspot.com/login?hubs_signup-url=www.hubspot.com/products/crm&hubs_signup-cta=homepage-nav-login&_ga=2.41322916.720657439.1652097137-650251035.1652097137");
WebElement email = driver.findElement(By.id("username"));
email.sendKeys("abc@gmail.com");

WebElement pwd =driver.findElement(By.id("password"));
pwd.sendKeys("123456");

driver.findElement(By.id("loginBtn")).click();

	}

}
