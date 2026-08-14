package Selenium2024.Selenium2024;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalendarHandling {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		
	driver.get("https://www.ixigo.com/flights?utm_source=google&utm_medium=paid_search_google&utm_campaign=ixigo_brand&gad_source=1&gclid=CjwKCAiA44OtBhAOEiwAj4gpOYtzWQIff56A7igZjnabcc7SLZlWnLh4LNdrZP0I7txbKxTMDwUWSBoCK0EQAvD_BwE");
	driver.manage().window().fullscreen();
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		
	WebElement departureCalendar = driver.findElement(By.xpath("//input[@placeholder='Depart']"));
	departureCalendar.click();
		
//	button[@type='button']/following-sibling::div
//	(//div[@class='rd-month-label'])[1]
	String captureCurrentMonth = driver.findElement(By.xpath("(//div[@class='rd-month-label'])[1]")).getText();

	
	while(!captureCurrentMonth.contains("June 2024")) {
		  driver.findElement(By.xpath("//button[@class='ixi-icon-arrow rd-next']")).click();
		captureCurrentMonth = driver.findElement(By.xpath("//button[@type='button']/following-sibling::div")).getText();
		System.out.println(captureCurrentMonth);	
	}
		
	}

}
