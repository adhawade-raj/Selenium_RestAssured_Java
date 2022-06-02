package FromSelenium06;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HubSpotLogin_ImplicitWait {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://app.hubspot.com/login");
//		Thread.sleep(2000);
	System.out.println(driver.getTitle());	
		
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		WebElement emailId=driver.findElement(By.id("username"));
		WebElement password=driver.findElement(By.id("password"));
		
		emailId.sendKeys("test@gmail.com");
		
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		password.sendKeys("123");
		
	}

}
