package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utils;

import java.util.List;

public class Code02_Helper {

    Utils utils = new Utils();

    //All Locators
    By allTable = By.xpath("//div[@class='tableFixHead']//table[@id='product']//tr");
    By tableValues = By.xpath("//div[@class='tableFixHead']//table[@id='product']/tbody/tr");

    public void getAllTableData_OneRow() {
        utils.scroll();
        List<WebElement> allRows = utils.findElements(allTable);
        for (WebElement row : allRows) {
            if (row.getText().contains("Jack")) {
                System.out.println("Row containing 'Jack': " + row.getText());
            }
        }
    }


    public void getAllTableData_SpecificData() {
        utils.scroll();
        List<WebElement> allRows = utils.findElements(tableValues);
        for (WebElement row : allRows) {
            List<WebElement> allValues = row.findElements(By.tagName("td"));

            // System.out.println("Row: " + row.getText());
            // System.out.println("TD count: " + allValues.size());
            if (allValues.size() >= 4) {
                if (allValues.get(2).getText().equalsIgnoreCase("Pune")) {
                    String position = allValues.get(1).getText();
                    System.out.println("Row containing 'Pune': " + position);
                }
            }
        }
    }
}
