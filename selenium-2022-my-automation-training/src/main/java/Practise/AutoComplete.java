package Practise;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutoComplete {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		co.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(co);
		Thread.sleep(5000);
		driver.get("https://www.twoplugs.com/newsearchserviceneed");
		
		WebElement searchbox = driver.findElement(By.xpath("//input[@id='autocomplete']"));
		searchbox.clear();
		searchbox.sendKeys("Toronto");
		String text;
		do {
			searchbox.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(5000);
			 text = searchbox.getAttribute("value");
			if(text.equals("Toronto, OH, USA")) {
				searchbox.sendKeys(Keys.ENTER);
			break;
			}
		}	
		while(!text.isEmpty());
}
}

