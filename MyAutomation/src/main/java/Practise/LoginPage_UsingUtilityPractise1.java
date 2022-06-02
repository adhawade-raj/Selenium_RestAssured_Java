package Practise;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage_UsingUtilityPractise1 {

	public static void main(String[] args) {

		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://app.hubspot.com/login/");
		
		
		By emailId=By.id("username");
		By password=By.id("password");
		By LoginButton = By.id("loginBtn");
		By msg = By.cssSelector("#username-validationMessage > i18n-string");
		
		UtilityPractise1 test =new UtilityPractise1(driver);
		test.DoSendKeys(emailId, "Test#gmail.com");
		test.DoSendKeys(password, "test");
		test.DoClick(LoginButton);
		System.out.println(test.getWebElement(msg).getText());
		
		
				
	}
		
		
	}


