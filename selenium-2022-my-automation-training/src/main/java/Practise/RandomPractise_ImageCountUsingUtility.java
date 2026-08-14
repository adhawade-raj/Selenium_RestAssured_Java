package Practise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RandomPractise_ImageCountUsingUtility {

	
	WebDriver driver;
	RandomPractise_ImageCountUsingUtility(WebDriver driver)
	{
		this.driver=driver;
	}
	/**
	 * 
	 * @param args
	 */
	public List<WebElement> getImageCount(By Locator)
	{
		List<WebElement> element = driver.findElements(Locator);
		
		for(int i=0; i<element.size(); i++)
		{
			String text = element.get(i).getAttribute("src");
			System.out.println(text);
		}
		return element;
	}
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		
		By links = By.tagName("img");
		
		RandomPractise_ImageCountUsingUtility test =new RandomPractise_ImageCountUsingUtility(driver);
		test.getImageCount(links);
		
		
	}

}


