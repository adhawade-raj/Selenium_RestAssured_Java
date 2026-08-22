Framework flow and concepts

Purpose:
- High-level theoretical explanation of common test automation framework structure and runtime flow.

Core concepts:
- Layers: Test layer, Service/API or Page layer, Core utilities, Models, and Reporting.
- Test orchestration: Tests invoke clients/page objects which call lower-level helpers.
- Reuse: Base classes (BaseTest, BasePage, BaseClient) centralize setup/teardown and common behaviour.
- Utilities: Logging, config, data providers and helpers are used across tests and clients.
- Data-driven testing: External data (JSON/CSV) is loaded by data providers and fed to tests.
- Reporting and CI: Test results are collected (Allure/Extent) and pipelines (Jenkins/Docker) run tests.

Typical runtime flow:
1. Test runner/CI triggers test execution (Maven, TestNG, JUnit).
2. Test setup initializes config, reporting and WebDriver or RestAssured client.
3. Test method reads data and calls API client or PageObject.
4. Client/Page performs actions and uses utilities for lower-level tasks.
5. Responses or UI states are converted to models and assertions run.
6. Teardown and reporting send results to report folders.

Where utilities are used:
- Config and logging are initialized at setup.
- Reusable helpers (waits, http helpers, json parsers) are invoked by clients and pages.
- Data providers feed tests from external resources (JSON/CSV).

This file is intended to be a concise reference for how a typical automation framework is structured and how responsibilities flow between layers.