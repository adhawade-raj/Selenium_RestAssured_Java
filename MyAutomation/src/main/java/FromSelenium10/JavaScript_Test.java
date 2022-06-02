package FromSelenium10;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScript_Test {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://app.hubspot.com/login?hubs_signup-url=www.hubspot.com/products/crm&hubs_signup-cta=homepage-nav-login&_ga=2.212361108.1059341697.1652710566-650251035.1652097137");
	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	System.out.println(JavaScriptUtil.getTitleByJS(driver));
		
		}

}
