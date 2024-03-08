package Selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TotalImageCount {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.amazon.in");
		
		List<WebElement> imageslist=driver.findElements(By.tagName("img"));
		System.out.println(imageslist.size());
		
		
		for(int A=0; A<imageslist.size(); A++)
		{
		
		String imageurl = imageslist.get(A).getAttribute("src");
System.out.println(imageurl);
		
	}

}
}
