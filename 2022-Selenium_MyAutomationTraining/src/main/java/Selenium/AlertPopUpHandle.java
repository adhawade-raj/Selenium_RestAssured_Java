package Selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertPopUpHandle {

	public static void main(String[] args) throws InterruptedException {
		

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.name("proceed")).click();
		Thread.sleep(5000);
	Alert alert =driver.switchTo().alert();
	String text =alert.getText();
	System.out.println(text);
	
	if(text.equals("abc"));
	{
		System.out.println("pass");   //why it is passing with abc?
	}
//	else
//	{
//	System.out.println("Fail");   //Error if else part
//	 }
	//alert.accept();
	  alert.dismiss();
	
	}		
	}

