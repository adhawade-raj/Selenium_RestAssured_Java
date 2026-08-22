# RestAssured API Automation Framework - TestNG Based Framework Guide

## Table of Contents
1. [Framework Architecture](#framework-architecture)
2. [Core Components](#core-components)
3. [Tech Stack](#tech-stack)
4. [Configuration Management](#configuration-management)
5. [RestClient Layer](#restclient-layer)
6. [Authentication & Prerequisites](#authentication--prerequisites)
7. [Test Execution Flow](#test-execution-flow)
8. [Request & Response Handling](#request--response-handling)
9. [Data-Driven Testing](#data-driven-testing)
10. [Best Practices](#best-practices)
11. [Common Code Patterns](#common-code-patterns)
12. [CI/CD Integration](#cicd-integration)
13. [Reporting & Artifacts](#reporting--artifacts)

---

## Framework Architecture

### Layered Architecture Overview

```
┌─────────────────────────────────────────┐
│      TestNG Test Classes                │  <- @Test methods in test layer
├─────────────────────────────────────────┤
│          BaseTest & Fixtures            │  <- @BeforeTest, @BeforeMethod, @AfterTest
├─────────────────────────────────────────┤
│   Authentication & Token Management    │  <- Token refresh, expiry handling
├─────────────────────────────────────────┤
│  RestClient (GET/POST/PUT/PATCH/DELETE)│  <- Request execution layer
├─────────────────────────────────────────┤
│   Configuration Manager & Properties   │  <- Env-specific config, URLs, credentials
├─────────────────────────────────────────┤
│    Utilities, Constants & Enums        │  <- Reusable logic & shared resources
└─────────────────────────────────────────┘
```

### Design Principles
- **Reusability**: Common utilities, constants, and enums shared across all layers
- **Readability**: Clear separation of concerns; easy to understand test flow
- **Maintainability**: Centralized configuration; minimal code duplication
- **Scalability**: Pure API-centric test design with TestNG for execution and data-driven testing
- **Reliability**: Token refresh, retry logic, and error handling for robust tests

---

## Core Components

### 1. Configuration Manager
**Purpose**: Centralize environment-specific settings

```
Responsibilities:
├── Read API base URLs
├── Store credentials (API keys, tokens)
├── Manage environment selection (dev, staging, prod)
├── Load properties from files or environment variables
└── Supply configuration to RestClient layer
```

**Common Implementation**: 
- `application.properties` or `application-env.properties` files
- Owner classes (e.g., `ConfigManager`, `AppConfig`) to encapsulate config
- Environment selection via system properties or Maven profiles

---

### 2. RestClient Layer
**Purpose**: Encapsulate Rest Assured request execution for all HTTP methods

**Supported Operations**:
- GET - Retrieve data
- POST - Create resources
- PUT - Full update of resources
- PATCH - Partial update of resources
- DELETE - Remove resources

**Key Responsibilities**:
- Build RequestSpecification with headers, auth, content-type
- Handle request/response logging
- Apply global headers and authentication
- Manage response parsing and error handling

---

### 3. Authentication & Prerequisites Layer
**Purpose**: Manage API authentication and token lifecycle

**Responsibilities**:
- Invoke authentication APIs before test execution
- Retrieve and cache access tokens
- Inject tokens into subsequent requests
- Handle token expiry and refresh logic
- Validate authentication prerequisites

**Token Handling**:
```
Test Execution
    ↓
Check Token Validity
    ↓
[Expired?] → Refresh Token → Store New Token
    ↓
Execute Test with Valid Token
```

---

### 4. BaseTest Class
**Purpose**: Manage common test setup and teardown

**Typical Responsibilities**:
- Initialize RestClient instances
- Set up common headers and authentication
- Configure logging and reporting
- Handle test preconditions and postconditions
- Manage test data setup/cleanup

---

### 5. Test Layer
**Purpose**: Contain actual test methods using TestNG annotations

**Test Characteristics**:
- TestNG test methods using `@Test` annotation
- `@BeforeTest` and `@BeforeMethod` for setup
- `@AfterTest` and `@AfterMethod` for teardown
- Focused on single API endpoint or workflow
- Use RestClient for API calls
- Assert response status, body, headers with Hamcrest/AssertJ matchers
- Capture logs automatically via Allure reporting

---

## Tech Stack

| Component | Technology | Version | Purpose |
|-----------|-----------|---------|---------|
| **Language** | Java | 1.8 (Java 8) | Test development (source/target set to 1.8 in pom.xml) |
| **API Testing** | RestAssured | 5.3.1 | HTTP request/response handling |
| **JSON Schema Validation** | rest-assured json-schema-validator | 5.3.1 | Contract/schema validation |
| **Testing Framework** | TestNG | 7.0.0 | Test execution and data-driven testing |
| **Reporting** | Allure (allure-testng, allure-rest-assured) | 2.22.1 / 2.23.0 | Test reporting, attachments and request/response logging |
| **JSON Serialization** | Jackson Databind | 2.15.2 | POJO ↔ JSON mapping |
| **POJO Boilerplate** | Lombok | 1.18.28 | Reduce boilerplate (getters/setters) |
| **JSON Parsing / Extraction** | json-path | 2.8.0 | JSON path expressions and assertions |
| **AOP / Weaving** | AspectJ Weaver | 1.9.19 | Runtime weaving (used by instrumentation/hooks) |
| **Reporting (optional)** | ExtentReports | 5.0.8 | Additional HTML reporting capability |
| **Excel / Data Management** | Apache POI | 3.9 | Read/write Excel files (present in pom.xml; consider upgrading) |
| **Build Tool** | Maven | 3.x | Dependency management & build |
| **Maven Plugins** | maven-surefire-plugin, maven-assembly-plugin | 2.20, 3.3.0 | Test execution and creating jar-with-dependencies |
| **CI Platforms** | Jenkins / Docker | - | Automated execution and containerized runs |

---

## Configuration Management

### Property File Structure

```properties
# application.properties or application-dev.properties

# API Configuration
api.base.url=https://api.example.com/v1
api.timeout=5000
api.retry.count=3

# Authentication
auth.endpoint=/api/auth/login
auth.username=user@example.com
auth.password=password

# Headers
api.content.type=application/json
api.accept.type=application/json

# Environment
env=dev
log.level=INFO
report.path=./allure-results
```

### Owner Class Pattern (Example)

```java
public class AppConfig {
    @Value("${api.base.url}")
    private String baseUrl;
    
    @Value("${api.timeout}")
    private int timeout;
    
    @Value("${auth.username}")
    private String username;
    
    // Getters
}
```

### BaseTest Configuration (Example - TestNG Pattern)

**From rest-assured-2024-framework-gorest-naveen project:**

```java
public class BaseTest {
    
    // Service URLs
    public static final String GOREST_ENDPOINT = "/public/v2/users";
    public static final String REQRES_ENDPOINT = "/api/users";
    public static final String CIRCUIT_ENDPOINT = "/api/f1";
    
    protected ConfigurationManager config;
    protected Properties prop;
    protected RestClient restClient;
    protected String baseURI;
    
    @Parameters({"baseURI"})
    @BeforeTest
    public void setUp(String baseURI) {
        // Add Allure filter for logging
        RestAssured.filters(new AllureRestAssured());
        
        // Initialize configuration
        config = new ConfigurationManager();
        prop = config.initProp();
        this.baseURI = baseURI;
    }
}
```

**Usage in Test Methods:**

```java
public class GetUserTest extends BaseTest {
    
    @BeforeMethod
    public void getUserSetup() {
        restClient = new RestClient(prop, baseURI);
    }
    
    @Test(enabled = true)
    @Description("Fetch all users with authorization")
    public void getAllUsersTest() {
        restClient.get(GOREST_ENDPOINT, true, true)
            .then().log().all()
            .assertThat().statusCode(APIHttpStatus.OK_200.getCode());
    }
    
    @Test()
    public void getUserWithQueryParamsTest() {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("name", "naveen");
        queryParams.put("status", "active");
        
        restClient.get(GOREST_ENDPOINT, queryParams, null, true, true)
            .then().log().all()
            .assertThat().statusCode(APIHttpStatus.OK_200.getCode());
    }
}
```

---

## RestClient Layer

### RestClient Implementation Pattern (From Project)

**Real implementation from rest-assured-2024-framework-gorest-naveen:**

```java
public class RestClient {
    
    private RequestSpecBuilder specBuilder;
    private Properties prop;
    private String baseURI;
    private boolean isAuthorizationHeaderAdded = false;
    
    public RestClient(Properties prop, String baseURI) {
        specBuilder = new RequestSpecBuilder();
        this.prop = prop;
        this.baseURI = baseURI;
    }
    
    public void addAuthorizationHeader() {
        if(!isAuthorizationHeaderAdded) {
            specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenId"));
            isAuthorizationHeaderAdded = true;
        }
    }
    
    private RequestSpecification createRequestSpec(boolean includeAuth) {
        specBuilder.setBaseUri(baseURI);
        if(includeAuth) { addAuthorizationHeader(); }
        return specBuilder.build();
    }
    
    // GET Request
    public Response get(String serviceUrl, boolean includeAuth, boolean log) {		
        if(log) {
            return RestAssured.given(createRequestSpec(includeAuth)).log().all()
                .when().get(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(includeAuth)).when().get(serviceUrl);
    }
    
    // GET with Query Parameters
    public Response get(String serviceUrl, Map<String, Object> queryParams, 
                       Map<String, String> headersMap, boolean includeAuth, boolean log) {
        specBuilder.setBaseUri(baseURI);
        if(includeAuth) { addAuthorizationHeader(); }
        if(headersMap != null) { specBuilder.addHeaders(headersMap); }
        if(queryParams != null) { specBuilder.addQueryParams(queryParams); }
        
        if(log) {
            return RestAssured.given(specBuilder.build()).log().all()
                .when().get(serviceUrl);
        }
        return RestAssured.given(specBuilder.build()).when().get(serviceUrl);
    }
    
    // POST Request
    public Response post(String serviceUrl, String contentType, Object requestBody, 
                         boolean includeAuth, boolean log) {
        specBuilder.setBaseUri(baseURI);
        specBuilder.setContentType(ContentType.JSON);
        if(includeAuth) { addAuthorizationHeader(); }
        if(requestBody != null) { specBuilder.setBody(requestBody); }
        
        if(log) {
            return RestAssured.given(specBuilder.build()).log().all()
                .when().post(serviceUrl);
        }
        return RestAssured.given(specBuilder.build()).when().post(serviceUrl);
    }
    
    // Similar methods for PUT, PATCH, DELETE...
}
```

### Supported HTTP Methods
- **GET** - Retrieve data with optional query params and headers
- **POST** - Create resources with request body
- **PUT** - Full update of resources
- **PATCH** - Partial update of resources  
- **DELETE** - Remove resources

---

## Authentication & Prerequisites

### Token Refresh Pattern

```java
public class AuthenticationManager {
    private String accessToken;
    private long tokenExpiryTime;
    private RestClient restClient;
    
    public String getValidToken() {
        if (isTokenExpired()) {
            refreshToken();
        }
        return accessToken;
    }
    
    private boolean isTokenExpired() {
        return System.currentTimeMillis() > tokenExpiryTime;
    }
    
    private void refreshToken() {
        LoginRequest request = new LoginRequest(username, password);
        Response response = restClient.post("/auth/login", request, new HashMap<>());
        LoginResponse loginResp = response.as(LoginResponse.class);
        
        this.accessToken = loginResp.getAccessToken();
        this.tokenExpiryTime = System.currentTimeMillis() + (loginResp.getExpiresIn() * 1000);
    }
}
```

### Prerequisite API Execution

```java
public class PrerequisiteAPI {
    public static User createUserPrerequisite(RestClient restClient) {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setName("Test User");
        userRequest.setEmail("test@example.com");
        
        Response response = restClient.post("/users", userRequest, getAuthHeaders());
        
        assert response.getStatusCode() == 201;
        return response.as(User.class);
    }
}
```

---

## Test Execution Flow

### Typical TestNG Test Execution Sequence

```
1. Test Framework Initialization
   └─ Maven loads project configuration
   └─ TestNG discovers test classes
   
2. BaseTest Setup (@BeforeTest)
   └─ Load Configuration (BaseUrl, credentials)
   └─ Initialize ConfigurationManager
   └─ Load Properties from config files
   
3. Test Method Setup (@BeforeMethod)
   └─ Initialize RestClient with properties
   └─ Perform Authentication (if required)
   └─ Store access token
   
4. Test Execution (@Test)
   ├─ Prepare test data (inline or from @DataProvider)
   ├─ Call RestClient method (GET/POST/PUT/PATCH/DELETE)
   ├─ Receive Response
   ├─ Assert status code, headers, body
   ├─ Log results via Allure filter (RestAssured.filters(new AllureRestAssured()))
   └─ Capture request/response automatically
   
5. Test Teardown (@AfterMethod)
   └─ Delete test data (cleanup prerequisites)
   └─ Close connections if needed
   
6. Report Generation
   └─ Allure processes test results and logs
   └─ Generate HTML report from allure-results/
```

### TestNG Test Execution (Real Example)

```java
public class GetUserTest extends BaseTest {
    
    @BeforeMethod
    public void getUserSetup() {
        restClient = new RestClient(prop, baseURI);
    }
    
    @Test(enabled = true)
    @Description("Test 1: Fetch all users with authorization")
    public void getAllUsersTest() {
        // Arrange - Nothing extra needed, RestClient handles all setup
        
        // Act - Call REST API
        restClient.get(GOREST_ENDPOINT, true, true)
            .then().log().all()
            // Assert - Verify response
            .assertThat().statusCode(APIHttpStatus.OK_200.getCode());
    }
    
    @Test()
    @Description("Test 2: Get users with query parameters")
    public void getUserWithQueryParamsTest() {
        // Arrange
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("name", "naveen");
        queryParams.put("status", "active");
        
        // Act
        restClient.get(GOREST_ENDPOINT, queryParams, null, true, true)
            .then().log().all()
            // Assert
            .assertThat().statusCode(APIHttpStatus.OK_200.getCode());
    }
    
    @AfterMethod
    public void tearDown() {
        // Cleanup if needed
    }
}
```

### Running Tests with TestNG

**TestNG XML Configuration:**
```xml
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<suite name="GoREST API Tests">
    <parameter name="baseURI" value="https://gorest.co.in"/>
    
    <test name="User API Tests">
        <classes>
            <class name="com.qa.gorest.tests.GetUserTest"/>
            <class name="com.qa.gorest.tests.CreateUserTest"/>
        </classes>
    </test>
</suite>
```

**Maven Command:**
```bash
mvn clean test -Dsuitexml=src/test/resources/testrunners/testng_regression.xml
```

---

## Request & Response Handling

### POJO with Lombok

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String name;
    private String email;
    private String phone;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
}
```

### Jackson Serialization/Deserialization

```java
// POJO to JSON
ObjectMapper mapper = new ObjectMapper();
String jsonString = mapper.writeValueAsString(userRequest);

// JSON to POJO
User user = mapper.readValue(jsonString, User.class);

// Direct deserialization from RestAssured Response
User user = response.as(User.class);
```

### Response Handling Patterns

```java
Response response = restClient.post("/users", userRequest, headers);

// Extract status code
int statusCode = response.getStatusCode();

// Extract headers
String contentType = response.getHeader("Content-Type");

// Extract body as String
String bodyAsString = response.getBody().asString();

// Extract body as POJO
User user = response.as(User.class);

// Extract JSON path value
String userId = response.jsonPath().getString("data.id");

// Extract with JSONPath
List<String> emails = response.jsonPath().getList("users.email");
```

### JSON Schema Validation

```java
public void validateResponseSchema() {
    String schemaPath = "classpath:schemas/user-response-schema.json";
    
    response
        .then()
        .assertThat()
        .body(matchesJsonSchemaInClasspath(schemaPath));
}
```

---

## Data-Driven Testing

### Excel Utility Pattern

```java
public class ExcelUtility {
    public static List<Map<String, String>> readTestData(String filePath, String sheetName) {
        List<Map<String, String>> testData = new ArrayList<>();
        
        // Read Excel file
        Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath));
        Sheet sheet = workbook.getSheet(sheetName);
        
        // Extract headers
        Row headerRow = sheet.getRow(0);
        List<String> headers = new ArrayList<>();
        for (Cell cell : headerRow) {
            headers.add(cell.getStringCellValue());
        }
        
        // Extract data rows
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            Map<String, String> rowData = new HashMap<>();
            for (int j = 0; j < headers.size(); j++) {
                rowData.put(headers.get(j), row.getCell(j).getStringCellValue());
            }
            testData.add(rowData);
        }
        
        return testData;
    }
}
```

### DataProvider Example

```java
@DataProvider(name = "userTestData")
public Object[][] getUserTestData() {
    List<Map<String, String>> data = ExcelUtility.readTestData(
        "src/test/resources/testdata.xlsx", 
        "Users"
    );
    
    Object[][] dataArray = new Object[data.size()][1];
    for (int i = 0; i < data.size(); i++) {
        dataArray[i][0] = data.get(i);
    }
    return dataArray;
}

@Test(dataProvider = "userTestData")
public void testCreateMultipleUsers(Map<String, String> testData) {
    CreateUserRequest request = new CreateUserRequest();
    request.setName(testData.get("Name"));
    request.setEmail(testData.get("Email"));
    
    Response response = restClient.post("/users", request, getAuthHeaders());
    assertEquals(response.getStatusCode(), 201);
}
```

---

## Best Practices

### 1. **Centralize Configuration**
```
✓ Use ConfigManager/Owner classes for all settings
✓ Environment-specific property files (dev, staging, prod)
✓ Store credentials in secure vaults (not in code)
✗ Don't hardcode URLs, credentials, or timeouts
```

### 2. **Keep Tests Small & Focused**
```
✓ One test = one API endpoint or workflow
✓ Clear test names: testCreateUserWithValidEmail()
✓ Arrange-Act-Assert (AAA) pattern
✗ Don't create massive tests with multiple endpoints
```

### 3. **Reuse RestClient Methods**
```
✓ Create helper methods in RestClient for common patterns
✓ Build RequestSpecification once, reuse across methods
✓ Leverage RequestSpec and ResponseSpec for validation
✗ Don't duplicate request/response logic in every test
```

### 4. **Data-Driven Testing**
```
✓ Use DataProviders for multiple test scenarios
✓ Externalize test data (Excel, CSV, JSON)
✓ Parameterize tests for different inputs
✗ Don't hardcode test data in test methods
```

### 5. **Handle Flaky Tests**
```
✓ Use Awaitility for async operations
✓ Implement retry logic for transient failures
✓ Set appropriate timeouts
✗ Don't ignore intermittent test failures
```

### 6. **Token & Auth Management**
```
✓ Centralize token refresh logic
✓ Cache tokens to avoid repeated authentication
✓ Handle token expiry gracefully
✗ Don't pass credentials in every request
```

### 7. **Logging & Debugging**
```
✓ Log all requests/responses
✓ Use log levels (INFO, DEBUG) appropriately
✓ Capture logs in Allure reports
✗ Don't rely on console output alone
```

### 8. **Error Handling**
```
✓ Assert status codes and error messages
✓ Validate error response structure
✓ Log failures with context
✗ Don't assume all requests succeed
```

---

## Common Code Patterns

### Pattern 1: Simple GET Request

```java
@Test
public void testGetUserById() {
    // Arrange
    int userId = 123;
    Map<String, String> headers = getAuthHeaders();
    
    // Act
    Response response = restClient.get("/users/" + userId, headers);
    
    // Assert
    assertEquals(response.getStatusCode(), 200);
    User user = response.as(User.class);
    assertEquals(user.getId(), userId);
}
```

### Pattern 2: POST with JSON Body

```java
@Test
public void testCreateUserWithValidData() {
    // Arrange
    CreateUserRequest userRequest = new CreateUserRequest(
        "Jane Doe", 
        "jane@example.com", 
        "1234567890"
    );
    
    // Act
    Response response = restClient.post(
        "/users", 
        userRequest, 
        getAuthHeaders()
    );
    
    // Assert
    assertEquals(response.getStatusCode(), 201);
    User createdUser = response.as(User.class);
    assertNotNull(createdUser.getId());
}
```

### Pattern 3: PUT/PATCH Update

```java
@Test
public void testUpdateUserEmail() {
    // Arrange
    int userId = 123;
    UpdateUserRequest updateRequest = new UpdateUserRequest();
    updateRequest.setEmail("newemail@example.com");
    
    // Act
    Response response = restClient.patch(
        "/users/" + userId, 
        updateRequest, 
        getAuthHeaders()
    );
    
    // Assert
    assertEquals(response.getStatusCode(), 200);
    User updatedUser = response.as(User.class);
    assertEquals(updatedUser.getEmail(), "newemail@example.com");
}
```

### Pattern 4: DELETE Request

```java
@Test
public void testDeleteUserById() {
    // Arrange
    int userId = 123;
    
    // Act
    Response response = restClient.delete("/users/" + userId, getAuthHeaders());
    
    // Assert
    assertEquals(response.getStatusCode(), 204);
}
```

### Pattern 5: Async Operations with Awaitility

```java
@Test
public void testAsyncJobCompletion() {
    // Initiate async job
    Response jobResponse = restClient.post("/jobs", jobRequest, getAuthHeaders());
    String jobId = jobResponse.jsonPath().getString("id");
    
    // Wait for job completion
    Awaitility.await()
        .atMost(Duration.ofSeconds(30))
        .pollInterval(Duration.ofSeconds(2))
        .until(() -> isJobCompleted(jobId));
}

private boolean isJobCompleted(String jobId) {
    Response response = restClient.get("/jobs/" + jobId, getAuthHeaders());
    return response.jsonPath().getString("status").equals("COMPLETED");
}
```

### Pattern 6: Query Parameters

```java
@Test
public void testGetUsersWithPagination() {
    // With query parameters
    Response response = restClient
        .given()
        .queryParam("page", 1)
        .queryParam("limit", 10)
        .header("Authorization", "Bearer " + token)
        .get("/users")
        .then()
        .log().all()
        .extract().response();
    
    assertEquals(response.getStatusCode(), 200);
}
```

### Pattern 7: Error Handling

```java
@Test
public void testCreateUserWithDuplicateEmail() {
    // First user creation
    CreateUserRequest userRequest = new CreateUserRequest("John", "john@example.com", "123");
    restClient.post("/users", userRequest, getAuthHeaders());
    
    // Attempt duplicate creation
    Response response = restClient.post("/users", userRequest, getAuthHeaders());
    
    // Assert error response
    assertEquals(response.getStatusCode(), 409);
    String errorMessage = response.jsonPath().getString("message");
    assertTrue(errorMessage.contains("already exists"));
}
```

---

## CI/CD Integration

### Jenkins Pipeline Example

```groovy
pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test -Denv=dev'
            }
        }
        
        stage('Report') {
            steps {
                sh 'mvn allure:report'
            }
        }
    }
    
    post {
        always {
            allure([
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'allure-results']]
            ])
        }
    }
}
```

### Maven Execution

```bash
# Run all tests
mvn clean test

# Run specific test class
mvn test -Dtest=UserAPITest

# Run with specific environment
mvn test -Denv=staging

# Run with specific TestNG suite
mvn test -DsuiteXmlFile=testng.xml

# Generate Allure report
mvn allure:report
```

### Docker Execution

```dockerfile
FROM maven:3.8-openjdk-11

WORKDIR /app

COPY . .

RUN mvn clean install

CMD ["mvn", "test", "-Denv=prod"]
```

---

## Reporting & Artifacts

### Allure Configuration

```xml
<!-- pom.xml -->
<dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-testng</artifactId>
    <version>2.20.0</version>
</dependency>
```

### Allure Annotations in Tests

```java
@Feature("User Management")
@Story("User CRUD Operations")
public class UserAPITest extends BaseTest {
    
    @Test
    @Description("Create a new user with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateUserSuccessfully() {
        // Test code
    }
    
    @Step("Creating user with email: {email}")
    private void createUser(String email) {
        // Step implementation
    }
}
```

### Allure Report Generation

```bash
# Generate HTML report
mvn allure:report

# View report
mvn allure:serve
```

### Report Directory Structure

```
project-root/
├── allure-results/          # Raw Allure test results
│   ├── *.json               # Test execution data
│   └── *.txt                # Logs and attachments
├── allure-report/           # Generated HTML report
│   ├── index.html
│   ├── css/
│   └── js/
└── target/
    └── allure-results/      # Maven build results
```

---

## Utilities & Helpers

### Logging Utility

```java
public class Logger {
    private static final org.apache.log4j.Logger logger = LogManager.getLogger();
    
    public static void info(String message) {
        logger.info(message);
    }
    
    public static void error(String message, Exception e) {
        logger.error(message, e);
    }
}
```

### Random Data Utility

```java
public class RandomDataGenerator {
    public static String generateEmail() {
        return "user" + System.currentTimeMillis() + "@example.com";
    }
    
    public static String generatePhoneNumber() {
        return "9" + RandomUtils.nextInt(9000000, 9999999);
    }
}
```

### Common Constants

```java
public class APIConstants {
    public static final String CREATE_USER = "/users";
    public static final String GET_USER = "/users/{id}";
    public static final String UPDATE_USER = "/users/{id}";
    public static final String DELETE_USER = "/users/{id}";
    
    public static final int TIMEOUT_MS = 5000;
    public static final int RETRY_COUNT = 3;
}
```

### Retry Logic Utility

```java
public class RetryableAPI {
    public static <T> T executeWithRetry(
        Supplier<T> apiCall, 
        int maxRetries, 
        long delayMs
    ) {
        for (int i = 0; i < maxRetries; i++) {
            try {
                return apiCall.get();
            } catch (Exception e) {
                if (i == maxRetries - 1) throw e;
                try { Thread.sleep(delayMs); } catch (InterruptedException ie) {}
            }
        }
        return null;
    }
}
```

---

## Quick Reference Cheat Sheet

| Task | Code |
|------|------|
| Create RestClient | `new RestClient(baseUrl)` |
| GET request | `restClient.get(endpoint, headers)` |
| POST request | `restClient.post(endpoint, body, headers)` |
| Put request | `restClient.put(endpoint, body, headers)` |
| PATCH request | `restClient.patch(endpoint, body, headers)` |
| DELETE request | `restClient.delete(endpoint, headers)` |
| Extract response status | `response.getStatusCode()` |
| Extract as POJO | `response.as(UserClass.class)` |
| Extract JSON path | `response.jsonPath().getString("path.to.value")` |
| Assert status | `assertEquals(response.getStatusCode(), 200)` |
| Set auth header | `.header("Authorization", "Bearer " + token)` |
| Add query param | `.queryParam("key", "value")` |
| Log all | `.log().all()` |
| Read Excel data | `ExcelUtility.readTestData(file, sheet)` |
| Wait for condition | `Awaitility.await().until(() -> condition)` |
| Fresh token | `authManager.getValidToken()` |

---

## Troubleshooting

### Common Issues

| Issue | Solution |
|-------|----------|
| Token expired | Implement token refresh in AuthenticationManager |
| Flaky tests | Use Awaitility for async operations |
| Timeout errors | Increase timeout in Config or RestClient |
| Serialization errors | Verify POJO getters/setters and Jackson config |
| SSL certificate errors | Disable SSL checks (dev only) or provide certificates |
| Port conflicts | Change port in config or stop existing services |
| Data conflicts | Use unique data generators or cleanup after tests |

---

## References & Resources

- RestAssured GitHub: https://github.com/rest-assured/rest-assured
- RestAssured Documentation: http://rest-assured.io/
- Allure Documentation: https://docs.qameta.io/allure/
- TestNG Documentation: https://testng.org/
- Jackson Documentation: https://github.com/FasterXML/jackson
- Maven Guide: https://maven.apache.org/guides/

---

**Document Version**: 1.0  
**Last Updated**: 2026-08-22  
**Framework**: RestAssured 5.x + Java + TestNG/BDD Cucumber
