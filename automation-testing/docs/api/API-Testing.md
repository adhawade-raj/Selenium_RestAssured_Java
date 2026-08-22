# API Testing — Overview

This document explains APIs, why we test them, comparisons (REST vs SOAP, RESTful vs RESTless), how APIs differ from web services, and key API‑testing concepts, tools and best practices. Refer to the `postman/` and `restassured/` subfolders for examples and runnable collections/tests.

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
- SOAP:
  - Protocol with strict standards (WSDL, XML messaging).
  - Uses XML only, has built-in WS-* standards for security (WS-Security).
  - Often heavier and stateful features via extensions.
- REST:
  - Architectural style, not a protocol — uses HTTP verbs and resource-oriented URLs.
  - Typically uses JSON (but can use XML, others).
  - Lightweight, cacheable, stateless (ideally), easy to use with web clients.

When to use each: SOAP for enterprise systems needing formal contracts, advanced WS-* features, or strong message-level security; REST for general-purpose web APIs, microservices, mobile apps.

## RESTful vs RESTless
- RESTful: API design that follows REST constraints (client-server, stateless, uniform interface, cacheable, layered, code-on-demand optional). Uses resources (nouns) and HTTP verbs (GET/POST/PUT/DELETE) properly.
- RESTless: APIs that are HTTP-based but do not follow REST principles — often RPC-style endpoints (e.g., `/doPayment`), misuse of verbs, inconsistent status codes, or rely on custom action semantics.

RESTful designs are usually easier to understand, cache, and scale; RESTless designs can be pragmatic but may be harder to standardize and document.

## API vs Web Service — Differences
- Web Service: a service available over the web; traditionally implies SOAP or WSDL-based services and strict messaging formats.
- API: broader term for any interface exposed by a system (can be local libraries, OS APIs, or web APIs). Every web service is an API, but not all APIs are web services.

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
- `postman/` — Postman collections, exported environments, and Newman examples.
- `restassured/` — Java RestAssured test examples, sample Maven/Gradle setup and test classes.

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

If examples or runnable collections are needed, see the `postman` and `restassured` subfolders for ready-to-run artifacts. For help creating Postman collections, Newman CI steps, or RestAssured test classes, provide which format (Postman or RestAssured) to prioritize and a target endpoint example.
