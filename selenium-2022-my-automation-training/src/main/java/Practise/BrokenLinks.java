package Practise;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinks {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(co);
		driver.get("http://www.deadlinkcity.com/");
		
		
		List<WebElement> broken = driver.findElements(By.tagName("a"));
		
		for(WebElement e: broken)
		{
			String text = e.getAttribute("href");
			if(text==null || text.isEmpty())
			{
				System.out.println("URl is Empty");
			}
		URL url = new URL(text);
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		
		if(http.getResponseCode()>=400)
			System.out.println("Links are broken"+http.getResponseCode()+" "+text);
		
		else {
			System.out.println("Links are  Valid"+http.getResponseCode()+" "+text);
		}
		
				
		}
	}

}
