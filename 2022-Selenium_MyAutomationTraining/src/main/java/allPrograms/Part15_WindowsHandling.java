package allPrograms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part15_WindowsHandling {

	public static void main(String[] args) {


		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		// getWindowHandle
	String windowHandle=	driver.getWindowHandle();
	System.out.println("Widnow id is:"+windowHandle);
	
	//getWindowHandles
	driver.findElement(By.xpath("//a[text()='OrangeHRM, Inc']")).click();
	
	Set<String> windowsHandles = driver.getWindowHandles();
	
	Iterator<String> it = windowsHandles.iterator();
	String parenWindowID = it.next();
	String childWindowId = it.next();
	
	System.out.println("Parent WindowId is:"+parenWindowID);
	System.out.println("Parent WindowId is:"+childWindowId);
	
	List<String> windowsList = new ArrayList(windowsHandles);
String parentWindow =	windowsList.get(0);
String childWindow = windowsList.get(1);
System.out.println("--------------------------------");

System.out.println("Parent WindowId is:"+parentWindow);
System.out.println("Parent WindowId is:"+childWindow);
	
System.out.println("----------------------------------");
driver.switchTo().window(parenWindowID);
System.out.println("Parent window Title is:" +driver.getTitle());

driver.switchTo().window(childWindowId);
System.out.println("Child window Title is:" +driver.getTitle());
System.out.println("====================================");


//for each loop
for(String winID : windowsList)
{
	String title = driver.switchTo().window(winID).getTitle();
	System.out.println(title);
	if(title.equals("OrangeHRM"))
	{
		driver.close();
	}
}
driver.close();
//driver.quit();

	
	}

}
