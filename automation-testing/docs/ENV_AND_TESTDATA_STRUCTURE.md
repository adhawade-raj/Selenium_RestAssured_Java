# Environment & Test Data Structure for BDD Framework (2.1)

## Quick Reference

### Environments

```
SCT             → System Capability Test
MCT-AND-CERT    → Mobile Conformance Test & Certification (MCT & CERT share same test data)
```

### Channels (per Environment)

For each environment, test data is organized by channel:

```
MOB  → Mobile device/app test data
EXT  → External/Web API test data
```

---

## Configuration Structure

### Property Files Organization

```
src/test/resources/
├── application.properties           ← Default properties (fallback)
├── application-sct.properties       ← SCT environment config
└── application-mct.properties       ← MCT-AND-CERT environment config
```

### Sample application-sct.properties

```properties
# Environment identifier
env=SCT
test.data.env=SCT

# Channel selection (MOB or EXT)
test.data.channel=MOB

# Base URLs
api.base.url.sct=https://api-sct.example.com/v1
api.base.url.mct=https://api-mct.example.com/v1

# API Configuration
api.timeout=5000
api.retry.count=3
api.content.type=application/json

# Authentication
auth.endpoint=/api/auth/login
auth.username=sct_user@example.com
auth.password=${AUTH_PASSWORD}  # Use environment variable

# Headers
api.accept.type=application/json

# Reporting
report.path=./allure-results
log.level=INFO
```

---

## Test Data Directory Structure

```
src/test/resources/testdata/
│
├── SCT/                          ← System Capability Test environment
│   ├── MOB/                       ← Mobile test data for SCT
│   │   ├── users.xlsx
│   │   ├── auth.xlsx
│   │   ├── products.xlsx
│   │   └── workflows.xlsx
│   │
│   └── EXT/                       ← Web API test data for SCT
│       ├── users.xlsx
│       ├── auth.xlsx
│       ├── products.xlsx
│       └── workflows.xlsx
│
├── MCT-AND-CERT/                 ← Mobile Conformance Test & Certification environment
│   ├── MOB/                       ← Mobile test data for MCT-AND-CERT
│   │   ├── users.xlsx
│   │   ├── auth.xlsx
│   │   ├── products.xlsx
│   │   └── workflows.xlsx
│   │
│   └── EXT/                       ← Web API test data for MCT-AND-CERT
│       ├── users.xlsx
│       ├── auth.xlsx
│       ├── products.xlsx
│       └── workflows.xlsx
```

---

## Usage Examples

### 1. Running Tests for SCT Environment with Mobile Channel

```bash
# Via Maven CLI
mvn clean test -Denv=SCT -Dtest.data.channel=MOB

# Via environment variables
export ENV=SCT
export TEST_DATA_CHANNEL=MOB
mvn clean test
```

### 2. Running Tests for MCT-AND-CERT Environment with Web API Channel

```bash
mvn clean test -Denv=MCT-AND-CERT -Dtest.data.channel=EXT
```

### 3. ConfigurationManager Implementation

```java
public class ConfigurationManager {
    private Properties prop;
    private String env;        // SCT or MCT-AND-CERT
    private String channel;    // MOB or EXT
    
    public Properties initProp() {
        // Get environment from system properties, default to SCT
        String envName = System.getProperty("env", "SCT");
        String channelName = System.getProperty("test.data.channel", "MOB");
        
        prop = new Properties();
        String propFile = "application-" + envName.toLowerCase() + ".properties";
        InputStream input = getClass().getClassLoader()
            .getResourceAsStream(propFile);
        
        if (input != null) {
            prop.load(input);
        }
        
        this.env = envName;
        this.channel = channelName;
        
        return prop;
    }
    
    public String getTestDataPath() {
        // Returns path like: src/test/resources/testdata/SCT/MOB/
        return "src/test/resources/testdata/" + env + "/" + channel + "/";
    }
    
    public String getEnvironment() {
        return env;
    }
    
    public String getChannel() {
        return channel;
    }
}
```

### 4. Hook Example Using Environment

