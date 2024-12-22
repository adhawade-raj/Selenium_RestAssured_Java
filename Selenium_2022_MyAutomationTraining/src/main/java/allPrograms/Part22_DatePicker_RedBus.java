package allPrograms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part22_DatePicker_RedBus {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.redbus.in/");
		
//		Error in selecting year
		
		String date = "21";
		String Month = "July";
		String Year="2023";
			
		driver.findElement(By.xpath("//input[@id='onward_cal']")).click();
		
		while(true)
		{
			String MonthYear = driver.findElement(By.xpath("//td[@class='monthTitle']")).getText();
			
			String arr[] = MonthYear.split(" ");
			String mon = arr[0];
			String year = arr[1];
			
			if(mon.equalsIgnoreCase(Month)&& year.equalsIgnoreCase(year))
				break;
			else
			driver.findElement(By.xpath("//td[@class='next']")).click();
			
		}
//		Date Selection
		List<WebElement> allDates = driver.findElements(By.xpath("//table[@class='rb-monthTable first last']//tr/td"));
		
		for(WebElement ele : allDates)
		{
			String dt = ele.getText();
			if(dt.equals(date))
			{
				ele.click();
				break;
			}
		}
		
	}

}
