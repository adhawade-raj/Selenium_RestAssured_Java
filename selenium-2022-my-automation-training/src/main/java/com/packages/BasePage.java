package com.packages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {

	public WebDriver driver;
	
	//intiatlization of browser
	
	


public WebDriver setup(String browserName)
{
	if(browserName.equalsIgnoreCase("chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver();
	}
	else if(browserName.equalsIgnoreCase("firefox"))
	{
		System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver();
	}
	driver.get("https://www.google.com");
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	return driver;
	}
}