package allPrograms;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part13_AutoCompleteGooglePlacesDropDown {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		Thread.sleep(5000);
		driver.get("https://www.twoplugs.com/newsearchserviceneed");
		
		 Thread.sleep(5000);
		WebElement SearchBox = driver.findElement(By.id("autocomplete"));
		SearchBox.clear();
		
		SearchBox.sendKeys("Toronto");
		String text;
		
		do
		{
				SearchBox.sendKeys(Keys.ARROW_DOWN);
				Thread.sleep(5000);
				text = SearchBox.getAttribute("value");
				if(text.equals("Toronto, OH, USA"))
				{
					SearchBox.sendKeys(Keys.ENTER);
				break;
				}
			
		}while(!text.isEmpty());
	}
}
	
	
		
		
	


