Introduction to this framework

1. Purpose
- A TestNG-driven REST automation framework that emphasizes reusable clients, data-driven suites and CI-ready reporting.

2. Objectives
- Standardize how requests are built, executed and validated; integrate Allure and other listeners for artifacts.

3. Layers
- TestNG test layer (suites and tests)
- Base/Client layer (RequestSpec, common authentication)
- Models/DTOs for mapping
- Utilities and data providers
- Reporting (Allure/Extent)

4. Configuration
- Owner/properties supply environment-specific baseURIs and credentials; listeners/BaseTest apply them to RestAssured.

5. Request/Response reuse
- Reusable RequestSpecification and ResponseSpecification keep tests consistent.

6. Data-driven
- @DataProvider and external JSON/CSV drive CRUD and regression scenarios.

7. Validation & mapping
- Jackson POJOs and JSON schema validation standardize contract checks.

8. Utilities & CI
- Helpers for retry, wait, logging; Jenkinsfile and Dockerfile support CI execution.

9. Reporting
- Listeners attach requests/responses and test metadata to Allure/Extent reports.

10. Extension & best practices
- New endpoints added by creating client/service methods that reuse BaseClient; keep tests idempotent and data-driven.