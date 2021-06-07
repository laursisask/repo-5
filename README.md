# Bridgekeeper - What is your Quest?

## Request Throttling Wrapper For http.Client or any implementation of bridgekeeper.Client

[![Build & Test](https://github.com/devnw/bridgekeeper/actions/workflows/build.yml/badge.svg)](https://github.com/devnw/bridgekeeper/actions/workflows/build.yml)
[![Go Report Card](https://goreportcard.com/badge/github.com/devnw/bridgekeeper)](https://goreportcard.com/report/github.com/devnw/bridgekeeper)
[![GoDoc](https://godoc.org/devnw.com/bridgekeeper?status.svg)](https://pkg.go.dev/devnw.com/bridgekeeper)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)

Bridgekeeper implements the interface shown below

```go
type Client interface {
    Do(request *http.Request) (*http.Response, error)
}
```

This interface is also implemented by `http.Client`.

`bridgekeeper` replaces the hard implementation of `http.Client` with an
implementation of a shared interface such that anything accepting the
above interface can use `bridgekeeper` to throttle their API requests through
the configuration of bridgekeeper.

An example of this is handling API rate limiting from an API you do not
control. bridgekeeper can be configured through the `bridgekeeper.New` method.
