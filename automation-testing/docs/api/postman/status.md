# HTTP Status Codes — Quick Reference

Below are common HTTP response codes organized into separate tables per series (1xx–5xx). Each table lists the status, a short meaning, and a one-line note about when it commonly appears.

## 1xx — Informational

| Status | Meaning | When it can appear |
|---:|---|---|
| 100 Continue | Informational | Client should continue the request; seen with `Expect: 100-continue` |
| 101 Switching Protocols | Informational | Server switches protocols (e.g., to WebSocket) |
| 102 Processing | Informational | WebDAV async processing acknowledgement |

## 2xx — Success

| Status | Meaning | When it can appear |
|---:|---|---|
| 200 OK | Success | Standard response for successful GET/POST returning a body |
| 201 Created | Success (created) | Resource created (POST); `Location` header usually provided |
| 202 Accepted | Success (async) | Request accepted for processing but not completed (async jobs) |
| 204 No Content | Success (no body) | Action succeeded but no body returned (DELETE, or PUT with no response) |
| 206 Partial Content | Success (partial) | Served when honoring `Range` requests (download resume) |

## 3xx — Redirection

| Status | Meaning | When it can appear |
|---:|---|---|
| 300 Multiple Choices | Redirection | Multiple possible representations/URIs for the resource |
| 301 Moved Permanently | Redirection | Resource permanently moved; clients should update stored URI |
| 302 Found | Redirection | Temporary redirect; often used for legacy redirects |
| 303 See Other | Redirection | Redirect after POST to a GET (PRG: Post/Redirect/Get) |
| 304 Not Modified | Redirection (caching) | Client cache is fresh; no body returned |
| 307 Temporary Redirect | Redirection | Temporary redirect that preserves HTTP method |
| 308 Permanent Redirect | Redirection | Permanent redirect that preserves HTTP method |

## 4xx — Client Error

| Status | Meaning | When it can appear |
|---:|---|---|
| 400 Bad Request | Client error | Malformed request, invalid JSON, or validation failure |
| 401 Unauthorized | Client error | Authentication required or credentials missing/expired |
| 402 Payment Required | Client error (reserved) | Rare; reserved for payment systems |
| 403 Forbidden | Client error | Authenticated but not authorized to access resource |
| 404 Not Found | Client error | Resource not found or wrong URL |
| 405 Method Not Allowed | Client error | HTTP method not supported on this endpoint |
| 406 Not Acceptable | Client error | Server cannot produce an acceptable representation |
| 407 Proxy Authentication Required | Client error | Proxy authentication is required |
| 408 Request Timeout | Client error | Server timed out waiting for the request |
| 409 Conflict | Client error | Conflict with current resource state (edit/version conflict) |
| 410 Gone | Client error | Resource permanently removed |
| 411 Length Required | Client error | `Content-Length` header required by server |
| 412 Precondition Failed | Client error | Request precondition failed (If-Match/If-Unmodified-Since) |
| 413 Payload Too Large | Client error | Request payload too large for the server to handle |
| 414 URI Too Long | Client error | Request URI too long (large query strings) |
| 415 Unsupported Media Type | Client error | Payload media type not supported by server |
| 416 Range Not Satisfiable | Client error | Requested `Range` cannot be satisfied |
| 417 Expectation Failed | Client error (obsolete) | Server cannot meet the `Expect` header |
| 418 I'm a teapot | Client error (joke) | April Fools' RFC — occasionally used as an easter egg |
| 422 Unprocessable Entity | Client error | Semantic validation failed (common in REST APIs) |
| 429 Too Many Requests | Client error | Rate limiting; client should retry after delay |

## 5xx — Server Error

| Status | Meaning | When it can appear |
|---:|---|---|
| 500 Internal Server Error | Server error | Generic unexpected server-side condition |
| 501 Not Implemented | Server error | Server does not support requested method |
| 502 Bad Gateway | Server error | Invalid response from upstream/service proxy |
| 503 Service Unavailable | Server error | Server overloaded or down for maintenance; `Retry-After` may be set |
| 504 Gateway Timeout | Server error | Upstream service timed out |
| 505 HTTP Version Not Supported | Server error | Server does not support HTTP protocol version used |

Notes:
- Prefer 4xx for client-side validation/usage errors and 5xx for server faults.
- Use `201` for resource creation (include `Location`); use `202` for async processing.
- Error responses should include structured JSON (e.g., `error.code`, `error.message`, `error.details`).
