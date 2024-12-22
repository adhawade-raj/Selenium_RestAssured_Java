package Practise;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RandomPractise2 {
static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com");		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		Thread.sleep(5000);
		
		WebElement Daylist= driver.findElement(By.xpath("//select[@id='day'and @title='Day']"));
		Thread.sleep(2000);

		 WebElement month = driver.findElement(By.id("month"));
		WebElement YearList = driver.findElement(By.xpath("//select[@id='year'and @title='Year']"));

//		WebDriverWait wait = new WebDriverWait(driver, 15);
//		wait.until(ExpectedConditions.visibilityOf(Daylist));
		
		RandomPractise2.DropDownValue(Daylist, "25");
//		RandomPractise2.DropDownValue(month, "Oct");
		RandomPractise2.DropDownValue(YearList, "1996");
	}
		public static void DropDownValue(WebElement element, String value)
		{		
		Select select = new Select(element);
		select.selectByValue(value);
		}
		
		
		
	}


