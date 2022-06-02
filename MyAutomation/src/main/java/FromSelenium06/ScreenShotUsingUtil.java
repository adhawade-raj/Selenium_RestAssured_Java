package FromSelenium06;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScreenShotUsingUtil {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://app.hubspot.com/login");
		Thread.sleep(5000);
		
		TakeScreenshot(driver, "loginPage");
		
		

	}

	private static void TakeScreenshot(WebDriver driver, String Filename) {

		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try
		{
			FileUtils.copyFileToDirectory(srcFile, new File("./target/screenshot/page.png"));
			
		}
		catch(Exception e)
		{
			System.out.println("Some exception is coming");
		}
	}

}
