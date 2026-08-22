# WireMock — Stub Creation Steps

Two practical approaches used for testing

## 1. Temporary Stub Creation — Postman / No Stub Deployment

Important first step: Before creating the temporary stub, the API/application under test must be configured to use the company's WireMock URL instead of the actual downstream service URL. This configuration is maintained in the application's environment configuration, such as application.env.yaml.

### Step 1 — Change the downstream URL in application.env.yaml

In the environment YAML, change the downstream service path/base URL from the real service to the WireMock URL used by the company environment.

# application.env.yaml
```
downstream-service:
  url: https://<REAL-DOWNSTREAM-SERVICE>
# For WireMock testing, point it to:
downstream-service:
  url: https://<COMPANY-WIREMOCK-URL>
```

Key point: We are not changing the API contract/RAML just to create the stub. The application configuration is what directs the downstream call to WireMock. The exact property name can differ in the project; the important part is that the downstream URL/path is changed to the company's WireMock URL.

### Step 2 — Deploy/Run the API with the WireMock URL

1. Deploy/restart the API using the updated application.env.yaml configuration.
2. Verify that the API is now configured to call WireMock for the downstream dependency.
3. The application flow becomes:

Application API -> configured downstream URL -> Company WireMock

### Step 3 — Use the SAME WireMock URL in Postman

The same company WireMock URL used in application.env.yaml is also used as the base URL for creating the temporary mapping.

WireMock Base URL = <COMPANY-WIREMOCK-URL>

POST <COMPANY-WIREMOCK-URL>/__admin/mappings

Do not use the real downstream URL here. The WireMock URL is the common base. The /__admin/mappings path accesses WireMock's Admin API to create the mapping.

### Step 4 — Create the Temporary Stub from Postman

1. Open Postman.
2. Select POST.
3. Set the URL to <COMPANY-WIREMOCK-URL>/__admin/mappings.
4. Add authentication/headers if required by the company WireMock setup.
5. Add the mapping definition in the request body.
6. Define the request matcher: HTTP method, path and any required headers/query/body conditions.
7. Define the response: status code, response headers and response body.
8. Send the POST request and verify that the mapping is created.

Temporary Stub — Postman Body Example
```
{
  "request": {
    "method": "GET",
    "urlPath": "/<stub-path>"
  },
  "response": {
    "status": 200,
    "headers": {
      "Content-Type": "application/json"
    },
    "jsonBody": {
      "status": "SUCCESS"
    }
  }
}
```

### Step 5 — Trigger the API and Validate

1. Trigger the actual API/application request.
2. Because application.env.yaml now points the downstream service to WireMock, the downstream call goes to WireMock.
3. WireMock matches the request against the temporary mapping created through /__admin/mappings.
4. WireMock returns the predefined response.
5. Validate how the application behaves with that response.
6. The project notes indicate that this temporary stub is valid for approximately 30 minutes.

Temporary Flow to Memorize

```
application.env.yaml: Real downstream URL -> Company WireMock URL
Deploy/restart API with updated configuration
Postman: POST <COMPANY-WIREMOCK-URL>/__admin/mappings
Create temporary stub mapping
Trigger API
API calls WireMock -> WireMock returns predefined response
Validate -> Temporary stub expires after ~30 minutes
```

## 2. Permanent Stub Creation — Repository + Jenkins

Use this approach when the stub should become part of the shared/permanent stubbing framework and be available repeatedly.

### Step 1 — Create the Stub in the Local Branch

1. Create the required stub in the local feature branch.
2. Define the request matcher and expected response.
3. Check existing stubs before adding a new one.
4. Avoid duplicate/conflicting mappings.
5. Validate the stub locally.

### Step 2 — Build the Project

Before pushing the change, build the stubbing project:

```
mvn clean install
```

### Step 3 — Commit, Push and Raise PR

1. Commit the stub changes.
2. Push the feature branch to the remote repository.
3. Raise a Pull Request.
4. Get the changes reviewed and address review comments.

### Step 4 — Merge to Master

After approval, merge the Pull Request into the master branch.

### Step 5 — Deploy Through Jenkins

1. Jenkins deploys the updated master version of the stubbing project.
2. Verify that the permanent stub is available in the required environment.
3. The consuming API can then use the WireMock URL configured for that environment.
4. Execute the automation/API test and validate the expected response.

Permanent Flow to Memorize

```
Create stub locally -> Validate -> mvn clean install -> Commit -> Push -> PR -> Review -> Merge to master -> Jenkins deployment -> Use permanent stub
```

## Important Note on RAML

RAML/API specification describes the API contract — for example resources, methods, request/response structure and schemas. For this WireMock workflow, the key runtime change is the application.env.yaml downstream URL. We do not need to replace the API contract merely because the downstream call is being mocked. The application continues to make the same logical API call, but its configured downstream destination is changed to WireMock.

