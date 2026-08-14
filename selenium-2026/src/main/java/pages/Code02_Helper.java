package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utils;

import java.util.List;

public class Code02_Helper {

    Utils utils = new Utils();

    //All Locators
    By allTable = By.xpath("//div[@class='tableFixHead']//table[@id='product']//tr");

    public void getAllTableData() {
        utils.scroll();
        List<WebElement> allRows = utils.findElements(allTable);
        for (WebElement row : allRows) {
            if(row.getText().contains("Jack")) {
                System.out.println("Row containing 'Jack': " + row.getText());
            }
        }
    }
}
