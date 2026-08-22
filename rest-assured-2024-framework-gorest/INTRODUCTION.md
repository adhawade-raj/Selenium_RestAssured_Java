Introduction to this framework

1. Purpose
- Provide a structured API automation framework using RestAssured to validate REST endpoints with reusable clients and clear tests.

2. Design goals
- Encapsulate HTTP details in clients; keep tests expressive and independent.
- Support data-driven tests and robust reporting.

3. Layers
- Test layer: TestNG suites and test classes.
- Client layer: RestClient/BaseClient with RequestSpecification builders.
- Model layer: POJOs for requests/responses.
- Utilities: config, token manager, json loaders.
- Reporting: Allure and Extent integration.

4. Configuration & base URI
- Central config (properties/Owner) determines environment and baseURI; BaseTest or listener applies it to RestAssured.baseURI.

5. Request/Response patterns
- Use RequestSpecBuilder and ResponseSpecBuilder to centralize headers, auth and common assertions.

6. Data-driven flow
- External JSON or TestNG @DataProvider feed test methods; DataLoader maps to POJOs.

7. Validation & mapping
- Jackson maps responses to POJOs; schema validation ensures contract compliance.

8. Utilities
- Helpers for retries, logging, random test-data generation, and token refresh.

9. Reporting & CI
- Tests attach artifacts to Allure; CI pipelines run suites and archive results.

10. Extension
- Add clients or new resources by creating service classes that reuse BaseClient and utilities.

11. Trace
- Config -> BaseTest -> Test -> Client -> RequestSpec -> RestAssured -> Response -> Mapper -> Assertions -> Report

12. Best practices
- Avoid hardcoding endpoints in tests; use reusable specs and data-driven tests for coverage.