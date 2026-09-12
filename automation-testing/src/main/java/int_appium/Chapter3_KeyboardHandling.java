package int_appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Chapter3_KeyboardHandling {

    private AppiumDriver driver;


    /*
     * ============================================================
     * TASK 1 - HIDE KEYBOARD
     * ============================================================
     * Safely hides the keyboard only when it is visible.
     */
    public void hideKeyboard() {

        AndroidDriver androidDriver = (AndroidDriver) driver;
        try {
            androidDriver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Keyboard is already hidden or not present");
        }
    }


    /*
     * ============================================================
     * TASK 2 - CHECK KEYBOARD VISIBILITY
     * ============================================================
     * Checks whether the soft keyboard is currently displayed.
     */
    public boolean isKeyboardVisible() {

        AndroidDriver androidDriver = (AndroidDriver) driver;
        try {
            return androidDriver.isKeyboardShown();
        } catch (Exception e) {
            return false;
        }
    }


    /*
     * ============================================================
     * TASK 3 - PRESS BACK
     * ============================================================
     * Android-specific key event.
     * BACK may close the keyboard, dialog, or current screen.
     */
    public void pressBack() {

        ((AndroidDriver) driver)
                .pressKey(
                        new KeyEvent(AndroidKey.BACK)
                );
    }


    /*
     * ============================================================
     * TASK 4 - PRESS ENTER
     * ============================================================
     * Commonly used for search fields and form submission.
     */
    public void pressEnter() {

        ((AndroidDriver) driver)
                .pressKey(
                        new KeyEvent(AndroidKey.ENTER)
                );
    }


    /*
     * ============================================================
     * TASK 5 - PRESS HOME
     * ============================================================
     * Sends the application to the background and opens
     * the device home screen.
     */
    public void pressHome() {

        ((AndroidDriver) driver)
                .pressKey(
                        new KeyEvent(AndroidKey.HOME)
                );
    }


    /*
     * ============================================================
     * TASK 6 - PRESS DELETE
     * ============================================================
     * Deletes the character before the current cursor position.
     */
    public void pressDelete() {

        ((AndroidDriver) driver)
                .pressKey(
                        new KeyEvent(AndroidKey.DEL)
                );
    }


    /*
     * ============================================================
     * TASK 7 - PRESS TAB
     * ============================================================
     * Moves focus to the next field when supported by the app.
     */
    public void pressTab() {

        ((AndroidDriver) driver)
                .pressKey(
                        new KeyEvent(AndroidKey.TAB)
                );
    }


    /*
     * ============================================================
     * TASK 8 - PRESS SEARCH
     * ============================================================
     * Triggers the device search functionality where supported.
     */
    public void pressSearch() {

        ((AndroidDriver) driver)
                .pressKey(
                        new KeyEvent(AndroidKey.SEARCH)
                );
    }


    /*
     * ============================================================
     * TASK 9 - VOLUME CONTROLS
     * ============================================================
     * Android-specific volume key events.
     * Useful for media and volume-related test scenarios.
     */
    public void volumeControls() {

        AndroidDriver androidDriver =
                (AndroidDriver) driver;

        androidDriver.pressKey(
                new KeyEvent(AndroidKey.VOLUME_UP)
        );

        androidDriver.pressKey(
                new KeyEvent(AndroidKey.VOLUME_DOWN)
        );
    }


    /*
     * ============================================================
     * TASK 10 - REAL LOGIN SCENARIO
     * ============================================================
     * If the keyboard covers the Login button:
     *
     * 1. Enter username
     * 2. Enter password
     * 3. Hide keyboard
     * 4. Wait for Login button
     * 5. Click Login
     */
    public void loginScenario(
            By username,
            By password,
            By loginButton) {

        type(username, "demo");

        type(password, "pass123");

        hideKeyboard();

        waitAndClick(loginButton);
    }


    /*
     * ============================================================
     * TYPE UTILITY
     * ============================================================
     * Clears the existing value and enters the supplied text.
     */
    public void type(By locator, String value) {

        driver.findElement(locator)
                .clear();

        driver.findElement(locator)
                .sendKeys(value);
    }

    public void pressNumbers() {

        AndroidDriver androidDriver =
                (AndroidDriver) driver;

        androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_2));
        androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_3));
        androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_4));
        androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_5));
    }

    public void pressNumber(int number) {
        AndroidDriver androidDriver =
                (AndroidDriver) driver;

        AndroidKey key = switch (number) {
            case 0 -> AndroidKey.DIGIT_0;
            case 1 -> AndroidKey.DIGIT_1;
            case 2 -> AndroidKey.DIGIT_2;
            case 3 -> AndroidKey.DIGIT_3;
            case 4 -> AndroidKey.DIGIT_4;
            case 5 -> AndroidKey.DIGIT_5;
            case 6 -> AndroidKey.DIGIT_6;
            case 7 -> AndroidKey.DIGIT_7;
            case 8 -> AndroidKey.DIGIT_8;
            case 9 -> AndroidKey.DIGIT_9;
            default -> throw new IllegalArgumentException("Invalid digit");
        };

        androidDriver.pressKey(new KeyEvent(key));
    }


    /*
     * ============================================================
     * CLICK UTILITY
     * ============================================================
     * Placeholder for explicit-wait based clicking.
     * In a real framework, use WebDriverWait and
     * ExpectedConditions.elementToBeClickable().
     */
    public void waitAndClick(By locator) {

        driver.findElement(locator)
                .click();
    }

}
