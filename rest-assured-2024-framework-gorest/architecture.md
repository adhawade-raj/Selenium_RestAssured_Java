Architecture: rest-assured-2024-framework-gorest

Folder structure (top-level items):
- .settings
- allure-results
- extentReport
- src
- target
- test-output
- pom.xml

Tech stack and versions (from pom.xml):
- Java: 17
- RestAssured: 5.4.0
- TestNG: 7.4.0
- Jackson: jackson-databind 2.15.2
- JSON schema validation: 5.3.2
- Allure: 2.27.0, ExtentReports 5.x

Common base URLs used across tests in this module and repo:
- https://gorest.co.in
- https://test.api.amadeus.com (seen in some RestClient samples)
- http://httpbin.org
- https://fakestoreapi.com
- http://localhost (for WireMock tests in other modules)

Detailed flow (how an API test executes):
1. Setup: BaseTest or a TestNG listener loads config and sets RestAssured.baseURI (from properties/owner or hardcoded in test helpers).
2. Request assembly: BaseClient/RestClient builds a RequestSpecification (RequestSpecBuilder) with headers, auth and common timeouts.
3. Test invocation: Test methods call client methods which use RestAssured.given(requestSpec).when().<method>() and return Response or mapped POJO.
4. Validation & mapping: Responses are validated (ResponseSpecBuilder / json-schema-validator) and mapped to POJOs via Jackson.
5. Reporting & artifacts: TestNG listeners and Allure/Extent integrations collect results into allure-results/extentReport.

Where utilities are used:
- Config (owner/properties) and token managers are used by RestClient.
- Data providers and JSON files (if present) feed tests via TestNG data providers.

To find exact call sites: search src for RestClient, BaseClient, RequestSpecBuilder, ResponseSpecBuilder, and any property files that contain base URIs.