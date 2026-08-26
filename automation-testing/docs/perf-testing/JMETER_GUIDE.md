# JMeter Performance Testing Guide

Apache JMeter is an open-source load testing tool for analyzing and measuring application performance under various load conditions.

---

## What is JMeter?

JMeter is a Java-based tool that simulates multiple users making requests to a server and analyzes the performance.

**Key Features:**
- Load testing (HTTP, FTP, JDBC, SOAP, LDAP)
- Distributed testing
- Request correlation
- Dynamic token/header management
- Detailed reporting and graphs
- Scriptable test plans

**Use Cases:**
- API load testing
- Website performance testing
- Database performance testing
- FTP server testing
- Message queue testing

---

## JMeter Installation

### Prerequisites
- Java JDK 8 or higher installed
- Set JAVA_HOME environment variable

### Steps
1. Download from: https://jmeter.apache.org/download_jmeter.cgi
2. Extract to a folder (e.g., C:\tools\apache-jmeter)
3. Run: `jmeter.bat` (Windows) or `jmeter.sh` (Linux/Mac)

### Verify Installation
```bash
jmeter -version
```

---

## JMeter Components

### 1. Test Plan
- Root element containing all test configuration
- One test plan per JMeter project

### 2. Thread Group
- Defines number of users (threads)
- Ramp-up time (how long to start all users)
- Loop count (iterations per user)

**Example:**
```
Threads (users): 100
Ramp-up (seconds): 60
Loop count: 10
```
→ 100 users start gradually over 60 seconds, each making 10 requests

### 3. Sampler
- Sends actual request to server
- Types: HTTP, FTP, JDBC, SOAP, LDAP

### 4. Listener
- Collects and displays results
- Types: View Results Tree, Graph Results, Summary Report

### 5. Config Elements
- Set up defaults and variables
- HTTP Request Defaults
- User Defined Variables
- **Header Manager** (for adding headers and tokens)

### 6. Pre/Post Processors
- Execute before/after request
- Regular Expression Extractor (extract dynamic values)
- BeanShell Processor (custom logic)

### 7. Timer
- Add delay between requests
- Constant Timer, Gaussian Timer, Poisson Timer

---

## How to Create a Basic Test Plan

### Step 1: Add Thread Group
1. Right-click Test Plan → Add → Threads (Users) → Thread Group
2. Set:
   - Number of Threads: 100
   - Ramp-up: 60
   - Loop Count: 10

### Step 2: Add HTTP Sampler
1. Right-click Thread Group → Add → Sampler → HTTP Request
2. Configure:
   - Protocol: https
   - Server Name: api.example.com
   - Path: /api/users
   - Method: GET

### Step 3: Add Header Manager
1. Right-click HTTP Sampler → Add → Config Element → HTTP Header Manager
2. Add headers (e.g., Authorization token)

### Step 4: Add Listener
1. Right-click Thread Group → Add → Listener → Summary Report
2. Run test and view results

---

## Header Manager - Token Management

The Header Manager allows adding HTTP headers to requests, including authentication tokens.

### Basic Setup

1. Right-click HTTP Sampler → Add → Config Element → HTTP Header Manager
2. Add headers:
   ```
   Name: Authorization
   Value: Bearer <token_here>
   ```

### Problem: Token Expiration (After 5 minutes)

**Scenario:**
- Login endpoint returns token with 5-minute expiration
- Token needs to be used in subsequent API calls
- After 5 minutes, token expires and requests fail
- Solution: Extract token dynamically and refresh before expiration

### Solution: Dynamic Token Management

#### Step 1: Create Login Request
1. Add HTTP Sampler for login endpoint
   - URL: `https://api.example.com/api/auth/login`
   - Method: POST
   - Body: `{"username":"user","password":"pass"}`

#### Step 2: Extract Token from Login Response
1. Right-click Login HTTP Sampler → Add → Post Processor → Regular Expression Extractor
2. Configure:
   ```
   Name of variable: TOKEN
   Regular expression: "accessToken":"([^"]+)"
   Template: $1$
   Match number: 1
   ```

**Example Response:**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "expiresIn": 300
}
```
→ Extracted token stored in `${TOKEN}` variable

#### Step 3: Use Token in Subsequent Requests
1. Add HTTP Sampler for actual API call (e.g., GET /api/users)
2. Right-click → Add → Config Element → HTTP Header Manager
3. Add header:
   ```
   Name: Authorization
   Value: Bearer ${TOKEN}
   ```

#### Step 4: Refresh Token Before Expiration

**Option A: Login for Each User Session**
1. Add Timer between Login and API calls
   - Type: Constant Timer
   - Delay: 240 seconds (4 minutes, before 5-min expiration)

2. After 4 minutes, next login request gets new token automatically

**Option B: Refresh Token Endpoint (Recommended)**
1. Create separate flow for token refresh
2. Add Sampler for refresh endpoint
   - URL: `https://api.example.com/api/auth/refresh`
   - Method: POST
   - Body: `{"refreshToken":"${REFRESH_TOKEN}"}`

