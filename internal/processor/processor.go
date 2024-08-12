package processor

import (
	"bucket-simple-server/api/v1alpha1"
	"bucket-simple-server/internal/globals"
	"fmt"
	"io"
	"net/http"
	"regexp"
	"strings"

	"cloud.google.com/go/storage"
	"google.golang.org/api/option"
)

type Processor struct {
	// compiledRouteList is a list of compiled regex expressions
	// that represent the allowed routes
	compiledRouteList []*regexp.Regexp

	// GCSClient is a Google Cloud Storage client
	GCSClient *storage.Client
}

// TODO
func NewProcessor() (*Processor, error) {

	newProcessor := Processor{}

	// Compile routes just once to increase performance
	ctrList, err := newProcessor.compileRouteExpressions(globals.Application.Config.Spec.Webserver.AllowedTargets)
	if err != nil {
		return &newProcessor, fmt.Errorf("error compiling allowed routes regex: %s", err)
	}

	newProcessor.compiledRouteList = ctrList

	//
	newProcessor.GCSClient, err = storage.NewClient(globals.Application.Context,
		option.WithCredentialsFile(globals.Application.Config.Spec.Source.GCS.Credentials.Path))
	if err != nil {
		return &newProcessor, fmt.Errorf("error creating GCS client: %s", err)
	}

	return &newProcessor, err
}

// compileRouteExpressions return a list of compiled regex expressions of routes passed in allowed targets.
func (p *Processor) compileRouteExpressions(targets []v1alpha1.AllowedTargetT) (compiledRouteList []*regexp.Regexp, err error) {

	for _, target := range targets {
		compiledRegex, err := regexp.Compile(target.Route)
		if err != nil {
			return compiledRouteList, fmt.Errorf("error compiling regex %s: %s", target.Route, err)
		}

		compiledRouteList = append(compiledRouteList, compiledRegex)
	}

	return compiledRouteList, nil
}

// TODO
func (p *Processor) HandleRequest(w http.ResponseWriter, r *http.Request) {

	// Check Authorization header presence
	authorizationHeader := r.Header.Get("Authorization")
	if authorizationHeader == "" {
		http.Error(w, "Unauthorized", http.StatusUnauthorized)
		return
	}

	// Check Authorization header format
	authorizationHeaderParts := strings.Split(authorizationHeader, " ")
	if len(authorizationHeaderParts) != 2 {
		http.Error(w, "Unauthorized", http.StatusUnauthorized)
		return
	}

	if authorizationHeaderParts[0] != "Bearer" {
		http.Error(w, "Unauthorized", http.StatusUnauthorized)
		return
	}

	// Check Authorization header token
	// ATM, only Bearer tokens are supported
	requestAuthorized := false
	for _, credential := range globals.Application.Config.Spec.Webserver.Credentials {

		if strings.ToLower(credential.Type) != "bearer" {
			continue
		}

		if credential.Token == authorizationHeaderParts[1] {
			requestAuthorized = true
		}
	}

	if !requestAuthorized {
		http.Error(w, "Unauthorized", http.StatusUnauthorized)
		return
	}

	// Check if the route is allowed
	urlPathAllowed := false
	for _, compiledRoute := range p.compiledRouteList {
		if compiledRoute.MatchString(r.URL.Path) {
			urlPathAllowed = true
		}
	}

	if !urlPathAllowed {
		http.Error(w, "Forbidden", http.StatusForbidden)
		return
	}

	// Get the GCS file reader
	objectPath := strings.TrimPrefix(r.URL.Path, "/")
	srcReader, err := p.GCSClient.Bucket(globals.Application.Config.Spec.Source.GCS.Bucket).Object(objectPath).NewReader(globals.Application.Context)
	if err != nil {
		globals.Application.Logger.Infof("Error reading file from GCS: ", err)
		http.Error(w, "Not Found", http.StatusNotFound)
		return
	}
	defer srcReader.Close()

	io.Copy(w, srcReader)
}
