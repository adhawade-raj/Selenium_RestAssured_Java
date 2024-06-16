package FromSelenium_06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DragAndDropConcept {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://jqueryui.com/droppable/");
		
		Actions action = new Actions(driver);
		
		WebElement Source = driver.findElement(By.cssSelector("#draggable"));
		
		WebElement Target = driver.findElement(By.id("droppable"));
		
//		action.clickAndHold(Source).moveToElement(Target).release().build().perform();
		
		action.dragAndDrop(Source, Target).build().perform();
	}

}
