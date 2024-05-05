package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.Augmenter;

public class Utility {

	WebDriver driver;
	WebDriver augmentedDriver;
	
	String TestDataSheet = "src/test/resources/ExcelTestData/ExcelHandling.xlsx";
	 Workbook book;
	 Sheet sheet;
	
	Utility(WebDriver driver, WebDriver augmentedDriver){
		this.driver=driver;
		this.driver=augmentedDriver;
	}
	
	/**
	 * This is to provide run time scrolling values as per requirement
	 * @param driver
	 * @param horizontalValue
	 * @param verticalValue
	 */
	public void scrollWithRunTimeInput(WebDriver driver, int horizontalValue, int verticalValue ) {
		 String a ="window.scrollBy("+horizontalValue;
		 String b = ",";
		 String c = verticalValue+")";
		 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(a+b+c, "");
	 }
	
	/**
	 * To scroll only vertically
	 * @param driver
	 */
	public void scrollVertically(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,125);", "");
	}
	
	/**
	 * To scroll only Horizontally
	 * @param driver
	 */
	public void scrollHorizontally(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(125,0);", "");
	}
	
	
	/**
	 * To capture screen shot of page for specially Selenium 4.8.1
	 * @param driver
	 * @param augmentedDriver
	 * @param location
	 */
	public void captureScreenShot(WebDriver driver, WebDriver augmentedDriver, String location) {
		
		String path;
		try {
		augmentedDriver = new Augmenter().augment(driver);
		File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
		 path = location + source.getName();
		FileHandler.copy(source, new File(path));
		} catch (IOException e) {
			path = "Failed to capture Screenshot";
		}
	}
	
	/**
	 * To highlight element while taking screenshot
	 * @param driver
	 * @param ele
	 */
	public void makeBorder(WebDriver driver, WebElement ele) {
		
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("argument[0].style.border='3px Solid Red' ",ele);
		}
	
	
	/**
	 * For Data Driven Testing ( Excel Data Fetching)
	 * @param sheetName
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public Object[][] getTestData(String sheetName) throws InvalidFormatException, IOException{
		
		 Object data[][] = null;
		 
		 FileInputStream fis = new FileInputStream(TestDataSheet); 
		 book = WorkbookFactory.create(fis);
		 sheet = book.getSheet(sheetName);
		 
		 data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		 for(int i=0; i<sheet.getLastRowNum(); i++) {
			 for(int j=0; j<sheet.getRow(0).getLastCellNum(); j++) {
				 data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				 System.out.println(data[i][j]+" ");
			 }
		 }

		return data;
		
	}
	
}
