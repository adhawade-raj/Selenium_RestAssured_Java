package com.qa.opencart.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;
	
	public ElementUtil(WebDriver driver)
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
		return driver.findElement(Locator);
	}
	/**
	 * 
	 * @param locator
	 * @return
	 */
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	/**
	 * 
	 * @param Locator
	 */
	public void doClick(By Locator)
	{
		getElement(Locator).click();
	}
	/**
	 * 
	 * @param Locator
	 */
	public void doClear(By locator) {
		getElement(locator).clear();
	}
/**
 */
	public void doSendKeys(By locator, String value) {
		doClear(locator);
		getElement(locator).sendKeys(value);
	}
	/**
	 * 
	 * @param Locator
	 * @return
	 */
	public boolean doIsDisplayed(By Locator)
	{
		return getElement(Locator).isDisplayed();
	}
	/**
	 * 
	 * @param Locator
	 * @return
	 */
	public String doGetText(By Locator)
	{
		return getElement(Locator).getText();
	}
	/**
	 * 
	 * @param titleFraction
	 * @param timeOut
	 * @return
	 */
	public boolean waitForTitleContains(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleContains(titleFraction));
	}
	/**
	 * 
	 * @param url
	 * @param timeOut
	 * @return
	 */
	public boolean waitForURLToBe(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlToBe(url));
	}
	/**
	 * 
	 * @param title
	 * @param timeOut
	 * @return
	 */
	public String doGetTitle(String title, int timeOut) {
		if (waitForURLToBe(title, timeOut)) {
			return driver.getTitle();
		}
		return null;
	}
	/**
	 * 
	 * @param urlFraction
	 * @param timeOut
	 * @return
	 */
	public boolean waitForURLToContain(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlContains(urlFraction));
	}
	/**
	 * 
	 * @param Locator
	 * @param TimeOut
	 * @return
	 */
	public List<WebElement> WaitForeElementsToBePresent(By Locator, int TimeOut)
	{
		List<WebElement> element = driver.findElements(Locator);
		for(int i=0; i<element.size(); i++)
		{
			String text = element.get(i).getText();
		}
		return element;	
	}
	/**
	 * 
	 * @param locator
	 * @param timeOut
	 * @param intervalTime
	 * @return
	 */
	public WebElement waitForElementToBeVisibleWithWebElement(By locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}
	/**
	 * 
	 * @param locator
	 * @param timeOut
	 * @param intervalTime
	 * @return
	 */
	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	
	public boolean waitForTitleToBe(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleIs(title));
	
}
	
	/**
	 * 
	 * @param driver
	 * @param horizontalValue
	 * @param verticalValu
	 */
	public void scrollDownWithRnTimeInput(WebDriver driver, int horizontalValue,int verticalValu ) {
		String a ="window.scrollBy("+horizontalValue;
		String b= ",";
		String c=verticalValu+")";
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(a+b+c, "");
	}

}

