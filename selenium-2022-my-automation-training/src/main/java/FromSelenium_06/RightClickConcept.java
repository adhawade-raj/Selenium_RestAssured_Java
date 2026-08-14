package FromSelenium_06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RightClickConcept {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement RightClickElement=driver.findElement(By.className("context-menu-one"));
		
		Actions action = new Actions(driver);
		action.contextClick(RightClickElement).build().perform();
String Text=driver.findElement(By.xpath("/html/body/ul/li[3]")).getText();		
		System.out.println(Text);
		
		
		
		
	}

}
