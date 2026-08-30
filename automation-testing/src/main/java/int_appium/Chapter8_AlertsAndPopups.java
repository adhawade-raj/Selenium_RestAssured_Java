package int_appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Chapter8_AlertsAndPopups {

    private AppiumDriver driver;


    /*
     * ============================================================
     * TASK 1 - ACCEPT AN ALERT
     * ============================================================
     * Accepts confirmation dialogs or supported permission prompts.
     */
    public void acceptAlert() {

        Alert alert = driver.switchTo().alert();

        alert.accept();
    }


    /*
     * ============================================================
     * TASK 2 - DISMISS AN ALERT
     * ============================================================
     * Rejects or closes an alert when supported by the application.
     */
    public void dismissAlert() {

        Alert alert = driver.switchTo().alert();

        alert.dismiss();
    }


    /*
     * ============================================================
     * TASK 3 - READ ALERT TEXT
     * ============================================================
     * Retrieves the alert message for validation or debugging.
     */
    public String getAlertText() {

        Alert alert = driver.switchTo().alert();

        String message = alert.getText();

        System.out.println(message);

        return message;
    }


    /*
     * ============================================================
     * TASK 4 - ENTER TEXT INTO ALERT
     * ============================================================
     * Sends text to an alert that contains an editable input field.
     */
    public void enterTextInAlert() {

        Alert alert = driver.switchTo().alert();

        alert.sendKeys("Sample Input");

        alert.accept();
    }


    /*
     * ============================================================
     * TASK 5 - WAIT FOR AN ALERT
     * ============================================================
     * Explicitly waits until the alert becomes available.
     */
    public void waitAndAcceptAlert() {

        Alert alert =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                ).until(
                        ExpectedConditions.alertIsPresent()
                );

        alert.accept();
    }


    /*
     * ============================================================
     * TASK 6 - HANDLE UNEXPECTED ALERT
     * ============================================================
     * Safely handles optional alerts without failing the test
     * when no alert is displayed.
     */
    public void handleUnexpectedAlert() {

        try {

            driver.switchTo()
                    .alert()
                    .accept();

        } catch (NoAlertPresentException e) {

            System.out.println("No alert displayed.");
        }
    }


    /*
     * ============================================================
     * TASK 7 - ANDROID PERMISSION DIALOG
     * ============================================================
     * Some Android permission dialogs are exposed as native
     * UI elements and can be located using AppiumBy.
     */
    public void allowAndroidPermission() {

        driver.findElement(
                AppiumBy.id(
                        "com.android.permissioncontroller:id/" +
                                "permission_allow_button"
                )
        ).click();
    }


    /*
     * ============================================================
     * TASK 8 - iOS PERMISSION DIALOG
     * ============================================================
     * Supported iOS permission dialogs can be handled through
     * the Alert interface.
     */
    public void acceptIOSPermission() {

        driver.switchTo()
                .alert()
                .accept();
    }


    /*
     * ============================================================
     * TASK 9 - REUSABLE ALERT UTILITY
     * ============================================================
     * Centralizes alert handling so tests do not duplicate
     * explicit wait and accept logic.
     */
    public void acceptAlertWithWait() {

        new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        )
                .until(
                        ExpectedConditions.alertIsPresent()
                )
                .accept();
    }


}
