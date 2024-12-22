package Practise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class W3School_Row_Col {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		Thread.sleep(5000);
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='customers']//tr"));
		
		List<WebElement> Columns = driver.findElements(By.xpath("//table[@id='customers']//tr/th"));
		
		
		for(WebElement e1: rows)
		{
			String text = e1.getText();
			System.out.println(text);
		}
		
	}

}
