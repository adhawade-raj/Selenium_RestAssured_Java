package Practise;

import java.util.Set;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRMPopUpHandling {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		driver.findElement(By.xpath("//*[@id='social-icons\']/a[3]")).click();
		
		Set<String> handles =driver.getWindowHandles();
		System.out.println(handles);
		
		Iterator<String> it =handles.iterator();
		String pid =it.next();
		System.out.println("Parent :" +pid);
		
		String Cid = it.next();
		System.out.println("Child :"+Cid);
	
		
		driver.switchTo().window(Cid);
		System.out.println("child window url : " +driver.getCurrentUrl());
		
		driver.close();
		
		driver.switchTo().window(pid);
		System.out.println("parent window url : " +driver.getCurrentUrl());
		System.out.println("==========================================");
		
		driver.findElement(By.cssSelector("#social-icons > a:nth-child(2)")).click();
		Set<String> handles2 = driver.getWindowHandles();
		System.out.println(handles2);

		Iterator<String> it2 = handles2.iterator();
		
		
		
	    String pid2 = it2.next();
	    System.out.println("Parent :" +pid2);
	    
	    String cid2=it2.next();
	    System.out.println("Child :"+cid2);
	    
	    driver.switchTo().window(cid2);
	    System.out.println("Child :" +driver.getTitle());
	    
	    driver.close();
	    
	    driver.switchTo().window(pid2);
	    System.out.println("Parent "+driver.getTitle());
			
	}

}
