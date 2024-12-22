package TestNG1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage {

	WebDriver driver;

	By Email=By.xpath("//input[@id='username' and @type='email']");
	By PassWord = By.xpath("//input[@id='password' and @type='password']");
	By Login = By.xpath("//button[@id='loginBtn' and @type='submit']");
	By signup = By.linkText("Sign up");



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
		public void sigupLinkTest()
		{
			Assert.assertTrue(driver.findElement(signup).isDisplayed());
		}
		
		@Test(priority=2)
		public void PageTitleTest()
		{
			String Title=driver.getTitle();
			System.out.println("Page title is :"+Title);
			Assert.assertEquals(Title, "HubSpot Login");	
		}
		@Test(priority=3)
		public void LoginPageTest()
		{
		driver.findElement(Email).sendKeys("adhawaderaj@gmail.com");
		driver.findElement(PassWord).sendKeys("Justbring@21");
		driver.findElement(Login).click();
		
		String HomePageTitleVAl=driver.getTitle();
		
		Assert.assertEquals(HomePageTitleVAl, "HubSpot Login");
		}
		
		
		@AfterTest()
		public void TearDown()
		{
			driver.quit();
		}
		
		
		
		
	}
	
	

