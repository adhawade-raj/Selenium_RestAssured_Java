package Practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetText_Attribute {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeOptions co =new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		
		WebDriver driver = new ChromeDriver(co);
		Thread.sleep(5000);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/?&ext_vrnc=hi&tag=googinhydr1-21&ref=pd_sl_4b3e4wrxds_b&adgrpid=60639568242&hvpone=&hvptwo=&hvadid=617721280315&hvpos=&hvnetw=g&hvrand=4956040605341485258&hvqmt=b&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=1007788&hvtargid=kwd-311187680635&hydadcr=5841_2362028");
	
		Thread.sleep(5000);
		String text = driver.getTitle();
		System.out.println(text);
		
		WebElement a1 = driver.findElement(By.xpath("//a[contains(text(),'Amazon miniTV')]"));
		System.out.println(a1.getText());
		
		System.out.println("-----------------------------------------");
		
		WebElement a2 = driver.findElement(By.xpath("//a[contains(text(),'Amazon miniTV')]"));
		System.out.println(a2.getAttribute("href"));
		
		
	}

}
