package FromSelenium_19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckBoxWithStream {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://datatables.net/extensions/select/examples/initialisation/checkbox.html");
		
		driver.findElements(By.xpath("//tr/td[@class='  select-checkbox']")).stream().forEach(ele -> ele.click());
		
	}

}
