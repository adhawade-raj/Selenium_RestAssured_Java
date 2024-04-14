package Practise;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowsHandles_04June {
	public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {


		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");	
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//img[@alt='LinkedIn OrangeHRM group']")).click();
//		WebElement Twitter = driver.findElement(By.xpath("//img[@alt='OrangeHRM on twitter']"));
		
	Set<String> handles=	driver.getWindowHandles();
	Iterator<String> it = handles.iterator();
	String ParentWindowId = it.next();
	driver.switchTo().window(ParentWindowId);
System.out.println(driver.getTitle());
	
	String ChildWindoew = it.next();
	driver.switchTo().window(ChildWindoew);
	System.out.println(driver.getTitle());
	
//	LinkedIn.click();
	driver.close();
	}

}
