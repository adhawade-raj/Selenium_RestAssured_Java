Architecture: rest-assured-2024-framework-ergast

Folder structure (top-level items):
- .settings
- allure-results
- extentReport
- src
- target
- test-output
- pom.xml

Tech stack and versions (from pom.xml):
- Java: 17 (maven.compiler.source/target 17)
- RestAssured: io.rest-assured:rest-assured 5.4.0
- Test framework: TestNG 7.4.0
- JSON / mapping: jackson-databind 2.15.2
- JSON schema validation: io.rest-assured:json-schema-validator 5.3.2
- Reporting: Allure (allure-rest-assured 2.27.0), ExtentReports

Common base URLs and endpoints found in repo (examples):
- https://ergast.com (F1/Ergast API)
- https://gorest.co.in
- https://fakestoreapi.com
- https://petstore.swagger.io
- http://httpbin.org
- http://localhost (WireMock tests)
- https://restful-booker.herokuapp.com
- https://the-internet.herokuapp.com
- https://rickandmortyapi.com/graphql

Detailed runtime flow and where to look in code:
1. Configuration: BaseTest or RestClient reads configs (properties/owner) and sets RestAssured.baseURI (often in a @BeforeClass/@BeforeTest). This centralizes the base URL and common RequestSpec.
2. Request building: Clients or BaseClient construct requests using RequestSpecBuilder / RequestSpecification, adding headers, auth and query/path params.
3. Execution: Tests invoke clients (service layer) which call RestAssured.given()...when().then() and return Responses or mapped POJOs.
4. Mapping & Validation: Responses are deserialized into POJOs via Jackson and validated using json-schema-validator or ResponseSpecBuilder.
5. Utilities: Common helpers (logging, token managers, test data generators, retry/awaitility) are invoked by clients/tests.
6. Reporting: Test listeners and Allure/Extent integrations collect logs, attachments and results into allure-results / extentReport.

Call/inheritance mapping (concrete patterns to search for):
- Tests -> BaseTest (setup/teardown, config) -> RestClient/BaseClient -> Models/DTOs
- Utilities (utils, helpers) are used by both BaseClient and Tests

Where to extend this file:
- For exact class-level call graphs, search src for classes named BaseTest, RestClient, RequestSpecBuilder usages and any config/owner/property files that hold base URLs.