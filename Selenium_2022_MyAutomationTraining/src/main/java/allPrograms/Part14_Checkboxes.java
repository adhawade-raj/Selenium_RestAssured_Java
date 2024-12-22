package allPrograms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part14_Checkboxes {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://itera-qa.azurewebsites.net/home/automation");
		
//		To select particular checkbox
		driver.findElement(By.xpath("//input[@id='female']")).click();
		
//		To select all checkbox
		List<WebElement> checkboxes =driver.findElements(By.xpath("//input[@class='form-check-input' and contains(@id,'day')]"));
		
	System.out.println("Size is :"+checkboxes.size());
	
//	for(int i =0; i<checkboxes.size(); i++)
//	{
//		checkboxes.get(i).click();
//		
//	}
//	To select all checkbox -- for Each loop
//	for(WebElement e : checkboxes)
//	{
//		e.click();
//	}
	
//	--------------------------------
//	To select Multiple checkbox
	
//	for(WebElement e : checkboxes)
//	{
//		String checkboxName= e.getAttribute("id");
//		if(checkboxName.equals("monday") || checkboxName.equals("sunday"))
//		{
//			e.click();
//		}
//	}
	
//	----------------------------------
//	To select Last2 checkbox
	
	int checkboxSize = checkboxes.size();
//	
//	for(int i=checkboxSize-2; i<checkboxSize; i++)
//	{
//		checkboxes.get(i).click();
//	}
	
//	----------------------------------
//	To select first2 checkbox
	
	for(int i=0; i<checkboxSize; i++)
	{
		if(i<2)
		{
			checkboxes.get(i).click();
		}
	}
		
	}

}
