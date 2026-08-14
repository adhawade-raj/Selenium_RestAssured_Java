package Selenium;

import java.util.Set;
import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PopUpHandlingAssignment {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");//parent window

		driver.findElement(By.xpath("//*[@id='social-icons\']/a[3]")).click();
		
		Set<String> handles = driver.getWindowHandles();
		
		System.out.println(handles);
		
		Iterator<String> it = handles.iterator();
		String pid = it.next();
		while (it.hasNext()) {
				String id = it.next();
				driver.switchTo().window(id);
				System.out.println(driver.getTitle());
				driver.close();
		}
		driver.switchTo().window(pid);
		System.out.println(driver.getTitle());
		System.out.println("---------------------");
		
	driver.findElement(By.xpath("//*[@id='social-icons\']/a[2]/img")).click();
		
System.out.println("Parent :"+driver.getTitle());

Set<String> handles2 = driver.getWindowHandles();
System.out.println(handles2);

Iterator<String> it2 = handles2.iterator();

String paid = it2.next();
while (it2.hasNext()) {
		String id2 = it2.next();
		driver.switchTo().window(id2);
		System.out.println(driver.getTitle());
		driver.close();
		
		driver.switchTo().window(paid);
		System.out.println(driver.getTitle());

	}

}
}
