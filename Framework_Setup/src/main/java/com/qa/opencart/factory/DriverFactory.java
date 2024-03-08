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
 * To initialize method
 * @param browserName
 * @return driver
 */
public WebDriver init_driver(Properties prop){
	
	String browserName = prop.getProperty("browser");
	System.out.println("Browser is " +browserName);

if(browserName.equals("chrome")) {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
}
else {
	WebDriverManager.firefoxdriver().setup();
	driver =new FirefoxDriver();
}
driver.manage().window().fullscreen();
driver.get(prop.getProperty("url"));
return driver;

}
/**
 * this method is to initialize properties
 * @return
 */
public Properties init_prop() {
    prop = new Properties();
    try {
		FileInputStream ip = new FileInputStream("./Resources/ConfigData/Config.Properties");
		prop.load(ip);
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return prop;
}
}
