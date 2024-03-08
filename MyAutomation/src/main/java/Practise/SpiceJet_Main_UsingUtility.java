package Practise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SpiceJet_Main_UsingUtility {

	public static void main(String[] args) {

		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.spicejet.com/");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
//		WebDriverWait wait = new WebDriverWait(driver, 100);
//		wait.until(ExpectedConditions.urlContains("https://www.spicejet.com/"));
		
		System.out.println(driver.getTitle());
		
		WebDriverWait wait2 = new WebDriverWait(driver, 500);
		wait2.until(ExpectedConditions.urlContains("https://www.spicejet.com/"));
//		driver.switchTo().alert().dismiss();

		String txt =driver.switchTo().alert().getText();
		System.out.println(txt);
		
		
	}

}
