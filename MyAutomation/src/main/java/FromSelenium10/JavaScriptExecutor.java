package FromSelenium10;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScriptExecutor {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
	
		driver.get("https://www.w3schools.com/tags/att_default.asp");
		Thread.sleep(5000);
		
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("window.scrollBy(000,1000)");
		Thread.sleep(5000);
		
		j.executeScript("window.scrollBy(000,-500)");
		Thread.sleep(5000);
		
		System.out.println("------Scroll upto specific element------");
		WebElement HTMLQuiz = driver.findElement(By.xpath("//a[text()='Start HTML Quiz!']"));
		j.executeScript("arguments[0].scrollIntoView(true)", HTMLQuiz);
		Thread.sleep(10000);
		driver.quit();
	}

}
