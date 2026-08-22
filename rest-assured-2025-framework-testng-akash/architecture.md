Architecture: rest-assured-2025-framework-testng-akash

Folder structure (top-level items):
- .github
- src
- target
- pom.xml and TestNG config files (suite.xml, crud-tests.xml)

Typical layers and flow:
- TestNG suites trigger test classes in src/test (suite.xml/crud-tests.xml).
- Base classes and TestNG listeners initialize configuration and reporting.
- API clients or page/service classes provide reusable actions.
- Utilities and data providers feed tests with test data.
- CI/CD: Jenkinsfile and Dockerfile are used to run tests in pipeline.

Call/inheritance mapping:
- TestNG tests -> BaseTest -> Utils/Listeners -> Reporting

Notes:
- Look into src for packages named tests, listeners, utils and pages/services.