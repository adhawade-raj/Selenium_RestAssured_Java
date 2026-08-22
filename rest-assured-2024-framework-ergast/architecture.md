Architecture: rest-assured-2024-framework-ergast

Folder structure (top-level items):
- .settings
- allure-results
- extentReport
- src
- target
- test-output
- pom.xml

Typical layers and flow:
- Test layer (src/test/java): Test classes that define scenarios and assertions.
- API/Client layer (src/main/java or src): API clients and request builders wrapping RestAssured calls.
- Model layer: POJOs used to deserialize responses.
- Core/utility layer: Utils, Config and Base classes used across tests and clients.
- Reporting layer: extentReport and allure-results receive test results from test execution.

Call/inheritance mapping (common patterns):
- Tests -> BaseTest -> Test Utilities
- Tests -> API clients -> Models
- Page/Service classes (if present) extend base helpers and use utilities

Notes:
- Look for utilities under src or a utils package; those are invoked by both clients and tests.