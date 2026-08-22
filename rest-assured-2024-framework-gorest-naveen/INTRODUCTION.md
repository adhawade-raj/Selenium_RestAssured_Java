Introduction to this framework

1. Purpose
- A practical API automation framework built with RestAssured focused on reusability, readability and CI-friendly reporting.

2. Goals
- Centralize HTTP setup, authentication and common validations in base classes; enable data-driven scenarios.

3. Layers
- Tests: expressive TestNG tests orchestrating scenarios.
- Service/Client: RestClient wrappers for endpoint calls.
- Models: POJOs and DTOs for mapping responses and building requests.
- Core: config, utils, token managers, data loaders.
- Reporting: Allure/Extent collectors via listeners.

4. Configuration
- Owner/properties or env-based configuration provides baseURI and credentials applied during test setup.

5. Request/response reuse
- RequestSpec and ResponseSpec patterns to keep tests DRY and consistent.

6. Data-driven approach
- JSON or TestNG data providers drive multiple test scenarios; data loader utilities transform JSON to POJOs.

7. Validation & mapping
- Jackson for mapping; json-schema-validator for contract validation.

8. Utilities
- Logging, retries, awaitility, and test data generation reside here and are reused across layers.

9. CI & reporting
- Suite XML + Maven run tests; Allure and extentReport store execution artifacts.

10. Extension points
- Add services/clients and new data loaders without changing test core.

11. Trace
- Config -> BaseTest -> Test -> Client -> RequestSpec -> Execution -> Response -> Mapper -> Assertion -> Report

12. Best practices
- Keep tests small, deterministic and fast; centralize shared behavior and configuration.