Architecture: rest-assured-2025-framework-testng-akash

Folder structure (top-level items):
- .github
- src
- target
- pom.xml
- suite.xml / crud-tests.xml (TestNG suites)
- Jenkinsfile, Dockerfile

Tech stack and versions (from pom.xml):
- Java: 21 (maven.compiler.source/target 21)
- RestAssured: 5.4.0
- TestNG: 7.10.2
- Allure: 2.24.0
- Owner (config): org.aeonbits.owner 1.0.8
- Lombok: 1.18.32

Execution flow and wiring:
1. CI/Runner: Maven/CI triggers TestNG suites (suite.xml/crud-tests.xml) which select tests to run.
2. Setup: TestNG listeners/BaseTest initialize config (Owner/properties), logging and reporting (Allure) and may set RestAssured.baseURI.
3. Requests: Tests call API clients built on a BaseClient/RestClient that encapsulates RestAssured usage and common RequestSpecifications.
4. Validation: ResponseSpecBuilders / json-schema-validator are used; responses are mapped to POJOs via Jackson.
5. Data providers: TestNG @DataProvider or external JSON drive CRUD/regression tests.
6. CI: Jenkinsfile / Dockerfile run the test command and archive allure/extent reports.

Where to inspect code for details:
- BaseTest, RestClient, listeners and src/test/resources for suite XML and property files that contain base URLs and credentials.