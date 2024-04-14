package allPrograms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part11_Sorted_Unsorted_DropDowns {

	public static void main(String[] args) throws InterruptedException {

		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		Thread.sleep(10000);
		driver.get("https://www.twoplugs.com/");
		
		driver.findElement(By.xpath("//a[text()='Live Posting']")).click();
		
		WebElement drpList = driver.findElement(By.xpath("//select[@name='category_id']"));
		Select select = new Select(drpList);
		
		List<WebElement> options = select.getOptions();
		
		
		ArrayList originalList = new ArrayList();
		ArrayList tempList = new ArrayList();
		
		for(WebElement option : options)
		{
			originalList.add(option.getText());
			tempList.add(option.getText());
		}
		System.out.println("Original list:" +originalList);
		System.out.println("temp list:" +tempList);
		
		Collections.sort(tempList);
		System.out.println("Original list:" +originalList);
		System.out.println("temp list:" +tempList);
		
		if(originalList.equals(tempList))
		{
			System.out.println("DropDown Soretd");
		}
		else
		{
			System.out.println("DropDown not Soretd");
		}
		driver.close();
		
		
				
		
		
		
	}

}
