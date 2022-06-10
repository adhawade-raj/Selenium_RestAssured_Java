package FromSelenium10;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScriptExecutor_Raj {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		
		JavaScriptUtil_Raj js = new JavaScriptUtil_Raj(driver);
	System.out.println(js.getTitleByJS());
	}

}
