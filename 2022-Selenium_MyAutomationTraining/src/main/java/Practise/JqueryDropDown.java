package Practise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JqueryDropDown {
static WebDriver driver;

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.jqueryscript.net/demo/Drop-Down-Combo-Tree/");		
		driver.findElement(By.xpath("//input[@placeholder='Select' and @type='text' and @id='justAnInputBox']")).click();
		
		JqueryDropDown.SelectByValue(driver, "choice 1");
		JqueryDropDown.SelectByValue2(driver, "choice 1", "choice 2");	
	}




public static void SelectByValue(WebDriver driver, String Value)
{
	List<WebElement> DropList = driver.findElements(By.xpath("//li/span[@class='comboTreeItemTitle']"));

	for(WebElement e: DropList)
	{
		
	String Text =	e.getText();
	
		if(Text.equals(Value))
		{
			e.click();
		}
	}
}
	
	public static void SelectByValue2(WebDriver driver, String... Value)
	{
		List<WebElement> DropList = driver.findElements(By.xpath("//li/span[@class='comboTreeItemTitle']"));

		for(int i=0; i<DropList.size(); i++)
		{
		String Text = DropList.get(i).getText();
		
		for(int k=0; i<Value.length; k++)
		
			if(Text.equals(Value[k]))
			{
			DropList.get(i).click();
			}
		}
}
}


