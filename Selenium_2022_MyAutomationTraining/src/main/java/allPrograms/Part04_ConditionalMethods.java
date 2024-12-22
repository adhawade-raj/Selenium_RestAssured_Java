package allPrograms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part04_ConditionalMethods {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/register");
		
		String Title = driver.getTitle();
		System.out.println(Title);
		
		WebElement Search = driver.findElement(By.xpath("//input[@placeholder='Search store']"));
		System.out.println(Search.isDisplayed());
		System.out.println("-----isEnabledMethod----");
		
		System.out.println(Search.isEnabled());
		System.out.println("-----isSelectedMethod----");
		
		WebElement male = driver.findElement(By.xpath("//label[text()='Male']"));
		WebElement female = driver.findElement(By.xpath("//label[text()='Female']"));
		System.out.println(male.isSelected());
		System.out.println(female.isSelected());
		System.out.println("------------------");
		
		
		
		
		
	}

}
