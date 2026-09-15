# Automation Testing Commands Guide

## Table of Contents
- [Selenium API Commands](#selenium-api-commands)
- [Postman Commands](#postman-commands)
- [Mobile Testing Commands](#mobile-testing-commands)
- [Gradle Build Commands](#gradle-build-commands)

---

## Selenium API Commands

| Command | Purpose | Usage |
|---------|---------|-------|
| `driver.get(url)` | Navigate to a URL | `driver.get("https://example.com");` |
| `driver.findElement(By.id("id"))` | Find element by ID | `WebElement elem = driver.findElement(By.id("elementId"));` |
| `driver.findElements(By.className("class"))` | Find multiple elements | `List<WebElement> elements = driver.findElements(By.className("className"));` |
| `element.click()` | Click on an element | `element.click();` |
| `element.sendKeys(text)` | Send text to an element | `element.sendKeys("Test Input");` |
| `element.clear()` | Clear input field | `element.clear();` |
| `element.submit()` | Submit a form | `element.submit();` |
| `driver.switchTo().frame(frame)` | Switch to iframe | `driver.switchTo().frame("frameName");` |
| `driver.switchTo().alert()` | Switch to alert | `Alert alert = driver.switchTo().alert();` |
| `driver.wait()` | Implicit wait | `driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);` |
| `WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10))` | Explicit wait | `wait.until(ExpectedConditions.visibilityOf(element));` |
| `driver.getTitle()` | Get page title | `String title = driver.getTitle();` |
| `driver.getCurrentUrl()` | Get current URL | `String url = driver.getCurrentUrl();` |
| `driver.quit()` | Close browser | `driver.quit();` |

---

## Postman Commands

| Command | Purpose | Usage |
|---------|---------|-------|
| **GET** | Retrieve data | `GET /api/users` |
| **POST** | Create new resource | `POST /api/users` with JSON body |
| **PUT** | Update entire resource | `PUT /api/users/{id}` with JSON body |
| **PATCH** | Partial update | `PATCH /api/users/{id}` with JSON body |
| **DELETE** | Delete resource | `DELETE /api/users/{id}` |
| `{{variable}}` | Use variables | `{{base_url}}/api/endpoint` |
| `pm.test()` | Write test assertions | `pm.test("Status is 200", function() { pm.response.to.have.status(200); });` |
| `pm.expect()` | Validate response | `pm.expect(jsonData.name).to.eql("John");` |
| `pm.environment.set()` | Set environment variable | `pm.environment.set("token", jsonData.token);` |
| `pm.environment.get()` | Get environment variable | `var token = pm.environment.get("token");` |
| `response.json()` | Parse JSON response | `var jsonData = pm.response.json();` |
| `response.text()` | Get response as text | `var responseText = pm.response.text();` |
| **Pre-request Script** | Execute before request | JavaScript code to setup request |
| **Tests Script** | Execute after response | JavaScript code to validate response |

---

## Mobile Testing Commands

| Command | Purpose | Usage |
|---------|---------|-------|
| **Android Driver** | Initialize Android driver | `new AndroidDriver(url, options)` |
| **iOS Driver** | Initialize iOS driver | `new IOSDriver(url, options)` |
| `UiAutomator2Options options` | Android capabilities | `options.setDeviceName("Android Emulator");` |
| `XCUITestOptions options` | iOS capabilities | `options.setDeviceName("iPhone Simulator");` |
| `options.setAppPackage()` | Set Android app package | `options.setAppPackage("com.example.app");` |
| `options.setAppActivity()` | Set Android app activity | `options.setAppActivity(".MainActivity");` |
| `options.setBundleId()` | Set iOS bundle ID | `options.setBundleId("com.example.app");` |
| `driver.findElement(By.id("id"))` | Find element by ID | `WebElement elem = driver.findElement(By.id("elementId"));` |
| `driver.findElement(By.accessibility("text"))` | Find by accessibility ID | `WebElement elem = driver.findElement(By.AccessibilityId("contentDescription"));` |
| `driver.findElement(By.xpath("//xpath"))` | Find by XPath | `WebElement elem = driver.findElement(By.xpath("//android.widget.Button[@text='Click']"));` |
| `element.tap()` | Tap on element | `element.click();` |
| `driver.swipe()` | Swipe on screen | `((AppiumDriver) driver).executeScript("mobile: swipeGesture", args);` |
| `driver.navigate().back()` | Back button | `driver.navigate().back();` |
| `driver.startActivity()` | Start app activity | `((AndroidDriver) driver).startActivity(activity);` |
| `driver.closeApp()` | Close app | `driver.closeApp();` |
| `driver.resetApp()` | Reset app | `driver.resetApp();` |

---

## Gradle Build Commands

| Command | Purpose | Usage |
|---------|---------|-------|
| **Build Project** | Compile and build | `gradle build` |
| **Build without tests** | Build excluding tests | `gradle build -x test` |
| **Run tests** | Execute all tests | `gradle test` |
| **Run specific test** | Run single test class | `gradle test --tests TestClassName` |
| **Run test pattern** | Run tests matching pattern | `gradle test --tests *Selenium*` |
| **Clean build** | Clean and build | `gradle clean build` |
| **Compile Java** | Compile source code | `gradle compileJava` |
| **Compile tests** | Compile test code | `gradle compileTestJava` |
| **Check dependencies** | View project dependencies | `gradle dependencies` |
| **Install dependencies** | Download dependencies | `gradle build --refresh-dependencies` |
| **Generate JavaDoc** | Create documentation | `gradle javadoc` |
| **Assemble** | Create JAR/WAR | `gradle assemble` |
| **Run application** | Execute main class | `gradle run` |
| **Properties** | Show Gradle properties | `gradle properties` |
| **Tasks** | List available tasks | `gradle tasks` |
| **Help** | Show command help | `gradle help` |

---

## Running Tests with Gradle

### Run All Tests
```bash
gradle test
```

### Run Specific Test Class
```bash
gradle test --tests Int01_DriverInitialization
```

### Run Tests with Pattern
```bash
gradle test --tests *Selenium*
```

### Run Tests with Details
```bash
gradle test --info
```

### Run Tests and Generate Report
```bash
gradle test --continue
```

### Skip Tests During Build
```bash
gradle build -x test
```

### Run Specific Task
```bash
gradle :automation-testing:test
```

---

## Example Test Execution

### Selenium API Test
```java
// Navigate and find element
driver.get("https://example.com");
WebElement searchBox = driver.findElement(By.id("searchBox"));
searchBox.sendKeys("Test");
searchBox.submit();
```

### Mobile Test with Appium
```java
UiAutomator2Options options = new UiAutomator2Options();
options.setDeviceName("Android Emulator");
options.setAppPackage("com.android.settings");
AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
driver.quit();
```

### REST API Test with RestAssured
```java
given()
    .baseUri("https://jsonplaceholder.typicode.com")
    .when()
    .get("/posts/1")
    .then()
    .statusCode(200);
```

---

## Quick Reference

### Gradle Shortcuts
- `gradle build` - Full build
- `gradle test` - Run tests
- `gradle clean` - Clean build artifacts
- `gradle assemble` - Create package

### Browser Drivers Required
- ChromeDriver (Chrome)
- GeckoDriver (Firefox)
- SafariDriver (Safari)
- EdgeDriver (Edge)

### Mobile Setup Required
- Appium Server running on `http://127.0.0.1:4723`
- Android Emulator or physical device
- iOS Simulator or physical device
