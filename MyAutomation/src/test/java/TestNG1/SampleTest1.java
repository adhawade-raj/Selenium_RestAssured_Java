package TestNG1;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleTest1 {
	
	WebDriver driver;
	
@BeforeTest
public void setup()
{
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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

@AfterTest
public void TearDown()
{
	driver.quit();
}
}
