# API Testing — Overview

This document explains APIs, why we test them, comparisons (REST vs SOAP, RESTful vs RESTless), how APIs differ from web services, and key API‑testing concepts, tools and best practices. Refer to the [postman/](postman/) and [restassured/](restassured/) subfolders for examples and runnable collections/tests.

---

## What is an API?
An API (Application Programming Interface) is a defined set of rules and endpoints that allow one software component to talk to another. For web APIs, this means HTTP endpoints that accept requests and return data (JSON, XML, etc.).

## Why do API testing?
- Validates business logic and functional correctness of the service layer.
- Faster and more stable than UI tests (no UI flakiness).
- Enables early detection of integration bugs between services.
- Verifies security, performance, and data contracts (schemas).
- Supports CI/CD by enabling automated verification of service behavior.

## REST vs SOAP

| Aspect | SOAP | REST |
|---|---|---|
| Nature | Protocol with strict standards (WSDL, XML messaging) | Architectural style using HTTP verbs and resource-oriented URLs |
| Message format | XML only | Typically JSON (can be XML or others) |
| Standards | Built-in WS-* (security, transactions) | Fewer standards; relies on HTTP features |
| Weight | Heavier, more verbose | Lightweight, cacheable, ideal for web clients |
| State | Can support stateful features via extensions | Ideally stateless |
| Typical use cases | Enterprise systems needing formal contracts and message-level security | Microservices, mobile/web APIs, general-purpose services

When to use each: SOAP for enterprise systems needing formal contracts, advanced WS-* features, or strong message-level security; REST for general-purpose web APIs, microservices, mobile apps.

## RESTful vs RESTless

| Aspect | RESTful | RESTless |
|---|---|---|
| Conformance | Follows REST constraints: client-server, stateless, uniform interface, cacheable, layered | HTTP-based but ignores REST constraints; often RPC-like |
| URL design | Resource-oriented (nouns) and uses HTTP verbs correctly | Action-oriented endpoints (e.g., /doPayment), misuse of verbs |
| Predictability | Easier to understand, cache, and scale | Pragmatic but harder to standardize and document |
| When seen | Recommended for APIs aiming for standardization | Often seen in legacy or ad-hoc services

RESTful designs are usually easier to understand, cache, and scale; RESTless designs can be pragmatic but may be harder to standardize and document.

## API vs Web Service — Differences

| Aspect | Web Service | API |
|---|---|---|
| Scope | Typically a network-accessible service (often SOAP/WSDL historically) | Broad term for any interface exposed by a system (web APIs, libraries, OS APIs) |
| Protocol/format | Traditionally SOAP/XML with formal contracts | Often REST/JSON for web APIs, but can be anything |
| Implication | Implies service over web with formal contracts | General programming interface; not necessarily over network

Key practical difference: "web service" often implies SOAP/XML and formal contracts; "API" is the general programming interface (commonly REST/JSON today).

## Core API testing types
- Functional testing: validate endpoints return correct responses for valid inputs.
- Contract/schema testing: verify responses match OpenAPI/JSON Schema/XSD.
- Integration testing: ensure multiple components work together.
- End-to-end testing: full flow covering backend interactions.
- Security testing: authentication, authorization, input validation, injection tests.
- Performance/load testing: throughput, latency, concurrency, stress limits.
- Negative/error-path testing: invalid inputs, missing headers, malformed bodies.
- Compatibility/versioning tests: older clients vs new server versions.
- Mocking and Stubbing: isolate services using mocks for dependent systems.

## Common checks for each endpoint
- Status code correctness (200/201/204/400/401/403/404/409/500 etc.).
- Response schema and data types.
- Required headers and CORS when relevant.
- Authentication and authorization behavior.
- Idempotency of safe methods where applicable.
- Proper pagination, filtering, sorting behavior.
- Rate limit handling and retry/backoff behavior.

## Test data and environment
- Use dedicated test environments and isolated test accounts.
- Seed test data or use disposable resources (create -> test -> teardown).
- Avoid testing on production unless read-only monitoring with limited scope.

## Tooling (examples)
- Postman: collections, environments, monitors, Newman CLI for CI.
- RestAssured (Java): expressive DSL for HTTP assertions and integrations with test frameworks (JUnit/TestNG).
- SoapUI / ReadyAPI: SOAP and REST testing focused tool.
- curl / HTTPie for quick manual checks and scripts.
- Pact: consumer-driven contract testing.
- JMeter / Gatling: load and performance testing.
- OpenAPI / Swagger: API specification and auto-generated client/server stubs.

See subfolders:
- [postman/](postman/) — Postman collections, exported environments, and Newman examples.
- [restassured/](restassured/) — Java RestAssured test examples, sample Maven/Gradle setup and test classes.

## Best practices for API tests
- Automate in CI: run functional and contract tests on every build.
- Keep tests fast and deterministic — mock external slow dependencies.
- Validate contracts (OpenAPI/JSON Schema) as part of CI.
- Separate test suites: smoke, regression, performance.
- Keep test data lifecycle-managed (setup/teardown).
- Use assertion libraries that give readable failure messages.
- Version APIs and tests together; include compatibility tests.

## Quick checklist before releasing an API
- All critical functional tests pass.
- Contract validation against OpenAPI/schema.
- Security scans and authentication checks.
- Load/performance baseline met for expected traffic.
- Backwards-compatibility tests for supported clients.
- Proper logging, metrics, and graceful error messages.

---

If examples or runnable collections are needed, see the [postman](postman/) and [restassured](restassured/) subfolders for ready-to-run artifacts. For help creating Postman collections, Newman CI steps, or RestAssured test classes, provide which format (Postman or RestAssured) to prioritize and a target endpoint example.
