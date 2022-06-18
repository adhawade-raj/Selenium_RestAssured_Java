package allPrograms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part10_JQueryDropDown {

	static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		Thread.sleep(10000);
		driver.get("https://www.jqueryscript.net/demo/Drop-Down-Combo-Tree/");
		
	driver.findElement(By.xpath("(//input[contains(@id,'justAnInputBox')])[1]")).click();
	SelectChoiceValue(driver, "choice 1", "choice 2");

	}

	public static void SelectChoiceValue(WebDriver driver, String...value)
	{
		List<WebElement> ChoiceList = driver.findElements(By.xpath("(//input[contains(@id,'justAnInputBox')])[1]//following::ul//li"));
	
		if(!value[0].equalsIgnoreCase("all"))
		{
			for(WebElement Item: ChoiceList)
			{
				String text = Item.getText();
				for(String val : value)
				{
				if(text.equals(val))
				{
					Item.click();
					break;
				}
				}
			}
		}
		else
		{
			for(WebElement item : ChoiceList)
			{
				item.click();
				break;
			}
		}
	
	
	
	}
}
