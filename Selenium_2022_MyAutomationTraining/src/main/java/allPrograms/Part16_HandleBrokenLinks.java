package allPrograms;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part16_HandleBrokenLinks {

	public static void main(String[] args) throws MalformedURLException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.deadlinkcity.com/");
		
		List<WebElement>  links = driver.findElements(By.tagName("a"));
		int brokenLinks =0;
		
		for(WebElement element : links)
		{
			String url = element.getAttribute("href");
			if(url == null || url.isEmpty())
			{
				System.out.println("url is Empty");
				continue;
			}
		URL link = new URL(url);
		try {
			HttpURLConnection httpConn = (HttpURLConnection)link.openConnection();
			if(httpConn.getResponseCode()>=400)
		
			{
				System.out.println(httpConn.getResponseCode() +url+"is" +"brokenLinks");
			}
			else
			{
				System.out.println(httpConn.getResponseCode() +url+"is" +"Valid Links");
			}
		}
			catch(Exception e)
		{
			}
}
	}
}