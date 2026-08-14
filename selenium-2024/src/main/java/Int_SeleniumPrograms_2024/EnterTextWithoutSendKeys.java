package Int_SeleniumPrograms_2024;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EnterTextWithoutSendKeys {

	public static void main(String[] args) {
		
		{
			WebDriverManager.chromedriver().setup();
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get("url");

			WebElement searchText=driver.findElement(By.xpath(""));	
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("argument[0].value='test'",searchText);
//			test is the value to be passed as input to webelemnt
		   }

	}

}
