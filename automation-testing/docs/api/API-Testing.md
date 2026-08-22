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

**Authentication types (quick notes)**

| Type | Description | When to use |
|---|---|---|
| No auth | Public endpoints with no access control | Simple public APIs or health checks |
| API Key | Key passed in header or query (e.g., x-api-key or ?api_key=) | Simple service-to-service access or basic API gating |
| Basic Auth | username:password base64 in Authorization header | Internal/legacy services over HTTPS where simple creds suffice |
| Bearer Token (JWT) | Authorization: Bearer <token> (often JWT) | Stateless auth for web/mobile clients; use with HTTPS |
| OAuth 1.0 | Signature-based (consumer key/secret, token, nonce, signature) | Legacy APIs requiring signed requests (e.g., older Twitter APIs) |
| OAuth 2.0 | Token-based flows (Authorization Code, Client Credentials, etc.) | Modern delegated auth for user or server apps; widely adopted |

**OAuth 1.0 vs OAuth 2.0**

| Aspect | OAuth 1.0 | OAuth 2.0 |
|---|---|---|
| Mechanism | Per-request signatures (HMAC-SHA1) plus tokens | Bearer tokens (access + refresh tokens); no per-request signature |
| Complexity | More complex to implement and verify | Simpler flows but requires secure token handling and HTTPS |
| Security model | Signed requests protect against token interception | Relies on TLS; tokens must be protected at rest/in transit |
| Common flows | Signature-based requests | Authorization Code, Client Credentials, Resource Owner Password Credentials, Refresh Token |
| When to use | Legacy systems or where signing is required | New development, delegated access, mobile/web clients; preferred today |

**Headers & Content-Types (quick reference)**

| Header / Type | Purpose | Example |
|---|---|---|
| Authorization | Carries credentials (Basic, Bearer, OAuth) | Authorization: Bearer {{tokenID}} |
| Content-Type | Indicates request body media type | Content-Type: application/json |
| Accept | Indicates expected response media types | Accept: application/json |
| Content-Disposition | Used with multipart for file metadata | Content-Disposition: form-data; name="file"; filename="a.png" |
| Content-Length | Size of request body in bytes | Content-Length: 5243 |
| Accept-Encoding | Compression formats client accepts | Accept-Encoding: gzip, deflate |
| X-Request-ID | Correlation id for tracing | X-Request-ID: 123e4567-e89b-12d3-a456-426614174000 |

**Common Content-Types (table)**

| MIME type | Use case | Notes |
|---|---|---|
| application/json | Structured JSON payloads | Default for modern APIs |
| application/xml | XML payloads | Used by SOAP or legacy APIs |
| multipart/form-data | File uploads and mixed form fields | Uses boundary; each part has its own headers (Content-Disposition) |
| application/x-www-form-urlencoded | HTML form submissions | Key=value&key2=value2 form encoding |
| text/plain | Plain text payloads | Simple text responses or debugging |
| application/octet-stream | Binary data | Generic binary stream for downloads/uploads |

**When using multipart/form-data**

- Each part is separated by a boundary string defined in the Content-Type header, e.g. Content-Type: multipart/form-data; boundary=----WebKitFormBoundaryxyz
- Each part has its own headers (Content-Disposition, Content-Type optional)
- Use for file uploads alongside form fields; in Postman choose "form-data" body type and set the field to "file" for file parts

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
