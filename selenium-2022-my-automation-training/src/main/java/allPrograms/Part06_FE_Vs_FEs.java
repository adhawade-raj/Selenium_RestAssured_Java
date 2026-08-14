package allPrograms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part06_FE_Vs_FEs {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/");
		
		
		WebElement email = driver.findElement(By.xpath("//input[@type='text']"));
		System.out.println(email.getText());
		
		List <WebElement> LinksList = driver.findElements(By.tagName("a"));
		System.out.println(LinksList.size());
		
//       NoElement still not thrown any exceptions... returned an empty array
		List <WebElement> LinksList2 = driver.findElements(By.xpath("//input[@type='text123']"));
		System.out.println(LinksList2);
	}

}
