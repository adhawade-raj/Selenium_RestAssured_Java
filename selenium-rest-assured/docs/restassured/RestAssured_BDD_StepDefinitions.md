BDD Mapping — Step Definitions (JUnit runner)

This document maps the framework layers to a Cucumber BDD step-definition approach using JUnit as the test runner. It explains where responsibilities live and how features, step-defs and runners interact so the design can be explained in interviews.

High-level design (same layered architecture)

- Configuration Manager
  - Reads environment-specific values (API base URLs, credentials, timeouts) and exposes them via a config helper used by step definitions and hooks.

- RestClient (common HTTP layer)
  - Encapsulates Rest Assured calls (GET, POST, PUT, PATCH, DELETE).
  - Provides methods returning deserialized POJOs or raw responses. Step definitions call RestClient methods to perform API actions.

- Authentication / Prerequisite Layer
  - Implemented as Cucumber hooks (e.g., @Before or @BeforeAll) or a dedicated helper invoked from hooks.
  - Calls auth endpoints, stores tokens in a thread-safe Context (TestContext) for use in step defs, and handles token renewal.

- Hooks / Test Context (replaces BaseTest responsibilities)
  - Cucumber Hooks manage setup and teardown (global or per-scenario).
  - A TestContext object holds shared state (tokens, IDs, response objects) and is injected into step classes.

- Feature files (src/test/resources/features)
  - Human-readable Gherkin scenarios describing API behaviour.
  - Each Given/When/Then maps to step-definition methods.

- Step Definitions (src/test/java/..../steps)
  - Lightweight glue code: parse Gherkin parameters, call RestClient/helper methods, and perform assertions.
  - Keep step defs concise: delegate logic to service / client classes.

- Payloads and Mapping
  - Continue using POJOs with Lombok and Jackson for serialization/deserialization in RestClient and StepDefs.

- Data-driven Scenarios
  - Use Scenario Outlines with Examples in feature files or external data sources (e.g., Excel or CSV) processed by step defs or a data provider helper.

- Utilities, Constants and Enums
  - Shared utilities remain in a common package; step defs consume them as needed.

- Runner (JUnit)
  - Use Cucumber + JUnit Platform (Cucumber-JUnit) to run feature files. The runner class configures glue, feature locations and plugins (Allure, pretty, json).
  - Example (conceptual):
    - @CucumberOptions(features = "src/test/resources/features", glue = "com.myorg.steps", plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})

- Reporting
  - Use Allure Cucumber adapter to capture steps, attachments and results under `allure-results` for HTML reports.

Folder structure (example)

- src/test/resources/features/*.feature
- src/test/java/com/myorg/steps/*StepDefs.java
- src/test/java/com/myorg/hooks/Hooks.java
- src/test/java/com/myorg/runner/CucumberRunner.java
- src/main/java/com/myorg/http/RestClient.java
- src/main/java/com/myorg/config/ConfigurationManager.java
- src/main/java/com/myorg/model/*.java
- src/main/java/com/myorg/utils/*.java

Notes for interviews

- Emphasize separation of concerns: step defs glue features to implementation; RestClient contains HTTP logic; hooks manage auth and lifecycle; ConfigurationManager isolates environment details.
- Mention POJOs + Lombok + Jackson for payloads and Allure for reports.
- Be ready to explain TestContext and thread-safety for parallel runs.

This mapping helps explain how the framework supports readable BDD tests while keeping implementation maintainable and reusable.