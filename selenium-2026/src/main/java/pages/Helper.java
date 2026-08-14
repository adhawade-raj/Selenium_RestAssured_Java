package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utils;

import javax.swing.*;
import java.util.List;

public class Helper {

    Utils utils = new Utils();

    // All Locators
    By allTable = By.xpath("//div[@class='tableFixHead']//table[@id='product']//tr");
    By tableValues = By.xpath("//div[@class='tableFixHead']//table[@id='product']/tbody/tr");

    // Iterate all table data
    public void getAllTableData() {
        utils.scroll();
        List<String> text = utils.getTextMultiple(allTable);
        for (String rowText : text) {
            System.out.println(rowText);
        }
    }

    // Iterate only one row based on name/value
    public void getAllTableData_OneRow() {
        utils.scroll();
        List<WebElement> allRows = utils.findElements(allTable);
        for (WebElement row : allRows) {
            if (row.getText().contains("Jack")) {
                System.out.println("Row containing 'Jack': " + row.getText());
            }
        }
    }

    // Get data from specific column based on value in another column
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

    public void getAllTableData_FromSpecificLocation(){
        int count = 0;
        String name = "";

        utils.scroll();
        List<WebElement> allRows = utils.findElements(tableValues);
        for (WebElement row : allRows) {
            List<WebElement> allValues = row.findElements(By.tagName("td"));

            if(allValues.get(2).getText().equalsIgnoreCase("Chennai")){
                count++;
                name = allValues.get(0).getText();
                System.out.println("Row containing 'Chennai': " + name);
            }

        }
        System.out.println("Total Count of names from Chennai: " + count);

    }

/** Finds the person with the highest amount in the table
 * and prints their name and the amount.*/
    public void getAllTableData_PersonWithHighestAmount(){
        String name = "";
        int max = Integer.MIN_VALUE;

        utils.scroll();
        List<WebElement> allRows = utils.findElements(tableValues);
        for (WebElement row : allRows) {
            List<WebElement> allValues = row.findElements(By.tagName("td"));
            if (allValues.size() >= 4) {
                int amount = Integer.parseInt(allValues.get(3).getText());

                if(amount > max){
                    max = amount;
                    name = allValues.get(0).getText();
                }
            }

        }
        System.out.println("Person: " + name);
        System.out.println("Max amount: " + max);
    }
}
