package Selenium;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandling {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");//parent window
		driver.findElement(By.xpath("//*[@id=\"social-icons\"]/a[3]/img")).click();
		
		Set<String> handles = driver.getWindowHandles();
		Iterator <String> it =handles.iterator();
		
		String parenWindowId = it.next();
		System.out.println("p is :" +parenWindowId);
		
		String childWindowID = it.next();
		System.out.println("Ch is :" +childWindowID);
		
		driver.switchTo().window(childWindowID);
		System.out.println("child title :"+driver.getTitle());
	
		driver.close();
		
		driver.switchTo().window(parenWindowId);
		System.out.println("parent title :" +driver.getTitle());

}
	
}
