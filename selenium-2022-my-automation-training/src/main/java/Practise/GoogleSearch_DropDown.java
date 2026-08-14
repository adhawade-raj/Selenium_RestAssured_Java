package Practise;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleSearch_DropDown {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");	
		Thread.sleep(5000);
//		driver.findElement(By.xpath("//button[@aria-label='No thanks' and @data-dismiss]")).click();
		
				
		WebElement List=driver.findElement(By.xpath("//input[@name='q' and @type='text']"));
//		List.sendKeys("naveen automationlabs");
		
		Actions action = new Actions(driver);
action.sendKeys(List, "naveen automationlabs").build().perform();		
		
		
		
//		List<WebElement> TrendList = driver.findElements(By.xpath("//div[@role='presentation']"));
//		for(WebElement e:TrendList)
//		{
//			String List =e.getText();
//			System.out.println(List);
//		}
		
		
		//*[@id="input"]
	}

}
