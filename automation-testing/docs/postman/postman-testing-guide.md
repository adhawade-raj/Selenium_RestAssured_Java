Postman API Testing — Quick Guide

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

Environment / collection variables found
- baseURL (QA_Raj environment) — e.g., https://gorest.co.in
- tokenID (QA_Raj environment) — API bearer token
- Many requests use {{baseURL}} and {{tokenID}} or {{tokenId}} and other temp variables

High-level Postman concepts
- Variable scopes: global, collection, environment, local. Use {{varName}} in URLs/headers/bodies.
- Pre-request script: runs before the request; use to compute values or fetch tokens.
- Tests script: run after the response; use pm.test, pm.expect, and pm.response to assert.
- Chaining requests: extract values in tests and set environment/global variables to be used by later requests.

How to set variables from response (examples)
- Set environment variable from JSON response:

```
const json = pm.response.json();
pm.environment.set('tokenID', json.token);
```

- Set global variable:

```
pm.globals.set('username', json.data[0].first_name);
```

- Set collection/temporary variable (collection runner):

```
pm.variables.set('abc', 'xyz');
```

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

If you want, can add a short README or copy this guide into that Postman repo as README_POSTMAN.md.
