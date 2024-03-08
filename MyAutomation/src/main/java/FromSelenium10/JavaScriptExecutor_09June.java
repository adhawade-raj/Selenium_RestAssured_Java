package FromSelenium10;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScriptExecutor_09June {

	public static void main(String[] args) {

				WebDriverManager.chromedriver().setup();
				WebDriver driver = new ChromeDriver();
				driver.get("https://www.amazon.in/");
				
//				WebElement emailId = driver.findElement(By.id("input-email"));
//				WebElement password = driver.findElement(By.id("input-password"));

				JavaScriptUtil jsUtil = new JavaScriptUtil(driver);
				System.out.println(jsUtil.getTitleByJS());
				

				jsUtil.refreshBrowserByJS();
				
				//jsUtil.generateAlert("I m on login page");
				
//				if(jsUtil.getPageInnerText().contains("Privacy Policy")) {
//					System.out.println("PASS");
//				}
				
				//jsUtil.drawBorder(emailId);
				//WebElement login = driver.findElement(By.xpath("//input[@value='Login']"));
//				jsUtil.flash(emailId);
				//emailId.sendKeys("test@gmail.com");
//				jsUtil.flash(password);
				//password.sendKeys("test@12345");
				
				//jsUtil.clickElementByJS(login);
				
//				jsUtil.scrollPageDown();
//				jsUtil.scrollPageUp();
//				jsUtil.scrollPageDown("700");
				
//				WebElement ess = driver.findElement(By.xpath("//h2[text()='Masks and faceshields']"));
//				jsUtil.scrollIntoView(ess);
				
				
				
			}

		
	}


