package allPrograms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part16_HandleLinks {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		
//		driver.findElement(By.linkText("Best Sellers")).click();
//		driver.findElement(By.partialLinkText("Sellers")).click();
		
//		How to capture all links
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("total links ::" +links.size());
		
		for(int i=0; i<links.size(); i++)
		{
			
			if(!links.isEmpty())
			{
			
			System.out.println("-----------");
			System.out.println(links.get(i).getText());
			System.out.println("-----------");
			System.out.println("links name:"+links.get(i).getAttribute("href"));
			}
		}
		
//		-----------------------------------------------
//		For Each Loop
//		for(WebElement link : links)
//		{
//			System.out.println(link.getText());
//			System.out.println(link.getAttribute("href"));
//		}
		
	}

}
