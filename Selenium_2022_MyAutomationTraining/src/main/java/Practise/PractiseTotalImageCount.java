package Practise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PractiseTotalImageCount {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.amazon.in");
		
		List<WebElement> list =driver.findElements(By.tagName("img"));
		System.out.println(list.size());

		for(int i=0; i<list.size(); i++)
		{
			String text = list.get(i).getAttribute("src");
			System.out.println(text);
		}
	}

}
