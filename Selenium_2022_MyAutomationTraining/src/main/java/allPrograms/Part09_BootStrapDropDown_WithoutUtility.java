package allPrograms;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part09_BootStrapDropDown_WithoutUtility {

		static WebDriver driver;
		
		public static void main(String[] args) throws InterruptedException {
			WebDriverManager.chromedriver().setup();
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--disable-notifications");
			
			driver = new ChromeDriver(co);
			Thread.sleep(5000);
			driver.get("https://www.hdfcbank.com/");
			
//			
//			Alert alert = driver.switchTo().alert();
//			alert.dismiss();
			
			String text = driver.getTitle();
			System.out.println(text);
		
			WebElement CList = driver.findElement(By.xpath("//a[contains(text(),'Select Product Type')]"));
			CList.click();
			
		List<WebElement> ProdType = driver.findElements(By.xpath("//ul[contains(@Class,'dropdown1 dropdown-menu')]//li"));
			
		for(WebElement prod : ProdType)
		{
			if(prod.getText().equals(ProdType))
			{
				prod.click();
			}
		}
			
	}

}
