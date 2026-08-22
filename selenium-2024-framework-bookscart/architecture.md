Architecture: selenium-2024-framework-bookscart

Folder structure (top-level items):
- .mvn
- .settings
- src
- test-output
- pom.xml

Typical layers and flow:
- Tests -> Test runners/NG suites -> BaseTest
- PageObjects -> BasePage -> Utilities
- Test data and fixtures feed tests from resources or data folders
- Reporting collects results in test-output or allure/extent (if configured)

Call/inheritance mapping:
- Tests call PageObjects which use shared utilities; Base classes manage WebDriver lifecycle.

Notes:
- Inspect src for packages named pages, tests, base, utils to map exact call sites.