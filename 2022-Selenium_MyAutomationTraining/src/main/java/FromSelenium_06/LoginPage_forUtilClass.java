package FromSelenium_06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage_forUtilClass {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://app.hubspot.com/login");
		
		By emailid =By.id("username");
		By password=By.id("password");
		By loginButton = By.id("loginBtn");
		
		Util util =new Util(driver);
		util.DoSendKeys(emailid, "test@gmail.com");
		util.DoSendKeys(password, "test123");
		util.doClick(loginButton);
		
	}

}
