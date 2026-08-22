# Jenkins QA/SDET Guide
## 20 Practical, Scenario-Based Questions + Jenkinsfile Scripts | 2026 Preparation

**How to use this:** Focus on explaining the Jenkins concepts from a QA/SDET perspective: automation execution, Maven/Gradle, TestNG/JUnit, environments, browser/grid execution, reports, failures and CI troubleshooting.

---

## 📋 20 Key Questions & Answers

### 1. How would you create a Jenkins job to execute a Java Selenium automation framework?

**Key Concept:** Explain the flow: Git checkout → build/dependency resolution → execute TestNG/JUnit → generate reports → publish/archive artifacts.

**Example:**
```bash
mvn clean test
```

**Expected Answer:**
- Job type: Pipeline or Freestyle
- Checkout source code from Git
- Build with Maven (dependency resolution)
- Execute tests using TestNG/JUnit
- Generate reports (JUnit XML, screenshots, logs)
- Archive/publish artifacts for analysis

---

### 2. Write a basic Jenkinsfile for Java + Selenium + TestNG

**Key Concept:** Be able to write a simple Declarative Pipeline.

**Example:**
```groovy
pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps { 
                checkout scm 
            }
        }
        stage('Build') {
            steps { 
                sh 'mvn clean compile' 
            }
        }
        stage('Test') {
            steps { 
                sh 'mvn test' 
            }
        }
    }
}
```

**Key Points:**
- Use declarative syntax for clarity
- Each stage should have a clear responsibility
- Always use version control for Jenkinsfile

---

### 3. What is a Jenkinsfile and why do you use it?

**Key Concept:** A Jenkinsfile is the pipeline definition stored as code, normally in Git. It is version-controlled, reviewable and keeps CI logic with the automation project.

**Answer:**
- Jenkinsfile is a text file containing Jenkins pipeline definition
- Stored in Git repository (same as your code)
- Enables "Pipeline as Code" approach
- Benefits:
  - Version control for CI/CD logic
  - Code review process for pipeline changes
  - Easy to reproduce and troubleshoot
  - No manual UI configuration needed
  - Supports branching strategies

---

### 4. Freestyle Job vs Pipeline — which would you choose for an SDET project?

**Key Concept:** Know both, but prefer Pipeline as Code for modern automation because the pipeline is version-controlled and easier to maintain, review and evolve.

**Comparison:**

| Aspect | Freestyle Job | Pipeline |
|--------|---------------|----------|
| **Version Control** | No | Yes (Jenkinsfile in Git) |
| **Code Review** | Manual/UI changes | Git PR review |
| **Maintainability** | Harder to track changes | Easy to audit |
| **Complexity** | Simple jobs only | Complex workflows |
| **Best For** | Simple tasks | SDET/automation projects |

**Recommendation:** Choose **Pipeline** for SDET projects due to better maintainability and version control.

---

### 5. Declarative vs Scripted Pipeline?

**Key Concept:** Declarative uses structured syntax such as pipeline/stages/steps and is easier to maintain. Scripted is Groovy-based and gives more programming flexibility for complex dynamic logic.

**Comparison:**

| Feature | Declarative | Scripted |
|---------|-------------|----------|
| **Syntax** | Structured (pipeline/stages/steps) | Groovy code |
| **Readability** | Easy to read and understand | Requires Groovy knowledge |
| **Maintainability** | Better for standard workflows | Better for complex logic |
| **Error Handling** | Built-in with `post` section | Manual error handling |
| **Learning Curve** | Beginner-friendly | Intermediate/Advanced |

**Recommendation:** Use **Declarative** for most SDET projects; fall back to **Scripted** only for complex dynamic logic.

---

### 6. Your tests pass locally but fail on Jenkins. How will you troubleshoot?

**Key Concept:** Start with the Jenkins console log and compare environments: Git commit, JDK, Maven/Gradle, browser/driver versions, OS, environment variables, application URL, credentials, filesystem paths, permissions, network/proxy, headless mode, timing and Jenkins agent.

**Troubleshooting Checklist:**

1. **Jenkins Console Log**
   - Check for build failure, test failure, or script error
   - Look for stack traces and error messages

