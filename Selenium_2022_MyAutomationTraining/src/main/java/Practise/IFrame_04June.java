package Practise;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IFrame_04June {
	public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		
			


					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert");	
					Thread.sleep(5000);
//					driver.switchTo().frame(2);
					driver.switchTo().frame("iframeResult");
					
					driver.findElement(By.xpath("//button[text()='Try it']")).click();
					//button[text()='Try it']
					
					Alert alert = driver.switchTo().alert();
					alert.accept();
					
	}

}
