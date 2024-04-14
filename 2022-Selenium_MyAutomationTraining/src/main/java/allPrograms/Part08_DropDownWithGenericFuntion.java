package allPrograms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part08_DropDownWithGenericFuntion {

	static WebDriver driver;
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.orangehrm.com/orangehrm-30-day-trial/?");
	
		WebElement CList = driver.findElement(By.xpath("//select[contains(@name,'Country')]"));
		
//		Select select = new Select(CList);
//		select.selectByVisibleText("India");
		Part08_DropDownWithGenericFuntion.SelectOptionFromDropDown(CList, "India");
	}

	public static void SelectOptionFromDropDown(WebElement ele, String value)
	{
		Select select = new Select(ele);
		java.util.List<WebElement> DropList = select.getOptions();
		for(WebElement option : DropList)
		{
			if(option.getText().equals(value))
			{
				option.click();
			}
		}
		
	}
		
	}


