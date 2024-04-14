package Practise;

import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Links {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		co.addArguments("-disbale-notofications");
		
		WebDriver driver =new ChromeDriver(co);
		driver.get("https://www.orangehrm.com/orangehrm-30-day-trial/?");
		
		java.util.List<WebElement> a = driver.findElements(By.tagName("a"));
		
		for(WebElement e: a) {
//			System.out.println(e.getSize());
			
			if(!e.getText().isEmpty())
//				System.out.println(e.getAttribute("href"));
				System.out.println(e.getText());
		}
		
		

	}

}
