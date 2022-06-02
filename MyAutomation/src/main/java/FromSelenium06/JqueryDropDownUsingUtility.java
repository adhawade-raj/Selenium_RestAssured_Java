package FromSelenium06;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JqueryDropDownUsingUtility {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.jqueryscript.net/demo/Drop-Down-Combo-Tree/");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.id("justAnInputBox")).click();

		JqueryDropDownUtility Test = new JqueryDropDownUtility(driver);
		Test.ChoiceValues(driver, "choice 1"); // -----------> for selecting one value at a time from Drop Down
			
		// -----------> for selecting multiple values at a time from Drop Down

		Test.ChoiceValues1(driver, "choice 1", "choice 2", "choice 2 1");
		
		// -----------> for selecting All values at a time from Drop Down
		Test.ChoiceValues2(driver, "All");
		
		
		
		
		
	}

}
