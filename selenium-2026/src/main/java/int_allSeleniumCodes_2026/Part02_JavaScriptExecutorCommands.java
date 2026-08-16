package int_allSeleniumCodes_2026;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Part02_JavaScriptExecutorCommands {

    static WebDriver webDriver;
    static JavascriptExecutor js;

    // Click an already located WebElement using JavaScript

    public static void javaScriptExecutorLocate_ClickElement(WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor)webDriver);
        js.executeScript("arguments[0].click();", element);
    }

    /** Scrolls the specified WebElement into view and clicks it using JavaScript. */
    public void clickUsingJSWithWebElement(final WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }

    /** Scrolls the page until the specified element is brought into view. */
    public void scrollToViewElement(final By by) {
        final WebElement element = webDriver.findElement(by);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Locate an element using Selenium By locator and click it using JavaScript

    public static void javaScriptExecutorLocateElement_ClickElement(By locator) {
        JavascriptExecutor js = ((JavascriptExecutor)webDriver);
        WebElement element = webDriver.findElement(locator);
        js.executeScript("arguments[0].click();", element);
    }


    // Scroll to a specific horizontal and vertical position using JavaScript

    public static void javaScriptExecutorScrolling_02(int horizontal, int vertical) {
        JavascriptExecutor js = ((JavascriptExecutor)webDriver);
        js.executeScript("window.scrollTo(" + horizontal + ", " + vertical + ");");
    }

    /** Scrolls to the end of the page and pauses briefly after scrolling. */
    public void scrollToEndOfPage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** Scrolls to the top of the page and pauses briefly after scrolling. */
    public void scrollToTopOfPage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript("window.scrollTo(0, 0);");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /** Waits until the page JavaScript document is completely loaded. */
    public void waitForJavaScriptLoad() {
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
                .until((ExpectedCondition<Boolean>) wd ->
                        ((JavascriptExecutor) wd)
                                .executeScript("return document.readyState")
                                .equals("complete"));
    }

}
