package Selenium2024.Selenium2024;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTableHandling {

	static String country;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://money.rediff.com/gainers");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
	List<WebElement> companyList = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[1]/a"));
	for(WebElement e: companyList) {
//		System.out.println(e.getText());
		country =e.getText();
//		if(e.getText().contains("Siyaram Recycling In")) {
//			
//		}
	}
	
	
	}

}
