package Practise;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait_05June {
public static WebDriver driver;

	public static void main(String[] args) {

		
	}

	public static void waitForElementToBeSelected(By Locator, int TimeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.elementToBeSelected(Locator));
			
	}
	public static void waitForElementToBeSelectedByVisibility(WebElement Locator, int TimeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.visibilityOf(Locator));
			
	}
	
	public static void waitForElementsToBeSelectedByVisibility(WebElement Locator, int TimeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOut));
		wait.until(ExpectedConditions.visibilityOfAllElements(Locator));
			
	}
	
	public static Alert WaitForAlert(int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));	
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	
	
}
