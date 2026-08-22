Architecture: rest-assured-2026-framework-json-driven

Folder structure (top-level items):
- .idea, .mvn, allure-results, src, target, pom.xml

Typical layers and flow:
- Data-driven layer: external JSON files drive test scenarios and inputs.
- Test layer: test classes read JSON inputs and call API clients.
- Client layer: encapsulates RestAssured calls and maps responses to models.
- Utilities: JSON parsers, config, common helpers.
- Reporting: allure-results captures execution data.

Call/inheritance mapping:
- Tests -> DataLoader -> API clients -> Models -> Assertions

Notes:
- Search src for folders like data, resources, utils, clients to see exact wiring.