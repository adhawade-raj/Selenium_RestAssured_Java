package SeleniumTrials2024.SeleniumTrials2024;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    System.setProperty("webdriver.chrome.driver","E:\\Eclipse\\WorkSpace\\chromedriver 121\\chromedriver.exe");  
    
	WebDriver driver = (WebDriver) new ChromeDriver();	
	driver.get("https://www.google.com");
}
