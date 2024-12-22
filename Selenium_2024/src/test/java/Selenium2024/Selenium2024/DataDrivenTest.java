package Selenium2024.Selenium2024;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Selenium2024.Selenium2024.ExcelHandling;

public class DataDrivenTest {

	ExcelHandling excelHandling;
	@DataProvider()
	public Object[][] getRegistrationData() throws InvalidFormatException, IOException{
		
		
		Object data[][] = ExcelHandling.getTestData("Sheet1");
		return data;
	}
	
	@Test(dataProvider="getRegistrationData")
	public void getData(String UserName, String Password) {
		
		System.out.println(UserName+ " "+Password);
	}
}
