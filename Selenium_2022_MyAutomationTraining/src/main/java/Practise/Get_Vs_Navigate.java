package Practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Get_Vs_Navigate {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http:/www.amazon.com");
		System.out.println(driver.getTitle());
		Thread.sleep(5000);
		
		driver.navigate().to("http:/www.amazon.com");
		System.out.println(driver.getTitle());
			
	}

}
