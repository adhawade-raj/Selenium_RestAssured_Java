package FromSelenium06;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MoveToElementAssignment {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.tcs.com/");
	Thread.sleep(10000);
		

		Actions action = new Actions(driver);
		WebElement link=driver.findElement(By.cssSelector("WebElement link=driver.findElement"));
//		WebElement link=driver.findElement(By.id("w-dropdown-list-0"));
//		WebElement link = driver.findElement(By.className("nav_dropdown_list w-dropdown-list"));
		
		action.moveToElement(link).build().perform();
		Thread.sleep(10000);
		
		
		
	}

}
