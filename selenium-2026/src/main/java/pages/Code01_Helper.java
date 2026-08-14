package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utils;

import java.util.List;

import static utilities.Utils.getTextMultiple;

public class Code01_Helper {
    Utils utils = new Utils();

    //All Locators
    By allTable = By.xpath("//div[@class='tableFixHead']//table[@id='product']//tr");

    public void getAllTableData() {
        utils.scroll();
        List<String> text = utils.getTextMultiple(allTable);
        for (String rowText : text) {
            System.out.println(rowText);
        }
    }
}
