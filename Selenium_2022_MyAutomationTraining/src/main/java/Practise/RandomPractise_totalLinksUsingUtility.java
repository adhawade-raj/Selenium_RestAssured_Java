package Practise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RandomPractise_totalLinksUsingUtility {

		WebDriver driver;
		RandomPractise_totalLinksUsingUtility(WebDriver driver)       
		{
			this.driver=driver;
		}
		
		public List<WebElement> getLinks(By locator)
		{
			List<WebElement> element = driver.findElements(locator);
			for(int i=0; i<element.size(); i++)
			{
				String text = element.get(i).getText();
				System.out.println(text);
			}
			return element;
		}
		
		public static void main(String[] args) {
			
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.get("https://www.amazon.in/");
			
			By links = By.tagName("a");
			
			
			RandomPractise_totalLinksUsingUtility test = new RandomPractise_totalLinksUsingUtility(driver);
			test.getLinks(links);
		}

	}

