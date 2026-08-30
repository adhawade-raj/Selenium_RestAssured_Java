package int_appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;

public class Chapter5_ApplicationManagement {

    private AppiumDriver driver;


    /*
     * ============================================================
     * TASK 1 - ACTIVATE AN APPLICATION
     * ============================================================
     * Brings an installed application to the foreground.
     */
    public void activateApplication() {

        if (driver instanceof AndroidDriver) {
            ((AndroidDriver) driver).activateApp("com.demo.app");
        }
    }


    /*
     * ============================================================
     * TASK 2 - TERMINATE AN APPLICATION
     * ============================================================
     * Closes the application without ending the Appium session.
     */
    public void terminateApplication() {

        if (driver instanceof AndroidDriver) {
            ((AndroidDriver) driver).terminateApp("com.demo.app");
        }
    }


    /*
     * ============================================================
     * TASK 3 - INSTALL AN APPLICATION
     * ============================================================
     * Installs an application during test execution.
     * Commonly used in local and CI execution.
     */
    public void installApplication() {

        if (driver instanceof AndroidDriver) {
            ((AndroidDriver) driver).installApp("/apps/demo.apk");
        }
    }


    /*
     * ============================================================
     * TASK 4 - REMOVE AN APPLICATION
     * ============================================================
     * Uninstalls the application from the device.
     * Useful for fresh-install test scenarios.
     */
    public void removeApplication() {

        if (driver instanceof AndroidDriver) {
            ((AndroidDriver) driver).removeApp("com.demo.app");
        }
    }


    /*
     * ============================================================
     * TASK 5 - VERIFY APPLICATION INSTALLATION
     * ============================================================
     * Checks whether the application is already installed.
     */
    public boolean isApplicationInstalled() {

        if (driver instanceof AndroidDriver) {
            return ((AndroidDriver) driver).isAppInstalled("com.demo.app");
        }
        return false;
    }


    /*
     * ============================================================
     * TASK 6 - QUERY APPLICATION STATE
     * ============================================================
     * Returns the current state of the application.
     *
     * Possible states include:
     * - Running in foreground
     * - Running in background
     * - Not running
     * - Not installed
     */
    public void queryApplicationState() {

        if (driver instanceof AndroidDriver) {
            ApplicationState state =
                    ((AndroidDriver) driver).queryAppState("com.demo.app");

            System.out.println(state);
        }
    }


    /*
     * ============================================================
     * TASK 7 - RESTART AN APPLICATION
     * ============================================================
     * Terminates and activates the application again.
     * Preferred approach instead of deprecated restart APIs.
     */
    public void restartApplication() {

        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            androidDriver.terminateApp("com.demo.app");
            androidDriver.activateApp("com.demo.app");
        }
    }


    /*
     * ============================================================
     * TASK 8 - FRESH INSTALL WORKFLOW
     * ============================================================
     * Removes the application if already installed,
     * installs a fresh copy and launches it.
     *
     * Useful for:
     * - First-time user experience
     * - Onboarding validation
     * - Clean installation testing
     */
    public void freshInstallWorkflow() {

        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;

            if (androidDriver.isAppInstalled("com.demo.app")) {
                androidDriver.removeApp("com.demo.app");
            }

            androidDriver.installApp("/apps/demo.apk");
            androidDriver.activateApp("com.demo.app");
        }
    }

}
