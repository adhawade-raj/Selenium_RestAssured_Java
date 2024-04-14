package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatorByLinkText {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");		
		WebDriver driver =new ChromeDriver();
		
		driver.get("https://app.hubspot.com/login?hubs_signup-url=www.hubspot.com/products/crm&hubs_signup-cta=homepage-nav-login&_ga=2.41322916.720657439.1652097137-650251035.1652097137");
		
		driver.findElement(By.linkText("Sign up")).click();
		
		
		

	}

}
