/*
Copyright 2024.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package v1alpha1

// MetadataT TODO
type MetadataT struct {
	Name string `yaml:"name"`
}

// GoogleContentStorageCredentialsT TODO
type GoogleContentStorageCredentialsT struct {
	Path string `yaml:"path"`
}

// ConditionT TODO
type GoogleContentStorageT struct {
	Bucket      string                           `yaml:"bucket"`
	Credentials GoogleContentStorageCredentialsT `yaml:"credentials"`
}

// SourceT TODO
type SourceT struct {
	GCS GoogleContentStorageT `yaml:"gcs"`
}

// ListenerT TODO
type ListenerT struct {
	Port int    `yaml:"port"`
	Host string `yaml:"host"`
}

// CredentialT TODO
type CredentialT struct {
	Type  string `yaml:"type"`
	Token string `yaml:"token"`
}

// AllowedTargetT TODO
type AllowedTargetT struct {
	Route string `yaml:"route"`
}

// WebserverT TODO
type WebserverT struct {
	Listener       ListenerT        `yaml:"listener"`
	Credentials    []CredentialT    `yaml:"credentials"`
	AllowedTargets []AllowedTargetT `yaml:"allowedTargets"`
}

// SpecificationT TODO
type SpecificationT struct {
	Source    SourceT    `yaml:"source"`
	Webserver WebserverT `yaml:"webServer"`
}

// ConfigT TODO
type ConfigT struct {
	ApiVersion string         `yaml:"apiVersion"`
	Kind       string         `yaml:"kind"`
	Metadata   MetadataT      `yaml:"metadata"`
	Spec       SpecificationT `yaml:"spec"`
}
