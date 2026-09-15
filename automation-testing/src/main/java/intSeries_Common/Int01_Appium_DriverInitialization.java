package intSeries_Common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class Int01_Appium_DriverInitialization {

    public AppiumDriver getDriver(String platform) throws MalformedURLException {

        if (platform.equalsIgnoreCase("android")) {

            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("Android Emulator");
            options.setAppPackage("com.android.settings");
            options.setAppActivity(".Settings");
            return new AndroidDriver(
                    new URL("http://127.0.0.1:4723"),
                    options
            );

        } else if (platform.equalsIgnoreCase("ios")) {

            XCUITestOptions options = new XCUITestOptions();
            options.setDeviceName("iPhone Simulator");
            options.setBundleId("com.apple.Preferences");
            return new IOSDriver(
                    new URL("http://127.0.0.1:4723"),
                    options
            );

        } else {
            throw new IllegalArgumentException(
                    "Unsupported platform: " + platform
            );
        }
    }
}