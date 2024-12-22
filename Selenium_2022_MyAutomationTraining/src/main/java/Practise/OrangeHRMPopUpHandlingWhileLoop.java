package Practise;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
public class OrangeHRMPopUpHandlingWhileLoop {

	public static void main(String[] args) {
	
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		driver.findElement(By.xpath("//*[@id='social-icons\']/a[3]")).click();
		
		Set<String>handles =driver.getWindowHandles();
		
		System.out.println(handles);
		
		Iterator<String> it=handles.iterator();
		String pid =it.next();
		
		while(it.hasNext())
		{
		String cid = it.next();
		driver.switchTo().window(cid);
		System.out.println(driver.getTitle());
		
		driver.close();
		}
		
		driver.switchTo().window(pid);
		System.out.println(driver.getTitle());
		System.out.println("================================");
		
		driver.findElement(By.xpath("//*[@id='social-icons\']/a[2]")).click();
		
		Set<String>handles2 =driver.getWindowHandles();
		System.out.println(handles);
		
		Iterator<String> it2= handles2.iterator();
		
		String pid2=it2.next();
		while(it2.hasNext())
		{
		
			String cid2=it2.next();
			driver.switchTo().window(cid2);
			System.out.println("Child 2 :"+driver.getTitle());
			driver.close();
		}
		
		driver.switchTo().window(pid2);
		System.out.println("Parent:"+pid2);
		
		
	
		
		
		
	}

}
