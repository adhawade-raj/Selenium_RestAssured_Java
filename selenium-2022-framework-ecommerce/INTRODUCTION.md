Introduction to this framework

1. Purpose
- A Selenium-based UI automation framework for ecommerce flows that emphasizes page objects, reusability and stable tests.

2. Objectives
- Encapsulate UI interactions in PageObjects and centralize WebDriver setup and teardown for consistency.

3. Layers
- Test layer: TestNG tests implementing user journeys.
- Page layer: PageObjects encapsulating locators and actions.
- Core: BasePage/BaseTest, utilities for waits and data.
- Reporting: ExtentReports / TestNG listeners.

4. Configuration
- Centralized properties determine baseURL, browser and timeouts; BaseTest applies WebDriver configuration.

5. Page object pattern
- BasePage provides common actions; concrete page classes model pages with methods returning next page objects.

6. Data-driven testing
- TestNG @DataProvider or external data drives test scenarios and input values.

7. Utilities & stability
- Retry logic, fluent waits, and helper methods improve stability; WebDriverManager manages drivers.

8. Reporting & CI
- Test artifacts and logs attached to ExtentReports; CI runs suites and publishes results.

9. Extension points
- Add new page objects, utilities or listeners to extend functionality without changing core setup.

10. Best practices
- Keep page methods focused and return page objects; avoid assertions in page methods; centralize config and test data.