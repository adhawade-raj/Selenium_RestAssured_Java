package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


//LocatorBY.Name
public class LocatorConcept {

	public static void main(String[] args) {
		

		System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");		
		WebDriver driver =new ChromeDriver();
	
		
		driver.get("https://classic.crmpro.com/index.html");
		driver.findElement(By.name("username")).sendKeys("abd");
		
		driver.findElement(By.name("password")).sendKeys("tbdn");
		


    	
	}

}
