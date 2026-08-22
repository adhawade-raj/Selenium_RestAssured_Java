Architecture: rest-assured-2026-framework-json-driven

Folder structure (top-level items):
- .idea
- .mvn
- allure-results
- src
- target
- pom.xml

Tech stack and versions (from pom.xml):
- Java: 17
- RestAssured: 5.4.0
- TestNG: 7.4.0
- Cucumber (where used): cucumber-java 7.14.0, cucumber-junit 7.14.0
- Jackson: 2.15.2
- Allure: 2.27.0

Common base URLs across repo (examples used by tests):
- https://gorest.co.in
- http://httpbin.org
- https://ergast.com
- https://fakestoreapi.com
- https://petstore.swagger.io

Data-driven execution flow (concrete):
1. Data files: JSON files (resources/data or src/test/resources) define test scenarios and request payloads.
2. Data loader: a DataLoader or utility reads JSON into POJOs (Jackson) and yields test cases to TestNG/Cucumber data providers.
3. Test execution: Test method receives POJO/data map and calls API client methods.
4. Client layer: Builds RequestSpecification, injects data from JSON into path/query/body, executes request and returns Response or POJO.
5. Assertions & validation: Response is validated against expected values or JSON schema; failures produce attachments for Allure.
6. Reporting: Allure captures request/response and test metadata into allure-results.

Notes:
- To map exact wiring search src for DataLoader/JsonReader classes and TestNG data provider annotations (@DataProvider) or Cucumber step definitions.