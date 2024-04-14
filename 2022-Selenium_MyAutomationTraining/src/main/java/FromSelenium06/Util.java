package FromSelenium06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Util {

	WebDriver driver;
	public Util(WebDriver driver)
	{
	this.driver=driver;	
	}
	
	
	public WebElement getElement(By Locator)
	{
		WebElement element =null;
		try {
			element = driver.findElement(Locator);
		}
		catch(Exception e)
		{
			System.out.println("some exeption is coming");
			System.out.println(e.getMessage());
		}
		
		return element;
	}
	
	public void doClick(By Locator)
	{
		try {
		getElement(Locator).click();
		}
		
		catch(Exception e)
		{
			System.out.println("coming......");
		System.out.println(e.getMessage());
		}
	}
	
	public void DoSendKeys(By Locator, String value)
	{
		try {
		getElement(Locator).sendKeys(value);
	}
	catch(Exception e)
		{
		System.out.println("some exception is coming");
		System.out.println(e.getMessage());
		}
}
}