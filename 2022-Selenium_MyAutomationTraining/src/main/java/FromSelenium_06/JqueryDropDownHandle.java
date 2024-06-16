package FromSelenium_06;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JqueryDropDownHandle {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.jqueryscript.net/demo/Drop-Down-Combo-Tree/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.id("justAnInputBox")).click();
		
		List<WebElement> choiceList = driver.findElements(By.xpath("//div[@class='comboTreeDropDownContainer']//ul//li//span[@class='comboTreeItemTitle']"));
	
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		for(int i=0; i<choiceList.size(); i++)
		{
			String text =choiceList.get(i).getText();
			System.out.println(text);
		
		if(text.equals("choice 1"))
		{
			choiceList.get(i).click();
			break;
		}
	}
	}

}
