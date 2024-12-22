package Practise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BigBasket_xPath_and_MoveToEle {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.bigbasket.com/?utm_source=google&utm_medium=cpc&utm_campaign=Brand-PUN&gclid=Cj0KCQjwheyUBhD-ARIsAHJNM-Mg2YfUv9GQPpKhZLEMzFFSZGGQU7s5DzH-n1QAvEGo0HSXWmMUDj4aAjNmEALw_wcB");	
		Thread.sleep(10000);
		WebElement DropDown = driver.findElement(By.xpath("//li/a[@data-toggle='dropdown']"));
		Actions action = new Actions(driver);
		
		action.moveToElement(DropDown).perform();
		
		List<WebElement> List = driver.findElements(By.xpath("//ul[@id='navBarMegaNav']"));
		for(WebElement e: List)
		{
			String text = e.getText();
			System.out.println(text);
		}
		
		
		
	}

}
