package allPrograms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part05_GetAndNavigate {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http:/www.amazon.com");
		driver.get("https://www.snapdeal.com/");
		
		System.out.println(driver.getTitle());
//		Thread.sleep(5000);
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.close();
		
		driver = new ChromeDriver();
		
		driver.navigate().to("https://www.snapdeal.com/");
		System.out.println(driver.getTitle());
		System.out.println(driver.getTitle());
	}

}
