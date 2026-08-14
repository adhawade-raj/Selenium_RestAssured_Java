package Practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Get_Navigate {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeOptions co =new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		
		WebDriver driver = new ChromeDriver(co);
		Thread.sleep(5000);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/?&ext_vrnc=hi&tag=googinhydr1-21&ref=pd_sl_4b3e4wrxds_b&adgrpid=60639568242&hvpone=&hvptwo=&hvadid=617721280315&hvpos=&hvnetw=g&hvrand=4956040605341485258&hvqmt=b&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=1007788&hvtargid=kwd-311187680635&hydadcr=5841_2362028");
	
		
		String text = driver.getTitle();
	System.out.println(text);
	
	
	
	driver.navigate().to("https://www.googleadservices.com/pagead/aclk?sa=L&ai=DChcSEwjE64LH0bj_AhVjmWYCHcfUAqEYABAAGgJzbQ&ohost=www.google.com&cid=CAESauD2kqTntbmhqsOVMM3k1scaZwR1AH2HI9RQ7zxDbGvDQRa0DD3TwDR1qL5V6z2JoAc-UTXxXfZeO37nan-eH3g-PL-Qd5FJ_5CspduAW57tB4li_EMJmpnQiMV9_4SyBKlGEyI0DMn_Vrc&sig=AOD64_1D62vBHK86O0JkHFsNbxS7vUUtDA&q&adurl&ved=2ahUKEwijwvrG0bj_AhU3cGwGHdfoBq0Q0Qx6BAgJEAE");
	String text2 = driver.getTitle();
	System.out.println(text2);
	
	driver.navigate().back();
	String text3 = driver.getTitle();
	System.out.println(text3);
	
	driver.navigate().forward();
	System.out.println("Page is on Snapdeal Again");
	}

}
