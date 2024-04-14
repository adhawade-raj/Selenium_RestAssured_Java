package com.google.threadclasses;

import org.openqa.selenium.WebDriver;

import com.packages.GooglePage;

public class GoogleFeatureThread extends Thread {

	public String BrowserName;
	GooglePage googlepage;
	
	public GoogleFeatureThread(String ThreadName, String BrowserName)
	{
		super(ThreadName);
		this.BrowserName=BrowserName;
		googlepage = new GooglePage();	
	}
	

@Override
public void run()
{
System.out.println("thread--started" +Thread.currentThread().getName());	
try{
	Thread.sleep(1000);
	googlepage.setup(this.BrowserName);
	googlepage.googleSearchTest();
}
catch(Exception e)
{
	e.printStackTrace();
}
finally {
	googlepage.tearDown();
}
System.out.println("thread-ended" +Thread.currentThread().getName());
}
}