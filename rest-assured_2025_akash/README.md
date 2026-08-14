# RestAssured with Java 21

![Java](https://img.shields.io/badge/Java-21-blue.svg?style=for-the-badge&logo=openjdk&logoColor=black&labelColor=ED8B00)
![Maven](https://img.shields.io/badge/Maven-3.9.5-blue?logo=apachemaven&logoColor=black&labelColor=C71A36&style=for-the-badge)
![TestNG](https://img.shields.io/badge/TestNG-7.10.2-blue?logo=testng&labelColor=CD6532&style=for-the-badge)
![RestAssured](https://img.shields.io/badge/RestAssured-5.4.0-blue?labelColor=00A86B&style=for-the-badge)

Let's unleash the power of RestAssured with JDK 21. This course will help you understand how to automate RESTful APIs
using RestAssured with Java. You will learn how to write test cases for RESTful APIs, send requests, and validate
responses using RestAssured. You will also learn how to handle authentication, query parameters and path parameters in
RestAssured.

## Table of Contents

- [Introduction](#introduction)
- [Installation](#installation)
  - [Setup JDK 21 in IntelliJ IDEA](#setup-jdk-21-in-intellij-idea)
  - [Verify Installations](#verify-installations)
- [Setup Environment Variables](#setup-environment-variables)
  - [For macOS Users](#macos)
  - [For Windows Users](#windows)
  - [Verify Environment Variables](#verify-environment-variables)
- [Final Checklist before We Start](#final-checklist-before-we-start)
- [Next Steps](#next-steps)
- [Course Navigation](#course-navigation)
- [Theoretical Docs](#theoretical-docs)
- [IntelliJ IDEA Essentials](course-resources/intellij-essentials.md)

## Introduction

***

This course will help you understand how to automate RESTful APIs using RestAssured with Java. You will learn how to
write test cases for RESTful APIs, send requests, and validate responses using RestAssured. You will also learn how to
handle authentication, query parameters, form parameters, and path parameters in RestAssured.

## Installation

***

- [JDK 21](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/download.cgi)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
- [Allure Commandline](https://allurereport.org/docs/install/)

You can copy the project `pom.xml` file that already has all the dependencies.

### Setup JDK 21 in IntelliJ IDEA

1. Open IntelliJ IDEA
2. Go to `File` -> `Project Structure`
3. Click on `Project` and select the `SDK` as `21`

### Verify Installations

Verify Maven and Java installation by running the following commands in the terminal:

```bash
mvn -v
java --version
```

Make sure `JAVA_HOME` environment variable is set to the JDK 21 path.

```bash
# This should point to the JDK 21 path
$ echo $JAVA_HOME

```

## Setup Environment Variables

### macOS

- Open the terminal
- Run the following command to open the `.bash_profile` file:

```bash
open ~/.bash_profile
```

or for `zsh` users:

```bash
open ~/.zshrc
```

- Add the following lines to the file:

```bash

export JAVA_HOME=$(<path to JDK 21>)
export PATH=$JAVA_HOME/bin:$PATH

#Course related environment variables
export IMGUR_CLIENT_ID=<your imgur client id>
export IMGUR_CLIENT_SECRET=<your imgur client secret>
export IMGUR_ACCESS_TOKEN=<your imgur access token>
export IMGUR_REFRESH_TOKEN=<your imgur refresh token>

```

- Save the file and run the following command to apply the changes:

```bash
source ~/.bash_profile
```

or for `zsh` users:

```bash
source ~/.zshrc
```

### Windows

- Right-click on `My Computer` and select `Properties`
- Click on `Advanced system settings`
- Click on `Environment Variables`
- Add the previously mentioned environment variables from macOS section.

### Verify Environment Variables

You can verify the environment variables by running the following commands:

```bash
echo $IMGUR_CLIENT_ID
echo $IMGUR_CLIENT_SECRET
echo $IMGUR_ACCESS_TOKEN
echo $IMGUR_REFRESH_TOKEN
```

> ❌ Do NOT expose Imgur credentials in the code. Use environment variables to store sensitive information. When you are
> practicing
> or working on a project, use credentials from ENV vars. If you have to keep them in a file, then make sure to add this
> file in `.gitignore` file to prevent any exposure.

## Final Checklist before We Start

- [x] You have IDE of your choice or IntelliJ Idea Installed. Follow the IDE essentials lecture for more.
- [x] Java and Maven are installed and verified.
- [x] `JAVA_HOME` and `MAVEN_HOME` pointing to relevant installation.

## Next Steps

- [IDE Setup](course-resources/intellij-essentials.md)

## Course Navigation

1. Introduction to RestAssured
2. Installation and Setup
3. Get API Examples: [SimpleGetApi](/src/test/java/practiceTests/SimpleGetApi.java)
4. Post API Examples: [SimplePostAndPutApi](/src/test/java/practiceTests/SimplePostApi.java)
5. Put API Examples: [SimplePostAndPutApi](/src/test/java/practiceTests/SimplePutApi.java)
6. Delete API Examples: [BasicAndDigestAuthTests](/src/test/java/practiceTests/auth/BasicAndDigestAuthTests.java)
7. OAuth 2.0 Authentication: [ImgurOAuth2Example](/src/test/java/practiceTests/auth/ImgurOAuth2ExampleTests.java)
8. Basic and Digest
   Authentication: [BasicAndDigestAuthTests](/src/test/java/practiceTests/auth/BasicAndDigestAuthTests.java)
9. Query and Path Param Examples: [SimpleGetApi](/src/test/java/practiceTests/SimpleGetApi.java)
    - Method `getApiWithQueryParams()` and `getApiWithUrlParams()`
10. Logging and Filters: [LoggingAndFilters](/src/test/java/practiceTests/LoggingAndFilters.java)
11. Response Extraction: [ResponseExtraction](/src/test/java/practiceTests/ResponseExtraction.java)
12. Response Parsing: [ResponseParsing](/src/test/java/practiceTests/ResponseParsing.java)

## Theoretical Docs

1. [Authentication](course-resources/auth.md)
2. [Cucumber Essentials](course-resources/cucumber-essentials.md)
3. [Request Specification](course-resources/request-spec.md)
4. [JSONPath with Jayway](course-resources/jsonpaths.md)
5. [Practice Resources](course-resources/practice-resources.md)
