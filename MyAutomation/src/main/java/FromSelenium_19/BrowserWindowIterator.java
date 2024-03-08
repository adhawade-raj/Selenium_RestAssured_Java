package FromSelenium_19;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserWindowIterator {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://opensource-demo.orangehrmLive.com/");
		Thread.sleep(5000);
		
		String ParentWIndowId = driver.getWindowHandle();
		System.out.println(driver.getTitle());
		
		
		driver.findElement(By.xpath("//img[@alt='LinkedIn OrangeHRM group']")).click();
		driver.findElement(By.xpath("//img[@alt='OrangeHRM on Facebook']")).click();
		driver.findElement(By.xpath("//img[@alt='OrangeHRM on twitter']")).click();
		driver.findElement(By.xpath("//img[@alt='OrangeHRM on youtube']")).click();
		
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		
		while(it.hasNext())
		{
			String childWindowId= it.next();
			driver.switchTo().window(childWindowId);
			System.out.println(driver.getTitle());
			driver.close();
		}
//		driver = new ChromeDriver();
		driver.switchTo().window(ParentWIndowId);
		System.out.println(driver.getTitle());
	}

}
