package Practise;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Alert2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(co);
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		Thread.sleep(5000);
		
		
//		WebElement jsAlert = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
//		jsAlert.click();
//		driver.switchTo().alert().accept();
		
		Thread.sleep(2000);
		WebElement jsAlert2 = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
		jsAlert2.click();
		
		Alert alert = driver.switchTo().alert();
		alert.sendKeys("Raj");
		Thread.sleep(2000);
		alert.accept();
		
		
		
		

		
	}

}
