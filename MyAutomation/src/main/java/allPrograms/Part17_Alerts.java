package allPrograms;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part17_Alerts {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		
//		Alert window with Ok Button
//		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
//		Thread.sleep(3000);
//		driver.switchTo().alert().accept();
		
//		Alert Window with OK and Cancel button
//		driver.findElement(By.xpath("Click for JS Confirm")).click();
//		driver.switchTo().alert().accept();
//		driver.switchTo().alert().dismiss();
		
//		Alert Window with input box and capture text from alert
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Alert alertWindow = driver.switchTo().alert();
		System.out.println("Message displayed on alert is:" +alertWindow.getText());
		
		alertWindow.sendKeys("wELcOME");
		alertWindow.accept();
		
		
		
	}

}
