package FromSelenium10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Reversing_x_path {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver  driver = new ChromeDriver();
		driver.get("https://app.hubspot.com/login?hubs_signup-url=www.hubspot.com/products/crm&hubs_signup-cta=homepage-nav-login&_ga=2.144324405.1059341697.1652710566-650251035.1652097137");
		
		System.out.println(driver.findElement(By.xpath("//input[@id='username']//..//../..//..//..")).getAttribute("id"));
	}

}
