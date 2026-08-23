package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BrowserHandling {

    static WebDriver driver;

    /** Launches ChromeDriver, sets timeouts and returns a singleton driver instance. */
    public static WebDriver launchDriver() {
        if (driver == null) {
            System.out.println("Launching WebDriver...");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.manage().window().maximize();
        }
        return driver;
    }

    /** Opens the provided URL, launching the driver first if necessary. */
    public static void openUrl(String url) {
        launchDriver();
        driver.get(url);
    }

    /** Returns the singleton driver instance (launches if needed). */
    public static WebDriver getDriver() {
        return launchDriver();
    }

    /** Quits the driver and clears the singleton reference. */
    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }

}
