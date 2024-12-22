package Practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropDown3 {

	static WebDriver  driver;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		co.addArguments("--disable-notoficatiosn");
		
		 driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		
		driver.get("https://www.orangehrm.com/orangehrm-30-day-trial/?");
		
		WebElement drpList = driver.findElement(By.xpath("//select[@name='Country']"));

		DropDown3.dropDown3Utility(drpList,"India");
	}
	
	public static void dropDown3Utility(WebElement ele, String value) {
		
		Select sel = new Select(ele);
		java.util.List<WebElement>  a1 =sel.getOptions();
	
		for(WebElement e: a1) {
			if(e.getText().equals(value))
			e.click();
//		break;
		}	
	}

}
