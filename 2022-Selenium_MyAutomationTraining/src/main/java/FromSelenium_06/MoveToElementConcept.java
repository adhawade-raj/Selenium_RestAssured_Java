package FromSelenium_06;

import java.awt.Desktop.Action;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MoveToElementConcept {

	public static void main(String[] args) throws InterruptedException {

		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://mrbool.com/");

		
		Actions action = new Actions(driver);
		
WebElement contentlink = driver.findElement(By.className("menulink"));	
		action.moveToElement(contentlink).build().perform();
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("COURSES")).click();
		
		
		

	}

}
