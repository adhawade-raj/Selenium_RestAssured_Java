Postman API Testing — Quick Guide

[Reference: status.md](status.md)

This guide summarizes the Postman collections in https://github.com/adhawade-raj/Postman_API-Testing and provides concrete examples for API testing with Postman: environment variables, pre-request scripts, test assertions, setting variables, and validating status codes and JSON bodies.

Collections analyzed
- 2024_V2, 2024_V1, 2022, Performance testing (various endpoints and examples)

Common URLs used (examples from collections)
- https://gorest.co.in/public/v2/users
- https://restful-booker.herokuapp.com/auth
- https://httpbin.org/post (and /get)
- https://postman-echo.com/get
- https://reqres.in/api/users
- http://dummy.restapiexample.com/api/v1/
- https://fakestoreapi.com/
- https://ergast.com/api/f1/:year/circuits.json
- https://rahulshettyacademy.com/maps/api/place/*
- https://api.weatherapi.com/v1/*


High-level Postman concepts
- Variable scopes: global, collection, environment, local. Use {{varName}} in URLs/headers/bodies.
- Pre-request script: runs before the request; use to compute values or fetch tokens.
- Tests script: run after the response; use pm.test, pm.expect, and pm.response to assert.
- Chaining requests: extract values in tests and set environment/global variables to be used by later requests.

Variable precedence (highest → lowest)
- Local (pm.variables.set / request-level)
- Data (runner / CSV row values)
- Environment (pm.environment)
- Collection (collection-level variables)
- Global (pm.globals)

**How to set variables from response (examples)**

Example environment/collection variables used in these collections:
- baseURL (QA_Raj environment) — e.g., https://gorest.co.in
- tokenID (QA_Raj environment) — API bearer token
- Many requests use {{baseURL}} and {{tokenID}} or {{tokenId}} and other temp variables

Variable precedence (highest → lowest):
- Local (pm.variables.set / request-level)
- Data (runner / CSV row values)
- Environment (pm.environment)
- Collection (collection-level variables)
- Global (pm.globals)

Common examples

Set environment variable from JSON response:

```
const json = pm.response.json();
pm.environment.set('tokenID', json.token);
```

Set global variable:

```
pm.globals.set('username', json.data[0].first_name);
```

Set collection/temporary variable (collection runner):

```
pm.variables.set('abc', 'xyz');
```

Chaining and using variables across requests

- Extract id and set environment variable:

```
const resJson = pm.response.json();
pm.environment.set('createdUserId', resJson.id);
```

- Use in later requests: `{{createdUserId}}` in path or query

- Example: set in one request and use in another

Request A - test script
```
const data = pm.response.json();
pm.environment.set('userId', data.id);
```

Request B - use variable in URL
```
GET {{baseURL}}/users/{{userId}}
Authorization: ******
```

Read variable in Request B script

```
const uid = pm.environment.get('userId');
pm.test('userId exists', () => {
  pm.expect(uid).to.not.be.undefined;
});
```

Notes
- Prefer `pm.environment` for test-run-specific state, `pm.globals` for truly global values, and `pm.variables` for request-scoped temporary values.
- Keep secrets out of committed environments; use CI secrets or runtime injection where possible.

Pre-request snippets
- Simple header injection using env var (no script needed): add header Authorization: {{tokenID}}

- Obtain token in pre-request (pm.sendRequest) and set env variable:

```
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

Test assertions — status and JSON body
- Validate HTTP status code (simple):

```
pm.test("Status code is 200", function () {
  pm.response.to.have.status(200);
});
```

- Validate response time (used in Performance collection):

```
pm.test("Response time is less than 1000ms", function () {
  pm.expect(pm.response.responseTime).to.be.below(1000);
});
```

- Validate JSON body fields:

```
const jsonData = pm.response.json();
pm.test("user name is Julian", function () {
  pm.expect(jsonData.name).to.eql('Julian');
});

pm.test("gender is male", function () {
  pm.expect(jsonData.gender).to.eql('male');
});
```

- Validate nested array/object fields (reqres example):

```
const json = pm.response.json();
pm.test('first name is Janet', () => {
  pm.expect(json.data.first_name).to.eql('Janet');
});
```

- Body contains substring (text-based check):

```
pm.test('body contains Julian', () => {
  pm.expect(pm.response.text()).to.include('Julian');
});
```

Setting variables inside tests (chaining)
- Extract id and set environment variable:

```
const resJson = pm.response.json();
pm.environment.set('createdUserId', resJson.id);
```

- Use in later requests: {{createdUserId}} in path or query

- Example: set in one request and use in another (bullet points + dark code blocks):
  - Set variable in Request A (test script):

  ```javascript
  // Request A - test script
  const data = pm.response.json();
  // store user id in environment for next requests
  pm.environment.set('userId', data.id);
  ```

  - Use in Request B (URL/path/header):

  ```http
  GET {{baseURL}}/users/{{userId}}
  Authorization: Bearer {{tokenID}}
  ```

  - Read variable in Request B script:

  ```javascript
  // Request B - test script
  const uid = pm.environment.get('userId');
  pm.test('userId exists', () => {
    pm.expect(uid).to.not.be.undefined;
  });
  ```

Console / debug options
- Use pm.console.log / pm.console.info / pm.console.warn / pm.console.error for debugging inside scripts.

```
pm.console.log('response body:', pm.response.text());
pm.console.warn('token missing');
```

Best-practices & tips
- Use environment variables for baseURL and tokens; keep secrets out of repo.
- Use descriptive test names for easy debugging when running Newman.
- Prefer pm.response.json() for JSON payloads, and pm.response.text() when response may not be JSON.
- Use Retry-After and status checks (429/503) logic in tests if needed for robust suites.

How to run
1. Import collection JSON into Postman (Collections > Import)
2. Import QA_Raj.postman_environment.json or create environment with baseURL and tokenID
3. Update secret tokens in the environment (do not commit secrets)
4. Run collection via Collection Runner or Newman

Newman (example):
newman run 2024_V2.postman_collection.json -e QA_Raj.postman_environment.json --reporters cli,json
