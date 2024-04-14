package Selenium2024.Selenium2024;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriver121_Trials {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","E:\\Eclipse\\WorkSpace\\chromedriver 121g\\chromedriver.exe");  
	    
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
//		options.addArguments("headless");
		WebDriver driver = (WebDriver) new ChromeDriver();	
		driver.get("https://www.google.com");
		
	}

}
