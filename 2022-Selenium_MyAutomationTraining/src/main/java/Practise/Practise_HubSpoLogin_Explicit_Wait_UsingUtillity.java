package Practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Practise_HubSpoLogin_Explicit_Wait_UsingUtillity {

	public static void main(String[] args) {

		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://app.hubspot.com/login");
		
		By mail = By.id("username");
		
		Practise_HubSpoLogin_Explicit_Wait_UtilityClass test = new Practise_HubSpoLogin_Explicit_Wait_UtilityClass(driver);
		WebElement text = test.getElement(mail);
		text.sendKeys("128795");
		
		test.ExplicitWait(mail, 5);
		
		
		
	}

}
