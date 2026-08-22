# Migration & Sync Guide: 1.1 (TestNG) → 2.1 (BDD/Cucumber)

## Overview

2.1 is a **BDD/Cucumber adaptation** of the 1.1 TestNG framework. Both share the same core layers (RestClient, ConfigurationManager, utilities), but differ in test execution style and data-driven approach.

---

## Quick Reference: What Changed?

| Aspect | 1.1 (TestNG) | 2.1 (BDD/Cucumber) | Reason |
|---|---|---|---|
| **Test Execution** | `@Test` methods | Gherkin feature files + step defs | Better readability & stakeholder communication |
| **Data-Driven Testing** | `@DataProvider` | Scenario Outline + Examples | Eliminates JUnit dependency, aligns with Gherkin |
| **Setup/Teardown** | BaseTest + `@BeforeTest/@AfterTest` | Hooks + `@Before/@After` | PicoContainer DI for cleaner state management |
| **State Management** | BaseTest class fields | TestContext (injected) | Thread-safe, scenario-scoped state |
| **Test Runner** | TestNG XML suite | Cucumber Runner | Supports feature-based filtering (tags) |
| **Configuration** | Environment-based properties | Environment + Channel properties | Supports SCT, MCT-AND-CERT with MOB/EXT channels |
| **RestClient** | ✓ (same) | ✓ (reused from 1.1) | No changes needed |
| **Config Manager** | ✓ (same) | ✓ (enhanced for testdata paths) | Added testdata path resolution |
| **Utilities** | ✓ (same) | ✓ (reused from 1.1) | ExcelUtility, Constants, Enums shared |

---

## 1.1 → 2.1 Component Mapping

### Test Layer

**1.1 (TestNG):**
```java
@Test
public void testCreateUserSuccessfully() {
    // Arrange
    CreateUserRequest request = new CreateUserRequest("John", "john@example.com");
    
    // Act
    Response response = restClient.post("/users", request, true, true);
    
    // Assert
    assertEquals(response.getStatusCode(), 201);
}
```

**2.1 (BDD):**

Feature file:
```gherkin
Scenario: Create user successfully
  Given I have a user request with name "John" and email "john@example.com"
  When I create a new user
  Then the response status code should be 201
```

Step definition:
```java
@When("I create a new user")
public void createUser() {
    CreateUserRequest request = (CreateUserRequest) context.getData("userRequest");
    Response response = context.getRestClient().post("/users", request, true, true);
    context.setLastResponse(response);
}
```

### Setup & Teardown

**1.1 (TestNG) - BaseTest:**
```java
public class BaseTest {
    protected RestClient restClient;
    protected Properties prop;
    
    @BeforeTest
    public void setUp(String baseURI) {
        config = new ConfigurationManager();
        prop = config.initProp();
        restClient = new RestClient(prop, baseURI);
    }
}
```

**2.1 (BDD) - Hooks:**
```java
public class Hooks {
    private TestContext context;
    
    @Before
    public void beforeScenario(Scenario scenario) {
        ConfigurationManager config = new ConfigurationManager();
        Properties prop = config.initProp();
        RestClient restClient = new RestClient(prop, getBaseUri());
        context.setRestClient(restClient);
        // Authenticate
    }
}
```

### Data-Driven Testing

**1.1 (TestNG) - DataProvider:**
```java
@DataProvider(name = "userTestData")
public Object[][] getUserTestData() {
    List<Map<String, String>> data = ExcelUtility.readTestData(
        "src/test/resources/testdata.xlsx", 
        "Users"
    );
    // Convert to Object[][]
}

@Test(dataProvider = "userTestData")
public void testCreateUser(Map<String, String> userData) {
    // Test implementation
}
```

**2.1 (BDD) - Scenario Outline:**
```gherkin
Scenario Outline: Create users with different roles
  Given I have a user with name "<name>"
  When I create a new user with role "<role>"
  Then the response status code should be 201

  Examples:
    | name     | role  |
    | John     | admin |
    | Jane     | user  |
```

### State Management

**1.1 (TestNG) - BaseTest fields:**
```java
public class BaseTest {
    protected String token;
    protected Response lastResponse;
    protected Map<String, Object> testData = new HashMap<>();
}
```

**2.1 (BDD) - TestContext:**
```java
public class TestContext {
    private String token;
    private Response lastResponse;
    private Map<String, Object> sharedData = new HashMap<>();
    
    // Getters/setters
    public void setToken(String token) { this.token = token; }
    public String getToken() { return token; }
}

// Injected via PicoContainer into step definitions & hooks
public class UserStepDefinitions {
    private TestContext context;
    
    public UserStepDefinitions(TestContext context) {
        this.context = context;
    }
}
```

---

## Environment & Channel Structure (New in 2.1)

### Property Files

```
application-sct.properties          ← SCT environment
application-mct.properties          ← MCT-AND-CERT environment
```

### Test Data Organization

```
testdata/
├── SCT/
│   ├── MOB/          → Mobile test data for SCT
│   └── EXT/          → Web API test data for SCT
└── MCT-AND-CERT/
    ├── MOB/          → Mobile test data for MCT-AND-CERT
    └── EXT/          → Web API test data for MCT-AND-CERT
```

### Execution

```bash
# 1.1 (TestNG)
mvn clean test -Denv=dev

# 2.1 (BDD/Cucumber)
mvn clean test -Denv=SCT -Dtest.data.channel=MOB
```

---

## Shared Components (No Changes)

These components remain **identical** between 1.1 and 2.1:

### 1. RestClient
- HTTP method implementations (GET, POST, PUT, PATCH, DELETE)
- Request/response handling
- Auth header injection
- Logging integration

