# Postman API Testing — Quick Guide

[Reference: status.md](../status.md)

Compact, revision-oriented notes for quick lookup — each title is bold and variable-related material is grouped in one box.

**Collections analyzed**
- 2024_V2, 2024_V1, 2022, Performance testing

**Common URLs (examples)**
- https://gorest.co.in/public/v2/users
- https://restful-booker.herokuapp.com/auth
- https://httpbin.org/post
- https://postman-echo.com/get
- https://reqres.in/api/users


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
| Common flows | N/A (signature-based) | Authorization Code, Implicit, Client Credentials, Resource Owner Password Credentials, Refresh Token |
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
- Each part is separated by a boundary string defined in Content-Type header, e.g. Content-Type: multipart/form-data; boundary=----WebKitFormBoundaryxyz
- Each part has its own headers (Content-Disposition, Content-Type optional)
- Use for file uploads alongside form fields; in Postman choose "form-data" body type and set the field to "file" for file parts


**How to set variables from response (examples)**

> VARIABLES (box)
>
> - Example environment/collection variables used in these collections:
>   - baseURL (QA_Raj environment) — e.g., https://gorest.co.in
>   - tokenID (QA_Raj environment) — API bearer token
>   - Many requests use {{baseURL}} and {{tokenID}} or {{tokenId}} and other temp variables
>
> - Variable precedence (highest → lowest):
>   1. Local (pm.variables.set / request-level)
>   2. Data (runner / CSV row values)
>   3. Environment (pm.environment)
>   4. Collection (collection-level variables)
>   5. Global (pm.globals)
>
> - Common examples:
>   - Set environment variable from JSON response:
>
>     ```js
>     const json = pm.response.json();
>     pm.environment.set('tokenID', json.token);
>     ```
>
>   - Set global variable:
>
>     ```js
>     pm.globals.set('username', json.data[0].first_name);
>     ```
>
>   - Set collection/temporary variable (collection runner):
>
>     ```js
>     pm.variables.set('abc', 'xyz');
>     ```
>
> - Chaining and using variables across requests:
>
>   - Extract id and set environment variable:
>
>     ```js
>     const resJson = pm.response.json();
>     pm.environment.set('createdUserId', resJson.id);
>     ```
>
>   - Use in later requests: `{{createdUserId}}` in path or query
>
>   - Example flow:
>
>     Request A - test script:
>
>     ```js
>     const data = pm.response.json();
>     pm.environment.set('userId', data.id);
>     ```
>
>     Request B - use variable in URL:
>
>     ```http
>     GET {{baseURL}}/users/{{userId}}
>     Authorization: Bearer {{tokenID}}
>     ```
>
>     Request B - read variable in script:
>
>     ```js
>     const uid = pm.environment.get('userId');
>     pm.test('userId exists', () => {
>       pm.expect(uid).to.not.be.undefined;
>     });
>     ```
>
> - Notes:
>   - Prefer `pm.environment` for test-run-specific state, `pm.globals` for truly global values, and `pm.variables` for request-scoped temporary values.
>   - Keep secrets out of committed environments; use CI secrets or runtime injection where possible.


**Pre-request snippets**

**Simple header injection**
- Add header: `Authorization: {{tokenID}}` (no script needed)

**Obtain token in pre-request and set env variable**
```js
pm.sendRequest({
  url: pm.environment.get('baseURL') + '/auth',
  method: 'POST',
  header: { 'Content-Type': 'application/json' },
  body: { mode: 'raw', raw: JSON.stringify({username: 'admin', password: 'password123'}) }
}, function (err, res) {
  if (!err && res.code === 200) {
    const t = res.json().token || res.json().access_token;
    pm.environment.set('tokenID', t);
  }
});
```


**Postman assertions**

**Status code**
```js
pm.test("Status code is 200", function () {
  pm.response.to.have.status(200);
});
```

**Response time**
```js
pm.test("Response time is less than 1000ms", function () {
  pm.expect(pm.response.responseTime).to.be.below(1000);
});
```

**JSON body fields**
```js
const jsonData = pm.response.json();
pm.test("user name is Julian", function () {
  pm.expect(jsonData.name).to.eql('Julian');
});
```

**Nested fields**
```js
const json = pm.response.json();
pm.test('first name is Janet', () => {
  pm.expect(json.data.first_name).to.eql('Janet');
});
```

**Body contains (text)**
```js
pm.test('body contains Julian', () => {
  pm.expect(pm.response.text()).to.include('Julian');
});
```


**Console / debug options**
- Use `pm.console.log` / `pm.console.info` / `pm.console.warn` / `pm.console.error`
```js
pm.console.log('response body:', pm.response.text());
pm.console.warn('token missing');
```


**Best-practices & tips**
- Use environment variables for baseURL and tokens; keep secrets out of repo.
- Use descriptive test names for easy debugging when running Newman.
- Prefer `pm.response.json()` for JSON payloads, and `pm.response.text()` when response may not be JSON.
- Use Retry-After and status checks (429/503) logic in tests if needed for robust suites.


**How to run (Newman) & common commands**

| Command | Example | Purpose |
|---|---|---|
| Run collection (CLI) | newman run collection.json -e env.json --reporters cli,json | Execute collection locally and output CLI + JSON report |
| Export JSON report | newman run collection.json -e env.json --reporters json --reporter-json-export report.json | Save run results to report.json for CI/artifacts |
| Export HTML report | newman run collection.json -e env.json --reporters html --reporter-html-export report.html | Generate HTML report (requires html reporter plugin) |
| Use environment file | -e env.json | Inject environment variables at runtime |
| Run with iteration data | -d data.csv | Run collection per row in CSV (data-driven) |
| CI-friendly run | newman run collection.json -e env.json --reporters cli,junit --reporter-junit-export junit.xml | Produce JUnit XML for CI test reporting |
| Diff reports (quick) | git diff report-old.json report-new.json | Quick textual diff between two JSON reports (or use jq for structured compare) |


**Quick Newman example**
```sh
newman run 2024_V2.postman_collection.json -e QA_Raj.postman_environment.json --reporters cli,json --reporter-json-export results/2024_V2_run.json
```


**Notes**
- Keep secrets (tokens) out of repository; use CI secrets or environment variables in pipeline.
- Label requests and tests clearly for easier debugging in Newman reports.


**Further reading**
- Postman docs: https://learning.postman.com/
- Newman docs: https://www.npmjs.com/package/newman
