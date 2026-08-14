package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CaptureErrorText {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");		
		WebDriver driver =new ChromeDriver();
		
		driver.get("https://app.hubspot.com/login");
		
		Thread.sleep(5000);
		
driver.findElement(By.id("username")).sendKeys("abc");
driver.findElement(By.id("password")).sendKeys("123");
//driver.findElement(By.id("logBtn")).click();

Thread.sleep(5000);

String ErrorText = driver.findElement(By.xpath("//*[@id=\'username-validationMessage\']/i18n-string")).getText();
System.out.println(ErrorText);

		
	}

}
