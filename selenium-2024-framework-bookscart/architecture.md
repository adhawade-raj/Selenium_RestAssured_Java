Architecture: selenium-2024-framework-bookscart

Folder structure (top-level items):
- .mvn
- .settings
- src
- test-output
- pom.xml

Tech stack and versions (from pom.xml):
- Java: 17
- Selenium: selenium-java 4.12.0
- TestNG: 6.14.3
- JavaFaker: 1.0.2

UI test flow and wiring (concrete):
1. Test setup: BaseTest sets up WebDriver via WebDriverManager or a configured driver binary; browser capabilities configured in setup hooks.
2. Page objects: Implement UI interactions; common methods are provided by BasePage (waits, actions, element utilities).
3. Test execution: Tests call PageObjects to perform flows; data/providers feed inputs from resources.
4. Reporting: TestNG + listeners write results to test-output; additional report libs can be integrated.

Where to inspect:
- src/test/java for tests, src/main/java or src/test/java for page objects, and any config/property files for environment/base URLs.