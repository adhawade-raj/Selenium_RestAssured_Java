package FromSelenium10;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Custom_Xpath_selenium10 {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://app.hubspot.com/login?hubs_signup-url=www.hubspot.com/products/crm&hubs_signup-cta=homepage-nav-login&_ga=2.212361108.1059341697.1652710566-650251035.1652097137");
		
		Thread.sleep(5000);
		System.out.println(driver.findElement(By.xpath("//input[@id='username']")).getText());
		
		Thread.sleep(5000);
		System.out.println(driver.findElement(By.xpath("//input[@id='username']")).isDisplayed());
		
//		---->Syntax of Xpath = //htmlTag[@property ='value']
//		html tag = input,name,dev,img,a
		
		//input[@id='username']
		
//		---->Syntax for multiple element in one Xpath = //htmlTag[Property1='value' and Property2= 'value']
		//input[@id='name' and type='email']
		
		//input[@class="form-control private-form__control login-email"] --------> All defined classes are mentioned
		//input[@class="form-control private-form"] ------> Invalid all defined classes required
//------------------------------------------------------------------------------------------------------------------------------		
		
//		----> //Contains() in X path		
//		---->syntax = //htmlTag[contains(prop, 'value')]
				
				//input[contains(@id, "username")]
		//input[contains(@class,"form-control private-form__control login-email")] ----------> contains method using all defined classes
		//input[contains(@class,"form-control")] ------------->in contains method all defined classes are not mandatory to mention
        //Lengthy elements can be searched with short class name
		
		//input[@id="username" and contains(@class, "form-control")] -------valid. Combination normal x-path And contains()x-path
//    -------------------------------------------------------------------------------------------------------------------------------------		
//		---->contains user for dynamic ids:
		
//	---->Not only user for changing id's, But also used for name,class also.
//		 Dynamic id = <input id ="test_123">
//	                  <input id ="test_123">
//		              <input id ="test_123">
		
//		---->The moment you open page everytime new id will be generated. in that case we can use x path.
		
		//input[contains(@id, "test_")]
		
//---------------------------------------------------------------------------------------------------------------------------------		
//	---->if you want to filter as per indexing.
//	for ex---> teher are 2 elements are available. So we will allot them no in array so specific element can identified
		
		//input[contains(@class, "form-control")][1]
		//input[contains(@class, "form-control")][2]
		
//    --------------------------------------------------------
//	---->if you dont want to follow, indexing conecpt you can go with position() concept
		
		//input[contains(@class, "form-control")][position()=1]
		//input[contains(@class, "form-control")][position()=2]
//	 ------------------------------------------------------------
//		---->if you want to go to jump to directly at last position/index
		
		//input[contains(@class, "form-control")][last()]
//-------------------------------------------------------------------------------------------------------------------------------------------------------		
//------------------------------------------------------------------------------------------------------------------------------------------		
//		----> text() in x path
//		---->syntax = //htmlTag[text()='value']
		
		
		//span[text()='Show Password']
//	-------------------------------------------------------------------
//		----> text() with contains() in x path
		
		//span[contains (text(),'Show Password')]
		//span[contains (text(),'Show')] -------------> such short name can be used for validating long texts
		//span[contains (text(),'Password')] -------------> such short name can be used for validating long texts
		//i18n-string [contains (text(), 'account?')]

//		System.out.println(driver.findElement(By.xpath("//input[@id='username']")).getText());
//		System.out.println(driver.findElement(By.xpath("//input[@id='username']")).isDisplayed());
		
//		------------------------------------------------------------------------------
		
		//*[@id='username'] ----------> * Means all elements
		//input[@id='username'] -------> this is only for to input html Tag
		
//------------------------------------------------------------------------------------------------------------------------------------------------
		//div//input[@id='username']
//		div = parent to input
		
		//div/input   --------------> Direct child
		//div//input  --------------> Indirect + Direct Child
	}

}
