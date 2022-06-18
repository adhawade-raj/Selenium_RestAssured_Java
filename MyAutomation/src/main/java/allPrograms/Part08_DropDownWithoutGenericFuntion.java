package allPrograms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part08_DropDownWithoutGenericFuntion {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.opencart.com/index.php?route=account/register");
	
		WebElement List = driver.findElement(By.xpath("//select[contains(@name,'country_id')]"));
		Select select = new Select(List);
//		select.selectByVisibleText("India");
		
//		select.selectByIndex(13);
		
		
	}

}