```java
public class Hooks {
    private TestContext context;
    private ConfigurationManager configManager;
    
    public Hooks(TestContext context) {
        this.context = context;
        this.configManager = new ConfigurationManager();
    }
    
    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("=== Starting Scenario: " + scenario.getName() + " ===");
        
        // Initialize properties
        configManager.initProp();
        
        System.out.println("Environment: " + configManager.getEnvironment());
        System.out.println("Channel: " + configManager.getChannel());
        System.out.println("Test Data Path: " + configManager.getTestDataPath());
        
        // Initialize RestClient with environment-specific base URL
        String baseUrl = getBaseUrlForEnvironment(configManager.getEnvironment());
        context.setRestClient(new RestClient(configManager.initProp(), baseUrl));
        
        // Authenticate user
        authenticateUser();
    }
    
    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("=== Completed Scenario: " + scenario.getName() + " ===");
        // Cleanup
    }
    
    private String getBaseUrlForEnvironment(String env) {
        if ("SCT".equalsIgnoreCase(env)) {
            return "https://api-sct.example.com/v1";
        } else if ("MCT-AND-CERT".equalsIgnoreCase(env)) {
            return "https://api-mct.example.com/v1";
        }
        return "https://api.example.com/v1";
    }
    
    private void authenticateUser() {
        // Authentication logic
    }
}
```

### 5. Loading Test Data Based on Environment & Channel

```java
public class UserStepDefinitions {
    private TestContext context;
    private ConfigurationManager configManager;
    
    public UserStepDefinitions(TestContext context) {
        this.context = context;
        this.configManager = new ConfigurationManager();
    }
    
    @Given("I load users from test data for current environment")
    public void loadUsersFromTestData() {
        String testDataPath = configManager.getTestDataPath();
        String testDataFile = testDataPath + "users.xlsx";
        
        List<Map<String, String>> userData = ExcelUtility.readTestData(
            testDataFile,
            "Users"
        );
        
        context.putData("testUsers", userData);
        System.out.println("Loaded " + userData.size() + " users from " + testDataFile);
    }
    
    @When("I create a user from test data")
    public void createUserFromTestData() {
        List<Map<String, String>> testUsers = 
            (List<Map<String, String>>) context.getData("testUsers");
        
        for (Map<String, String> userData : testUsers) {
            CreateUserRequest request = new CreateUserRequest();
            request.setName(userData.get("Name"));
            request.setEmail(userData.get("Email"));
            request.setMobile(userData.get("Mobile"));
            
            Response response = context.getRestClient()
                .post("/users", "application/json", request, true, true);
            
            context.setLastResponse(response);
            context.putData("createdUserId", response.jsonPath().getString("id"));
        }
    }
}
```

---

## Key Points

### Environment Variable Mapping

| Variable | Value | Usage |
|---|---|---|
| `env` | `SCT` or `MCT-AND-CERT` | Selects property file and testdata folder |
| `test.data.channel` | `MOB` or `EXT` | Selects test data subfolder within environment |

### Property File Naming Convention

```
application-{env_lowercase}.properties

Example:
  - application-sct.properties
  - application-mct-and-cert.properties
```

### Test Data Path Construction

```
src/test/resources/testdata/{env}/{channel}/

Example:
  - src/test/resources/testdata/SCT/MOB/
  - src/test/resources/testdata/SCT/EXT/
  - src/test/resources/testdata/MCT-AND-CERT/MOB/
  - src/test/resources/testdata/MCT-AND-CERT/EXT/
```

---

## MCT-AND-CERT Special Case

**Note:** MCT (Mobile Conformance Test) and CERT (Certification) environments share the same test data.
- Single folder: `MCT-AND-CERT/` contains both MOB and EXT data
- Both test scenarios (mobile conformance + certification) use the same datasets
- Ensures consistency and reduces test data maintenance overhead

---

## CI/CD Integration

### Jenkins Pipeline Example

```groovy
pipeline {
    agent any
    
    parameters {
        choice(name: 'ENVIRONMENT', choices: ['SCT', 'MCT-AND-CERT'], description: 'Select environment')
        choice(name: 'CHANNEL', choices: ['MOB', 'EXT'], description: 'Select channel')
    }
    
    stages {
        stage('Test') {
            steps {
                sh '''
                    mvn clean test \
                        -Denv=${ENVIRONMENT} \
                        -Dtest.data.channel=${CHANNEL}
                '''
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

### Docker Execution

```bash
# Run tests in specific environment
docker run -v $(pwd):/app maven:3.8-openjdk-11 \
  -w /app \
  mvn clean test -Denv=SCT -Dtest.data.channel=MOB
```

---

## Summary

| Aspect | SCT | MCT-AND-CERT |
|---|---|---|
| **Full Name** | System Capability Test | Mobile Conformance Test & Certification |
| **Property File** | `application-sct.properties` | `application-mct.properties` |
| **Test Data Folder** | `testdata/SCT/` | `testdata/MCT-AND-CERT/` |
| **Channels** | MOB, EXT | MOB, EXT (same data) |
| **API Base URL** | `api.base.url.sct` | `api.base.url.mct` |
| **Use Case** | Verify system capabilities | Verify mobile compliance |
| **Test Data Sharing** | Independent per channel | MCT & CERT share same data |

