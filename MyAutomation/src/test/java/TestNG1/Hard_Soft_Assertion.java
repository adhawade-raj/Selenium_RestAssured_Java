package TestNG1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Hard_Soft_Assertion {

	
	WebDriver driver;
	SoftAssert softAssert;

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
		public void PageTitleTest()
		{
			String Title=driver.getTitle();
			System.out.println("Page title is :"+Title);
			Assert.assertEquals(Title, "HubSpot Login");	
			
			String Title2 = driver.getTitle();
			Assert.assertEquals(Title2, "HubSpot Login123");
		}
		
//		@Test(priority=2,enabled=false)
//		public void LoginPageTest()
//		{
//		driver.findElement(Email).sendKeys("naveenanimattion30@gmail.com");
//		driver.findElement(PassWord).sendKeys("Test@1234");
//		driver.findElement(Login).click();
//		
//		WebDriverWait wait = new WebDriverWait(driver, 15);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("Private-page__title")));
//		
//		softAssert.assertTrue(driver.findElement(By.className("Private-Page__title")).isDisplayed());
//	
//		String Header = driver.findElement(By.className("Private-Page__title")).getText();
//		softAssert.assertEquals(Header, "Sales DashBoard123");
//		
//		}
		
		@AfterMethod
		public void TearDown()
		{
			driver.quit();
		}	
	}

