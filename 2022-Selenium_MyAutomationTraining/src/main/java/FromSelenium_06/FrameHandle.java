package FromSelenium_06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameHandle {

	public static void main(String[] args) throws InterruptedException {

		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/guru99home/");
		
		driver.manage().window().maximize();
		driver.switchTo().frame("a077aa5e");
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("html/body/a/img")).click();
		
		

	}

}
