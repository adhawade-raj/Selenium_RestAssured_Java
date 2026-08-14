package FromSelenium_06;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JqueryDropDownUtility {

	WebDriver driver;
	public JqueryDropDownUtility(WebDriver driver) 
	{
	this.driver=driver;
	}
	
	/**
	 * For Selection of only one value from Drop Down
	 * @param driver
	 * @param Value
	 */
	public static void ChoiceValues(WebDriver driver, String Value)
	{
 List<WebElement> ChoiceList=driver.findElements(By.xpath("//div[@class= 'comboTreeDropDownContainer']//ul//li//span[@class='comboTreeItemTitle']"));
	for(int i=0; i<ChoiceList.size(); i++)
	{
		String text = ChoiceList.get(i).getText();
		System.out.println(text);
		if(text.equals(Value))
		{
			ChoiceList.get(i).click();
			break;
		}	
	}
	}
	
		/**
		 * For Selection of Multiple Values from Drop Down
		 * @param driver
		 * @param Value
		 */
	public static void ChoiceValues1(WebDriver driver, String... Value)
	{
 List<WebElement> ChoiceList1=driver.findElements(By.xpath("//div[@class= 'comboTreeDropDownContainer']//ul//li//span[@class='comboTreeItemTitle']"));
	for(int i=0; i<ChoiceList1.size(); i++)
	{
		String text = ChoiceList1.get(i).getText();
		System.out.println(text);
			
		for(int k=0; k<Value.length; k++)
		{
			
		if(text.equals(Value[k]))
		{
			ChoiceList1.get(i).click();
			break;
		}	
	}
	} 
	}
	
	public static void ChoiceValues2(WebDriver driver, String... Value)
	{
 List<WebElement> ChoiceList2=driver.findElements(By.xpath("//div[@class= 'comboTreeDropDownContainer']//ul//li//span[@class='comboTreeItemTitle']"));
	if(!Value[0].equalsIgnoreCase("ALL"))
	{
		for(int i=0; i<ChoiceList2.size(); i++)
	{
		String text = ChoiceList2.get(i).getText();
		System.out.println(text);
			
		for(int k=0; k<Value.length; k++)
		{
			
		if(text.equals(Value[k]))
		{
			ChoiceList2.get(i).click();
			break;
		}	
	}
	} 
	}
	
	else
	{
		try {
		for(int all=0; all<ChoiceList2.size(); all++)
		{
			ChoiceList2.get(all).click();
		}
		}
		catch(Exception e)
		{
			System.out.println("Some exception is coming");
		}
	}
}
}
	
