package Practise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Random2505 {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");	
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//	driver.findElement(By.xpath("//a[@data-testid=\"open-registration-form-button\"]")).click();
		driver.findElement(By.className("inputtext _55r1 _6luy")).sendKeys("abc");
	driver.findElement(By.className("inputtext _55r1 _6luy _9npi")).sendKeys("123");
	}

}
