Architecture: selenium-2022-framework-ecommerce

Folder structure (top-level items):
- .settings
- src
- target
- test-output
- pom.xml

Typical layers and flow:
- Tests (src/test/java): test classes that assert user journeys.
- Page objects (src/main/java or src): encapsulate UI actions.
- Base classes: WebDriver setup, common configuration and hooks.
- Utilities: helpers for waits, data, and common utilities used by pages and tests.
- Reporting: test-output or report folders (Allure/Extent) gather results.

Call/inheritance mapping:
- Tests -> BaseTest -> PageObjects -> Utils
- PageObjects may inherit BasePage for common actions

Notes:
- The architecture file shows where to find pages, tests and utilities and how they typically connect.