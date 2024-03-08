package allPrograms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part07_getText_Vs_getAttribute {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/");
		
//		error coming in x-path... becz of some site issue maybe... xpath is fine
		WebElement email = driver.findElement(By.xpath("//input[@id='Password']"));
		System.out.println(email.getAttribute("id"));
		
		System.out.println(email.getText());
			
	}

}
