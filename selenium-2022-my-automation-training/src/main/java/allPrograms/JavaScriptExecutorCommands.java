package allPrograms;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptExecutorCommands {

    static WebDriver driver;

    // Click an already located WebElement using JavaScript

    public static void javaScriptExecutorLocate_ClickElement(WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("arguments[0].click();", element);
    }

    // Locate an element using Selenium By locator and click it using JavaScript

    public static void javaScriptExecutorLocateElement_ClickElement(By locator) {
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        WebElement element = driver.findElement(locator);
        js.executeScript("arguments[0].click();", element);
    }

    // Scroll to the bottom of the page using JavaScript

    public static void javaScriptExecutorScrolling_01() {
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // Scroll to a specific horizontal and vertical position using JavaScript

    public static void javaScriptExecutorScrolling_02(int horizontal, int vertical) {
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("window.scrollTo(" + horizontal + ", " + vertical + ");");
    }

}
