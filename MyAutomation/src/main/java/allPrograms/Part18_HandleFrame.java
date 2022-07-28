package allPrograms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part18_HandleFrame {

	
//	INCOMPLETE PROGRAM
	
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.selenium.dev/selenium/docs/api/java/index.html?overview-summary_html");
		
		Thread.sleep(3000);
//		1st Frame
		driver.switchTo().frame("packageListFrame");
		driver.findElement(By.xpath("//a[normalize-space()='org.openqa.selenium']")).click();
		driver.switchTo().defaultContent();
		
		Thread.sleep(3000);
//		2nd Frame
		driver.switchTo().frame("packageFrame");
		driver.findElement(By.xpath("//a[normalize-space()='WebDriver']")).click();
		driver.switchTo().defaultContent();
		

		
	}

}
