package Practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PractiseFB {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		 driver.get("https://www.facebook.com/");
		    driver.manage().window().maximize();
		    driver.findElement(By.linkText("Create New Account")).click();
		 driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("123");
		 driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("123");
		 driver.findElement(By.xpath("//input[contains(@type,'text') and (@name='reg_email__')]")).sendKeys("123897");
		 driver.findElement(By.xpath("//input[contains(@type,'password')]")).sendKeys("123456");
		 
	WebElement day = driver.findElement(By.xpath("//select[@id='day']"));
	WebElement month = driver.findElement(By.xpath("//select[@id='month']"));
	WebElement year = driver.findElement(By.xpath("//select[@id='year']"));
	
	Select select = new Select(day);
	select.selectByVisibleText("12");
	
	Select select2 = new Select(month);
	select2.selectByVisibleText("Oct");

	Select select3 = new Select(year);
	select3.selectByVisibleText("1998");
	
	
	driver.findElement(By.xpath("//label[@class='_58mt']")).click();
	}
}
	
	


