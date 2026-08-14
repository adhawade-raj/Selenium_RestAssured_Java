package Selenium2024.Selenium2024;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedBusCalendarChallenge {

	static int counter = 0;
	static boolean foundElement = false;
	
	public static void main(String[] args) {
		
//	WebDriverManager.chromedriver().setup();
			
System.setProperty("webdriver.chrome.driver","C:\\Users\\LENOVO\\.m2\\repository\\webdriver\\chromedriver\\win32\\123.0.6212.122\\chromedriver.exe");		
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--remote-allow-origins=*");
	options.addArguments("--disable-notifications");
	WebDriver driver = new ChromeDriver(options);
	
	driver.get("https://www.redbus.in/");
	
	WebElement calendarOption = driver.findElement(By.xpath("//span[text()='Date']"));
	calendarOption.click();
	
	WebElement currentMonth_Year = driver.findElement(By.xpath("(//div[contains(@class,'DayNavigator')])[3]"));
	
	String Month_Year = currentMonth_Year.getText();
	String month = (Month_Year).split(" ")[0].trim();
	String year = (Month_Year).split(" ")[1].trim();
	
	System.out.println(month+ "   "+year);
		
	
	
//	while(!(month.contains("Jun") && year.contains("2024"))) {
//	(//div[contains(@class,'DayNavigator')])[4]
//	(//div[contains(@class,'DayNavigator__IconBlock')])[3]
	
	
//	driver.findElement(By.xpath("(//div[contains(@class,'DayNavigator__IconBlock')])[3]")).click();
//		System.out.println(Month_Year);
//	
//	}
	
	/**
	 * For Weekend Dates pickup
	 * 
	 */
	
//	(//div[contains(@class,'DayNameTitle')])[7]
	
	
	

	
	
}
}
