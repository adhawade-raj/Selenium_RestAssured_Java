package Practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PractiseDropDown2 {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		 driver.get("https://www.facebook.com/");
		 
		 driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		 Thread.sleep(5000);
		 WebElement Day= driver.findElement(By.xpath("//select[@id='day']"));
		 
		 WebElement Month = driver.findElement(By.xpath("//select[@aria-label='Month']"));
		 WebElement Year = driver.findElement(By.xpath("//select[@id='year']"));
		 
		 
		 Select select = new Select(Day);
		 select.selectByValue("20");

		 Select select2 = new Select(Month);
		 select2.selectByValue("Oct");
		
		
	}

}
