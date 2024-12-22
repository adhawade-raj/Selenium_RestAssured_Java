package allPrograms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part03_Launch_CaptureUrlTitle {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http:/www.amazon.com");
		
		String Title = driver.getTitle();
		System.out.println(Title);
		
		String url = driver.getCurrentUrl();
		System.out.println(url);
		
//		String Source = driver.getPageSource();
//		System.out.println(Source);
		
	}

}
