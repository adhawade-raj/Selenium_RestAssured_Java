package allPrograms;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptExecutorCommands {

    static WebDriver driver;

    public static void javaScriptExecutorClick() {
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("document.getElementById('elementId').click();");
    }

    public static void javaScriptExecutorScrolling_01() {
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public static void javaScriptExecutorScrolling_02(int horizontal, int vertical) {
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("window.scrollTo(" + horizontal + ", " + vertical + ");");
    }

    public static void javaScriptExecutorLocateElement(WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("arguments[0].click();", element);
    }

    public static void javaScriptExecutorLocateElement(By locator) {
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        WebElement element = driver.findElement(locator);
        js.executeScript("arguments[0].click();", element);
    }

    public static void javaScriptExecutorLocateClick(By locator) {
        javaScriptExecutorLocateElement(locator);
        javaScriptExecutorClick();
    }

}
