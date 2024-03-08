package FromSelenium06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Util2 {
	WebDriver driver;
	
	public Util2(WebDriver driver) {
		this.driver=driver;
	}

	/**
	 * 
	 * @param Locator
	 * @return
	 */
	public WebElement getElement(By Locator) {
		WebElement element = driver.findElement(Locator);
		return element;
	}
	
	/**
	 * finding element & then clicking
	 * @param element
	 */
	public void doClick(By element) {
		driver.findElement(element).click();
	}
	
	/**
	 * Calling getElement Method
	 * @param element
	 */
	public void doClick2(By element) {
		getElement(element).click();
	}
	
	public void doSendKeys(By element, String value) {
		getElement(element).sendKeys(value);
	}
	
	
	
	
}