3. Extract new token:
   ```
   Name of variable: TOKEN
   Regular expression: "accessToken":"([^"]+)"
   ```

4. Use in next requests with new token

### Real Project Example: E-commerce API with Token Expiration

**Test Scenario:**
```
1. Login (Get token, expires in 5 min)
2. Browse products (10 requests, 30 sec each)
3. Refresh token (before expiration)
4. Add to cart (5 requests, 20 sec each)
5. Checkout (1 request)
```

**Configuration:**
```
Thread Group:
- Threads: 500
- Ramp-up: 120 sec
- Loop: 3

Requests:
1. POST /auth/login
   - Extract: ${TOKEN}
   
2. GET /api/products (10 times)
   - Header: Authorization: Bearer ${TOKEN}
   
3. POST /auth/refresh (Before token expires)
   - Extract new: ${TOKEN}
   
4. POST /api/cart (5 times)
   - Header: Authorization: Bearer ${TOKEN}
   
5. POST /api/checkout
   - Header: Authorization: Bearer ${TOKEN}
```

**Timer Configuration:**
- After login: Wait 240 seconds (Constant Timer)
- Then refresh token
- Continue with remaining requests

---

## Regular Expression Extractor - Extract Dynamic Values

Extracts values from response to use in subsequent requests.

### Example 1: Extract Token
```
Regular expression: "accessToken":"([^"]+)"
Template: $1$
Match number: 1
Variable name: TOKEN
```

Response: `{"accessToken":"abc123xyz"}`
→ `${TOKEN}` = `abc123xyz`

### Example 2: Extract User ID
```
Regular expression: "userId":(\d+)
Template: $1$
Variable name: USER_ID
```

Response: `{"userId":12345,"name":"John"}`
→ `${USER_ID}` = `12345`

### Example 3: Extract Session ID from Header
```
Reference Name: SESSION_ID
Regular expression: SESSIONID=([^;]+)
Template: $1$
Field to check: HTTP Header Manager
```

---

## JMeter Test Plan Structure

```
Test Plan
├── Thread Group (100 users, 60 sec ramp-up)
│   ├── HTTP Header Manager (Global headers)
│   ├── User Defined Variables
│   ├── Login Request
│   │   └── Regular Expression Extractor (Extract TOKEN)
│   ├── Constant Timer (240 sec)
│   ├── GET Product List
│   │   └── HTTP Header Manager (Authorization: ${TOKEN})
│   ├── POST Refresh Token
│   │   └── Regular Expression Extractor (Extract new TOKEN)
│   ├── Gaussian Timer (30 sec)
│   ├── POST Add to Cart (5 times)
│   │   └── HTTP Header Manager (Authorization: ${TOKEN})
│   └── View Results Tree (Listener)
└── Summary Report (Listener)
```

---

## Running JMeter Tests

### GUI Mode (Development)
```bash
jmeter -t test_plan.jmx
```

### Non-GUI Mode (Production)
```bash
jmeter -n -t test_plan.jmx -l results.jtl -j jmeter.log
```

**Parameters:**
- `-n`: Non-GUI mode
- `-t`: Test plan file
- `-l`: Results file (JTL format)
- `-j`: Log file
- `-Jusers=500`: Override thread count
- `-Jhosts=api.example.com`: Override hostname

### Generate HTML Report
```bash
jmeter -g results.jtl -o html_report/
```

---

## Best Practices

✓ Use Thread Groups for realistic load simulation
✓ Use Regular Expression Extractor for dynamic token extraction
✓ Add think time (timers) between requests (realistic user behavior)
✓ Monitor server resources during tests
✓ Use assertions to validate response
✓ Extract tokens/IDs for correlation
✓ Test with ramp-up to simulate gradual load
✓ Run tests in non-GUI mode for production
✓ Store sensitive data (passwords, tokens) in external files
✓ Use distributed testing for large scale tests

---

## Common Issues & Solutions

| Issue | Cause | Solution |
|-------|-------|----------|
| Token expires mid-test | Token valid for short duration | Refresh token before expiration or re-login |
| 401 Unauthorized | Missing/expired token | Check token extraction, verify regex |
| Header not applied | Wrong scope | Add Header Manager at Sampler level |
| Variable not extracted | Wrong regex pattern | Test regex pattern separately |
| High latency | Insufficient think time | Add Timers between requests |
| Out of memory | Too many threads | Reduce threads or use distributed testing |

---

## Performance Test with JMeter Checklist

- [ ] Define performance requirements (response time, throughput)
- [ ] Create test plan with realistic scenarios
- [ ] Extract and manage dynamic tokens
- [ ] Add timers for realistic user behavior
- [ ] Configure assertions for response validation
- [ ] Run baseline test with few users
- [ ] Gradually increase load to find bottleneck
- [ ] Monitor server metrics (CPU, Memory, DB)
- [ ] Analyze results and identify bottlenecks
- [ ] Document findings and recommendations
- [ ] Re-test after optimization
