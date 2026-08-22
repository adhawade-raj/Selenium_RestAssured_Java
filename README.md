# Selenium_RestAssured_Java

A multi-module repository containing example projects, frameworks and practice code for automated testing using Selenium, RestAssured, Playwright and plain Java practice programs.

This repository is a personal collection of test automation and Java practice projects. Each top-level folder is a separate Maven module or project that can be built and run independently.

## Repository structure (high level)

- `wiremock-2025/` - API automation examples (Maven project)
 - `bdd-cucumber-2025/` - BDD Cucumber framework for test automation (Maven project)
 - `java-logical-programs/` - Small Java programs and logical problems (Maven project)
 - `playwright-2024/` - Playwright-based tests (Maven project)
- `rest-assured-2025/` - RestAssured 2025 API testing (Maven project)
- `rest-assured-2025-akash/` - RestAssured 2025 Akash variant with course resources (Maven project)
- `rest-assured-2025-framework-testng-akash/` - RestAssured 2025 Akash with TestNG framework (contains Dockerfile and Jenkinsfile)
- `rest-assured-2026/` - RestAssured 2026 API testing (Maven project)
- `rest-assured-2026-framework-json-driven/` - RestAssured 2026 JSON-driven framework with Allure reporting (Maven project)
- `rest-assured-2024/` - RestAssured API tests with Allure/extent outputs
- `rest-assured-2024-framework-ergast/` - RestAssured framework targeting Ergast API
- `rest-assured-2024-gorest-framework/` - RestAssured framework for GoRest API
- `rest-assured-2024-framework-gorest-naveen/` - GoRest framework (contains Dockerfile and Jenkins pipeline files)
- `rest-assured-2024-practise/` - Practice API tests
 - `selenium-2022-framework-ecommerce/` - Selenium-based ecommerce automation framework
 - `selenium-2022-my-automation-training/` - Selenium training exercises and examples
 - `selenium-2024/` - Updated Selenium examples
 - `selenium-2024-framework-bookscart/` - BooksCart demo framework
 - `selenium-2026/` - Selenium 2026 examples and updated tests (Maven project)

Many of the folders contain a `pom.xml` so they can be executed with Maven independently.

## Prerequisites

- Java JDK 8/11/17 installed (set `JAVA_HOME` appropriately).
- Apache Maven 3.6+ installed and on `PATH`.
- Git (to clone and manage the repo).
- A modern browser for Selenium tests (Chrome/Firefox) and corresponding WebDriver binaries (or use WebDriverManager in the projects).
- Node.js (if you want to run Playwright tests or install Playwright CLI/tools).
- (Optional) Allure CLI for generating / serving test reports: https://docs.qameta.io/allure/
- (Optional) Docker (for building Docker images located in some modules).


## Quick start — clone the repository

Open PowerShell and run:

```powershell
git clone https://github.com/adhawade-raj/Selenium_RestAssured_Java.git
cd Selenium_RestAssured_Java
```

## Build and run tests (per module)

Each subproject is designed to be run independently. The general pattern is to change into the module directory and run Maven commands.

Example: run tests for `rest-assured-2024`:

```powershell
cd rest-assured-2024
mvn clean test
```

Example: run tests for `java-logical-programs` (unit tests / main classes as configured):

```powershell
cd java-logical-programs
mvn clean test
```

Example: run Playwright project tests (if configured as a Maven project):

```powershell
cd playwright-2024
mvn clean test
```

If a project uses Allure, after running tests you can generate/serve the report (if Allure CLI is installed):

```powershell
# example; paths may vary depending on project configuration
allure serve target/allure-results
```

## Docker (example)

One module (`rest-assured-2024-framework-gorest-naveen`) contains a `Dockerfile`. To build the image locally:

```powershell
cd rest-assured-2024-framework-gorest-naveen
docker build -t gorest-framework:latest .
```

## CI / Jenkins

Some modules include `Jenkinsfile` or pipeline helpers. If you use Jenkins, the repository can be built using those pipeline definitions. The repo also contains a `Jenkins_PipeLine` folder in one module.

## Contributing

- Create a feature branch off `master` for non-trivial changes: `git checkout -b feature/my-change`.
- Commit often and write clear commit messages.
- Open a Pull Request to merge changes back into `master` (many projects prefer PR-based rules).

## Notes and tips

- Many modules are examples or practice code—read the `pom.xml` and tests in each module to understand how they are structured.
- If you have WebDriver issues, check whether the project uses WebDriverManager (recommended) or expects you to provide driver binaries.
- If Allure or extent reports are configured, check the module's `pom.xml` for plugins and report output locations.

## Contact / Author

Repository owner: adhawade-raj
