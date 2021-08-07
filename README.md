# Bridgekeeper - What is your (Re)Quest?

[![Build & Test](https://github.com/devnw/bridgekeeper/actions/workflows/build.yml/badge.svg)](https://github.com/devnw/bridgekeeper/actions/workflows/build.yml)
[![Go Report Card](https://goreportcard.com/badge/go.devnw.com/bridgekeeper)](https://goreportcard.com/report/go.devnw.com/bridgekeeper)
[![codecov](https://codecov.io/gh/devnw/bridgekeeper/branch/main/graph/badge.svg)](https://codecov.io/gh/devnw/bridgekeeper)
[![GoDoc](https://godoc.org/go.devnw.com/bridgekeeper?status.svg)](https://pkg.go.dev/go.devnw.com/bridgekeeper)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)

## Bridgekeeper is a request throttler for http.Client

Bridgekeeper replaces the hard implementation of `http.Client` with an
implementation of a shared interface such that anything implementing the
`bridgekeeper.Client` interface can use Bridgekeeper to throttle API requests through configuration.

### Using Bridgekeeper

```go
go get -u go.devnw.com/bridgekeeper@latest
```

### Example

```go
    client := New(
        ctx, // Your application context
        http.DefaultClient, // Your HTTP Client
        time.Millisecond, // Delay between requests
        5, // Retry count
        10, // Concurrent request limit
        http.DefaultClient.Timeout, // Request timeout
    )

    resp, err := client.Do(http.NewRequest(http.MethodGet, "localhost:5555"))
```

## Client Interface

Bridgekeeper implements the interface shown below

```go
type Client interface {
    Do(request *http.Request) (*http.Response, error)
}
```

This interface is also implemented by `http.Client`.
