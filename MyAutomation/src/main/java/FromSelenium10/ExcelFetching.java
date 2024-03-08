package FromSelenium10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFetching {

	public static void main(String[] args) throws InvalidFormatException, IOException {

		//Xls_Reader reader = new Xls_Reader("/MyAutomation/src/main/java/FromSelenium10/Sample For Selenium Prog.xlsx"); 
		String path = "/MyAutomation/src/main/java/FromSelenium10/Sample For Selenium Prog.xlsx";
		
		
		
//		String sheetName = "Sheet1";
//	String data =	reader.getCellData("Sheet1", 0, 2);
//		System.out.println(data);
		
		FileInputStream file = new FileInputStream(path);
		String value = WorkbookFactory.create(file).getSheet("Sheet1").getRow(2).getCell(1).getStringCellValue();
		
		System.out.println(value);
	}

}
