package FromSelenium_19;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalendarHandle {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.ixigo.com/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		Alert alert = driver.switchTo().alert();
//		alert.dismiss();
		driver.findElement(By.xpath("//input[@placeholder =\"Depart\"][@type='text']")).click();
		
		List<WebElement> StartDateList = driver.findElements(By.xpath("//div[@class='rd-date']//div[@class='rd-month']//td//div[contains(@class,'day has-info')]"));
//		WebDriverWait wait = new WebDriverWait(driver, 5);
//		wait.until(ExpectedConditions.presenceOfElementLocated((By) StartDateList));
		
		for(WebElement e:StartDateList)
		{
			if(e.getText().equals("25"))
			{
				e.click();
				break;
			}
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder =\"Return\"][@type='text']")).click();
Thread.sleep(2000);
	

//end date

String MonthValue = driver.findElement(By.xpath("(//div[@class='rd-month-label'])[4]")).getText();
while(MonthValue.equalsIgnoreCase("July 2022"))
{
driver.findElement(By.xpath("(//button[@class='ixi-icon-arrow rd-next'])[2]")).click();

}
List<WebElement> EndDateList = driver.findElements(By.xpath("(//div[@class='rd-month'])[4]/table//div[contains(@class,'day')]"));
for(WebElement e:EndDateList)
{
	if(e.getText().equals("28"))
	{
		e.click();
		break;
	}
}

		
	}

}