2. **Compare Environments**
   - JDK version: `java -version`
   - Maven/Gradle version: `mvn -version`
   - Browser driver versions (ChromeDriver, GeckoDriver)
   - OS differences (Windows vs Linux)
   - Environment variables

3. **Check Framework Configuration**
   - Application URL (dev, QA, UAT)
   - Browser capabilities (headless vs headed)
   - Wait times and timeouts
   - Test data availability

4. **Agent/Network**
   - Jenkins agent OS and configuration
   - Network access to application
   - Proxy settings
   - VPN connectivity

5. **Reproduce Locally**
   - Run same Git commit locally
   - Use same JDK/Maven versions
   - Try headless mode locally
   - Check file paths and permissions

---

### 7. Why can Selenium work locally but fail in Jenkins?

**Key Concept:** Common causes: different browser/driver versions, headless execution, browser not installed, different screen size/OS, missing environment variables, incorrect paths, permissions, proxy/network restrictions, timing issues or a different application environment.

**Common Causes & Solutions:**

| Issue | Root Cause | Solution |
|-------|-----------|----------|
| **Browser not found** | ChromeDriver/GeckoDriver not installed on Jenkins agent | Install browser or use containerized approach |
| **Headless mode failure** | Tests not compatible with headless execution | Add headless capabilities or use VNC |
| **Different screen size** | Local: 1920x1080; Jenkins: 1024x768 | Set explicit window size in code |
| **Missing environment variables** | Jenkins doesn't have USER, HOME, PATH set correctly | Explicitly set variables in Jenkinsfile |
| **Incorrect file paths** | Windows: C:\temp vs Linux: /tmp | Use relative paths or Jenkins workspace |
| **Timing issues** | Network latency different on Jenkins | Review and update wait strategies |
| **Network/Proxy** | Jenkins agent behind proxy | Configure proxy in Maven and browser |
| **Permissions** | Jenkins user cannot write to filesystem | Ensure correct permissions on workspace |

---

### 8. How do you pass environment and browser from Jenkins to your automation framework?

**Key Concept:** Use Jenkins parameters and pass them as Java system properties.

**Example:**
```bash
mvn clean test -Denv=QA -Dbrowser=chrome
```

**Java Code:**
```java
String env = System.getProperty("env");
String browser = System.getProperty("browser");
```

**Best Practices:**
- Use Maven `-D` flag for system properties
- Read properties in framework using `System.getProperty()`
- Default values if not provided: `System.getProperty("browser", "chrome")`
- Log the parameters at test start for troubleshooting

---

### 9. Write a Jenkinsfile with environment and browser parameters

**Key Concept:** Understand Jenkins parameters and how they connect to the test framework.

**Example:**
```groovy
pipeline {
    agent any
    
    parameters {
        choice(name: 'ENV', choices: ['QA', 'UAT', 'PROD'])
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'safari'])
    }
    
    stages {
        stage('Test') {
            steps {
                sh "mvn clean test -Denv=${params.ENV} -Dbrowser=${params.BROWSER}"
            }
        }
    }
    
    post {
        always {
            junit 'target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'target/screenshots/**', allowEmptyArchive: true
        }
    }
}
```

**Key Points:**
- `parameters` block defines pipeline inputs
- `${params.ENV}` accesses parameter value
- Parameters passed as Maven system properties
- Framework reads via `System.getProperty()`

---

### 10. How would you execute only smoke or regression tests from Jenkins?

**Key Concept:** Expose the suite as a parameter or maintain separate TestNG XML files.

**Option 1: Using TestNG Suite XML**
```bash
mvn clean test -DsuiteXmlFile=smoke.xml
mvn clean test -DsuiteXmlFile=regression.xml
```

**Option 2: Jenkinsfile with Suite Parameter**
```groovy
pipeline {
    agent any
    
    parameters {
        choice(name: 'SUITE', choices: ['smoke', 'regression', 'sanity'])
    }
    
    stages {
        stage('Execute Tests') {
            steps {
                sh "mvn clean test -DsuiteXmlFile=${params.SUITE}.xml"
            }
        }
    }
}
```

**Option 3: Using TestNG Groups**
```bash
mvn clean test -Dgroups="smoke"
mvn clean test -Dgroups="regression"
```

