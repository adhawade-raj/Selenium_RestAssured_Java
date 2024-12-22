package Practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UtilityPractise1 {
	
	WebDriver driver;
	public UtilityPractise1(WebDriver driver)
	{
		this.driver=driver;
	}
	public WebElement getWebElement(By Locator)
	{
		WebElement element = driver.findElement(Locator);
		return element;
	}
	public void DoClick(By Locator)
	{
		getWebElement(Locator).click();
	}
	public void DoSendKeys(By Locator, String value)
	{
		getWebElement(Locator).sendKeys(value);
	}
	
}

