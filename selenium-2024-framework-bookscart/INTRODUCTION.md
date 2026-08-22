Introduction to this framework

1. Purpose
- A Selenium automation framework tailored to Bookscart that uses PageObjects, reusable base classes and TestNG for structured UI tests.

2. Goals
- Maintainable page abstractions, centralized driver lifecycle, and clear reporting to reproduce failures quickly.

3. Layers
- Tests (TestNG) orchestrate flows.
- PageObjects encapsulate UI operations and extend BasePage.
- Core utilities provide waits, data handling and logging.
- Reporting captures results in test-output or integrated report libraries.

4. Configuration
- Properties define environment, baseURL and browser; BaseTest initializes WebDriver accordingly.

5. Page object pattern & flow
- Tests call page methods which return page objects or states; BasePage provides common helpers for interaction.

6. Data-driven testing
- Use TestNG data providers or resource files to drive test variations.

7. Stability & utilities
- Fluent waits, explicit waits and retry helpers reduce flakiness; use WebDriverManager for driver binaries.

8. Reporting & CI
- TestNG listeners write results to test-output; CI runs suites and archives reports.

9. Extension
- Add new pages and utilities; keep selectors and locators centralized for ease of maintenance.

10. Best practices
- Avoid assertions inside page methods, keep tests readable, and version test data and locators.