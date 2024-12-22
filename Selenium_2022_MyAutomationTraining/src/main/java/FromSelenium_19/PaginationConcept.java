package FromSelenium_19;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PaginationConcept {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://babynames.merschat.com/popular.cgi");
		Thread.sleep(5000);
	List<WebElement> namelist=	driver.findElements(By.xpath("//a[contains(text(),'Hela')]"));
	
	while(true)
	{
		if((namelist.size()==0))
//			if(!(namelist.size()==1))
		{
			driver.findElement(By.linkText("Next")).click();
			namelist=	driver.findElements(By.xpath("//a[contains(text(),'Hela')]"));
		}
	
	
	else
	{
		String Title =driver.findElement(By.xpath("(//a[contains(text(),'Hela')]/../parent::td/following-sibling::td)[2]//a")).getAttribute("title");
	System.out.println(Title);
		break;
	}
	}
	}
}
