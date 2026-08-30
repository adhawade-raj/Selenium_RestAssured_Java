package int_appium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Chapter2_LocatorsAndElementOperations {

    private AppiumDriver driver;


    /*
     * ============================================================
     * TASK 1 - FIND ELEMENT BY ACCESSIBILITY ID
     * ============================================================
     * Preferred locator for stable and cross-platform elements.
     */
    public void findByAccessibilityId() {

        WebElement loginBtn =
                driver.findElement(
                        AppiumBy.accessibilityId("Login")
                );
    }


    /*
     * ============================================================
     * TASK 2 - FIND ELEMENT BY ANDROID UIAUTOMATOR
     * ============================================================
     * Useful for advanced Android-specific element searches.
     */
    public void findByAndroidUIAutomator() {

        WebElement settings =
                driver.findElement(
                        AppiumBy.androidUIAutomator(
                                "new UiSelector().text(\"Settings\")"
                        )
                );
    }


    /*
     * ============================================================
     * TASK 3 - FIND MULTIPLE ELEMENTS
     * ============================================================
     * findElements() returns an empty list when no elements match.
     */
    public void findMultipleElements() {

        List<WebElement> menuItems =
                driver.findElements(
                        AppiumBy.className(
                                "android.widget.TextView"
                        )
                );
    }


    /*
     * ============================================================
     * TASK 4 - SAFE CLICK UTILITY
     * ============================================================
     * Wait until the element is clickable before performing click.
     * Helps reduce flaky test failures.
     */
    public void click(By locator) {

        new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        )
                .until(
                        ExpectedConditions.elementToBeClickable(locator)
                )
                .click();
    }


    /*
     * ============================================================
     * TASK 5 - ENTER TEXT
     * ============================================================
     * Clear existing text before entering the new value.
     */
    public void type(By locator, String value) {

        WebElement element =
                driver.findElement(locator);

        element.clear();
        element.sendKeys(value);
    }


    /*
     * ============================================================
     * TASK 6 - READ TEXT
     * ============================================================
     * getText() retrieves the visible text of an element.
     */
    public String getText(By locator) {

        return driver.findElement(locator)
                .getText();
    }


    /*
     * ============================================================
     * TASK 7 - READ ATTRIBUTE
     * ============================================================
     * Useful for reading element properties or UI state.
     */
    public String getAttribute(By locator) {

        return driver.findElement(locator)
                .getAttribute("enabled");
    }


    /*
     * ============================================================
     * TASK 8 - VERIFY DISPLAYED
     * ============================================================
     * Checks whether the element is currently visible.
     */
    public boolean isDisplayed(By locator) {

        return driver.findElement(locator)
                .isDisplayed();
    }


    /*
     * ============================================================
     * TASK 9 - VERIFY ENABLED
     * ============================================================
     * Commonly used before performing actions such as clicking.
     */
    public boolean isEnabled(By locator) {

        return driver.findElement(locator)
                .isEnabled();
    }


    /*
     * ============================================================
     * TASK 10 - VERIFY SELECTED
     * ============================================================
     * Commonly used for checkboxes, switches and radio buttons.
     */
    public boolean isSelected(By locator) {

        return driver.findElement(locator)
                .isSelected();
    }


    /*
     * ============================================================
     * TASK 11 - GET ELEMENT RECTANGLE
     * ============================================================
     * getRect() provides both element position and dimensions.
     */
    public void getElementRectangle(By locator) {

        Rectangle rectangle =
                driver.findElement(locator)
                        .getRect();

        int x = rectangle.getX();
        int y = rectangle.getY();

        int width = rectangle.getWidth();
        int height = rectangle.getHeight();
    }


    /*
     * ============================================================
     * TASK 12 - GET ELEMENT LOCATION
     * ============================================================
     * Returns the X and Y coordinates of the element.
     * Useful for gesture calculations.
     */
    public Point getElementLocation(By locator) {

        return driver.findElement(locator)
                .getLocation();
    }


    /*
     * ============================================================
     * TASK 13 - GET ELEMENT SIZE
     * ============================================================
     * Returns width and height of the element.
     * Useful for responsive UI validation.
     */
    public Dimension getElementSize(By locator) {

        return driver.findElement(locator)
                .getSize();
    }


    /*
     * ============================================================
     * INTERVIEW QUICK REVISION
     * ============================================================
     *
     * Locator Preference:
     *
     * Accessibility ID
     *       ↓
     * ID
     *       ↓
     * UIAutomator / Predicate
     *       ↓
     * XPath
     *
     * XPath should generally be avoided when a stable native
     * locator is available because it can be slower and brittle.
     *
     * getText()
     * -> Returns visible text.
     *
     * getAttribute()
     * -> Returns an element property/attribute.
     *
     * findElement()
     * -> Returns one element.
     * -> Throws NoSuchElementException if element is absent.
     *
     * findElements()
     * -> Returns multiple elements.
     * -> Returns an empty list if nothing matches.
     *
     * Explicit Wait
     * -> Synchronizes the test with the application state.
     * -> Helps reduce flaky failures.
     */
}

