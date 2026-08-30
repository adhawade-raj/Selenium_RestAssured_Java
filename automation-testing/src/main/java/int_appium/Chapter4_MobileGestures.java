package int_appium;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import java.util.Map;

public class Chapter4_MobileGestures {

    private AppiumDriver driver;


    /*
     * ============================================================
     * TASK 1 - SWIPE GESTURE
     * ============================================================
     * Performs a native swipe inside the specified bounding box.
     */
    public void swipeGesture() {

        driver.executeScript(
                "mobile: swipeGesture",
                Map.of(
                        "left", 100,
                        "top", 100,
                        "width", 800,
                        "height", 1200,
                        "direction", "up",
                        "percent", 0.75
                )
        );
    }


    /*
     * ============================================================
     * TASK 2 - SCROLL GESTURE
     * ============================================================
     * Scrolls in the specified direction.
     * Returns true if additional scrolling is possible.
     */
    public boolean scrollGesture() {

        return (Boolean) driver.executeScript(
                "mobile: scrollGesture",
                Map.of(
                        "left", 100,
                        "top", 100,
                        "width", 800,
                        "height", 1200,
                        "direction", "down",
                        "percent", 0.8
                )
        );
    }


    /*
     * ============================================================
     * TASK 3 - CLICK GESTURE
     * ============================================================
     * Performs a native click on the specified element.
     * Useful when normal click() is unreliable.
     */
    public void clickGesture(WebElement element) {

        driver.executeScript(
                "mobile: clickGesture",
                Map.of(
                        "elementId",
                        ((RemoteWebElement) element).getId()
                )
        );
    }


    /*
     * ============================================================
     * TASK 4 - LONG CLICK GESTURE
     * ============================================================
     * Performs a long press on an element.
     * Useful for context menus and long-press actions.
     */
    public void longClickGesture(WebElement element) {

        driver.executeScript(
                "mobile: longClickGesture",
                Map.of(
                        "elementId",
                        ((RemoteWebElement) element).getId(),
                        "duration",
                        2000
                )
        );
    }


    /*
     * ============================================================
     * TASK 5 - DOUBLE CLICK GESTURE
     * ============================================================
     * Performs a native double tap on an element.
     */
    public void doubleClickGesture(WebElement element) {

        driver.executeScript(
                "mobile: doubleClickGesture",
                Map.of(
                        "elementId",
                        ((RemoteWebElement) element).getId()
                )
        );
    }


    /*
     * ============================================================
     * TASK 6 - DRAG GESTURE
     * ============================================================
     * Drags an element to the specified target coordinates.
     */
    public void dragGesture(
            WebElement element,
            int endX,
            int endY) {

        driver.executeScript(
                "mobile: dragGesture",
                Map.of(
                        "elementId",
                        ((RemoteWebElement) element).getId(),
                        "endX",
                        endX,
                        "endY",
                        endY
                )
        );
    }


    /*
     * ============================================================
     * TASK 7 - FLING GESTURE
     * ============================================================
     * Performs a fast swipe/scroll.
     * Useful for quickly moving through long lists.
     */
    public void flingGesture() {

        driver.executeScript(
                "mobile: flingGesture",
                Map.of(
                        "left", 100,
                        "top", 100,
                        "width", 800,
                        "height", 1200,
                        "direction", "up",
                        "speed", 3000
                )
        );
    }


    /*
     * ============================================================
     * TASK 8 - PINCH OPEN
     * ============================================================
     * Pinches outward to zoom in.
     * Commonly used with maps and images.
     */
    public void pinchOpenGesture() {

        driver.executeScript(
                "mobile: pinchOpenGesture",
                Map.of(
                        "left", 100,
                        "top", 100,
                        "width", 800,
                        "height", 1200,
                        "percent", 0.75
                )
        );
    }


    /*
     * ============================================================
     * TASK 9 - PINCH CLOSE
     * ============================================================
     * Pinches inward to zoom out.
     * Commonly used with maps and images.
     */
    public void pinchCloseGesture() {

        driver.executeScript(
                "mobile: pinchCloseGesture",
                Map.of(
                        "left", 100,
                        "top", 100,
                        "width", 800,
                        "height", 1200,
                        "percent", 0.75
                )
        );
    }


    /*
     * ============================================================
     * TASK 10 - DEEP LINK
     * ============================================================
     * Opens a specific application screen directly using
     * a deep-link URL.
     */
    public void openDeepLink() {

        driver.executeScript(
                "mobile: deepLink",
                Map.of(
                        "url",
                        "myapp://dashboard",

                        "package",
                        "com.demo.app"
                )
        );
    }

}
