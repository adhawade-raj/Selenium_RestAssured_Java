package int_allSeleniumCodes_2026;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Utils_IntSeries extends Part02_JavaScriptExecutorCommands {


    private static final Logger LOG = LoggerFactory.getLogger(Utils_IntSeries.class);
    Part02_JavaScriptExecutorCommands jsExecutor = new Part02_JavaScriptExecutorCommands();
    public static JavascriptExecutor js;

    @Getter
    @Setter
    protected static WebDriver webDriver;
    private static final long DRIVER_WAIT_TIME = 60;
    private static final long ELEMENT_WAIT_TIME = 20;
    String HOME_PAGE_URL = "https://www.ixigo.com/flights";

    @Getter
    protected WebDriverWait wait;
    public final String platform = System.getenv("platform");
    public final String browser = System.getProperty("browser");

    public Utils_IntSeries() {

        webDriver = new ChromeDriver();
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
        js = (JavascriptExecutor) webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @SneakyThrows
    public void openHomePage() {
        LOG.info("Web site url:" + HOME_PAGE_URL);
        webDriver.manage().window().maximize();
        webDriver.navigate().to(HOME_PAGE_URL);
        LOG.info(HOME_PAGE_URL);
    }

    /** Clicks an element by locator, retrying with an explicit wait if the element is not initially found. */
    public void clickByElement(final By by) {

        LOG.info("Running: clickByElement");
        if (isElementPresentBy(by)) {
            webDriver.findElement(by).click();
        } else {
            try {
                LOG.info("could not find element on 1st attempt, trying again");
                waitForExpectedElement(by).click();
            } catch (final Exception e) {
                LOG.info("****ERROR FINDING ELEMENT " + by);
                LOG.info("failed to find element after 2 attempts");
            }
        }
    }

    /** Checks whether an element exists on the page using the specified locator. */
    public boolean isElementPresentBy(final By by) {
        LOG.info("Running: isElementPresentBy");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DRIVER_WAIT_TIME));
        final boolean exists = !webDriver.findElements(by).isEmpty();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DRIVER_WAIT_TIME));
        LOG.info("Element " + by + " exists: " + exists);
        return exists;
    }

    /** Checks whether the element located by the specified locator is displayed. */
    public boolean isDisplayedBy(final By by) {
        LOG.info("Running: isDisplayedBy");
        return webDriver.findElement(by).isDisplayed();
    }

    /** Waits until the specified element is visible and returns it. */
    protected WebElement waitForExpectedElement(final By by) {
        LOG.info("Running: waitForExpectedElement");
        try {
            return this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            LOG.info("Element not found: " + by, e);
            return null;
        }
    }

    /** Waits until the specified element is clickable. */
    protected void waitForExpectedElementToBeClickable(final By by) {
        LOG.info("Running: waitForExpectedElement");
        this.wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    /** Waits for an element to be visible, clears its existing text, and enters the specified text. */
    public void waitClearAndEnterText(final By by, final String textToEnter) {
        LOG.info("Running: waitClearAndEnterText");
        waitForExpectedElement(by).clear();
        waitForExpectedElement(by).sendKeys(textToEnter);
    }

    /** Verifies that the current page title matches the expected title. */
    public void verifyPageTitle(final String expectedTitle) {
        Assert.assertEquals(expectedTitle, webDriver.getTitle());
    }

    /** Waits for the specified element to become visible using a FluentWait with polling. */
    public void waitForSpecificElement(final By by) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(300))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(Exception.class);
        fluentWait.until(webDriver -> webDriver.findElement(by).isDisplayed());
    }

    /** Checks whether the specified element is clickable and returns the result. */
    public boolean isElementClickableBy(final By by) {
        LOG.info("Running: isElementClickable");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (Exception e) {
            LOG.info("Element is not clickable: " + by);
            return false;
        }
    }

    /** Moves the mouse pointer over the specified element. */
    public void hoverOnElement(final By by) {
        final Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(by)).build().perform();
    }

    /** Hovers over the main menu element and clicks the specified submenu element. */
    public void hoverOnElementAndClickSubmenu(final By mainMenuBy, final By by) {
        jsExecutor.scrollToTopOfPage();
        hoverOnElement(mainMenuBy);
        waitForExpectedElement(by).click();
    }

    /** Checks whether the specified element is available and displayed on the page. */
    public boolean isItAvailableBy(final By by) {
        LOG.info("Running: isItAvailableBy");
        try {
            return webDriver.findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /** Scrolls to the specified element and clicks it using Selenium Actions. */
    public void scrollToElementAndClick(final By by) {
        final Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(by))
                .click()
                .build()
                .perform();
    }

    /** Scrolls to the specified WebElement and clicks it using Selenium Actions. */
    public void scrollToElementAndClick(final WebElement element) {
        final Actions actions = new Actions(webDriver);
        actions.moveToElement(element)
                .click()
                .build()
                .perform();
    }
}
