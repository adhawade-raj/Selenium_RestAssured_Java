package allPrograms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Part20_WebTable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.selenium.dev/documentation/webdriver/getting_started/install_drivers/");
		
//		How Many rows in table
		int rows = driver.findElements(By.xpath("//table/tbody/tr")).size();
		System.out.println("Size is :"+rows);
		
//		How Many Coloumns in table
		int column = driver.findElements(By.xpath("//table//tr/th")).size();
		System.out.println("Size of coloumn is:"+column);
		
//		To retrieve specific coloumn 
	   String text = driver.findElement(By.xpath("//table//tr[2]/td[1]")).getText();
	   System.out.println("Text is:" +text);
	   System.out.println("--------------------");
		
//		To retrieve all data from table 
//		for(int i=0; i<=rows; i++)
//		{
//			for(int j=0; i<=column; j++)
//			{
//				String data = driver.findElement(By.xpath("//table/tbody/tr[\"+i+\"]/td["+j+"]")).getText();
//				System.out.println(data+ "  ");
//			}
//		}
//		
	   
	   
	   
//		Print 2 3 data from table
		for(int i=1; i<=rows; i++)
		{
			String browser = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[1]")).getText();
			System.out.println(browser+" ");
			
			if(browser.equalsIgnoreCase("Firefox"))
			{
				String SupportedOS= driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[2]")).getText();
				String Maintainedby= driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[3]")).getText();
				System.out.print(browser+" " +SupportedOS+ " "+Maintainedby);
			}
		}
	}

}