**Reuse Strategy:** Import directly from core layer; no modifications needed.

### 2. ConfigurationManager
- **Enhanced:** Added `getTestDataPath()` method for environment/channel-based paths
- Property file loading
- Environment selection

**Reuse Strategy:** Extend to support testdata path resolution.

### 3. Utilities & Constants
- ExcelUtility (test data reading)
- APIHttpStatus (HTTP status enums)
- Constants (endpoints, timeouts, etc.)
- Enums (Environment, Channel)

**Reuse Strategy:** Import directly; no modifications.

### 4. POJO Models
- Request classes (CreateUserRequest, UpdateUserRequest, etc.)
- Response classes (User, ApiResponse, etc.)
- Jackson/Lombok annotations

**Reuse Strategy:** Import directly.

---

## New Components in 2.1

### 1. TestContext
**Purpose:** Replace BaseTest fields; holds scenario-scoped state

**Location:** `src/test/java/com/qa/context/TestContext.java`

```java
public class TestContext {
    private RestClient restClient;
    private Response lastResponse;
    private String token;
    private ConfigurationManager configManager;
    private Map<String, Object> sharedData;
}
```

### 2. Hooks
**Purpose:** Setup/teardown via Cucumber hooks; initialize TestContext

**Location:** `src/test/java/com/qa/hooks/Hooks.java`

```java
@Before
public void beforeScenario(Scenario scenario) { ... }

@After
public void afterScenario(Scenario scenario) { ... }
```

### 3. Step Definitions
**Purpose:** Glue code mapping Gherkin steps to Java

**Location:** `src/test/java/com/qa/steps/*StepDefinitions.java`

```java
public class UserStepDefinitions {
    private TestContext context;
    
    @Given("I have a user ...")
    @When("I create ...")
    @Then("the response ...")
}
```

### 4. Feature Files
**Purpose:** Human-readable test scenarios

**Location:** `src/test/resources/features/*.feature`

```gherkin
Feature: User Management API
  Scenario: ...
  Scenario Outline: ...
```

### 5. Cucumber Runner
**Purpose:** Execute feature files

**Location:** `src/test/java/com/qa/runner/RunCucumberTests.java`

```java
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
public class RunCucumberTests { }
```

---

## Migration Steps

### Step 1: Analyze Existing Tests (1.1)

For each TestNG test class:
```java
public class UserAPITest extends BaseTest {
    @Test
    public void testCreateUser() { ... }
    
    @Test
    public void testGetUser() { ... }
}
```

### Step 2: Create Feature File (2.1)

```gherkin
Feature: User API
  Scenario: Create user successfully
    Given ...
    When ...
    Then ...
  
  Scenario: Get user by ID
    Given ...
    When ...
    Then ...
```

### Step 3: Create Step Definitions

```java
public class UserStepDefinitions {
    public UserStepDefinitions(TestContext context) { ... }
    
    @Given("...")
    public void step1() { ... }
    
    @When("...")
    public void step2() { ... }
    
    @Then("...")
    public void step3() { ... }
}
```

### Step 4: Update Hooks

```java
@Before
public void beforeScenario() {
    // Initialize context, authenticate, set environment
}
```

### Step 5: Run via Cucumber Runner

```bash
mvn clean test -Denv=SCT -Dtest.data.channel=MOB
```

---

## Dependency Updates (pom.xml)

### Remove (only if no other projects need TestNG)
```xml
<!-- If exclusively moving to BDD -->
<!-- <dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
</dependency> -->
```

### Add (required for Cucumber)
```xml
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>7.x.x</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-picocontainer</artifactId>
    <version>7.x.x</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-junit-platform-engine</artifactId>
    <version>7.x.x</version>
    <scope>test</scope>
</dependency>
```

---

## Advantages of 2.1 (BDD/Cucumber)

✓ **Readability:** Gherkin scenarios are business-friendly  
✓ **No JUnit Dependency:** DataProvider replaced by Scenario Outlines  
✓ **Better State Management:** PicoContainer DI via TestContext  
✓ **Environment Support:** SCT/MCT-AND-CERT with MOB/EXT channels  
✓ **Tag-Based Filtering:** @smoke, @critical, etc. for selective runs  
✓ **Allure Integration:** Step-level reporting and attachments  
✓ **Reusable Core:** Leverages 1.1's RestClient, Config, Utilities  

---

## When to Use 1.1 vs 2.1

| Use 1.1 (TestNG) | Use 2.1 (BDD) |
|---|---|
| Developer-focused tests | Stakeholder communication needed |
| Complex test logic | Clear step-by-step scenarios |
| No business stakeholder involvement | BDD culture in team |
| Pure Java preference | Gherkin preference |

**Note:** Both can coexist in the same project.

---

## Troubleshooting

### Issue: Step definitions not found
**Solution:** Ensure `glue` path in runner matches step package: `com.qa.steps`, `com.qa.hooks`

### Issue: TestContext not injected
**Solution:** Verify PicoContainer dependency in pom.xml and step constructor has `TestContext` parameter

### Issue: Test data not loading
**Solution:** Check `getTestDataPath()` returns correct path; verify Excel file exists at: `src/test/resources/testdata/{env}/{channel}/`

### Issue: Wrong environment loading
**Solution:** Set system property: `mvn test -Denv=SCT -Dtest.data.channel=MOB`

---

## Summary

2.1 **syncs** with 1.1's architecture (RestClient, Config, utilities) while adopting **BDD/Cucumber** for test execution and **Scenario Outlines** for data-driven testing. The environment & channel structure (SCT/MCT-AND-CERT, MOB/EXT) provides flexible test data organization for multiple deployment scenarios.

