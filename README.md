# Selenium_RestAssured_Java

A multi-module repository containing example projects, frameworks and practice code for automated testing using Selenium, RestAssured, Playwright and plain Java practice programs.

This repository is a personal collection of test automation and Java practice projects. Each top-level folder is a separate Maven module or project that can be built and run independently.

## Repository structure (high level)

- `API_2025/` - API automation examples (Maven project)
- `JavaLogicalPrograms/` - Small Java programs and logical problems (Maven project)
- `Playwright_2024/` - Playwright-based tests (Maven project)
- `RestAssured_2024/` - RestAssured API tests with Allure/extent outputs
- `RestAssured_2024_Ergast_Framework/` - RestAssured framework targeting Ergast API
- `RestAssured_2024_GoRest_Framework/` - RestAssured framework for GoRest API
- `RestAssured_2024_GoRest_Framework_Naveen/` - GoRest framework (contains Dockerfile and Jenkins pipeline files)
- `RestAssured_2024_Practise/` - Practice API tests
- `Selenium_2022_EcommerceFramework/` - Selenium-based ecommerce automation framework
- `Selenium_2022_MyAutomationTraining/` - Selenium training exercises and examples
- `Selenium_2024/` - Updated Selenium examples
- `Selenium_2024_BooksCart_Framework/` - BooksCart demo framework

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

Example: run tests for `RestAssured_2024`:

```powershell
cd RestAssured_2024
mvn clean test
```

Example: run tests for `JavaLogicalPrograms` (unit tests / main classes as configured):

```powershell
cd JavaLogicalPrograms
mvn clean test
```

Example: run Playwright project tests (if configured as a Maven project):

```powershell
cd Playwright_2024
mvn clean test
```

If a project uses Allure, after running tests you can generate/serve the report (if Allure CLI is installed):

```powershell
# example; paths may vary depending on project configuration
allure serve target/allure-results
```

## Docker (example)

One module (`RestAssured_2024_GoRest_Framework_Naveen`) contains a `Dockerfile`. To build the image locally:

```powershell
cd RestAssured_2024_GoRest_Framework_Naveen
docker build -t gorest-framework:latest .
```

## CI / Jenkins

Some modules include `Jenkinsfile` or pipeline helpers. If you use Jenkins, the repository can be built using those pipeline definitions. The repo also contains a `Jenkins_PipeLine` folder in one module.

## Recommended .gitignore

This repository should ignore IDE-specific files. Example entries (already applied):

```
.idea/
target/
*.log
```

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

---

If you'd like, I can:

- add module-specific run instructions for a particular project (pick one),
- add GitHub Actions / CI templates, or
- create a CONTRIBUTING.md and CODE_OF_CONDUCT.md.