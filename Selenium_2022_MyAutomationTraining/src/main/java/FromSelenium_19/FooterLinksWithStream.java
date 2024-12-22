package FromSelenium_19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FooterLinksWithStream {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		
		driver.findElements(By.xpath("//ul/li/a[@class='nav_a']"))
		.stream()
		.filter(ele->!ele.getText().isEmpty())
		.forEach(ele->System.out.println(ele.getText()));
	}

}
