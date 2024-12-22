package Selenium;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import io.netty.util.Timeout;

public class Utility {

	WebDriver driver;
	public Utility(WebDriver driver)
	{
	this.driver=driver;	
	}
	
/**
 * 
 * @param Locator
 * @return
 */
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
	
	/**
	 * Using getElement(By element)
	 * @param Locator
	 */
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
	/**
	 *  Using getElement(By element).sendKeys()
	 * @param Locator
	 * @param value
	 */
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
	/**
	 * Using presenceOfAllElementsLocatedBy(By element)
	 * @param Locator
	 * @param Timeout
	 * @return
	 */
	public WebElement WaitforElemenetToBePresent(By Locator, Duration timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Locator));
		return getElement(Locator);
	
	}
	/**
	 *  Using titleContains()
	 * @param Title
	 * @param Timeout
	 * @return
	 */
	public String WaitfortitleToBePresent(String Title, Duration Timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Timeout);
		wait.until(ExpectedConditions.titleContains(Title));
		return driver.getTitle();
	}
	/**
	 * Using elementToBeClickable(By element)
	 * @param Locator
	 * @param Timeout
	 * @return
	 */
	public WebElement WaitforElementToBEClickable(By Locator, Duration Timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Timeout);
		wait.until(ExpectedConditions.elementToBeClickable(Locator));
		return getElement(Locator);
	}
	/**
	 *  Using elementToBeClickable(By element) & Clicking when it becomes clickable
	 * @param Locator
	 * @param Timeout
	 */
	public void ClickWhenReady(By Locator, Duration Timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Timeout);
		wait.until(ExpectedConditions.elementToBeClickable(Locator));
		getElement(Locator).click();
	}
	/**
	 * Using visibilityOf(WebElemnt element)
	 * @param Locator
	 * @param Timeout
	 * @return
	 */
	public WebElement WaitforElementTobeVisible(By Locator, Duration Timeout)
	{
		WebElement element=getElement(Locator);
		WebDriverWait wait = new WebDriverWait(driver, Timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	/**
	 * using urlContains(String Url)
	 * @param Url
	 * @param Timeout
	 * @return
	 */
	public String WaitforUrl(String Url, Duration Timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Timeout);
		wait.until(ExpectedConditions.urlContains(Url));
		return driver.getCurrentUrl();
	}
	/**
	 * 
	 * @param Url
	 * @param Timeout
	 * @return
	 */
	public boolean WaitforAlertToBePresent(String Url, Duration Timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, Timeout);
		wait.until(ExpectedConditions.alertIsPresent());
		return true;
	}
	/**
	 * Fluent Wait
	 * @param driver
	 * @param Locator
	 * @return
	 */
	public WebElement waitForElementwithFluentWait(WebDriver driver, final By Locator) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(15))
			.pollingEvery(Duration.ofSeconds(3))
			.ignoring(NoSuchElementException.class);
	
	WebElement element = wait.until(new Function<WebDriver,WebElement>() {
		public WebElement apply(WebDriver driver)
		{
			return driver.findElement(Locator);
		}
			});
	return element;
	}	
	/**
	 * 
	 * @param locator
	 * @param timeOut
	 * @param intervalTime
	 * @return
	 */
	public WebElement waitForPresenceOfElementWithFluentWait(By locator, int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(intervalTime))
				.ignoring(StaleElementReferenceException.class,
						NoSuchElementException.class).withMessage(locator + " is not present...");

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	/**
	 * 
	 * @param driver
	 * @param Value
	 */
	public void SelectByValue(WebDriver driver, String Value)
	{
		List<WebElement> DropList = driver.findElements(By.xpath("//li/span[@class='comboTreeItemTitle']"));

		for(WebElement e: DropList)
		{
		String Text =	e.getText();
		
			if(Text.equals(Value))
			{
				e.click();
			}
		}
	}
		/**
		 * 
		 * @param driver
		 * @param Value
		 */
		public void SelectByValue2(WebDriver driver, String... Value)
		{
			List<WebElement> DropList = driver.findElements(By.xpath("//li/span[@class='comboTreeItemTitle']"));

			for(int i=0; i<DropList.size(); i++)
			{
			String Text = DropList.get(i).getText();
			
			for(int k=0; i<Value.length; k++)
			
				if(Text.equals(Value[k]))
				{
				DropList.get(i).click();
				}
			}
	}
	}

