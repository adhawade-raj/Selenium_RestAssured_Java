# WireMock — QA/SDET Guide

## What is WireMock?

WireMock is an HTTP API mocking/stubbing tool. In API automation, it allows us to simulate a downstream API and return a controlled response instead of depending on the real API.

**Simple flow:**
```
Our Application → WireMock → Predefined Response
```

**Instead of:**
```
Our Application → Real Downstream API → Response
```

WireMock is useful when the real API is:
- Unavailable
- Unpredictable
- Difficult to reproduce
- Still under development
- Slow
- Cannot easily produce required negative/error scenarios

---

## Important Clarification — Does WireMock Automatically Intercept the Real API?

**No.** WireMock does not automatically intercept every real API request. We configure the application/test environment so that the target API URL points to the WireMock server.

**Example:**
- Real API: `https://service.company.com/customer/123`
- WireMock: `http://localhost:8080/customer/123`

The application then sends the request to WireMock, and WireMock returns the response configured by the stub.

---

## Why Use WireMock?

The main reason is **controlled and reproducible API behavior**.

For example, a real API may normally return 200, but for automation we may need to reproduce:
- 200 / valid response
- 400 / bad request
- 404 / not found
- 500 / internal server error
- Timeout / delay
- Invalid or empty response

It can be difficult or unsafe to make the real API return these conditions whenever a test needs them. WireMock lets us define the request and response ourselves.

---

## What is a Stub?

A stub tells WireMock: **when a particular HTTP request is received, return a particular response.**

**Example:**
```
GET /customer/123 → HTTP 200 → predefined JSON response
```

A stub can match:
- HTTP method
- URL/path
- Query parameters
- Headers
- Request body

A stub can define:
- Status code
- Response headers
- Response body
- Delays

---

## Temporary Stub — Short-Term Testing

### Workflow

1. **Check existing stubs** - Use the generic/existing stub mechanism and first check whether the required stub already exists
2. **Check master branch** - Check the stubs already available under the master branch
3. **Create if needed** - If the required stub is not available, create the request/response needed for the test
4. **Use for testing session** - Use the temporary stub for the required testing session (typically short-term: ~30 minutes)
5. **Avoid duplicates** - Avoid creating duplicate/conflicting stubs because a duplicate mapping can affect other stubs or tests
6. **Clean up** - After the testing need is complete, remove/clean up the temporary stub according to the team's process

---

## Important Rule — Avoid Duplicate Stubs

**The handwritten notes specifically mention avoiding the same stub with multiple session IDs because it may impact other stubs.**

**Technical Concept: Stub Isolation**

If multiple mappings match the same HTTP method and request conditions, WireMock may select an unexpected mapping depending on matching priority/configuration. 

**Always check existing stubs before creating another mapping.**

---

## Permanent Stub — Long-Term Shared Stub

### Workflow

1. Create the stub in your local Git branch
2. Build the project before pushing the change
3. Push the branch to the remote stubbing repository/framework
4. Raise a Pull Request
5. Get the PR reviewed and merge it into master
6. Deploy master using Jenkins
7. The stub is then available as part of the shared/permanent stubbing setup

---

## Build Before Push

### mvn clean install

The handwritten notes specifically mention using:
```bash
mvn clean install
```

**Purpose:** Verify that the project builds successfully and that the new stub/configuration does not introduce build or test issues before raising the PR.

---

## Git + PR + Jenkins Flow

```
Local branch → mvn clean install → commit → push → PR → review → merge to master → Jenkins deployment
```

---

## Temporary vs Permanent Stub

| Aspect | Temporary Stub | Permanent Stub |
|--------|---|---|
| Duration | Short-term testing | Long-term/shared use |
| Creation | Created for a specific scenario | Maintained in the repository |
| Speed | Fast to create | Requires Git/PR/review process |
| Scope | Usually session/local oriented | Available to the team/environment |
| Cleanup | Cleaned up after use | Deployed through Jenkins |
| Use Case | Useful for debugging or quick validation | Useful for repeated automation |

---

## Complete WireMock Workflow

### Why Use WireMock?
```
Real API (unpredictable/unavailable/difficult to reproduce) 
  → WireMock 
  → Controlled response 
  → Reliable automation
```

### Temporary Stub Process
```
Check existing stubs 
  → create temporary stub 
  → run test 
  → validate scenario 
  → clean up
```

### Permanent Stub Process
```
Create locally 
  → mvn clean install 
  → commit 
  → push 
  → PR 
  → review 
  → merge master 
  → Jenkins deployment 
  → shared stub
```

---

## Common Scenarios

### Testing Error Responses
Create a stub for the required endpoint and configure its response status as 400, 404, 500, etc., with the desired body/headers.

### Simulating Slow APIs
Configure a response delay so the application can be tested for timeout and resilience behavior.

### Simulating Invalid Responses
Create a stub with invalid or empty response body to test error handling.

---

## Troubleshooting

### Stub Not Matching

Check the following:
- HTTP method (GET, POST, PUT, DELETE, etc.)
- Exact path and URL
- Query parameters
- Headers and body matchers
- WireMock logs/requests
- Whether the application is actually calling the WireMock URL

### Application Works with Real API but Fails with WireMock

Verify:
- Stub request matching configuration
- Response structure
- Headers and content type
- Status code
- URL configuration
- Whether the application expects behavior not represented by the stub

---

## Team Best Practices

1. **Always check existing stubs first** before creating new ones
2. **Avoid duplicate mappings** that can conflict with other tests
3. **Build locally before pushing** using `mvn clean install`
4. **Use descriptive stub names** for clarity and reusability
5. **Document stub purpose** in PR description
6. **Clean up temporary stubs** promptly after use
7. **Follow the Git/PR/Jenkins workflow** for permanent stubs
8. **Test both positive and negative scenarios** using stubs

