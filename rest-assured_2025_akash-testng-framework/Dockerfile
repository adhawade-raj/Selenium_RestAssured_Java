FROM maven:3.9-eclipse-temurin-21

ENV MAVEN_PROFILE=regression

WORKDIR /app

COPY src ./src
COPY pom.xml .
COPY suite.xml .
COPY crud-tests.xml .

CMD mvn clean test -P${MAVEN_PROFILE}