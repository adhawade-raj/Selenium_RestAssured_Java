package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	
	/**
	 * This method is to initialize the browser
	 * @param prop
	 * @return
	 */
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser Name :" +browserName);
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("FireFox"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new FirefoxDriver();	
		}
		else
		{
			System.out.println("Please pass the right browser:" +browserName);
		}
		
		driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url").trim());
	
	return driver;
}
	/**
	 * this method is to initialize the properties
	 * @return
	 */
	public Properties init_prop() {
		
		prop = new Properties();
		
		try {
			FileInputStream ip = new FileInputStream("./src/tesst/resources/Config/Config.properties");
			prop.load(ip);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return prop;
		
	}

}
