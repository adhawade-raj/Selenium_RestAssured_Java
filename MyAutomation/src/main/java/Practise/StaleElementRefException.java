package Practise;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class StaleElementRefException {

	public static void main(String[] args) throws InterruptedException {
		
System.setProperty("webdriver.chrome.driver", "C:/Program Files/Google/Chrome/Application/chromedriver-win64/chromedriver.exe");
		
	  	ChromeOptions options = new ChromeOptions();
	  	options.addArguments("--remote-allow-origins=*");
		
	  	WebDriver driver = new ChromeDriver(options);
		 //driver.manage().window().fullscreen();
		 driver.manage().deleteAllCookies();
		
		 driver.get("https://www.pavanonlinetrainings.com/");
		 Thread.sleep(3000);
		 WebElement link = driver.findElement(By.xpath("//a[text()='Udemy Courses']"));
		 link.click();
		 
		 driver.navigate().back();
		 Thread.sleep(5000);
		 try {
			 link.click();
		 }
		 catch(StaleElementReferenceException e) {
			 link = driver.findElement(By.xpath("//a[text()='Udemy Courses']"));
			 link.click();
		 }
		 
	
	}

}
