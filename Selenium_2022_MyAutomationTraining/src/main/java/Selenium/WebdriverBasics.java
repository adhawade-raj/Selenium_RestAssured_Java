package Selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebdriverBasics {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com");
		String title = driver.getTitle();
    System.out.println(title);		
//	System.out.println("----------");
//    if(title.equals(title))
//    {
//    	System.out.println("Pass");
//    }
//    else
//    {
//    	System.out.println("Fail");
//    }
//
//    //Difference between quit & close
//    
//    driver.quit();
//    
    driver.close();
    System.out.println(driver.getTitle());

	}

}
