package Practise;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Practise2_DropDownHandlingUtility {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
	    driver.get("https://www.facebook.com/campaign/landing.php?campaign_id=14884913640&extra_1=s%7Cc%7C589460569849%7Ce%7Cfacebook%7C&placement=&creative=589460569849&keyword=facebook&partner_id=googlesem&extra_2=campaignid%3D14884913640%26adgroupid%3D128696220912%26matchtype%3De%26network%3Dg%26source%3Dnotmobile%26search_or_content%3Ds%26device%3Dc%26devicemodel%3D%26adposition%3D%26target%3D%26targetid%3Dkwd-1001394929%26loc_physical_ms%3D9062085%26loc_interest_ms%3D%26feeditemid%3D%26param1%3D%26param2%3D&gclid=Cj0KCQjwmuiTBhDoARIsAPiv6L_Zg3cDtPtDHwrDKDAdHElsQQYoSsz_Eq5OC__xwhvrPqcPYYZPA6oaApdSEALw_wcB");
	
	    WebElement day = driver.findElement(By.id("day"));
	WebElement month = driver.findElement(By.id("month"));
	WebElement year = driver.findElement(By.id("year"));
	 
	ArrayList<String>DaysList = Practise1_DropDownHandling.getdropDownValues2(day);
	System.out.println(DaysList);
	
	ArrayList<String>monthList = Practise1_DropDownHandling.getdropDownValues2(month);
	System.out.println(monthList);
	
	ArrayList<String>yearList = Practise1_DropDownHandling.getdropDownValues2(year);
	System.out.println(yearList);
	    
	}
	}


