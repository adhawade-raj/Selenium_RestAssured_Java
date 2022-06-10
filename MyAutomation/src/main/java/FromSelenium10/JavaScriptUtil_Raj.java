package FromSelenium10;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptUtil_Raj {

	public WebDriver driver;
	
	public JavaScriptUtil_Raj(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String getTitleByJS()
	{
		JavascriptExecutor js =(JavascriptExecutor)driver;
		return js.executeScript("return document.title").toString();
			
	}
}
