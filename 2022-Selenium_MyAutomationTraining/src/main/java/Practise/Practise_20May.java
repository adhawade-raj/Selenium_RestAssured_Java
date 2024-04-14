package Practise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
public class Practise_20May {
	

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		 driver.get("https://www.facebook.com/campaign/landing.php?campaign_id=14884913640&extra_1=s%7Cc%7C589460569849%7Ce%7Cfacebook%7C&placement=&creative=589460569849&keyword=facebook&partner_id=googlesem&extra_2=campaignid%3D14884913640%26adgroupid%3D128696220912%26matchtype%3De%26network%3Dg%26source%3Dnotmobile%26search_or_content%3Ds%26device%3Dc%26devicemodel%3D%26adposition%3D%26target%3D%26targetid%3Dkwd-1001394929%26loc_physical_ms%3D9062085%26loc_interest_ms%3D%26feeditemid%3D%26param1%3D%26param2%3D&gclid=Cj0KCQjwmuiTBhDoARIsAPiv6L_Zg3cDtPtDHwrDKDAdHElsQQYoSsz_Eq5OC__xwhvrPqcPYYZPA6oaApdSEALw_wcB");
		    
		 By Fname =By.xpath("//input[contains(@type,'text')]");
		 By Lname = By.xpath("//input[contains(@type,'text') and (@name='lastname')]");
		 By mail_PhNo = By.xpath("//input[contains(@type,'text') and (@name='reg_email__')]");
		 By NewPass = By.xpath("//input[contains(@type,'password') and (@name='reg_passwd__')]");
	
		 Practise_20May Test = new Practise_20May(driver);
		 Test.doSendKeys(Fname, "test");
		 Test.doSendKeys(Lname, "abc");
		 Test.doSendKeys(mail_PhNo, "abc@gmail.com");
		 Test.doSendKeys(NewPass, "12456789");
		 
		 WebElement day = driver.findElement(By.id("day"));
		 WebElement month = driver.findElement(By.id("month"));
		 WebElement year = driver.findElement(By.id("year"));
		 
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		 Test.SelectvalueFromDropDown(day, "5");
		 Test.SelectvalueFromDropDown(month, "Oct");
		 Test.SelectvalueFromDropDown(year, "1990");
		 
		 By Gender = By.xpath("//label[@class='_58mt']");
		 Test.doClick(Gender);
			
		 ArrayList<String> DayList= Test.getvalueFromDropDown2(month); 
		 System.out.println(DayList);
		 
		 
	}
	
	WebDriver driver;
	public Practise_20May(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public WebElement getElement(By Locator)
	{
		WebElement element = driver.findElement(Locator);
		return element;
	}
	
	public void doSendKeys(By Locator, String value)
	{
	       getElement(Locator).sendKeys(value);
	}
	
	public void doClick(By Locator)
	{
		getElement(Locator).click();
	}
	
	 public void SelectvalueFromDropDown(WebElement element, String value)
	 {
		 Select select = new Select(element);
		 select.selectByVisibleText(value);
      }
	 public void getvalueFromDropDown(WebElement element)
	 {
		 System.out.println("--------------");
		 Select select = new Select(element);
		 List<WebElement> dropList = select.getOptions();
		 System.out.println(dropList.size());
		 
		 for(int i =0; i<dropList.size(); i++)
		 {
			 String text = dropList.get(i).getText();
			 System.out.println(text);
		 }
	 }
		 public ArrayList<String> getvalueFromDropDown2(WebElement element)
		 {
			 
			 System.out.println("--------------");
			 Select select = new Select(element);
			 List<WebElement> dropList = select.getOptions();
			 System.out.println(dropList.size());
			 
			 ArrayList<String> ar = new ArrayList<String>();
			 
			 
			 for(int i =0; i<dropList.size(); i++)
			 {
				 String text = dropList.get(i).getText();
                  ar.add(text);
                  
              }
			return ar;
            }
		 public void getvalueFromDropDownWithoutSelect(WebElement element, By Locator)
		 {
			 System.out.println("--------------");
//			 Select select = new Select(element);
			 List<WebElement> dropList = driver.findElements(By.xpath("//select[@id='day']/option"));
			 System.out.println(dropList.size());
			 
			 for(int i =0; i<dropList.size(); i++)
			 {
				 String text = dropList.get(i).getText();
				 System.out.println(text);
				 
				 if(text.equals("25"))
				 {
				 dropList.get(i).click();
				 break;
			 }
			 
}
}
}