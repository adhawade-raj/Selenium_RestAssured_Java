# Running Tests from Command Line - Maven vs Gradle Comparison

## Table of Contents
1. [Test Execution (Selenium & Appium)](#1-test-execution-selenium--appium)
2. [Cucumber Testing](#2-cucumber-testing)
3. [Advanced Scenarios](#3-advanced-scenarios)
4. [Appium Server & Drivers](#4-appium-server--drivers)
5. [Parallel Execution](#5-parallel-execution)
6. [Quick Reference](#6-quick-reference)

---

## 1. Test Execution (Selenium & Appium)

### Basic Test Execution (Both Selenium & Appium)

| Requirement | Maven Command | Gradle Command |
|-------------|---------------|-----------------|
| Run all tests | `mvn test` | `gradlew test` |
| Clean + run all tests | `mvn clean test` | `gradlew clean test` |
| Run specific test class | `mvn -Dtest=LoginTest test` | `gradlew test --tests LoginTest` |
| Run specific test method | `mvn -Dtest=LoginTest#validLogin test` | `gradlew test --tests "LoginTest.validLogin"` |
| Run multiple test classes | `mvn -Dtest=LoginTest,SearchTest test` | `gradlew test --tests Login* --tests Search*` |
| Skip tests during build | `mvn clean package -DskipTests` | `gradlew build -x test` |
| Skip test compilation | `mvn clean package -Dmaven.test.skip=true` | N/A |

### Browser Parameters (Selenium)

| Requirement | Maven Command | Gradle Command |
|-------------|---------------|-----------------|
| Run with Chrome browser | `mvn test -Dbrowser=chrome` | `gradlew test -Dbrowser=chrome` |
| Run with Firefox browser | `mvn test -Dbrowser=firefox` | `gradlew test -Dbrowser=firefox` |
| Run with headless mode | `mvn test -Dheadless=true` | `gradlew test -Dheadless=true` |
| Chrome + headless | `mvn test -Dbrowser=chrome -Dheadless=true` | `gradlew test -Dbrowser=chrome -Dheadless=true` |

**Java Code for Selenium (Browser Parameters):**
```java
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserSetup {
    public static WebDriver setupBrowser() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        
        if ("chrome".equals(browser)) {
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless");
            }
            return new ChromeDriver(options);
        } else if ("firefox".equals(browser)) {
            FirefoxOptions options = new FirefoxOptions();
            if (headless) {
                options.addArguments("--headless");
            }
            return new FirefoxDriver(options);
        }
        return null;
    }
}
```

### Mobile Platform Parameters (Appium)

| Requirement | Maven Command | Gradle Command |
|-------------|---------------|-----------------|
| Run all Appium tests | `mvn clean test` | `gradlew clean test` |
| Run Android tests only | `mvn clean test -Dplatform=android` | `gradlew clean test -Dplatform=android` |
| Run iOS tests only | `mvn clean test -Dplatform=ios` | `gradlew clean test -Dplatform=ios` |
| Run specific Android test class | `mvn -Dtest=AndroidTest -Dplatform=android test` | `gradlew test --tests AndroidTest -Dplatform=android` |
| Run specific iOS test class | `mvn -Dtest=IOSTest -Dplatform=ios test` | `gradlew test --tests IOSTest -Dplatform=ios` |
| Android + smoke suite | `mvn test -Dplatform=android -Dsuite=smoke` | `gradlew test -Dplatform=android -Dsuite=smoke` |
| iOS + regression suite | `mvn test -Dplatform=ios -Dsuite=regression` | `gradlew test -Dplatform=ios -Dsuite=regression` |

**Java Code for Appium (Platform Parameters):**
```java
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumSetup {
    public static AppiumDriver<?> setupDriver(String platform) throws Exception {
        String platformType = System.getProperty("platform", "android").toLowerCase();
        String suite = System.getProperty("suite", "smoke");
        
        if ("android".equals(platformType)) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("automationName", "UiAutomator2");
            caps.setCapability("app", "/path/to/app.apk");
            return new AndroidDriver(caps);
        } else if ("ios".equals(platformType)) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "iOS");
            caps.setCapability("automationName", "XCUITest");
            caps.setCapability("app", "/path/to/app.ipa");
            return new IOSDriver(caps);
        }
        return null;
    }
}
```

---

## 2. Cucumber Testing

### BDD Framework Execution (Selenium & Appium)

| Requirement | Maven Command | Gradle Command |
|-------------|---------------|-----------------|
| Run all Cucumber tests | `mvn clean test` | `gradlew clean test` |
| Run specific tag | `mvn test -Dcucumber.filter.tags="@smoke"` | `gradlew test -Dcucumber.filter.tags="@smoke"` |
| Run multiple tags (AND) | `mvn test -Dcucumber.filter.tags="@smoke and @login"` | `gradlew test -Dcucumber.filter.tags="@smoke and @login"` |
| Run multiple tags (OR) | `mvn test -Dcucumber.filter.tags="@smoke or @regression"` | `gradlew test -Dcucumber.filter.tags="@smoke or @regression"` |
| Exclude tags | `mvn test -Dcucumber.filter.tags="not @wip"` | `gradlew test -Dcucumber.filter.tags="not @wip"` |
| Smoke tests | `mvn test -Dcucumber.filter.tags="@smoke"` | `gradlew test -Dcucumber.filter.tags="@smoke"` |
| Regression tests | `mvn test -Dcucumber.filter.tags="@regression"` | `gradlew test -Dcucumber.filter.tags="@regression"` |

---

## 3. Advanced Scenarios

### Selenium with Cucumber

| Scenario | Maven Command | Gradle Command |
|----------|---------------|-----------------|
| Chrome regression tests | `mvn clean test -Dbrowser=chrome -Dcucumber.filter.tags="@regression"` | `gradlew clean test -Dbrowser=chrome -Dcucumber.filter.tags="@regression"` |
| Chrome headless smoke tests | `mvn clean test -Dbrowser=chrome -Dheadless=true -Dcucumber.filter.tags="@smoke"` | `gradlew clean test -Dbrowser=chrome -Dheadless=true -Dcucumber.filter.tags="@smoke"` |
| Firefox + regression | `mvn test -Dbrowser=firefox -Dcucumber.filter.tags="@regression"` | `gradlew test -Dbrowser=firefox -Dcucumber.filter.tags="@regression"` |

### Appium with Cucumber

| Scenario | Maven Command | Gradle Command |
|----------|---------------|-----------------|
| Android smoke tests | `mvn clean test -Dplatform=android -Dcucumber.filter.tags="@smoke"` | `gradlew clean test -Dplatform=android -Dcucumber.filter.tags="@smoke"` |
| iOS smoke tests | `mvn clean test -Dplatform=ios -Dcucumber.filter.tags="@smoke"` | `gradlew clean test -Dplatform=ios -Dcucumber.filter.tags="@smoke"` |
| Android regression tests | `mvn clean test -Dplatform=android -Dcucumber.filter.tags="@regression"` | `gradlew clean test -Dplatform=android -Dcucumber.filter.tags="@regression"` |
| iOS regression tests | `mvn clean test -Dplatform=ios -Dcucumber.filter.tags="@regression"` | `gradlew clean test -Dplatform=ios -Dcucumber.filter.tags="@regression"` |
| Android + smoke + login | `mvn clean test -Dplatform=android -Dcucumber.filter.tags="@smoke and @login"` | `gradlew clean test -Dplatform=android -Dcucumber.filter.tags="@smoke and @login"` |
| iOS + regression + payment | `mvn clean test -Dplatform=ios -Dcucumber.filter.tags="@regression and @payment"` | `gradlew clean test -Dplatform=ios -Dcucumber.filter.tags="@regression and @payment"` |

### Complex Combinations

| Scenario | Maven Command | Gradle Command |
|----------|---------------|-----------------|
| Chrome + specific browser + regression | `mvn test -Dbrowser=chrome -Dcucumber.filter.tags="@regression"` | `gradlew test -Dbrowser=chrome -Dcucumber.filter.tags="@regression"` |
| Android + specific platform + smoke | `mvn test -Dplatform=android -Dcucumber.filter.tags="@smoke"` | `gradlew test -Dplatform=android -Dcucumber.filter.tags="@smoke"` |
| Browser + Platform + Tag mix | `mvn test -Dbrowser=chrome -Dplatform=android -Dcucumber.filter.tags="@smoke"` | `gradlew test -Dbrowser=chrome -Dplatform=android -Dcucumber.filter.tags="@smoke"` |

---

## 4. Appium Server & Drivers

### Setup and Configuration

| Requirement | Command | Description |
|-------------|---------|-------------|
| Start Appium server (default) | `appium` | Starts on `http://127.0.0.1:4723` |
| Start Appium on port 4723 | `appium -p 4723` | Single instance setup |
| Start Appium on port 4725 | `appium -p 4725` | Parallel testing setup |
| Install Android driver | `appium driver install uiautomator2` | Required for Android testing |
| Install iOS driver | `appium driver install xcuitest` | Required for iOS testing |
| List installed drivers | `appium driver list` | Verify driver installation |

### Parallel Testing Setup
```
Device 1 (Android) → Appium Server on port 4723
Device 2 (Android) → Appium Server on port 4725
Device 3 (iOS)     → Appium Server on port 4727
```

---

## 5. Parallel Execution

### Concurrent Test Execution (Selenium & Appium)

| Requirement | Maven Command | Gradle Command |
|-------------|---------------|-----------------|
| Run tests in parallel | `mvn test -DthreadCount=5` | `gradlew test --max-workers=5` |
| Run with 3 threads | `mvn test -DthreadCount=3` | `gradlew test --max-workers=3` |
| Run with 10 threads | `mvn test -DthreadCount=10` | `gradlew test --max-workers=10` |
| Default (sequential) | `mvn test` | `gradlew test` |

**Java Code to Read Thread Count (Maven):**
```java
int threadCount = Integer.parseInt(System.getProperty("threadCount", "1"));
```

**Note:** Configure parallelism in `testng.xml` or Maven Surefire configuration for Maven.

---

## 6. Quick Reference

### Maven Quick Commands
```bash
# Selenium - Browser Tests
mvn clean test
mvn test -Dbrowser=chrome
mvn test -Dbrowser=chrome -Dheadless=true
mvn test -Dbrowser=firefox

# Appium - Mobile Tests
mvn clean test -Dplatform=android
mvn clean test -Dplatform=ios
mvn test -Dplatform=android -Dsuite=smoke

# Cucumber Tags (Both)
mvn test -Dcucumber.filter.tags="@smoke"
mvn test -Dcucumber.filter.tags="@regression"

# Combined & Parallel
mvn clean test -Dplatform=android -Dcucumber.filter.tags="@smoke"
mvn test -DthreadCount=5
```

### Gradle Quick Commands
```bash
# Selenium - Browser Tests
gradlew clean test
gradlew test -Dbrowser=chrome
gradlew test -Dbrowser=chrome -Dheadless=true
gradlew test -Dbrowser=firefox

# Appium - Mobile Tests
gradlew clean test -Dplatform=android
gradlew clean test -Dplatform=ios
gradlew test -Dplatform=ios -Dsuite=regression

# Cucumber Tags (Both)
gradlew test -Dcucumber.filter.tags="@smoke"
gradlew test -Dcucumber.filter.tags="@regression"

# Combined & Parallel
gradlew clean test -Dplatform=ios -Dcucumber.filter.tags="@smoke"
gradlew test --max-workers=5
```

### Appium Quick Commands
```bash
appium -p 4723
appium driver install uiautomator2
appium driver install xcuitest
appium driver list
```

---

## Maven vs Gradle Comparison

| Aspect | Maven | Gradle |
|--------|-------|--------|
| **Config File** | `pom.xml` | `build.gradle` |
| **Main Command** | `mvn` | `gradlew` |
| **Test Execution** | `mvn test` | `gradlew test` |
| **Skip Tests** | `-DskipTests` | `-x test` |
| **Specific Test** | `-Dtest=ClassName` | `--tests ClassName` |
| **Browser (Selenium)** | `-Dbrowser=chrome` | `-Dbrowser=chrome` |
| **Platform (Appium)** | `-Dplatform=android` | `-Dplatform=android` |
| **Parallel Threads** | `-DthreadCount=N` | `--max-workers=N` |
| **Pass Properties** | `-Dproperty=value` | `-Dproperty=value` |
| **Windows** | `mvn.bat` | `gradlew.bat` |
| **Unix/Mac** | `mvn` | `./gradlew` |

---

## Best Practices

✅ **Always clean before major runs**
```bash
Maven:  mvn clean test
Gradle: gradlew clean test
```

✅ **Use parallel execution for speed**
```bash
Maven:  mvn test -DthreadCount=5
Gradle: gradlew test --max-workers=5
```

✅ **Start Appium before mobile tests**
```bash
appium -p 4723  # In separate terminal
```

✅ **Organize tests with Cucumber tags**
```bash
@smoke       - Quick sanity tests
@regression  - Full suite
@wip         - Skip (work in progress)
@login       - Login-related
@payment     - Payment-related
```

✅ **Combine parameters for complete scenarios**
```bash
Browser/Platform + Tag + Parallel = Full test execution

Example: Selenium Chrome + Regression + 5 threads
mvn clean test -Dbrowser=chrome -Dcucumber.filter.tags="@regression" -DthreadCount=5

Example: Appium Android + Smoke + 3 threads
gradlew clean test -Dplatform=android -Dcucumber.filter.tags="@smoke" --max-workers=3
```
