package allPrograms;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CaptureScreenShots {

	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
//		Full page ScreenShot
		
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File src = ts.getScreenshotAs(OutputType.FILE);
//		File Target = new File(".\\src\\main\\java\\allPrograms\\Screenshots.png");
//		
//		FileUtils.copyFile(src, Target);
		
//		ScreenShot of section/portion of the page
		WebElement section = driver.findElement(By.xpath("(//div[@class='a-cardui fluid-fat-image-link fluid-card fluid-fat-image-link'])[1]"));
		
		File src2 = section.getScreenshotAs(OutputType.FILE);
		File Target2 = new File(".\\src\\main\\java\\allPrograms\\ScreenShots\\file.png");
		
		FileUtils.copyFile(src2, Target2);
		
	}

}
