package Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropDownWithoutSelect2 {

	public static void main(String[] args) {

		
		WebDriverManager.chromedriver().setup();
	    WebDriver driver = new ChromeDriver();
	    driver.get("https://www.facebook.com/campaign/landing.php?campaign_id=14884913640&extra_1=s%7Cc%7C589460569849%7Ce%7Cfacebook%7C&placement=&creative=589460569849&keyword=facebook&partner_id=googlesem&extra_2=campaignid%3D14884913640%26adgroupid%3D128696220912%26matchtype%3De%26network%3Dg%26source%3Dnotmobile%26search_or_content%3Ds%26device%3Dc%26devicemodel%3D%26adposition%3D%26target%3D%26targetid%3Dkwd-1001394929%26loc_physical_ms%3D9062085%26loc_interest_ms%3D%26feeditemid%3D%26param1%3D%26param2%3D&gclid=Cj0KCQjwmuiTBhDoARIsAPiv6L_Zg3cDtPtDHwrDKDAdHElsQQYoSsz_Eq5OC__xwhvrPqcPYYZPA6oaApdSEALw_wcB");
	    
	    String day = "//*[@id='day\']"; 
	    String month = "//*[@id='day\']";   
	    String year = "//*[@id='day\']";
	    
		DropDownUtilJava.selecDropDownvalueWithoutSelect(driver, day, "13");
		DropDownUtilJava.selecDropDownvalueWithoutSelect(driver, month, "oct");
		DropDownUtilJava.selecDropDownvalueWithoutSelect(driver, year, "1990");
	    
	}

}
