package Selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownUtilJava {

	public static void selectValueFromdropDownByText(WebElement element, String value)
	{
		Select s1 = new Select(element);
		s1.selectByVisibleText(value);
		
	}
	public static ArrayList<String> getdropDownValues(WebElement element)
	{
		System.out.println("-------------");
		Select s1 = new Select(element);
		List<WebElement> dlist = s1.getOptions();
		System.out.println(dlist.size());
		
		ArrayList<String> ar = new ArrayList<String>();
		
		for(int i=0; i<dlist.size(); i++)
		{
			String text = dlist.get(i).getText();

			ar.add(text);
	}
	
	return ar;
}
	/**
	 * Drop Down values without select( DropDownWithoutSelect2 = className)
	 * @param driver
	 * @param locator
	 * @param value
	 */
	public static void selecDropDownvalueWithoutSelect(WebDriver driver, String locator, String value)
	{
		List <WebElement> droplist =  driver.findElements(By.xpath("//*[@id='day\']"));
	    System.out.println(droplist.size());
	    
	    for(int i=0; i<droplist.size(); i++)
	    {
	    	String text = droplist.get(i).getText();
	    System.out.println(text);
	    
	    if(text.equals(value))
	    {
	    	droplist.get(i).click();
	    	break;
	    }
	}
}
}
