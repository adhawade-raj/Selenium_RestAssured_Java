package FromSelenium_19;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ListWithStream {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.orangehrm.com/orangehrm-30-day-trial/?");
		
		//select[@name='Country'] [@id='Form_submitForm_Country']
		By country = By.xpath("//select[@name='Country'] [@id='Form_submitForm_Country']");
		
		By CountryList = By.xpath("//select[@name='Country'] [@id='Form_submitForm_Country']/option");
		
		List<WebElement> CountList = driver.findElements(CountryList);
		
		/**
		 * Sequential Execution
		 */
//		long startTime = System.currentTimeMillis();
//		CountList.stream().forEach(ele -> System.out.println(ele.getText()));
//		long endTime = System.currentTimeMillis();	
//		System.out.println(endTime - startTime);
		
		/**
		 * Parallel Execution
		 */
		long startTime = System.currentTimeMillis();
		CountList.parallelStream().forEach(ele -> System.out.println(ele.getText()));
		long endTime = System.currentTimeMillis();	
		System.out.println(endTime - startTime);
	}

}
