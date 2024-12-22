package FromSelenium_19;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SVGElementConcept {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://petdiseasealerts.org/forecast-map#/");
		Thread.sleep(5000);
			
		String SvgxPath= "//*[local-name()='svg' and @id='map-svg']//*[name()='g' and @id='states']/*[name()='g']/*[name()='path']";
				
		List<WebElement> StatesList= driver.findElements(By.xpath(SvgxPath));
//		//*[local-name()='svg' and @id='map-svg']//*[name()='g' and @id='states']/*[name()='g']/*[name()='path' and @name='Washington']---need to move to countries
		for(WebElement e: StatesList)
		{
			Actions act = new Actions(driver);
			act.moveToElement(e).perform();
			
			String Name = e.getAttribute("name");
			System.out.println(Name);
			
			if(Name.equalsIgnoreCase("Washington"))
			{
				e.click();
				break;
			}
		}

	
	
	
	}

}
