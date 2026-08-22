# Postman API Testing — Quick Guide

[Reference: status.md](status.md)

Compact, revision-oriented notes for quick lookup — each title is bold and variable-related material is grouped in one box.

**Collections analyzed**
- 2024_V2, 2024_V1, 2022, Performance testing

**Common URLs (examples)**
- https://gorest.co.in/public/v2/users
- https://restful-booker.herokuapp.com/auth
- https://httpbin.org/post
- https://postman-echo.com/get
- https://reqres.in/api/users


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
