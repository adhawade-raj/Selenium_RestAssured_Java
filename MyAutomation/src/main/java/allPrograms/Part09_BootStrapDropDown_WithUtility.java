package allPrograms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part09_BootStrapDropDown_WithUtility {

static WebDriver driver;
		
		public static void main(String[] args) throws InterruptedException {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			Thread.sleep(10000);
			driver.get("https://www.hdfcbank.com/");
			
			List<WebElement> ProdType = driver.findElements(By.xpath("//ul[contains(@Class,'dropdown1 dropdown-menu')]//li"));
			SelectBootStrapDropDown(ProdType,"Cards");
			
		
	}
		
		public static void SelectBootStrapDropDown(List<WebElement> ele, String value)
		{
			
			for(WebElement prod : ele)
			{
				if(prod.getText().equals(value))
				{
					prod.click();
					break;
				}
			}
		}

}
