package Practise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutoSuggest2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		WebDriverManager.chromedriver().setup();
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(co);
		driver.manage().deleteAllCookies();
		
		
		driver.get("https://www.google.com/search?gs_ssp=eJzj4tTP1TcwMU02T1JgNGB0YPBiS8_PT89JBQBASQXT&q=google&rlz=1C1CHBD_enIN962IN962&oq=ggogle&aqs=chrome.1.69i57j46i10i67i199i465i650j0i10i131i433i512l5j5.2791j0j7&sourceid=chrome&ie=UTF-8");
		Thread.sleep(5000);
		
		WebElement searchbox = driver.findElement(By.xpath("//textarea[text()='google']"));
		searchbox.clear();
		searchbox.sendKeys("Raj");
		
		java.util.List<WebElement> options = driver.findElements(By.xpath("//div[@role='presentation']//ul//li"));
//		for(WebElement e : options) {
//			String text = e.getText();
//			if(text.equalsIgnoreCase("Rajasthan"))
//				e.click();
//				break;
//		}
	
		String text;
		do {
			searchbox.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(5000);
			text = searchbox.getAttribute("value");
			if(text.equalsIgnoreCase("raj thakrey")) {
				searchbox.sendKeys(Keys.ENTER);
				break;
			}
		}
		while(!text.isEmpty());
	
	}

}
