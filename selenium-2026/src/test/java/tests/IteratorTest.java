package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.BrowserHandling;
import utilities.Utils;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertNotNull;

/**
 * Example TestNG iterator-style test class that initializes the singleton driver in @BeforeClass
 * and accepts the base URL at runtime via TestNG @Parameters.
 */
public class IteratorTest {

    protected WebDriver driver;
    protected Utils utils;

    @Parameters({"baseUrl"})
    @BeforeClass(alwaysRun = true)
    public void setup(String baseUrl) {
        // Launch the singleton driver and open the runtime URL
        BrowserHandling.launchDriver();
        BrowserHandling.openUrl(baseUrl);

        // Retrieve driver instance and create Utils helper without forcing navigation
        driver = BrowserHandling.getDriver();
        utils = new Utils();

        assertNotNull(driver, "Driver should be initialized");
    }

    @Test
    public void sampleTest() {
        // Sample interaction: ensure page title is available
        String title = driver.getTitle();
        System.out.println("Page title: " + title);
        assertNotNull(title);
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        // Clean up driver and avoid leftover singleton
        BrowserHandling.quitDriver();
    }
}
