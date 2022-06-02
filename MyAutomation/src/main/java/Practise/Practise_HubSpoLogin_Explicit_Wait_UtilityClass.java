package Practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Practise_HubSpoLogin_Explicit_Wait_UtilityClass {

	WebDriver driver;
	Practise_HubSpoLogin_Explicit_Wait_UtilityClass(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public WebElement getElement(By Locator)
	{
		WebElement element = driver.findElement(Locator);
		return element;	
	}
	
	public void ExplicitWait(By Locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Locator));	
	}
	
		
}
