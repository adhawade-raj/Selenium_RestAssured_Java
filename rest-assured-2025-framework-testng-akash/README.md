# RestAssured TestNG Framework

![Java](https://img.shields.io/badge/Java-21-blue.svg?style=for-the-badge&logo=openjdk&logoColor=black&labelColor=ED8B00)
![Maven](https://img.shields.io/badge/Maven-3.9.5-blue?logo=apachemaven&logoColor=black&labelColor=C71A36&style=for-the-badge)
![TestNG](https://img.shields.io/badge/TestNG-7.10.2-blue?logo=testng&labelColor=CD6532&style=for-the-badge)
![RestAssured](https://img.shields.io/badge/RestAssured-5.4.0-blue?labelColor=00A86B&style=for-the-badge)
![Allure](https://img.shields.io/badge/Allure-2.24.0-blue?labelColor=FF6400&style=for-the-badge)

This is a sample project that demonstrates how to use RestAssured and TestNG to create a robust API testing framework.

## Prerequisites

- [Java 21](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- Allure
    - [Allure CLI](https://allurereport.org/docs/install/)
    - [Allure TestNG](https://allurereport.org/docs/testng/)

## Getting Started

### Install dependencies

    mvn install -DskipTests

## Project Structure

The project follows a standard Maven directory structure:

- `src/main/java`: Contains the main Java source code.
- `src/main/resources`: Contains the main resources such as property files.
- `src/test/java`: Contains the test Java source code.

## Features

- **TestNG**: The project uses TestNG as the testing framework, providing powerful features for test management and
  reporting.
- **RestAssured**: RestAssured is used for making HTTP requests and validating API responses.
- **Maven**: Maven is used as the build and dependency management tool, making it easy to manage project dependencies.
- **Allure**: Allure is used for test reporting, providing detailed reports with rich visualization.

## Running Tests

Run CRUD tests:

    mvn test -DsuiteXmlFile=crud-tests.xml

Run `suite.xml` with other tests:

    mvn test -DsuiteXmlFile=suite.xml

## Viewing Reports

After running the tests, you can view the Allure report by running the following command:

    allure serve

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvement, please open an issue or submit a
pull request.
