Introduction to this framework

1. Purpose
- Provide a reusable, modular REST API automation framework built on RestAssured and TestNG to validate API behaviour reliably and repeatably.

2. Design goals
- Reuse: centralize common setup (BaseTest/BaseClient) and request/response specifications.
- Readability: tests express intent, not plumbing.
- Extensibility: add new clients, data sources, or reporters without changing core code.

3. Layers (conceptual)
- Test layer: test classes defining scenarios and assertions.
- Client/Service layer: RestClient/BaseClient building requests and invoking endpoints.
- Model layer: POJOs for request/response mapping (Jackson).
- Core utilities: config, logging, token managers, data loaders.
- Reporting: Allure/Extent hooks and listeners.

4. Configuration & environment
- Centralized properties/owner-based config supplies baseURI, credentials and environment flags.
- BaseTest or listeners read config and set RestAssured.baseURI and common RequestSpecification.

5. Request & Response reuse
- RequestSpecBuilder / RequestSpecification encapsulate headers, auth, common timeouts and logging.
- ResponseSpecBuilder / reusable assertions standardize validations and reduce duplication.

6. Data-driven testing
- External JSON/CSV provide test data; TestNG @DataProvider or Cucumber feed test methods.
- DataLoader utilities map data to POJOs used by clients.

7. Mapping & validation
- Responses are mapped to POJOs via Jackson; JSON schema validation (rest-assured json-schema-validator) verifies contracts.

8. Utilities & cross-cutting concerns
- Logging, retry/awaitility helpers, random data generators, and token refresh managers live in core utils and are used by clients and tests.

9. Reporting & artifacts
- Test listeners capture request/response and attach artifacts to Allure/Extent results stored in allure-results/extentReport.

10. CI & execution
- TestNG suites or Maven goals run tests in CI (Jenkins/Docker). Suite XML and Maven profiles control test groups.

11. Error handling & stability
- Retry/awaitility patterns applied for flaky endpoints; timeouts and clear assertions reduce false positives.

12. Extension points
- Add new API clients under client/service packages, add new data loaders, or add custom listeners for extra reporting.

13. How to trace a request flow
- Config -> BaseTest (sets baseURI) -> Test -> Client/Service method -> RequestSpec -> RestAssured call -> Response -> POJO/Assertion -> Listener -> Report

14. Best practices
- Keep tests focused and deterministic; centralize shared behavior; avoid hardcoded URLs in tests; use small, stable test data sets.