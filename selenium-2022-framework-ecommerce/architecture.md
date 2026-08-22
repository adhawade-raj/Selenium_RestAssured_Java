Architecture: selenium-2022-framework-ecommerce

Folder structure (top-level items):
- .settings
- src
- target
- test-output
- pom.xml

Tech stack and versions (from pom.xml):
- Java: 1.8
- Selenium: selenium-java 4.1.2
- WebDriverManager: 3.7.1
- TestNG: 6.14.3
- ExtentReports: 5.0.8

UI test flow and wiring:
1. Setup: BaseTest initializes WebDriver using WebDriverManager and common browser options; test data/config loaded from properties.
2. Page Objects: Encapsulate element locators and actions; typically extend a BasePage that provides common helpers (click, wait, sendKeys).
3. Tests: Test classes instantiate page objects (or get them from a factory) and compose user journeys by calling page actions.
4. Utilities: Wait helpers, retry logic, test data providers and assertions are shared across pages and tests.
5. Reporting: Results are captured by TestNG listeners and written to test-output; ExtentReports provides HTML dashboards.

Where to find wiring:
- Search src for classes named BaseTest, BasePage, Page* (page objects), and any utilities under utils or helpers directories.