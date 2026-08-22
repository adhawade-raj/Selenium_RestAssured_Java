package int_allSeleniumCodes_2026;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class Part01_ExcelUtil {


    public static void main(String [] args) throws InvalidFormatException {
        String city = getCellData("TestData", "TC002", "City");
        System.out.println(city);
        System.out.println("------------------------------------------------------------");
        Object[][] data = getTestData("TestData");

        for (Object[] row : data) {

            for (Object cell : row) {
                System.out.print(cell + " | ");
            }

            System.out.println();
        }
    }

    private static String TEST_DATA_SHEET = "src/main/resources/TestData.xlsx";
    private static Workbook book;
    private static Sheet sheet;

    public static Object[][] getTestData(String sheetName) throws InvalidFormatException {

        Object data[][] = null;

        try {
            FileInputStream ip = new FileInputStream(TEST_DATA_SHEET);
            try {
                book = WorkbookFactory.create(ip);
            } catch (InvalidFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sheet = book.getSheet(sheetName);

            data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                    data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
//                    System.out.println(data[i][j] + " ");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }


    /**
     *
     * @param sheetName
     * @param testCase
     * @param headerName
     * @return
     */
    public static String getCellData(String sheetName,
                              String testCase,
                              String headerName) {

        Object[][] data = null;
        try {
            data = getTestData(sheetName);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }

        // Find header column
        int headerColumn = -1;

        for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {

            String header = sheet.getRow(0)
                    .getCell(j)
                    .toString();

            if (header.equalsIgnoreCase(headerName)) {
                headerColumn = j;
                break;
            }
        }

        if (headerColumn == -1) {
            throw new RuntimeException(
                    "Header not found: " + headerName
            );
        }

        // Find TestCase row
        int testCaseColumn = 0;

        for (int i = 0; i < data.length; i++) {

            String currentTestCase =
                    data[i][testCaseColumn].toString();

            if (currentTestCase.equalsIgnoreCase(testCase)) {

                return data[i][headerColumn].toString();
            }
        }

        throw new RuntimeException(
                "Test case not found: " + testCase
        );
    }

}


