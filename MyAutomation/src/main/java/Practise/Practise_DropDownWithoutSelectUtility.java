package Practise;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Practise_DropDownWithoutSelectUtility {

	public static ArrayList<String> selecDropDownvalueWithoutSelect(WebDriver driver, String locator)
	{
		
		List <WebElement> list1= driver.findElements(By.xpath("//*[@id='month']"));
		System.out.println(list1.size());
		
		ArrayList<String> ar= new ArrayList<String>();
		for(int i=0; i<list1.size(); i++)
		{
			String text = list1.get(i).getText();
			ar.add(text);
			
			if(text.equals("Oct"))
			{
			 list1.get(i).click();
			 break;
			}
		}
		return ar;
	}
	
	
	
}
