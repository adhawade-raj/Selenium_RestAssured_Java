package Practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Iframe {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.londonfreelance.org/courses/frames/");
		
		WebElement frame = driver.findElement(By.xpath("//frame[@name='main']"));
		//frame[@name='main']
		
		driver.switchTo().frame(frame);
		String Text = driver.findElement(By.xpath("//body//h2[text()='Title bar ']")).getText();
		System.out.println(Text);
		
		
	}

}
