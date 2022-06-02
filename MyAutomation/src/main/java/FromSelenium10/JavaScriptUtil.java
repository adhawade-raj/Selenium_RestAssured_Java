package FromSelenium10;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptUtil {
	WebDriver driver;
  public JavaScriptUtil(WebDriver driver)
  {
	  this.driver=driver;
  }
	
	public static String getTitleByJS(WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String Title = js.executeScript("return doucment.title;").toString();
		return Title;
	}
	
	
}
