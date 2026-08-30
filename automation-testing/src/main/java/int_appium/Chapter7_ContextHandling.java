package int_appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

public class Chapter7_ContextHandling {

    private AppiumDriver driver;


    /*
     * ============================================================
     * TASK 1 - GET AVAILABLE CONTEXTS
     * ============================================================
     * Lists all available contexts such as:
     * NATIVE_APP and WEBVIEW.
     */
    public void getAvailableContexts() {

        if (driver instanceof AndroidDriver) {
            Set<String> contexts =
                    ((AndroidDriver) driver).getContextHandles();

            for (String context : contexts) {
                System.out.println(context);
            }
        }
    }


    /*
     * ============================================================
     * TASK 2 - GET CURRENT CONTEXT
     * ============================================================
     * Returns the context in which the driver is currently operating.
     */
    public String getCurrentContext() {

        if (driver instanceof AndroidDriver) {
            String currentContext =
                    ((AndroidDriver) driver).getContext();

            System.out.println(currentContext);
            return currentContext;
        }
        return null;
    }


    /*
     * ============================================================
     * TASK 3 - SWITCH TO WEBVIEW
     * ============================================================
     * Finds an available WEBVIEW context and switches to it.
     * Required before interacting with HTML elements.
     */
    public void switchToWebView() {

        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            for (String context : androidDriver.getContextHandles()) {

                if (context.contains("WEBVIEW")) {
                    androidDriver.context(context);
                    break;
                }
            }
        }
    }


    /*
     * ============================================================
     * TASK 4 - SWITCH BACK TO NATIVE
     * ============================================================
     * Switches the driver back to the native application context.
     */
    public void switchToNative() {

        if (driver instanceof AndroidDriver) {
            ((AndroidDriver) driver).context("NATIVE_APP");
        }
    }


    /*
     * ============================================================
     * TASK 5 - HANDLE MULTIPLE WEBVIEWS
     * ============================================================
     * Selects a specific WebView when multiple WebView contexts
     * are available.
     */
    public void switchToSpecificWebView() {

        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            for (String context : androidDriver.getContextHandles()) {

                if (context.contains("WEBVIEW_com.demo")) {
                    androidDriver.context(context);
                    break;
                }
            }
        }
    }


    /*
     * ============================================================
     * TASK 6 - VERIFY CONTEXT BEFORE ACTION
     * ============================================================
     * Ensures the driver is in a WebView before locating HTML
     * elements.
     */
    public void clickWebElement() {

        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            if (androidDriver.getContext().contains("WEBVIEW")) {

                driver.findElement(
                        By.cssSelector("#login")
                ).click();
            }
        }
    }


    /*
     * ============================================================
     * TASK 7 - WAIT FOR WEBVIEW
     * ============================================================
     * Waits until an additional context becomes available.
     * Useful because WebView initialization can take time.
     */
    public void waitForWebView() {

        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            new WebDriverWait(
                    androidDriver,
                    Duration.ofSeconds(20)
            ).until(
                    d -> ((AndroidDriver) d).getContextHandles().size() > 1
            );
        }
    }


    /*
     * ============================================================
     * TASK 8 - NATIVE TO WEBVIEW LOGIN FLOW
     * ============================================================
     * Typical hybrid application flow:
     *
     * 1. Interact with native element.
     * 2. Switch to WebView.
     * 3. Interact with HTML element.
     * 4. Switch back to native.
     */
    public void nativeToWebViewLoginFlow(
            By userField) {

        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;

            // Native context
            driver.findElement(userField)
                    .click();

            // Switch to WebView
            androidDriver.context("WEBVIEW_com.demo");

            // WebView interaction
            driver.findElement(
                    By.id("login")
            ).click();

            // Return to native
            androidDriver.context("NATIVE_APP");
        }
    }
}
