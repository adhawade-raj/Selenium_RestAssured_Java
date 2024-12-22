package Selenium;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class NaukriPopUpHandle {

	
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.naukri.com/");
		// collect the window IDs:
		Set<String> handles = driver.getWindowHandles();

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
}
}
