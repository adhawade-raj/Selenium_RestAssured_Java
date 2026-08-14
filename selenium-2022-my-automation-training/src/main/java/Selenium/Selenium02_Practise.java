package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selenium02_Practise {

	public static void main(String[] args) {


		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://app.hubspot.com/login?hubs_signup-url=www.hubspot.com/products/crm&hubs_signup-cta=homepage-nav-login&_ga=2.41322916.720657439.1652097137-650251035.1652097137");
		
		driver.findElement(By.linkText("Sign up")).click();
		
		
		//driver.findElement(By.xpath("//*[@id='hs-eu-confirmation-button']")).click();
		
	
//			driver.findElement(By.xpath("//*[@id='UIFormControl-2']")).sendKeys("Raj");
	
	
//            driver.findElement(By.xpath("//*[@id='UIFormControl-4']")).sendKeys("adhawde");

//            driver.findElement(By.xpath("//*[@id='UIFormControl-6']")).sendKeys("adhawderaj@gmail.com");


	}

}
