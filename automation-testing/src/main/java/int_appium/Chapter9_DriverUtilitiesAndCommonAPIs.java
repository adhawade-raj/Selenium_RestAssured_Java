package int_appium;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import java.util.Map;
public class Chapter9_DriverUtilitiesAndCommonAPIs {

    private AppiumDriver driver;


    /*
     * ============================================================
     * TASK 1 - GET PAGE SOURCE
     * ============================================================
     * Returns the current UI hierarchy.
     * Useful for debugging locator failures.
     */
    public String getPageSource() {

        String pageSource =
                driver.getPageSource();

        System.out.println(pageSource);

        return pageSource;
    }


    /*
     * ============================================================
     * TASK 2 - GET SESSION ID
     * ============================================================
     * Helps identify and correlate a specific Appium session
     * with server logs.
     */
    public String getSessionId() {

        String sessionId =
                driver.getSessionId().toString();

        System.out.println(sessionId);

        return sessionId;
    }


    /*
     * ============================================================
     * TASK 3 - GET DRIVER CAPABILITIES
     * ============================================================
     * Retrieves runtime capabilities such as platform,
     * automation engine and device configuration.
     */
    public Capabilities getDriverCapabilities() {

        Capabilities caps =
                driver.getCapabilities();

        System.out.println(caps.asMap());

        return caps;
    }


    /*
     * ============================================================
     * TASK 4 - NAVIGATE BACK
     * ============================================================
     * Navigates back in supported contexts.
     */
    public void navigateBack() {

        driver.navigate().back();
    }


    /*
     * ============================================================
     * TASK 5 - REFRESH WEBVIEW
     * ============================================================
     * Refreshes the current page when working inside a WebView.
     */
    public void refreshWebView() {

        driver.navigate().refresh();
    }


    /*
     * ============================================================
     * TASK 6 - EXECUTE JAVASCRIPT / mobile:* COMMAND
     * ============================================================
     * executeScript() can be used for Appium mobile:* commands
     * and JavaScript execution in supported WebView contexts.
     */
    public void executeDeepLink() {

        driver.executeScript(
                "mobile: deepLink",
                Map.of(
                        "url",
                        "myapp://home",

                        "package",
                        "com.demo.app"
                )
        );
    }


    /*
     * ============================================================
     * TASK 7 - SWITCH TO ACTIVE ELEMENT
     * ============================================================
     * Returns the element that currently has focus.
     */
    public WebElement getActiveElement() {

        return driver.switchTo()
                .activeElement();
    }


    /*
     * ============================================================
     * TASK 8 - GRACEFULLY END SESSION
     * ============================================================
     * Terminates the Appium session and releases device resources.
     */
    public void quitDriver() {

        if (driver != null) {

            driver.quit();
        }
    }


    /*
     * ============================================================
     * TASK 9 - GENERIC DRIVER UTILITY
     * ============================================================
     * Centralizes driver access so page objects and utilities
     * do not need to create or manage the driver directly.
     *
     * Replace this with your actual DriverManager implementation
     * used by your framework.
     */
    public AppiumDriver getDriver() {

        return driver;
    }

}
