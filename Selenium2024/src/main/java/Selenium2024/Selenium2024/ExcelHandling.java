package Selenium2024.Selenium2024;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelHandling {

	static String TestDataSheet = "src/test/resources/ExcelTestData/ExcelHandling.xlsx";
	 static Workbook book;
	 static Sheet sheet;
	
	public static void main(String[] args) throws InvalidFormatException, IOException {

		getTestData("Sheet1");

	}
	
	
	
	public static Object[][] getTestData(String sheetName) throws InvalidFormatException, IOException{
		
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
