package int_appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterMethod;
import java.net.URL;
import java.util.Map;

public class Chapter1_DriverInitilization {

    private AppiumDriver driver;

    /*
     * ============================================================
     * TASK 1 - CONFIGURE ANDROID DRIVER
     * ============================================================
     * UiAutomator2Options is the modern approach for Android
     * driver configuration in Appium Java Client 9.x.
     */
    public void configureAndroidDriver() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("Pixel_8")
                .setAutomationName("UiAutomator2")
                .setApp("/apps/demo.apk");

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                options
        );
    }


    /*
     * ============================================================
     * TASK 2 - CONFIGURE iOS DRIVER
     * ============================================================
     * XCUITestOptions is used for iOS automation.
     */
    public void configureIOSDriver() throws Exception {

        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName("iOS")
                .setDeviceName("iPhone 15")
                .setAutomationName("XCUITest")
                .setApp("/apps/demo.app");

        driver = new IOSDriver(
                new URL("http://127.0.0.1:4723"),
                options
        );
    }


    /*
     * ============================================================
     * TASK 3 - START LOCAL APPIUM SERVER
     * ============================================================
     * AppiumDriverLocalService allows the framework to manage
     * the Appium server programmatically.
     */
    public void startLocalAppiumServer() {

        AppiumDriverLocalService service =
                AppiumDriverLocalService.buildDefaultService();

        service.start();

        // Driver creation can happen after server startup.

        service.stop();
    }


    /*
     * ============================================================
     * TASK 4 - BROWSERSTACK SETUP
     * ============================================================
     * For cloud execution, the main difference is the remote
     * BrowserStack hub URL and cloud-specific capabilities.
     */
    public void configureBrowserStack() throws Exception {

        UiAutomator2Options options =
                new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setDeviceName("Pixel 8");
        options.setAutomationName("UiAutomator2");

        options.setCapability(
                "bstack:options",
                Map.of(
                        "userName", "YOUR_USER",
                        "accessKey", "YOUR_KEY"
                )
        );

        driver = new AndroidDriver(
                new URL("https://hub.browserstack.com/wd/hub"),
                options
        );
    }


    /*
     * ============================================================
     * TASK 5 - DRIVER FACTORY
     * ============================================================
     * Driver Factory centralizes driver creation and allows the
     * framework to switch between Android and iOS easily.
     */
    public AppiumDriver createDriver(boolean android)
            throws Exception {

        if (android) {

            return new AndroidDriver(
                    new URL("http://127.0.0.1:4723"),
                    androidOptions()
            );
        }

        return new IOSDriver(
                new URL("http://127.0.0.1:4723"),
                iosOptions()
        );
    }


    /*
     * Android capability configuration is kept separately so
     * Driver Factory remains clean and maintainable.
     */
    private UiAutomator2Options androidOptions() {

        return new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("Pixel_8")
                .setAutomationName("UiAutomator2")
                .setApp("/apps/demo.apk");
    }


    /*
     * iOS capability configuration is kept separately for
     * maintainability and easy framework extension.
     */
    private XCUITestOptions iosOptions() {

        return new XCUITestOptions()
                .setPlatformName("iOS")
                .setDeviceName("iPhone 15")
                .setAutomationName("XCUITest")
                .setApp("/apps/demo.app");
    }


    /*
     * ============================================================
     * TASK 6 - DRIVER CLEANUP
     * ============================================================
     * quit() terminates the complete Appium session and releases
     * associated device and server resources.
     */
    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }


    /*
     * ============================================================
     * TASK 7 - VERIFY APPIUM SESSION
     * ============================================================
     * Useful for debugging and validating the active session
     * and the capabilities used to create it.
     */
    public void verifySession() {

        System.out.println("Session ID: "
                + driver.getSessionId());

        System.out.println("Capabilities: "
                + driver.getCapabilities());
    }
}