**TestNG XML Example (smoke.xml):**
```xml
<suite name="Smoke Tests">
    <test name="Smoke">
        <classes>
            <class name="tests.LoginTest" />
            <class name="tests.DashboardTest" />
        </classes>
    </test>
</suite>
```

---

### 11. How do you run Selenium tests on different browsers in parallel?

**Key Concept:** Use Jenkins parallel stages and/or Selenium Grid. Each execution should have an isolated driver and test data.

**Example: Parallel Stages in Jenkinsfile**
```groovy
pipeline {
    agent any
    
    stages {
        stage('Browsers') {
            parallel {
                stage('Chrome') {
                    steps { 
                        sh 'mvn test -Dbrowser=chrome' 
                    }
                }
                stage('Firefox') {
                    steps { 
                        sh 'mvn test -Dbrowser=firefox' 
                    }
                }
                stage('Safari') {
                    steps { 
                        sh 'mvn test -Dbrowser=safari' 
                    }
                }
            }
        }
    }
}
```

**Key Considerations:**
- Each parallel stage needs isolated WebDriver instance
- Separate test data to avoid conflicts
- Use thread-local variables for driver management
- Monitor Jenkins executor availability
- Parallel execution requires sufficient resources

**Best Practices:**
- Ensure test isolation (no shared state)
- Use unique browser profiles per thread
- Monitor resource consumption (CPU, memory)
- Consider Selenium Grid for distributed execution

---

### 12. What is a Jenkins Agent and why is it important for SDET?

**Key Concept:** The controller orchestrates the pipeline while agents execute the work. Agents can be labeled for capabilities such as Linux, Docker or browser infrastructure. This allows tests to run on appropriate machines.

**Jenkins Architecture:**
- **Controller (Master):** Orchestrates pipelines, manages schedules, stores configurations
- **Agents (Slaves):** Execute the actual pipeline steps (build, test, deploy)

**Why Important for SDET:**
- **Environment Flexibility:** Different agents can have different OS, JDK, browsers
- **Scalability:** Run multiple jobs in parallel across different agents
- **Browser Infrastructure:** Dedicated agent with Chrome, Firefox, Safari installed
- **Isolation:** Test failures on one agent don't affect others
- **Docker Support:** Use containerized agents for consistency

**Labels Example:**
```groovy
pipeline {
    agent {
        label 'selenium'  // Run on agent labeled "selenium"
    }
}
```

---

### 13. What is agent any? When would you use a label?

**Key Concept:** Use agent any when any suitable executor can run the job. Use a label when the test needs a specific environment, for example a Selenium/Docker/Linux agent.

**agent any:**
- Runs job on any available Jenkins executor
- Good for simple builds with no special requirements
- Used for Maven builds, dependency checks

**agent { label 'label-name' }:**
- Runs job only on agents with specific label
- Good for Selenium tests (need browser)
- Good for Docker builds (need Docker)
- Good for specific OS requirements (Linux, Windows)

**Examples:**
```groovy
// Use any executor
pipeline {
    agent any
    stages { ... }
}

// Use specific labeled agent
pipeline {
    agent {
        label 'selenium'  // Must have Selenium/browser capability
    }
    stages { ... }
}

// Multiple labels (AND logic)
pipeline {
    agent {
        label 'linux && docker'  // Must have both Linux and Docker
    }
    stages { ... }
}
```

---

### 14. How do you publish test reports in Jenkins?

**Key Concept:** Generate the report during the test run and publish/archive it in the post section. For JUnit-style results, Jenkins can consume XML results; other report tools can be integrated through plugins or generated HTML artifacts.

**Example:**
```groovy
pipeline {
    agent any
    
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
    
    post {
        always {
            junit 'target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'target/screenshots/**', allowEmptyArchive: true
        }
    }
}
```

**Common Report Types:**

1. **JUnit Reports (Built-in):**
   ```groovy
   junit 'target/surefire-reports/*.xml'
   ```

2. **HTML Reports (with plugin):**
   ```groovy
   publishHTML([
       reportDir: 'target/html-report',
       reportFiles: 'index.html',
       reportName: 'HTML Report'
   ])
   ```

3. **Allure Reports:**
   ```groovy
   allure results: [[path: 'target/allure-results']]
   ```

