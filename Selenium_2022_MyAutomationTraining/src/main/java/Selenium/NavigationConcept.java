package Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationConcept {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");		
		WebDriver driver =new ChromeDriver();
		
    	driver.get("http://www.google.com");
//		System.out.println(driver.getTitle());

	driver.navigate().to("https://www.amazon.in/");
	System.out.println(driver.getTitle());
	
	driver.navigate().back();
	driver.navigate().forward();
	driver.navigate().back();
	System.out.println(driver.getTitle());
		

	}

}
