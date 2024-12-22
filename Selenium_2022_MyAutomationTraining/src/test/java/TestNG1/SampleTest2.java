package TestNG1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleTest2 {

	WebDriver driver;
	
@BeforeMethod
public void setup()
{
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.get("https://www.google.com");
}

@Test
public void SearchTest()
{
	boolean flag = driver.findElement(By.name("q")).isDisplayed();
	Assert.assertTrue(flag);
}
@Test
public void UrlTest()
{
	String Url= driver.getCurrentUrl();
	Assert.assertTrue(Url.contains("Google"));
}

@AfterMethod
public void TearDown()
{
	driver.quit();
}
}