4. **Extent Reports:**
   ```groovy
   archiveArtifacts artifacts: 'target/extent-report/**'
   ```

5. **Screenshots & Artifacts:**
   ```groovy
   archiveArtifacts artifacts: 'target/screenshots/**', allowEmptyArchive: true
   ```

---

### 15. What is the difference between build failure and test failure?

**Key Concept:** A test failure means the automation/application verification failed. A build failure can happen earlier because of compilation errors, dependency resolution, infrastructure problems or pipeline/script errors. Your diagnosis should identify which layer failed.

**Comparison:**

| Aspect | Build Failure | Test Failure |
|--------|---------------|--------------|
| **When** | Before tests run | During test execution |
| **Cause** | Compilation error, dependency issue, script error | Application/assertion failure |
| **Example** | `[ERROR] COMPILATION ERROR` | `AssertionError: expected true but got false` |
| **Layer** | Code/Build framework layer | Test/Application layer |
| **Impact** | No tests can run | Tests ran but assertions failed |

**Diagnosis Steps:**

1. **Check Console Output**
   - Compilation error? → Build failure
   - Test failed? → Test failure
   - Timeout? → Could be either

2. **Identify Layer**
   - `[ERROR] BUILD FAILURE` → Build layer
   - `Tests run: 10, Failures: 2` → Test layer
   - Network error → Infrastructure layer

3. **Root Cause Analysis**
   - Build: Fix code/dependencies
   - Test: Fix test logic or application issue
   - Infrastructure: Fix Jenkins/agent/network

---

### 16. How would you handle screenshots when a Selenium test fails in Jenkins?

**Key Concept:** Capture the screenshot in the framework's failure handling/listener and store it under the workspace. Archive the screenshot as a Jenkins artifact or attach it through the reporting solution so the failure can be investigated.

