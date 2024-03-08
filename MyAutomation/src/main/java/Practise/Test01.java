package Practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test01 {

	public static void main(String[] args) throws InterruptedException {


		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.orangehrm.com/");
		Thread.sleep(5000);
		String text = driver.getTitle();
		System.out.println(text);

	}

}
