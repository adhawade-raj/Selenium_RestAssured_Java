Framework flow and concepts

Purpose:
- Concise reference describing common automation framework structure, runtime flow and tech stack choices used across this repository.

Core concepts:
- Layers: Test layer, Service/API or Page layer, Core utilities, Models/DTOs, and Reporting.
- Reuse: Base classes (BaseTest, BasePage, BaseClient) centralize setup/teardown and common behaviour.
- Data-driven testing: External JSON/CSV drive test scenarios via TestNG @DataProvider or Cucumber.
- Reporting and CI: Allure/Extent capture results; Jenkins/Docker pipelines run suites.

Common tech stack (repo-wide examples):
- Java: 1.8, 17 or 21 (varies per module; check module pom.xml)
- RestAssured: 5.4.0
- Test frameworks: TestNG (7.x), JUnit (where used)
- Selenium: selenium-java 4.x (for UI modules)
- JSON mapping: Jackson (jackson-databind 2.15+)
- Schema validation: io.rest-assured:json-schema-validator
- Reporting: Allure (allure-testng/allure-rest-assured), ExtentReports

Common base URLs and example APIs used in tests across the repository:
- https://gorest.co.in
- https://ergast.com (F1/Ergast API)
- https://fakestoreapi.com
- https://petstore.swagger.io
- http://httpbin.org
- http://localhost (WireMock tests)
- https://restful-booker.herokuapp.com
- https://the-internet.herokuapp.com
- https://rickandmortyapi.com/graphql

Detailed runtime flow (concrete steps):
1. Runner/CI triggers tests (Maven surefire/failsafe, TestNG suites, or Cucumber runner).
2. Setup: BaseTest or TestNG listeners load configuration (properties/Owner), initialize logging and reporting. RestAssured.baseURI or WebDriver is set here.
3. Request/Action assembly:
   - API: BaseClient/RestClient creates RequestSpecification (RequestSpecBuilder) with common headers/auth/timeouts.
   - UI: BasePage provides reusable actions; PageObjects implement page-specific operations.
4. Execution:
   - API: Clients call RestAssured.given(requestSpec).when().get/post/...; responses returned or mapped to POJOs.
   - UI: Tests call PageObjects which interact with WebDriver and return page states or DTOs.
5. Validation & mapping: Responses or UI states are validated using ResponseSpecBuilder, JSON schema validators or assertions; mapping is done with Jackson/POJOs.
6. Utilities: Common helpers (retry, awaitility, token managers, test-data generators, json loaders) are used by clients and tests.
7. Reporting & CI: Listeners attach request/response artifacts to Allure/Extent; pipelines archive and publish reports.

Where utilities/config are typically applied:
- Config/owner/property classes provide environment-specific baseURIs and credentials used by BaseTest/RestClient.
- Data providers load external JSON/CSV and feed tests.

How to use these docs to explain framework flow:
- Point to module pom.xml for exact tech versions.
- Show BaseTest/RestClient to illustrate where baseURI is set and how RequestSpec is reused.
- Demonstrate one end-to-end test: data -> test -> client -> response -> model -> assertion -> report.