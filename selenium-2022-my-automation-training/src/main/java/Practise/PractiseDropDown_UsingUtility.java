package Practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PractiseDropDown_UsingUtility {

	
	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/campaign/landing.php?campaign_id=14884913640&extra_1=s%7Cc%7C589460569900%7Ce%7Cfacebook%20login%7C&placement=&creative=589460569900&keyword=facebook%20login&partner_id=googlesem&extra_2=campaignid%3D14884913640%26adgroupid%3D128696221912%26matchtype%3De%26network%3Dg%26source%3Dnotmobile%26search_or_content%3Ds%26device%3Dc%26devicemodel%3D%26adposition%3D%26target%3D%26targetid%3Dkwd-1409285535%26loc_physical_ms%3D9062085%26loc_interest_ms%3D%26feeditemid%3D%26param1%3D%26param2%3D&gclid=Cj0KCQjwg_iTBhDrARIsAD3Ib5h7tiGYs5CX517fPZpCQSRsYFIlvwEYo7HioK1r6YSlAC3TH-jmTgwaAgy4EALw_wcB");
		
	WebElement day =driver.findElement(By.id("day"));
	WebElement month =driver.findElement(By.id("month"));
	WebElement year =driver.findElement(By.id("year"));
	
	
	selectDropDownValues(day,"5");
	selectDropDownValues(month, "Oct");
	selectDropDownValues(year, "1990");
	
	}
	

	private static void selectDropDownValues(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);		
	}

}
