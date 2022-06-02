package FromSelenium06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebTableHandleUtility {

	
	
	WebDriver driver;
	public WebTableHandleUtility(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public static void RowsCount(WebDriver driver)
	{
	int rowCount = driver.findElements(By.xpath("//table[@id='customers']//tr")).size()-1;
	System.out.println(rowCount);
	
	String beforeXPath ="//*[@id='customers']/tbody/tr[";
	String afterXpath="]/td[1]";
	
	for(int rowNum=2; rowNum<=rowCount+1; rowNum++)
	{
		String ActualXpath = beforeXPath + rowNum + afterXpath;
//		System.out.println(ActualXpath);
		String value = driver.findElement(By.xpath(ActualXpath)).getText();
		System.out.println(value);
	}
}
}
