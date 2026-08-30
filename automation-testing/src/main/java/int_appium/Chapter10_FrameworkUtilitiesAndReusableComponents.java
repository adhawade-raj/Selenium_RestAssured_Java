package int_appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class Chapter10_FrameworkUtilitiesAndReusableComponents {

    /*
     * ============================================================
     * TASK 1 - DRIVER MANAGER
     * ============================================================
     * ThreadLocal maintains a separate driver instance for each
     * execution thread, making it suitable for parallel testing.
     */
    public static class DriverManager {

        private static final ThreadLocal<AppiumDriver> DRIVER =
                new ThreadLocal<>();

        public static void setDriver(AppiumDriver driver) {

            DRIVER.set(driver);
        }

        public static AppiumDriver getDriver() {

            return DRIVER.get();
        }

        public static void unloadDriver() {

            DRIVER.remove();
        }
    }


    /*
     * ============================================================
     * TASK 2 - BASE PAGE
     * ============================================================
     * BasePage provides common driver and utility access to all
     * Page Object classes.
     */
    public static class BasePage {

        protected AppiumDriver driver =
                DriverManager.getDriver();

         WaitUtils wait =
                new WaitUtils(driver);

        protected ElementUtils element =
                new ElementUtils(driver);
    }


    /*
     * ============================================================
     * TASK 3 - ELEMENT UTILS
     * ============================================================
     * Centralizes common element interactions and avoids
     * duplicating click/type logic across page classes.
     */
    public static class ElementUtils {

        private final AppiumDriver driver;

        private final WaitUtils wait;

        public ElementUtils(AppiumDriver driver) {

            this.driver = driver;
            wait = new WaitUtils(driver);
        }

        public void click(By locator) {

            wait.waitUntilClickable(locator)
                    .click();
        }

        public void type(By locator, String text) {

            WebElement element =
                    wait.waitUntilVisible(locator);

            element.clear();
            element.sendKeys(text);
        }

        public String getText(By locator) {

            return wait.waitUntilVisible(locator)
                    .getText();
        }
    }


    /*
     * ============================================================
     * TASK 4 - WAIT UTILS
     * ============================================================
     * Centralizes explicit waits so synchronization logic is
     * consistent throughout the framework.
     */
    public static class WaitUtils {

        private final AppiumDriver driver;

        public WaitUtils(AppiumDriver driver) {

            this.driver = driver;
        }

        public WebElement waitUntilVisible(By locator) {

            return new WebDriverWait(
                    driver,
                    Duration.ofSeconds(15)
            ).until(
                    ExpectedConditions
                            .visibilityOfElementLocated(locator)
            );
        }

        public WebElement waitUntilClickable(By locator) {

            return new WebDriverWait(
                    driver,
                    Duration.ofSeconds(15)
            ).until(
                    ExpectedConditions
                            .elementToBeClickable(locator)
            );
        }
    }


    /*
     * ============================================================
     * TASK 5 - GESTURE UTILS
     * ============================================================
     * Gesture implementation should remain separate from
     * test cases and page objects.
     */
    public static class GestureUtils {

        private final AppiumDriver driver;

        public GestureUtils(AppiumDriver driver) {

            this.driver = driver;
        }

        public void swipeUp() {

            // W3C Actions swipe implementation
        }

        public void longPress() {

            // W3C Actions long press implementation
        }
    }


    /*
     * ============================================================
     * TASK 6 - SCREENSHOT UTILS
     * ============================================================
     * Central location for screenshot capture and file management.
     */
    public static class ScreenshotUtils {

        public String capture(String name) {

            // Capture screenshot and return file path

            String path =
                    "screenshots/" + name + ".png";

            return path;
        }
    }


    /*
     * ============================================================
     * TASK 7 - CONTEXT UTILS
     * ============================================================
     * Simplifies switching between native application and
     * WebView contexts.
     */
    public static class ContextUtils {

        private final AppiumDriver driver;

        public ContextUtils(AppiumDriver driver) {

            this.driver = driver;
        }

        public void switchToNative() {

            if (driver instanceof AndroidDriver) {
                ((AndroidDriver) driver).context("NATIVE_APP");
            }
        }

        public void switchToWebView() {

            if (driver instanceof AndroidDriver) {
                AndroidDriver androidDriver = (AndroidDriver) driver;
                for (String context :
                        androidDriver.getContextHandles()) {

                    if (context.contains("WEBVIEW")) {

                        androidDriver.context(context);

                        break;
                    }
                }
            }
        }
    }


    /*
     * ============================================================
     * TASK 8 - RETRY UTILS
     * ============================================================
     * Encapsulates retry logic so test cases do not contain
     * repeated try/catch loops.
     */
    public static class RetryUtils {

        public boolean retry(Action action) {

            for (int i = 0; i < 3; i++) {

                try {

                    action.execute();

                    return true;

                } catch (Exception ignored) {

                    // Retry failed action
                }
            }

            return false;
        }
    }


    /*
     * Functional interface representing an action that can
     * be executed and retried.
     */
    @FunctionalInterface
    public interface Action {

        void execute() throws Exception;
    }


    /*
     * ============================================================
     * TASK 9 - SAMPLE FRAMEWORK STRUCTURE
     * ============================================================
     *
     * src
     *  ├── base
     *  ├── driver
     *  ├── pages
     *  ├── utils
     *  ├── listeners
     *  ├── tests
     *  └── reports
     *
     * A clean package structure improves maintainability,
     * scalability and team collaboration.
     */

}
