package Practise;

import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutoSuggest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		co.addArguments("--disable-notofication");
		
		WebDriver driver = new ChromeDriver(co);
		driver.manage().deleteAllCookies();
	
driver.get("https://www.google.com/search?q=google&rlz=1C1CHBD_enIN962IN962&oq=google&aqs=chrome.0.0i131i355i433i512j46i131i199i433i465i512j0i131i433i512l2j5j69i60l3.2780j0j7&sourceid=chrome&ie=UTF-8");
		
		WebElement text = driver.findElement(By.xpath("//textarea[text()='google']"));
		text.clear();
		text.sendKeys("Raj");
		
		java.util.List<WebElement>  list = driver.findElements(By.xpath("(//div[@role='presentation'])[1]"));
		
		for(WebElement e : list) {
			String text2 = e.getText();
			if(text2.equalsIgnoreCase("rajasthan")) {
				e.click();
				break;
				
				
				}
			else {
				driver.close();
				
			}
				
		}
		
		

	}

}
