Introduction to this framework

1. Purpose
- A JSON-driven REST automation framework that externalizes test scenarios and payloads to maximize coverage with minimal code changes.

2. Goals
- Separate data from test logic; enable non-developers to author scenarios via JSON; centralize request/response handling.

3. Layers
- Data layer: JSON files defining scenarios and payloads.
- Test layer: TestNG/Cucumber runners reading JSON and invoking clients.
- Client layer: RestClient/BaseClient handling HTTP operations.
- Models & utils: Jackson mapping, loaders, and helpers.
- Reporting: Allure results capture artifacts.

4. Data-driven flow
- DataLoader reads JSON into POJOs; TestNG @DataProvider feeds test methods representing scenarios.

5. Request/response reuse
- RequestSpec/ResponseSpec encapsulate common headers, auth and validations.

6. Validation & mapping
- Jackson for mapping; schema validation for contract checks.

7. Utilities & extension
- Token managers, logging, retry helpers and custom validators live in utilities and are reused by clients and tests.

8. CI & reporting
- Maven/TestNG runs in CI; Allure results provide detailed request/response attachments.

9. Best practices
- Keep JSON scenarios small and focused; version data and avoid embedding environment-specific endpoints directly in test data.