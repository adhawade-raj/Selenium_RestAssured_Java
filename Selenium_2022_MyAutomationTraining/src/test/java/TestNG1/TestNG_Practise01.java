package TestNG1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNG_Practise01 {

	WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://app.hubspot.com/login");
//		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();	
	}
	
	@Test
	public void LoginPageTest()
	{
	String flag = driver.getTitle();
	Assert.assertEquals(flag, "HubSpot Login");
	}
	
	
	@Test
	public void LoginTest()
	{
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("adhawaderaj@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Justbring@21");
		driver.findElement(By.xpath("//i18n-string[text()='Log in']")).click();
		
		String Text=driver.getTitle();
		Assert.assertEquals(Text, "HubSpot Login");
		
	}
	
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
}
