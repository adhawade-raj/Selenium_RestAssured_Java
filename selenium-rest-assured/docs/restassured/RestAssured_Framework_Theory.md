API Automation Framework — Theory (Interview-friendly)

We have designed our API automation framework using Java, Rest Assured and BDD (Cucumber). The framework follows a layered architecture to keep responsibilities clear and tests maintainable.

- Configuration Manager
  - Reads environment-specific configuration such as API base URLs, credentials and timeouts.

- RestClient (common HTTP layer)
  - Encapsulates Rest Assured request execution for GET, POST, PUT, PATCH and DELETE.
  - Builds RequestSpecification with headers, authentication, content type and other request parameters.

- Prerequisite / Authentication Layer
  - Invokes authentication APIs when required, retrieves access tokens and exposes them for subsequent requests.
  - Handles token refresh / expiry concerns.

- BaseTest
  - Manages common setup and teardown (preconditions and postconditions) for tests.

- Test Layer
  - Contains BDD-style scenarios (Cucumber) or test cases with assertions that verify API behavior.

- Payloads and Mapping
  - Request and response payloads are modelled as POJOs using Lombok to reduce boilerplate.
  - Jackson Databind is used for JSON serialization / deserialization.

- Data-driven Testing
  - An Excel utility provides multiple combinations of test data to drive scenarios.

- Utilities, Constants and Enums
  - Reusable helpers and domain enums live in a common utilities package to avoid duplication.

- Reporting
  - Allure is used for reporting. Execution artifacts are produced under `allure-results`, which are then used to generate the HTML execution report.

This concise overview is useful for explaining the architecture and design choices during interviews or design discussions.