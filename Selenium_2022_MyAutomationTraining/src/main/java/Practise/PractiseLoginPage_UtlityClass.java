package Practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PractiseLoginPage_UtlityClass {

	public static void main(String[] args) {

		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://app.hubspot.com/login");
		
		By emailId =By.id("username");
		By password = By.id("password");
		By LoginButton = By.id("loginBtn");
		
		
		Utility util =new Utility(driver);
		util.DoSendKeys(emailId, "test@gmail.com");
		util.DoSendKeys(password, "Test123");
		util.doClick(LoginButton);
		
	}

}
