# HTTP Status Codes — Quick Reference

Below are common HTTP response codes, a short meaning, and a one-line note about when they commonly appear.

- 100 Continue — Informational. Client should continue the request; seen with Expect: 100-continue.
- 101 Switching Protocols — Informational. Server switching protocols (e.g., to WebSocket).

- 200 OK — Success. Standard response for successful GET/POST when body is returned.
- 201 Created — Success. Resource created (POST); Location header usually set.
- 202 Accepted — Success (async). Request accepted for processing but not completed.
- 204 No Content — Success. Action succeeded but no body returned (DELETE, PUT where no body needed).
- 206 Partial Content — Success. Returned when serving range requests (download resuming).

- 300 Multiple Choices — Redirection. Ambiguous choices for the resource.
- 301 Moved Permanently — Redirection. Resource has new permanent URI; clients should update links.
- 302 Found — Redirection. Temporary redirect; common for short-lived redirects.
- 303 See Other — Redirection. Redirect after POST to a GET (PRG pattern).
- 304 Not Modified — Redirection. Caching: client cache is fresh, no body returned.
- 307 Temporary Redirect — Redirection. Temporary redirect that must preserve method.
- 308 Permanent Redirect — Redirection. Permanent redirect that must preserve method.

- 400 Bad Request — Client error. Malformed request, invalid JSON, or validation failure.
- 401 Unauthorized — Client error. Authentication required or session expired.
- 402 Payment Required — Client error (reserved). Rare; used for payment systems.
- 403 Forbidden — Client error. Authenticated but not permitted to access resource.
- 404 Not Found — Client error. Resource does not exist or wrong URL.
- 405 Method Not Allowed — Client error. HTTP method not supported on this endpoint.
- 406 Not Acceptable — Client error. Server cannot return a response in accepted content types.
- 407 Proxy Authentication Required — Client error. Proxy authentication needed.
- 408 Request Timeout — Client error. Server timed out waiting for the request.
- 409 Conflict — Client error. Conflict with current resource state (e.g., edit conflict).
- 410 Gone — Client error. Resource permanently removed and no longer available.
- 411 Length Required — Client error. Server requires Content-Length header.
- 412 Precondition Failed — Client error. One of the request preconditions failed (If-Match, etc.).
- 413 Payload Too Large — Client error. Request entity too large for server to process.
- 414 URI Too Long — Client error. Request-URI too long (e.g., excessive query string).
- 415 Unsupported Media Type — Client error. Payload media type not supported by server.
- 416 Range Not Satisfiable — Client error. Requested Range not satisfiable for resource.
- 417 Expectation Failed — Client error (obsolete). Server cannot meet Expect header.
- 418 I'm a teapot — Client error (April Fools). Rarely used; used as an easter egg.
- 422 Unprocessable Entity — Client error. Semantic validation failed (common in REST APIs).
- 429 Too Many Requests — Client error. Rate limiting; client should retry after delay.

- 500 Internal Server Error — Server error. Generic error when an unexpected condition occurred.
- 501 Not Implemented — Server error. Server does not support the request method.
- 502 Bad Gateway — Server error. Invalid response received from upstream server.
- 503 Service Unavailable — Server error. Server overloaded or down for maintenance; Retry-After may be set.
- 504 Gateway Timeout — Server error. Upstream server failed to respond in time.
- 505 HTTP Version Not Supported — Server error. Server does not support the HTTP protocol version used.

Notes:
- Many APIs prefer 4xx codes for client-side validation errors (400/422) and 5xx for server faults.
- Use 201 for resource creation with Location; 202 for long-running async operations.
- Include helpful JSON body with error.code, error.message, and optionally error.details for clients.
