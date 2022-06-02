package TestNG1;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
public class LoginTest_BeforeMethod {

	

	WebDriver driver;

	By Email=By.xpath("//input[@id='username' and @type='email']");
	By PassWord = By.xpath("//input[@id='password' and @type='password']");
	By Login = By.xpath("//button[@id='loginBtn' and @type='submit']");
	By signup = By.linkText("Sign up");



		@BeforeMethod
		public void setup()
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://app.hubspot.com/login");
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
		
		WebElement HomePageTitleVAl=driver.findElement(By.className("Private-Page__title"));
		
		Assert.assertEquals(HomePageTitleVAl, "Sales Dashboard");
		}
		
		
		@AfterMethod
		public void TearDown()
		{
			driver.quit();
		}
		
		
		
		
	}


