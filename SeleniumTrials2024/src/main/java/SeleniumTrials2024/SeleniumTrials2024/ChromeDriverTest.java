package SeleniumTrials2024.SeleniumTrials2024;

import org.openqa.selenium.By;  
import org.openqa.selenium.JavascriptExecutor;  
import org.openqa.selenium.WebDriver;  
import org.openqa.selenium.chrome.ChromeDriver;  

public class ChromeDriverTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","E:\\Eclipse\\WorkSpace\\chromedriver 121\\chromedriver.exe");  
	      
			WebDriver driver = (WebDriver) new ChromeDriver();	
			driver.get("https://www.google.com");

  
	}

}
