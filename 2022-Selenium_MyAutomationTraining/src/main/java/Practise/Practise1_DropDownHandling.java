package Practise;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Practise1_DropDownHandling {

	public static void selectDropDownByText2(WebElement element, String value)
	{
		
		Select select = new Select(element);
		select.selectByVisibleText(value);
		
	}
		
	/**
	 * this is to return dropdown values in ArrayList
	 * @param element
	 * @return
	 */
		public static ArrayList<String> getdropDownValues2(WebElement element)
		{
			System.out.println("---------------------");
			Select select = new Select(element);
			List<WebElement> list =select.getOptions();
			System.out.println(list.size());
			
			ArrayList<String> ar=new ArrayList<String>();
			for(int i=0; i<list.size(); i++)
			{
				String text = list.get(i).getText();
				ar.add(text);
				
			}
			return ar;
			
		
	}
}

