package TestNG1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SignUp {

	WebDriver driver;
	By signUp = By.linkText("Sign up");
	By CreateAccountPage = By.linkText("Create your free account");
	
	
	

	@BeforeTest
	@Parameters({"url","browser"})
	public void setup(String urlName, String browserName) throws Exception
	{
		System.out.println("before method... setup");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("pass Correcr browser name");
			throw new Exception("NoSuchBrowserException");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(urlName);
		driver.manage().window().maximize();	
	}
	
	@Test(priority=1)
	public void VerifyAccountTest() throws InterruptedException {
		driver.findElement(signUp).click();
		
		Thread.sleep(8000);
		String Title = driver.getTitle();
		Assert.assertEquals(Title, "Get started with HubSpot");	
	}
	
	
	@AfterTest
	public void TearDown() {
		driver.quit();
	}
}
