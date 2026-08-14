package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

public class Utils {

    static WebDriver driver;

    /** Launches ChromeDriver, sets timeouts and opens the practice URL. */
    public static void launchDriver() {
        System.out.println("Launching WebDriver...");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
    }

    /** Waits until the element located by the locator is visible. */
    public static void waitForVisible(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /** Waits until the element located by the locator is present in the DOM. */
    public static void waitForPresence(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /** Finds and returns the visible WebElement for the given locator. */
    public static WebElement findElement(By locator) {
        waitForVisible(locator);
        WebElement ele = driver.findElement(locator);
        return ele;
    }

    /** Finds and returns a list of visible WebElements for the given locator. */
    public static List<WebElement> findElements(By locator) {
        waitForVisible(locator);
        List<WebElement> elements = driver.findElements(locator);
        return elements;
    }

    /** Clicks on the element located by the given locator. */
    public static void click(By locator) {
        findElement(locator).click();
    }

    /** Clicks all elements that match the given locator. */
    public static void clickMultipleElements(By locator) {
        List<WebElement> elements = findElements(locator);
        for (WebElement element : elements) {
            element.click();
        }
    }
    /** Clicks the first element whose text contains the provided text. */
    public static void clickMultipleElements(By locator, String Text) {
        List<WebElement> elements = findElements(locator);
        for (WebElement element : elements) {
            if(element.getText().contains(Text)) {
                element.click();
            }
        }
    }

    /** Prints the text content of the element located by the locator. */
    public static String getText(By locator) {
        String text = findElement(locator).getText();
        //System.out.println(text);
        return text;
    }

    /** Returns list of text contents from all elements matching the locator. */
    public static List<String> getTextMultiple(By locator) {
        List<WebElement> elements = findElements(locator);
        List<String> texts = new ArrayList<>();
        for (WebElement element : elements) {
            texts.add(element.getText());
        }
        return texts;
    }

    /** Scrolls the page down by 500 pixels. */
    public static void scroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
    }

    /** Scrolls the page to bring the given locator into view. */
    public static void scroll(By element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        waitForVisible(element);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
