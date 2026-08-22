Architecture: rest-assured-2024-framework-gorest

Folder structure (top-level items):
- .settings
- allure-results
- extentReport
- src
- target
- test-output
- pom.xml

Typical layers and flow:
- Test layer: test classes orchestrate API calls and assertions.
- Client layer: API clients and request builders encapsulate RestAssured usage.
- Model/DTO layer: POJOs for response/request mapping.
- Utilities: config, helpers and data providers used across layers.
- Reporting: extent/allure collect and publish test results.

Call/inheritance mapping:
- Tests -> BaseTest -> Utils
- API clients -> BaseClient -> HTTP helpers

Notes:
- The architecture.md shows where to find tests, shared utilities and reporting artifacts.