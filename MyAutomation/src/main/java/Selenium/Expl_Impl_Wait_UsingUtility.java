package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Expl_Impl_Wait_UsingUtility {

	public static void main(String[] args) {

		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://app.hubspot.com/login?hubs_signup-url=www.hubspot.com/products/crm&hubs_signup-cta=homepage-nav-login&_ga=2.212361108.1059341697.1652710566-650251035.1652097137");
		
		
		By Email = By.id("username");
		By PassWord =By.id("password");
		By LoginButton = By.id("loginBtn");
		
		Utility utility = new Utility(driver);
		utility.DoSendKeys(Email, "Abc@gmail.com");
		utility.DoSendKeys(PassWord, "123456");
//		utility.doClick(LoginButton);
		utility.ClickWhenReady(LoginButton, 5);
		
		utility.WaitforElemenetToBePresent(Email, 5);
//		utility.WaitfortitleToBePresent(null, 0)
	}

}
