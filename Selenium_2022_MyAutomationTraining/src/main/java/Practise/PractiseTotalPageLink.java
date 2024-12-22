package Practise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PractiseTotalPageLink {

	public static ArrayList<String> GetLink(String locator)
	{

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.amazon.in");
		
		List<WebElement> list1 =driver.findElements(By.tagName("a"));
		System.out.println(list1.size());
		
		ArrayList<String> ar =new ArrayList<String>();
		
		for(int i=0; i<list1.size();i++)
		{
			String text =list1.get(i).getText();
			ar.add(text);
		}
		return ar;
	}
			static String a="list1";
			public static void main(String[] args) {
				
				ArrayList<String> DayLi=PractiseTotalPageLink.GetLink(a);
				System.out.println(DayLi);
				
				
				
			}
//		if(list1.containsAll(list1))
//		{
//			System.out.println();
//			System.out.println("empty links");
//			System.out.println("=============");
		}
	