**Example: TestNG Listener**
```java
public class ScreenshotListener implements ITestListener {
    
    private WebDriver driver;
    private String screenshotPath = "target/screenshots";
    
    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        String filename = screenshotPath + "/" + testName + "_" + 
                         System.currentTimeMillis() + ".png";
        
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(filename));
            System.out.println("Screenshot saved: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

**Jenkinsfile Configuration:**
```groovy
post {
    failure {
        archiveArtifacts artifacts: 'target/screenshots/**', allowEmptyArchive: true
        // Optional: Attach to report
        publishHTML([
            reportDir: 'target/screenshots',
            reportFiles: '*.png',
            reportName: 'Failure Screenshots'
        ])
    }
}
```

**Best Practices:**
- Use unique filenames (timestamp or test name)
- Store in Jenkins workspace (`target/screenshots`)
- Archive as Jenkins artifact
- Include in reports or email notifications
- Consider video recording for flaky tests

---

### 17. How do you handle credentials in Jenkins?

**Key Concept:** Do not hardcode passwords or tokens in Jenkinsfiles or Git. Store secrets in Jenkins Credentials and bind/use them at runtime. Be careful not to print secret values in console logs.

**Wrong (Never do this):**
```groovy
// WRONG - Hardcoded credentials
sh 'mvn test -Dusername=admin -Dpassword=123456'
```

**Correct Approach:**
```groovy
pipeline {
    agent any
    
    environment {
        // Bind credentials to environment variables
        APP_CREDS = credentials('app-username-password')
    }
    
    stages {
        stage('Test') {
            steps {
                sh '''
                    mvn test \
                    -Dusername=${APP_CREDS_USR} \
                    -Dpassword=${APP_CREDS_PSW}
                '''
            }
        }
    }
}
```

**Steps to Store Credentials:**
1. Jenkins Dashboard → Manage Jenkins → Credentials
2. Add credentials (username/password, API token, etc.)
3. Note the credential ID
4. Use in Jenkinsfile via `credentials('credential-id')`

**Security Best Practices:**
- Never print credentials in logs (Jenkins masks by default)
- Rotate credentials regularly
- Use API tokens instead of passwords
- Restrict credential access to specific pipelines
- Audit credential usage

---

### 18. What would you check if Jenkins cannot access your QA/UAT application but your laptop can?

**Key Concept:** Check Jenkins agent network route, VPN, proxy, firewall/allow-list, DNS, certificates, application URL, credentials and whether the agent is inside the required network. The important point is that Jenkins may execute from a different machine/network than your laptop.

**Troubleshooting Checklist:**

| Check | Command | Solution |
|-------|---------|----------|
| **Network Access** | `ping <app-url>` | Check firewall/network rules |
| **DNS Resolution** | `nslookup <app-url>` | Verify DNS server on agent |
| **Port Access** | `telnet <app-url> 443` | Check firewall/allow-list |
| **VPN Connection** | Check VPN status | Ensure agent is on VPN |
| **Proxy Settings** | Check Maven `settings.xml` | Configure proxy if needed |
| **Certificates** | Check SSL cert | May need to add to keystore |
| **Environment** | `env` or `set` | Check PATH, HOME, etc. |
| **User Permissions** | Check Jenkins user permissions | May need elevated rights |

**Key Insight:**
Jenkins runs on **different machine** (agent) with **different network** than your laptop. The application may have access restrictions.

---

### 19. A Jenkins build is flaky: sometimes pass, sometimes fail. How do you investigate?

**Key Concept:** Check timing/synchronization, waits, parallel execution, shared test data, driver lifecycle, environment stability, browser/grid health and test isolation. Compare failure logs and screenshots across runs. Do not simply add large Thread.sleep values.

**Investigation Strategy:**

1. **Timing & Synchronization Issues**
   - Review wait strategies (implicit, explicit, fluent)
   - Check for race conditions
   - Look for `StaleElementReferenceException`
   - Verify timing is appropriate for network speed

2. **Parallel Execution Issues**
   - Test data conflicts in parallel runs
   - Shared resources (database, files)
   - Driver lifecycle problems
   - Thread-unsafe code

3. **Environment Stability**
   - Application instability
   - Browser/Grid health
   - Network issues
   - Resource constraints on agent

4. **Test Isolation**
   - State leaking between tests
   - Cleanup in `@AfterMethod`
   - Database rollback
   - Browser cache/cookies

**Investigation Checklist:**
```groovy
// Add detailed logging
sh '''
    mvn test -X \
    -Dorg.slf4j.simpleLogger.defaultLogLevel=DEBUG \
    -Dorg.apache.commons.logging.simplelog.defaultlog=debug
'''
```

**Do NOT:**
- Simply add `Thread.sleep()` to mask timing issues
- Increase waits without root cause analysis
- Retry failing tests without investigation

---

### 20. Design a Jenkins pipeline for a real QA/SDET project

**Key Concept:** A strong answer should include: checkout → build → unit/compile validation → smoke → regression/parallel execution → reports → screenshots/logs → artifact publishing → notifications. Add environment/browser/suite parameters and secure credentials. Mention retry/timeout only where justified.

**Complete Pipeline Example:**
```groovy
pipeline {
    agent {
        label 'selenium'  // Selenium-capable agent
    }
    
    parameters {
        choice(name: 'ENV', choices: ['DEV', 'QA', 'UAT'])
        choice(name: 'BROWSER', choices: ['chrome', 'firefox'])
        choice(name: 'SUITE', choices: ['smoke', 'regression', 'sanity'])
    }
    
    options {
        timeout(time: 2, unit: 'HOURS')
        timestamps()
    }
    
    environment {
        APP_CREDS = credentials('app-credentials')
        REPORT_DIR = 'target/reports'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
                sh 'git log -1 --oneline'
            }
        }
        
        stage('Build & Validate') {
            steps {
                sh 'mvn clean compile'
                sh 'mvn test-compile'
            }
        }
        
        stage('Smoke Tests') {
            steps {
                sh '''
                    mvn test \
                    -DsuiteXmlFile=smoke.xml \
                    -Denv=${params.ENV} \
                    -Dbrowser=${params.BROWSER}
                '''
            }
        }
        
        stage('Regression Tests') {
            when {
                expression { params.SUITE == 'regression' }
            }
            parallel {
                stage('Regression - Chrome') {
                    steps {
                        sh '''
                            mvn test \
                            -DsuiteXmlFile=regression.xml \
                            -Denv=${params.ENV} \
                            -Dbrowser=chrome
                        '''
                    }
                }
                stage('Regression - Firefox') {
                    steps {
                        sh '''
                            mvn test \
                            -DsuiteXmlFile=regression.xml \
                            -Denv=${params.ENV} \
                            -Dbrowser=firefox
                        '''
                    }
                }
            }
        }
    }
    
    post {
        always {
            // Publish reports
            junit 'target/surefire-reports/*.xml'
            
            // Archive artifacts
            archiveArtifacts artifacts: 'target/screenshots/**,target/logs/**', 
                           allowEmptyArchive: true
            
            // Publish HTML report
            publishHTML([
                reportDir: '${REPORT_DIR}',
                reportFiles: 'index.html',
                reportName: 'Test Report'
            ])
        }
        
        failure {
            // Send notification on failure
            emailext(
                subject: "Test Failure - ${params.ENV}",
                body: "Tests failed on ${params.BROWSER}. Check Jenkins logs.",
                to: "${TEST_TEAM_EMAIL}"
            )
        }
        
        success {
            // Optional: Trigger downstream job
            build job: 'Update-Test-Dashboard', wait: false
        }
    }
}
```

**Key Features:**
✅ Parameters for ENV, BROWSER, SUITE
✅ Secure credentials handling
✅ Parallel execution
✅ Timeout settings
✅ Report generation & archiving
✅ Failure notifications
✅ Proper sequencing (checkout → build → smoke → regression)

---

## 📚 Quick Jenkins QA/SDET Cheat Sheet

| Topic | Remember |
|-------|----------|
| **Pipeline** | Jenkinsfile stored in Git; Pipeline as Code |
| **Agent** | Where pipeline steps execute; use labels for specific environments |
| **Parameters** | ENV / BROWSER / SUITE / execution mode |
| **Maven** | `mvn clean test`, system properties with `-D` |
| **TestNG** | Suite XML, groups, parallel execution |
| **Selenium** | Browser/driver compatibility + headless mode |
| **Grid** | Remote and parallel browser execution |
| **Reports** | JUnit XML / HTML / Extent / Allure |
| **Artifacts** | Screenshots, logs, reports |
| **Credentials** | Use Jenkins Credentials; never hardcode secrets |
| **Troubleshooting** | Compare local vs agent environment |

---

## 🎯 Best Answer Pattern for Scenario Questions

When answering troubleshooting or design questions:

### 1. Identify the Layer
- Code layer (Java/Selenium)
- Framework layer (TestNG/Maven)
- Jenkins layer (pipeline/job config)
- Agent layer (OS/environment)
- Browser/Grid layer (driver/capabilities)
- Network/Application layer (connectivity/URL)

### 2. Check Evidence
- Console log (full, not truncated)
- Stack trace (get root cause)
- Screenshots/videos
- Reports and artifacts
- Environment details

### 3. Compare Environments
**Local vs Jenkins Agent:**
- JDK version
- Maven/Gradle version
- Browser/driver versions
- OS and architecture
- Environment variables
- File paths and permissions
- Network/proxy settings

### 4. Reproduce
- Run same Git commit on agent
- Use exact same command/parameters
- Keep all settings identical
- Verify reproducibility before deep dive

### 5. Fix Root Cause
- Don't mask failures with retries
- Avoid unnecessary `Thread.sleep()`
- Fix the actual problem
- Verify fix works consistently

---

## 💡 Key Tips

1. **Use Real Examples:** Reference actual projects and frameworks
2. **Explain Trade-offs:** Understand and explain why certain approach is chosen
3. **Show Problem Solving:** Demonstrate systematic troubleshooting approach
4. **Mention Security:** Always mention credential handling and never hardcoding secrets
5. **Ask Clarifying Questions:** Shows deep understanding of concepts
6. **Be Ready to Code:** Prepare Jenkinsfile snippets and pipeline examples

---

## 🔗 Related Resources

- [Jenkins Official Documentation](https://www.jenkins.io/doc/)
- [Pipeline Syntax Reference](https://www.jenkins.io/doc/book/pipeline/syntax/)
- [TestNG Documentation](https://testng.org/)
- [Selenium Documentation](https://www.selenium.dev/)
- [Maven Documentation](https://maven.apache.org/)

---

**Last Updated:** 2026
**Version:** 1.0
