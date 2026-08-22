# WireMock — Stub Creation Steps

## 1. Temporary Way to Create a Stub

This is the temporary approach used for quick testing/trials. The important point is that the API/application must first be deployed or configured to use the company's WireMock URL. After that, the same WireMock URL is used to access the WireMock Admin API and create the temporary mapping.

### Step 1 — Deploy/Configure the API with the WireMock URL

1. Take the WireMock URL provided/used by the company environment
2. Configure the API/application under test so that the downstream service points to this WireMock URL instead of the real downstream API
3. Deploy/restart the API with this WireMock configuration as required by the company setup
4. Verify that the API is now sending the downstream request to WireMock

### Step 2 — Use the Same WireMock URL as the Base URL in Postman

**Important relationship:**
```
Company WireMock URL = <WIREMOCK_BASE_URL>
```

The WireMock Admin API endpoint is then built using the same base URL:
```
POST <WIREMOCK_BASE_URL>/__admin/mappings
```

**Example:**
If the company WireMock environment has a generic WireMock host, that host remains the base URL and `/__admin/mappings` is appended to create the mapping endpoint.

### Step 3 — Create the Temporary Stub from Postman

1. Open Postman
2. Select **POST**
3. Use `<WIREMOCK_BASE_URL>/__admin/mappings` as the request URL
4. Add the required authentication/headers if the company's WireMock setup requires them
5. Add the stub definition in the request body
6. Define the request that WireMock should match:
   - HTTP method
   - Path/URL
   - Any required headers/query/body matchers
7. Define the response:
   - Status code
   - Response headers
   - Response body
8. Send the POST request and verify that the mapping is created successfully

### Step 4 — Use the Temporary Stub

1. Trigger the API/application request that was configured to use WireMock
2. The API sends its downstream request to the WireMock URL
3. WireMock checks the request against the temporary mapping created through `/__admin/mappings`
4. If the request matches, WireMock returns the predefined response
5. Validate the application's behavior against that response
6. The temporary stub is valid for approximately **30 minutes**

### Temporary Stub Flow

```
Deploy/configure API with WireMock URL
  ↓
Use same WireMock URL in Postman
  ↓
POST <WIREMOCK_BASE_URL>/__admin/mappings
  ↓
Create temporary stub
  ↓
Trigger API
  ↓
WireMock returns predefined response
  ↓
Test validation
  ↓
Stub expires after ~30 minutes
```

---

## 2. Permanent Way to Create a Stub

This is the permanent approach. Use it when the stub needs to be maintained in the shared stubbing framework/repository and used repeatedly.

### Step 1 — Create the Stub in Your Local Branch

1. Create the required stub in your local feature branch
2. Define the request matching conditions and expected response
3. **Check existing stubs first** and avoid duplicate/conflicting mappings
4. Test the stub locally and verify that the expected request receives the expected response

### Step 2 — Build Before Push

Run the following command:
```bash
mvn clean install
```

**Important:** Make sure the build is successful before pushing the changes.

### Step 3 — Push and Raise PR

1. Commit the stub changes
2. Push the branch to the remote stubbing repository/framework
3. Raise a Pull Request
4. Get the changes reviewed and address review comments

### Step 4 — Merge and Deploy

1. Merge the approved PR into the master branch
2. Deploy the updated master version through Jenkins
3. Verify that the permanent stub is available in the required environment
4. Execute the consuming automation/API test and validate the expected response

### Permanent Stub Flow

```
Create stub locally
  ↓
Validate locally
  ↓
mvn clean install
  ↓
Commit changes
  ↓
Push to remote
  ↓
Raise PR
  ↓
Code review
  ↓
Merge to master
  ↓
Jenkins deployment
  ↓
Permanent stub available in environment
  ↓
Use in automation/API tests
```

---

## Key Differences: Temporary vs Permanent

| Aspect | Temporary | Permanent |
|--------|-----------|-----------|
| **Creation Method** | Postman + `/__admin/mappings` API | Git branch + local development |
| **Duration** | ~30 minutes | Indefinite (in repository) |
| **Availability** | Only on WireMock instance | Available after Jenkins deployment |
| **Scope** | Single testing session | Team/environment-wide |
| **Setup** | Quick (no build/PR) | Requires Git/PR/Jenkins workflow |
| **Maintenance** | Auto-cleanup | Manual cleanup only via code removal |
| **Use Case** | Quick testing & debugging | Repeated, shared automation |

---

## Important Best Practices

### Before Creating Any Stub
- ✅ Always check existing stubs in the master branch first
- ✅ Avoid creating duplicate/conflicting mappings
- ✅ Review stub naming conventions used by the team

### For Temporary Stubs
- ✅ Document the stub purpose and expiration time
- ✅ Remember the 30-minute validity window
- ✅ Clean up promptly after testing

### For Permanent Stubs
- ✅ Test locally before pushing
- ✅ Always run `mvn clean install` before pushing
- ✅ Provide clear PR description
- ✅ Address all review comments
- ✅ Verify deployment through Jenkins

---

## Common Stub Definition Example

### Request Matching
```json
{
  "request": {
    "method": "GET",
    "url": "/api/customer/123",
    "headers": {
      "Content-Type": "application/json"
    }
  }
}
```

### Response Definition
```json
{
  "response": {
    "status": 200,
    "headers": {
      "Content-Type": "application/json"
    },
    "body": {
      "id": 123,
      "name": "John Doe",
      "email": "john@example.com"
    }
  }
}
```

### Complete Mapping (for Postman POST to `/__admin/mappings`)
```json
{
  "request": {
    "method": "GET",
    "url": "/api/customer/123",
    "headers": {
      "Content-Type": "application/json"
    }
  },
  "response": {
    "status": 200,
    "headers": {
      "Content-Type": "application/json"
    },
    "body": {
      "id": 123,
      "name": "John Doe",
      "email": "john@example.com"
    }
  }
}
```

