package Practise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class June04_JQuery {
static WebDriver driver;

	public static void main(String[] args) {

		
		
		//div[@class='comboTreeDropDownContainer']//li[@class='ComboTreeItemChlid']
	
	}
		public static void SelectByValue123(WebDriver driver, String value)
		{
			List<WebElement> DropDownList = driver.findElements(By.xpath("//div[@class='comboTreeDropDownContainer']//li[@class='ComboTreeItemChlid']"));
			for(WebElement e: DropDownList)
			{
				
			String Text =	e.getText();
			
				if(Text.equals(value))
				{
					e.click();
				}
			}
		}
		
		public static void SelectByValue1(WebDriver driver, String... value)
		{
			List<WebElement> DropDownList = driver.findElements(By.xpath("//div[@class='comboTreeDropDownContainer']//li[@class='ComboTreeItemChlid']"));
			for(int i=0; i<DropDownList.size(); i++)
			{
			String Text = DropDownList.get(i).getText();
			
			for(int k=0; k<value.length; k++)
			{
				if(Text.equals(value[k]))
				{
					DropDownList.get(i).click();
				}
			}
			
				
			}
		}
			
	

}
