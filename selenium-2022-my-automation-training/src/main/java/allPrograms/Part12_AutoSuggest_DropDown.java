package allPrograms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part12_AutoSuggest_DropDown {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		Thread.sleep(5000);
		driver.get("https://www.google.com");
		
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("java tutorial");
		
Thread.sleep(3000);

List<WebElement> list=driver.findElements(By.xpath("//li[@class='sbct']//div/span"));

System.out.println("Size is:" +list.size());

for(WebElement listItem : list)
{
	if(listItem.getText().contains("java tutorial pdf"))
	{
		listItem.click();
		break;
	}
}

		
	}

}
